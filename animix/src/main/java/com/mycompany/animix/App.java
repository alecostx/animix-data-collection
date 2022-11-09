package com.mycompany.animix;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Alexandre Costa
 */
public class App {

    public static void main(String[] args) {
        Timer timer = new Timer();
        Coleta coleta = new Coleta();
        Maquina maquina = new Maquina();
        
        maquina.setarInfos(2);
        Maquina maquinaMonitorar = maquina.getMaquina(2);
        Maquina maquinaMonitorar1 = maquina.getMaquina(1);
        
        timer.schedule(new TimerTask(){
            @Override
            public void run(){
                coleta.coletar(maquinaMonitorar);
                //coleta.coletar(maquinaMonitorar1);
            }
        }, 1500, 1500);
        
    }
}
