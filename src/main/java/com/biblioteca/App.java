package com.biblioteca;

import java.sql.SQLException;

import com.biblioteca.Dao.ConexaoBD;
import com.biblioteca.Dao.EmprestimoDao;
import com.biblioteca.Dao.LivroDao;
import com.biblioteca.Dao.UsuarioDao;
import com.biblioteca.dominio.Biblioteca;
import com.biblioteca.dominio.Emprestimo;
import com.biblioteca.dominio.Livro;
import com.biblioteca.dominio.Usuario;

public class App {
    public static void main(String[] args) throws SQLException {
        Biblioteca biblioteca = new Biblioteca();

        Livro livro1 = new Livro("livro5", "autor1", "editora1", 2001);
        Usuario user1 = new Usuario("Mantinha2", "12345678911", "mantinha2@email.com");

        ConexaoBD conn = new ConexaoBD();
        LivroDao livroDao = new LivroDao(conn.conectarBanco());
        UsuarioDao userDao = new UsuarioDao(conn.conectarBanco());

        userDao.cadastrarUsuario(user1);

        Usuario userPesq = userDao.pesquisarUsuarioCpf("12345678910");
        Livro livroPesq = livroDao.pesquisarLivroTitulo("livro5");

        Emprestimo emprestimo = new Emprestimo(biblioteca, userPesq.getId_usuario(), livroPesq.getId_livro());

        EmprestimoDao emp = new EmprestimoDao(conn.conectarBanco());

        emp.cadastrarEmprestimo(emprestimo);
        
        // Menu menu = new Menu(biblioteca);
        // menu.iniciarMenu();
    }
}
