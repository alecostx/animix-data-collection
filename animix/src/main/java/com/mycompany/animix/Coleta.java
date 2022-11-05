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

/**
 *
 * @author Alexandre Costa
 */
public class Coleta {

    public void coletar() {

        Sistema sistema = new Sistema();
        Memoria memoria = new Memoria();
        Processador processador = new Processador();
        Temperatura temperatura = new Temperatura();
        DiscoGrupo grupoDeDiscos = new DiscoGrupo();
        ServicoGrupo grupoDeServicos = new ServicoGrupo();
        ProcessoGrupo grupoDeProcessos = new ProcessoGrupo();

        Double usoMemoria = memoria.getEmUso().doubleValue();
        Double temp = temperatura.getTemperatura().doubleValue();
        
        Double usoCpu = processador.getUso();
        
        System.out.println(usoMemoria);
        System.out.println(temp);
        System.out.println(usoCpu);
    }
}
