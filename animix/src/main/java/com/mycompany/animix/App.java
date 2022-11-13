package com.mycompany.animix;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Alexandre Costa
 */
public class App {

    public static void main(String[] args) throws IOException, InterruptedException {
        Timer timer = new Timer();
        Coleta coleta = new Coleta();
        Maquina maquina = new Maquina();
        
        Maquina maquinaMonitorar = maquina.getMaquina(2);
        
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    coleta.coletar(maquinaMonitorar);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }, 1500, 1500);
    }
}
