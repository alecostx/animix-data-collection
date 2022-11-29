package Models;

import java.io.IOException;

/**
 *
 * @author Alexandre.Rodrigues
 */
public class Usuario {

    private String email;
    private String senha;

    public String getEmail() throws IOException {
        try {
        return email;
        } catch (Exception e) {
            System.out.println(e);
            LogModels lg = new LogModels();
            lg.gravarLog("USUARIO NAO ENCONTRADO." + e.getMessage() + "\n" + e.getClass());
        }
        return null;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() throws IOException {
        try {
        return senha;
        } catch (Exception e) {
            System.out.println(e);
            LogModels lg = new LogModels();
            lg.gravarLog("CLASSE:USUARIO:\nUSUARIO NAO ENCONTRADO." + e.getMessage() + "\n" + e.getClass());
        }
        return null;
    }

    public void setSenha(String senha) {
        this.senha = senha;

    }
}
