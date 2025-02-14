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
        String sql = "";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void atualizarLivro(Livro livro) throws SQLException {
        String sql = "";
        
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
        
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
        String sql = "SELECT titulo FROM livros WHERE titulo = ? ";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, titulo);
            ResultSet result = statement.executeQuery();
            
            if (result.next()) {
                while (result.next()) {
                    Livro livro = new Livro(
                        result.getString("titulo"),
                        result.getString("autor"),
                        result.getString("editora"),
                        result.getInt("ano")
                        );
                    livro.setId_livro(result.getInt("id"));
                    return livro;
                }
            }
        } catch(SQLException e){
            e.printStackTrace();
        }

        return null;
    }


}
