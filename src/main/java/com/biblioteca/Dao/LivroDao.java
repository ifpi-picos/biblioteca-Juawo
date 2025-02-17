package com.biblioteca.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.biblioteca.dominio.Livro;

public class LivroDao {
    private Connection connection;

    public LivroDao(Connection connection){
        this.connection = connection;
    }
    
    public void cadastrarLivro(Livro livro) throws SQLException {
        String sql = "INSERT INTO livros (titulo, autor, editora, ano, emprestado) VALUES (?, ?,?,?, FALSE)";

            try {
            PreparedStatement statment = connection.prepareStatement(sql);
            statment.setString(1,livro.getTitulo());
            statment.setString(2, livro.getAutor());
            statment.setString(3, livro.getEditora());
            statment.setInt(4, livro.getAno());
            statment.executeUpdate();
            System.out.println("Livro Adicionado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar livo : " + e.getMessage());
        }
    }

    public void removerLivro(Livro livro) throws SQLException {
        String sql = "DELETE FROM livros WHERE id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, livro.getId_livro());
            statement.executeUpdate();
            System.out.println("Livro removido com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao remover livro : " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void atualizarLivro(Livro livro) throws SQLException {
        String sql = "UPDATE livros SET titulo = ?, autor = ?, editora = ?, ano = ?, emprestado = ? WHERE id = ?";
        
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, livro.getTitulo());
            statement.setString(2, livro.getAutor());
            statement.setString(3, livro.getEditora());
            statement.setInt(4, livro.getAno());
            statement.setBoolean(5, livro.isEmprestado());
            statement.setInt(6, livro.getId_livro());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void listarLivros() throws SQLException {
        System.out.println("---- Livros da Biblioteca ----");
        String sql = "SELECT titulo FROM livros";
        try{
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                System.out.println("Titulo : " + result.getString("titulo"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void listarLivroStatus() throws SQLException {
        System.out.println("Lista Livros por Status");
        String sql = "SELECT titulo, emprestado FROM livros";

        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                if (result.getBoolean("emprestado") == false) {
                    System.out.println("Título : " + result.getString("titulo") + " | Status : Disponível");
                } else {
                    System.out.println("Título : " + result.getString("titulo") + " | Status : Emprestado");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Livro pesquisarLivroTitulo(String titulo) throws SQLException {
        String sql = "SELECT id, titulo, autor, editora, ano FROM livros WHERE titulo = ? ";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, titulo);
            ResultSet result = statement.executeQuery();
            
            if (result.next()) {
                    Livro livro = new Livro(
                        result.getString("titulo"),
                        result.getString("autor"),
                        result.getString("editora"),
                        result.getInt("ano")
                        );
                    livro.setId_livro(result.getInt("id"));
                    System.out.println("Livro encontrado por titulo!");
                    return livro;
            }
        } catch(SQLException e){
            System.out.println("Erro ao pesquisar livro por titulo : " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("Livro não encontrado por titulo, tente novamente.");
        return null;
    }

    public Livro pesquisarLivroId(int id) throws SQLException {
        String sql = "SELECT titulo, autor, editora, ano FROM livros WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                Livro livro = new Livro(
                    result.getString("titulo"),
                    result.getString("autor"),
                    result.getString("editora"),
                    result.getInt("ano")
                );
                livro.setId_livro(id);
                System.out.println("Livro encontrado por id!");
                return livro;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao pesquisar livro por id : " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("Livro não encontrado por id, tente novamente.");
        return null;
    }
}