package com.biblioteca;

import com.biblioteca.Dao.ConexaoBD;
import com.biblioteca.Dao.LivroDao;
import com.biblioteca.dominio.Biblioteca;
import com.biblioteca.dominio.Livro;

public class App {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        Livro livro1 = new Livro("livro5", "autor1", "editora1", 2001);
        ConexaoBD conn = new ConexaoBD();
        LivroDao livroDao = new LivroDao(conn);
        livroDao.pesquisarLivroTitulo("Senhor dos");

        // Menu menu = new Menu(biblioteca);
        // menu.iniciarMenu();
    }
}
