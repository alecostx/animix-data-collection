package com.mycompany.animix;

import com.github.britooo.looca.api.group.discos.Disco;
import com.github.britooo.looca.api.group.discos.DiscoGrupo;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.processador.Processador;
import com.github.britooo.looca.api.group.processos.ProcessoGrupo;
import com.github.britooo.looca.api.group.servicos.ServicoGrupo;
import com.github.britooo.looca.api.group.sistema.Sistema;
import com.github.britooo.looca.api.group.temperatura.Temperatura;
import com.github.britooo.looca.api.util.Conversor;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

    // Objetos diversos
    Sistema sistema = new Sistema();
    Memoria memoria = new Memoria();
    Processador processador = new Processador();
    Temperatura temperatura = new Temperatura();
    DiscoGrupo grupoDeDiscos = new DiscoGrupo();
    ServicoGrupo grupoDeServicos = new ServicoGrupo();
    ProcessoGrupo grupoDeProcessos = new ProcessoGrupo();
    Conversor conversor = new Conversor();

    public void coletar(Maquina maquina) {
        //Instanaciando uma nova data no momento que chama a função
        Date dataHoraAtual = new Date();

        // Coletando memória
        String memoriaNumbersOnly = conversor.formatarBytes(memoria.getEmUso()).replace(" GiB", "").replace(",", ".");
        Double usoMemoria = Double.parseDouble(memoriaNumbersOnly);

        String memoriaTotalNumersOnly = conversor.formatarBytes(memoria.getTotal()).replace(" GiB", "").replace(",", ".");
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
            
            //Coletando disco
            String discoTotalGb = conversor.formatarBytes(disco.getTamanho());
            String discoNumbersOnly = discoTotalGb.replace(" GiB", "").replace(",", ".");
            Double discoTotal = Double.parseDouble(discoNumbersOnly);

            String usoDiscoGb = conversor.formatarBytes(disco.getBytesDeLeitura()
                    + disco.getBytesDeEscritas());

            String usoNumbersOnly = usoDiscoGb.replace("GiB", "").replace(",", ".");
            Double usoDisco = Double.parseDouble(usoNumbersOnly);

            Double porcentDisco = getPorcentual(discoTotal, usoDisco);

            Dados dado = new Dados();
            dado.setUsoMemoria(usoMemoriaPorcentagem);
            dado.setPorcentDisco(porcentDisco);
            dado.setUsoCpu(usoCpu);
            dado.setTemperatura(temp);

            // Verificando criticidade do dado
            verifyData(dado, maquina);

            Boolean isCritico = dado.getIsCritico();
            List<String> comentarios = dado.getComment();

            //Inserindo dados 
            database.update("insert into dados values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                    maquina.getIdMaquina(), usoCpu, usoMemoriaPorcentagem, temp, porcentDisco, qtdProcessos, qtdServicos, data, hora, isCritico, comentarios.toString());

//            System.out.println(dado.toString());;
            System.out.println(porcentDisco);
        }
    }

    public void verifyData(Dados dado, Maquina maquina) {

        Double memoriaIdeal = maquina.getMemoriaIdeal();
        Double temperaturaIdeal = maquina.getTemperaturaIdeal();
        Double discoIdeal = maquina.getDiscoIdeal();
        Double processadorIdeal = maquina.getProcessamentoIdeal();
        List<String> comments = new ArrayList<>();

        if (dado.getPorcentDisco() > discoIdeal) {
            dado.setIsCritico(Boolean.TRUE);
            comments.add("Disco fora dos parametros ideais");
            dado.setComment(comments);
        }
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

        List<Dados> dados = database.query("select * from dados where fkMaquina = ? order by momento desc limit ? ",
                new BeanPropertyRowMapper(Dados.class), fkMaquina, qtdDados);
        System.out.println(dados.toString());
        return dados;
    }

    private static Double getPorcentual(Double espacoTotal, Double espacoEmUso) {
        return (espacoEmUso * 100) / espacoTotal;
    }
}
