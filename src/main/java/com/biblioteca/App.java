package com.biblioteca;
import com.biblioteca.Dao.LivroDao;
import com.biblioteca.dominio.Biblioteca;
import com.biblioteca.dominio.Livro;
import com.biblioteca.dominio.Menu;
import com.biblioteca.dominio.Usuario;

public class App {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        Livro livro1 = new Livro("livro1", "autor1", "editora1", 2001);
        Menu menu = new Menu(biblioteca);
        menu.iniciarMenu();
    }
}
