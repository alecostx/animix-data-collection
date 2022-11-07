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
                coleta.coletar(2);
            }
        };
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(collectionRun, 0, 5000, TimeUnit.MILLISECONDS);
    }
}
