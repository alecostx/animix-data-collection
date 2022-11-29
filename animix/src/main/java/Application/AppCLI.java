/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Application;

import Coleta.Coleta;
import Models.Maquina;
import Models.Usuario;
import Views.HomePage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

/**
 *
 * @author Aegis
 */
public class AppCLI {

    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
        Scanner leitor = new Scanner(System.in);
        Scanner leitor2 = new Scanner(System.in);

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection con = DriverManager.getConnection("jdbc:sqlserver://animix.database.windows.net:1433;database=animix;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;", "admin-1adsb-grupo07", "#Gfgrupo7");
        System.out.println("digite seu usuario");

        String usuarior = leitor.nextLine();
        System.out.println("digite sua senha");

        String senha = leitor.nextLine();
        Statement stm = con.createStatement();
        String loginn = "select * from funcionario where email='" + usuarior + "' and senha='" + senha + "'";
        ResultSet rs = stm.executeQuery(loginn);
        Statement stm2 = con.createStatement();
        String loginn2 = "select * from funcionario where email='" + usuarior + "' and senha='" + senha + "'";
        ResultSet rs2 = stm2.executeQuery(loginn2);
       while (!rs.next()) {
            System.out.println("Usuario ou senha incorretos");
            System.out.println("----------------------------");

            System.out.println("digite seu usuario");

            usuarior = leitor2.nextLine();
            System.out.println("digite sua senha");

            senha = leitor2.nextLine();
            stm2 = con.createStatement();
            loginn2 = "select * from funcionario where email='" + usuarior + "' and senha='" + senha + "'";
            rs2 = stm2.executeQuery(loginn2);
            stm = con.createStatement();
            loginn = "select * from funcionario where email='" + usuarior + "' and senha='" + senha + "'";
            rs = stm.executeQuery(loginn);
        }
        if (rs.next()) {
            Timer timer = new Timer();
            Coleta coleta = new Coleta();
            Maquina maquina = new Maquina();
            System.out.println("Iniciando Coleta");

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
      

        if (rs2.next()) {
            Timer timer = new Timer();
            Coleta coleta = new Coleta();
            Maquina maquina = new Maquina();
            System.out.println("Iniciando Coleta");
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
}
