package com.biblioteca;

import java.sql.SQLException;

import com.biblioteca.Dao.ConexaoBD;
import com.biblioteca.dominio.Menu;


public class App {
    public static void main(String[] args) throws SQLException {
        ConexaoBD conexaoBD = new ConexaoBD();
        Menu menu = new Menu(conexaoBD.conectarBanco());
        menu.iniciarMenu();
    }
}
