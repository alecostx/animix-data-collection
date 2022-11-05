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
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author Alexandre Costa
 */
public class Coleta {

    public void coletar() {
        // Objetos Looca api

        Sistema sistema = new Sistema();
        Memoria memoria = new Memoria();
        Processador processador = new Processador();
        Temperatura temperatura = new Temperatura();
        DiscoGrupo grupoDeDiscos = new DiscoGrupo();
        ServicoGrupo grupoDeServicos = new ServicoGrupo();
        ProcessoGrupo grupoDeProcessos = new ProcessoGrupo();
        Conversor conversor = new Conversor();

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

        for (Disco disco : discos) {

            Connection connection = new Connection();
            JdbcTemplate database = connection.getConnection();

            String discoTotalGb = conversor.formatarBytes(disco.getTamanho());
            String discoNumbersOnly = discoTotalGb.replace(" GiB", "").replace(",", ".");
            Double discoTotal = Double.parseDouble(discoNumbersOnly);

            String usoDiscoGb = conversor.formatarBytes(disco.getBytesDeLeitura()
                    + disco.getBytesDeEscritas());
            String usoNumbersOnly = usoDiscoGb.replace("GiB", "").replace(",", ".");
            Double usoDisco = Double.parseDouble(usoNumbersOnly);

            Double porcentDisco = getPorcentual(discoTotal, usoDisco);
            System.out.println(usoMemoria);
             database.update("insert into dados values (?, ?, ?, ?)",
                   usoCpu, usoMemoriaPorcentagem, temp, porcentDisco);
        }
    }

    private static Double getPorcentual(Double espacoTotal, Double espacoEmUso) {
        return (espacoEmUso * 100) / espacoTotal;
    }
}
