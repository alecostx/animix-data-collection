package Integracoes;

import Models.Dados;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

/**
 *
 * @author Alexandre Costa
 */
public class Slack {

    JSONObject json = new JSONObject();

    private static final HttpClient client = HttpClient.newHttpClient();
    private static final String URL = "https://hooks.slack.com/services/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";

    public static void sendMessage(JSONObject content) throws IOException, InterruptedException {
        try {
            HttpRequest request = HttpRequest.newBuilder(
                    URI.create(URL))
                    .header("accept", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(content.toString()))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println(String.format("Status: %s", response.statusCode()));
            System.out.println(String.format("Response: %s", response.body()));

            if (response.statusCode() != 200) {
                LogSlack lg = new LogSlack();
                lg.gravarLog("ERRO AO ENVIAR MENSAGEM." + response.statusCode() + "\n" + response.toString());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void verificarDados(Dados dado) throws IOException, InterruptedException {

        if (dado.getComment().size() == 2) {
            try {
                String dadoMensagem = dado.toString().replace("]", " ").replace("[", " ");

                json.put("text", dadoMensagem);

                Slack.sendMessage(json);

            } catch (IOException ex) {
                Logger.getLogger(Slack.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println(ex);
                LogSlack lg = new LogSlack();
                lg.gravarLog("ERRO AO VERIFICAR DADOS." + ex.getMessage() + "\n" + ex.getClass());
            } catch (InterruptedException ex) {
                Logger.getLogger(Slack.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println(ex);
                LogSlack lg = new LogSlack();
                lg.gravarLog("VERIFICACAO INTERROMPIDA." + ex.getMessage() + "\n" + ex.getClass());
            }
        }

    }

}
