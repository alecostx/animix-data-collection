package Coleta;

import Models.Maquina;
import Models.Dados;
import Integracoes.Slack;
import Database.Connection;
import Database.ConnectionMySqlLocal;
import com.github.britooo.looca.api.group.discos.Disco;
import com.github.britooo.looca.api.group.discos.DiscoGrupo;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.processador.Processador;
import com.github.britooo.looca.api.group.processos.ProcessoGrupo;
import com.github.britooo.looca.api.group.servicos.ServicoGrupo;
import com.github.britooo.looca.api.group.sistema.Sistema;
import com.github.britooo.looca.api.group.temperatura.Temperatura;
import com.github.britooo.looca.api.util.Conversor;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author Alexandre Costa
 */
public class Coleta {

    // Objetos conexão
    Connection connection = new Connection();
    JdbcTemplate database = connection.getConnection();

    ConnectionMySqlLocal connectionLocal = new ConnectionMySqlLocal();
    JdbcTemplate databaseLocal = connectionLocal.getConnectionMySql();

    // Objetos diversos
    Sistema sistema = new Sistema();
    Memoria memoria = new Memoria();
    Processador processador = new Processador();
    Temperatura temperatura = new Temperatura();
    DiscoGrupo grupoDeDiscos = new DiscoGrupo();
    ServicoGrupo grupoDeServicos = new ServicoGrupo();
    ProcessoGrupo grupoDeProcessos = new ProcessoGrupo();
    Conversor conversor = new Conversor();
    Slack slack = new Slack();

    public void coletar(Maquina maquina) {
        //Instanaciando uma nova data no momento que chama a função
        Date dataHoraAtual = new Date();

        // Coletando memória
        String memoriaNumbersOnly = conversor.formatarBytes(memoria.getEmUso()).replace(" GiB", "").replace(",", ".").replace(" TiB", "").replace(" MiB", "");
        Double usoMemoria = Double.parseDouble(memoriaNumbersOnly);

        String memoriaTotalNumersOnly = conversor.formatarBytes(memoria.getTotal()).replace(" GiB", "").replace(",", ".").replace(" TiB", "").replace(" MiB", "");
        Double totalMemoria = Double.parseDouble(memoriaTotalNumersOnly);

        Double usoMemoriaPorcentagem = getPorcentual(totalMemoria, usoMemoria);

        //Coletando temperatura
        Double temp = temperatura.getTemperatura();

        //Coletando uso da CPU
        Double usoCpu = processador.getUso();

        //Coletando discos
        List<Disco> discos = grupoDeDiscos.getDiscos();

        //Coletando quantidade de serviços
        Integer qtdServicos = grupoDeServicos.getTotalServicosAtivos();

        //Coletando quantidade de processos
        Integer qtdProcessos = grupoDeProcessos.getTotalProcessos();

        // Coletando o momento 
        String data = new SimpleDateFormat("dd/MM/yyyy ").format(dataHoraAtual);
        String hora = new SimpleDateFormat("HH:mm:ss").format(dataHoraAtual);

        for (Disco disco : discos) {

            try {
                String discoTotalGb = conversor.formatarBytes(disco.getTamanho());
                String discoNumbersOnly = discoTotalGb.replace(" GiB", "").replace(",", ".").replace(" TiB", "").replace(" MiB", "");
                Double discoTotal = Double.parseDouble(discoNumbersOnly);
                disco.getTempoDeTransferencia();

                // Coletando leitura do disco
                String discoLeitura = conversor.formatarBytes(disco.getBytesDeLeitura());
                String leituraNumbers = discoLeitura.replace(" GiB", "").replace(",", ".").replace(" TiB", "").replace(" MiB", "");
                Double leitura = Double.parseDouble(leituraNumbers);

                //Coletando escrita do disco
                String discoEscrita = conversor.formatarBytes(disco.getBytesDeEscritas());
                String escritaNumbers = discoEscrita.replace(" GiB", "").replace(",", ".").replace(" TiB", "").replace(" MiB", "");
                Double escrita = Double.parseDouble(escritaNumbers);

                Dados dado = new Dados();
                dado.setUsoMemoria(usoMemoriaPorcentagem);
                dado.setUsoCpu(usoCpu);
                dado.setTemperatura(temp);
                dado.setQtdProcessos(qtdProcessos);
                dado.setQtdServicos(qtdServicos);
                dado.setDataColeta(data);
                dado.setMomento(hora);
                dado.setFkMaquina(maquina.getIdMaquina());
                dado.setEscrita(escrita);
                dado.setLeitura(leitura);

                // Verificando criticidade do dado
                verifyData(dado, maquina);

                Boolean isCritico = dado.getIsCritico();
                String comentarios = dado.getComment().toString();

                if (dado.getComment().size() == 2) {
                    maquina.setSituacao(2);
                } else if (dado.getComment().size() == 3) {
                    maquina.setSituacao(1);
                } else {
                    maquina.setSituacao(3);
                }

                //Inserindo dados
                try {
                    database.update("insert into dados values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                            maquina.getIdMaquina(), usoCpu, usoMemoriaPorcentagem, temp, qtdProcessos, qtdServicos, data , isCritico, comentarios, leitura, escrita, discoTotal,hora);

                    databaseLocal.update("insert into dados values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                            null, maquina.getIdMaquina(), usoCpu, usoMemoriaPorcentagem, temp, qtdProcessos, qtdServicos, data, hora, isCritico, comentarios, leitura, escrita, discoTotal);

                } catch (Exception e) {
                    System.out.println("Erro ao inserir os dados");
                    System.out.println(e);
                }

                if (maquina.getSituacao() == 1) {
                    slack.verificarDados(dado);
                }

            } catch (IOException ex) {
                Logger.getLogger(Coleta.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(Coleta.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public void verifyData(Dados dado, Maquina maquina) {

        Double memoriaIdeal = maquina.getMemoriaIdeal();
        Double temperaturaIdeal = maquina.getTemperaturaIdeal();
        Double processadorIdeal = maquina.getProcessamentoIdeal();
        List<String> comments = new ArrayList<>();

        if (dado.getUsoMemoria() > memoriaIdeal) {
            dado.setIsCritico(Boolean.TRUE);
            comments.add("Memoria fora dos parametros ideais");
            dado.setComment(comments);
        }
        if (dado.getTemperatura() > temperaturaIdeal) {
            dado.setIsCritico(Boolean.TRUE);
            comments.add("Temperatura fora dos parametros ideais");
            dado.setComment(comments);
        }
        if (dado.getUsoCpu() > processadorIdeal) {
            dado.setIsCritico(Boolean.TRUE);
            comments.add("Processador fora dos parametros ideais");
            dado.setComment(comments);
        } else {
            dado.setIsCritico(false);
        }
    }

    public List<Dados> getLastData(Integer qtdDados, Integer fkMaquina) {
        // Montando objeto de retorno com os ultimos dados

        List<Dados> dados = database.query("select top (?) * from dados where fkMaquina = (?)",
                new BeanPropertyRowMapper(Dados.class), qtdDados, fkMaquina);

        return dados;
    }

    private static Double getPorcentual(Double espacoTotal, Double espacoEmUso) {
        return (espacoEmUso * 100) / espacoTotal;
    }
}
