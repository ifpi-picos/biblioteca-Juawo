package com.biblioteca.Dao;

import java.sql.PreparedStatement;

import com.biblioteca.dominio.Livro;

public class LivroDao {
    private ConexaoBD connection;

    public LivroDao(ConexaoBD connection){
        this.connection = connection;
    }
    
    public void cadastrarLivro(Livro livro) {
        String sql = "INSERT INTO livro (titulo, autor, editora, ano, emprestado) VALUES (?, ?,?,?, FALSE)";

            try (PreparedStatement statment = connection.conectarBanco().prepareStatement(sql)){
            statment.setString(1,livro.getTitulo());
            statment.setString(2, livro.getAutor());
            statment.setString(3, livro.getEditora());
            statment.setInt(4, livro.getAno());
            statment.executeUpdate();
            System.out.println("ADD com sucesso o book");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
