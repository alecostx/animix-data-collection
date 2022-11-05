package com.mycompany.animix;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Disco;
import com.github.britooo.looca.api.group.discos.DiscoGrupo;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.processador.Processador;
import com.github.britooo.looca.api.group.processos.Processo;
import com.github.britooo.looca.api.group.processos.ProcessoGrupo;
import com.github.britooo.looca.api.group.sistema.Sistema;
import com.github.britooo.looca.api.util.Conversor;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author Alexandre Costa
 */
public class App {

    public static void main(String[] args) {
        //CONEXAO BANCO
        Connection connection = new Connection();
        JdbcTemplate database = connection.getConnection();

        // LOOCA 
        Looca looca = new Looca();
        Processador processador = new Processador();
        Sistema sistema = new Sistema();
        Memoria memoria = new Memoria();
        Conversor conversor = new Conversor();
        DiscoGrupo grupoDeDiscos = new DiscoGrupo();
        List<Disco> discos = grupoDeDiscos.getDiscos();
        ProcessoGrupo grupoProcesso = new ProcessoGrupo();
        List<Processo> processos = grupoProcesso.getProcessos();

        //Coleta de dados
        
        Coleta col = new Coleta();
        
        col.coletar();
    }
}
