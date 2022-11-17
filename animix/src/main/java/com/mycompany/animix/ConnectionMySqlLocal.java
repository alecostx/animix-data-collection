/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.animix;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author Eduardo
 */
public class ConnectionMySqlLocal {
    private JdbcTemplate connectionMySql; 
        
    public ConnectionMySqlLocal(){
        BasicDataSource datasourceMySql = new BasicDataSource();
    
        datasourceMySql.setDriverClassName("com.mysql.cj.jdbc.Driver");
        datasourceMySql.setUrl("jdbc:mysql://localhost/animix?useTimezone=true&serverTimezone=UTC");
        datasourceMySql.setUsername("root");
        datasourceMySql.setPassword("67578900");
            connectionMySql = new JdbcTemplate(datasourceMySql);

    } 

    public JdbcTemplate getConnectionMySql() {
        return connectionMySql;
    }
    
    
}
