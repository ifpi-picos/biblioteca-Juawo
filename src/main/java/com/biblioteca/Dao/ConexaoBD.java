package com.biblioteca.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {
    private final String url = "jdbc:postgresql://localhost:5432/biblioteca";
    private final String usuario = "postgres";
    private final String senha = "postgres";

    public Connection conectarBanco(){
        try {
            Connection connection = DriverManager.getConnection(url, usuario, senha);

            if (connection != null) {
                System.out.println("Banco conectado com sucesso!");
                return connection;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
