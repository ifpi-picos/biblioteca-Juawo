package com.biblioteca;
import com.biblioteca.dominio.Biblioteca;
import com.biblioteca.dominio.Livro;
import com.biblioteca.dominio.Usuario;

public class App {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        Livro livro1 = new Livro("livro1", "autor1", "editora1", 2001);
        Livro livro2 = new Livro("livro2", "autor2", "editora2", 2002);

        biblioteca.cadastrarLivro(livro1);
        biblioteca.cadastrarLivro(livro2);

        Usuario user1 = new Usuario("user1", "1234", "user1@email.com");
        Usuario user2 = new Usuario("user2", "0987", "user2@email.com");

        biblioteca.cadastrarUsuario(user1);
        biblioteca.cadastrarUsuario(user2);
        
        Menu menu = new Menu(biblioteca);
        menu.iniciarMenu();
    }
}
