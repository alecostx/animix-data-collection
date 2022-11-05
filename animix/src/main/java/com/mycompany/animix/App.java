package com.mycompany.animix;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Alexandre Costa
 */
public class App {

    public static void main(String[] args) {
        Coleta coleta = new Coleta();
        Runnable collectionRun = new Runnable() {
            public void run() {
                coleta.coletar(1);
            }
        };
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(collectionRun, 0, 5, TimeUnit.SECONDS);
        //coleta.getLastData(5, 1);
    }
}
