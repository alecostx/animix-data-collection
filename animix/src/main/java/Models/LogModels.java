package Models;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author 55119
 */
public class LogModels {
    public void gravarLog(String textoLog) throws IOException {
        Date dataHoraAtual = new Date();
        String data = new SimpleDateFormat("dd/MM/yyyy").format(dataHoraAtual);
        String hora = new SimpleDateFormat("HH:mm:ss").format(dataHoraAtual);
        FileWriter arq = new FileWriter("C:\\Users\\Alexandre Costa\\Desktop\\animix-data-collection\\log.txt", true);
        PrintWriter gravarArq = new PrintWriter(arq);

        gravarArq.println(String.format("%s %s %s \n", data, hora, textoLog));

        arq.close();

    }
}
