package com.mycompany.animix;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
/**
 *
 * @author Alexandre.Rodrigues
 */
public class Telegram {
    public static void sendToTelegram() {
        String urlString = "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s";

        String apiToken = "5721116072:AAETj1OXcjNygCvfNYdmUR56mQPnU1k5aEc";
        
        String chatId = "@JudithTheBot";
        String text = "Hello world!";

        urlString = String.format(urlString, apiToken, chatId, text);

        try {
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();
            InputStream is = new BufferedInputStream(conn.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
