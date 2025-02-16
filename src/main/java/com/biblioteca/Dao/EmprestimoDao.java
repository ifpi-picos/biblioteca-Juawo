package com.biblioteca.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;

import com.biblioteca.dominio.Emprestimo;
import com.biblioteca.dominio.Livro;

public class EmprestimoDao {
    private Connection connection;

    public EmprestimoDao(Connection connection) {
        this.connection = connection;
    }
    
    public void cadastrarEmprestimo(Emprestimo emprestimo){
        String sql = "INSERT INTO emprestimos(id_usuario, id_livro, data_emprestimo, data_devolucao) VALUES (?,?,?,?)";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, emprestimo.getId_usuario());
            statement.setInt(2, emprestimo.getId_livro());
            statement.setDate(3, Date.valueOf(emprestimo.getDataEmprestimo()));
            statement.setDate(4, Date.valueOf(emprestimo.getDataDevolucao()));
            statement.executeUpdate();

            Livro livroAtt = new LivroDao(connection).pesquisarLivroId(emprestimo.getId_livro());
            livroAtt.setEmprestado(true);
            LivroDao livroDao = new LivroDao(connection);
            livroDao.atualizarLivro(livroAtt);
            
            System.out.println("Emprestimo cadastrado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar emprestimo " + e.getMessage());
        }
    }

    public void devolverEmprestimo(Emprestimo emprestimo){
       String sql = "UPDATE emprestimos SET devolvido = TRUE WHERE id = ?";
       try {

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, emprestimo.getId_emprestimo());
        statement.executeUpdate();

        Livro livroAtt = new LivroDao(connection).pesquisarLivroId(emprestimo.getId_livro());
        livroAtt.setEmprestado(false);
        LivroDao livroDao = new LivroDao(connection);
        livroDao.atualizarLivro(livroAtt);
        System.out.println("Emprestimo devolvido com sucesso!");

       } catch (Exception e) {
        System.out.println("Erro ao devolver emprestimo " + e.getMessage());
        e.printStackTrace();
       }
    }

}
