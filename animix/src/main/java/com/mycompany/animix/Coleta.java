package com.mycompany.animix;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Disco;
import com.github.britooo.looca.api.group.discos.DiscoGrupo;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.processador.Processador;
import com.github.britooo.looca.api.group.processos.Processo;
import com.github.britooo.looca.api.group.processos.ProcessoGrupo;
import com.github.britooo.looca.api.group.servicos.ServicoGrupo;
import com.github.britooo.looca.api.group.sistema.Sistema;
import com.github.britooo.looca.api.group.temperatura.Temperatura;
import com.github.britooo.looca.api.util.Conversor;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.Timer;
import java.util.TimerTask;

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
    
    public Dados coletar(Integer fkMaquina) {
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

            //Insrindo dados 
            database.update("insert into dados values (?, ?, ?, ?, ?, ?, ?, ?, ?)",
                    fkMaquina, usoCpu, usoMemoriaPorcentagem, temp, porcentDisco, qtdProcessos, qtdServicos, data, hora);
        }
        
        // Montando objeto de retorno com o dado
        List<Dados> dados = database.query("select * from dados where fkMaquina = ? ", new BeanPropertyRowMapper(Dados.class), fkMaquina);
        Dados lastDado = dados.get(dados.size() - 1);
        System.out.println(lastDado.toString());

        return lastDado;
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
