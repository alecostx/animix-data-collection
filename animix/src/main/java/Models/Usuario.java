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
        
        if (email == null) {
            LogModels lg = new LogModels();
            lg.gravarLog("USUARIO NAO ENCONTRADO." + "Email é nulo");
        }

        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() throws IOException{
        
        if (senha == null) {
            LogModels lg = new LogModels();
            lg.gravarLog("USUARIO NAO ENCONTRADO." + "Email é nulo");
        }

        return senha;

    }

    public void setSenha(String senha) {
        this.senha = senha;

    }
}
