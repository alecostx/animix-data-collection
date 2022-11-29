package Database;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author Alexandre Costa
 */
public class Connection {

    private JdbcTemplate conexao;

    public Connection() {

        BasicDataSource datasource = new BasicDataSource();
        datasource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        datasource.setUrl("jdbc:sqlserver://animix.database.windows.net:1433;database=animix;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;");
        datasource.setUsername("admin-1adsb-grupo07");
        datasource.setPassword("#Gfgrupo7");
        
        conexao = new JdbcTemplate(datasource);
    }

    public JdbcTemplate getConnection() {
        return conexao;
    }

    
}
