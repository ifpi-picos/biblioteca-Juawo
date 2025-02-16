package com.biblioteca.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    public Emprestimo pesquisarEmprestimo(int id_usuario, int id_livro) throws SQLException{
        String sql = "SELECT id, id_usuario, id_livro, data_emprestimo, data_devolucao, devolvido FROM emprestimos WHERE id_usuario = ? AND id_livro = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id_usuario);
            statement.setInt(2, id_livro);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                Emprestimo emprestimo = new Emprestimo(null, id_usuario, id_livro);
                emprestimo.setId_emprestimo(statement.getResultSet().getInt("id"));
                emprestimo.setDataEmprestimo(statement.getResultSet().getDate("data_emprestimo").toLocalDate());
                emprestimo.setDataDevolucao(statement.getResultSet().getDate("data_devolucao").toLocalDate());
                emprestimo.setDevolvido(statement.getResultSet().getBoolean("devolvido"));
                return emprestimo;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao pesquisar emprestimo " + e.getMessage());
        }
        return null;   
    }

}
