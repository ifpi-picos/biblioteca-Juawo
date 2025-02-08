package com.biblioteca.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.biblioteca.dominio.Livro;

public class LivroDao {
    private ConexaoBD connection;

    public LivroDao(ConexaoBD connection){
        this.connection = connection;
    }
    
    public void cadastrarLivro(Livro livro) {
        String sql = "INSERT INTO livros (titulo, autor, editora, ano, emprestado) VALUES (?, ?,?,?, FALSE)";

            try {
            PreparedStatement statment = connection.conectarBanco().prepareStatement(sql);
            statment.setString(1,livro.getTitulo());
            statment.setString(2, livro.getAutor());
            statment.setString(3, livro.getEditora());
            statment.setInt(4, livro.getAno());
            statment.executeUpdate();
            System.out.println("Livro Adicionado com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void listarLivros(){
        System.out.println("---- Livros da Biblioteca ----");
        String sql = "SELECT titulo FROM livros";
        try{
            Statement statement = connection.conectarBanco().createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                System.out.println("Titulo : " + result.getString("titulo"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void listarLivroStatus(){
        System.out.println("Lista Livros por Status");
        String sql = "SELECT titulo, emprestado FROM livros";

        try {
            Statement statement = connection.conectarBanco().createStatement();
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

    public void pesquisarLivroTitulo(String titulo){
        String sql = "SELECT titulo FROM livros WHERE titulo = ? ";
        try {
            PreparedStatement statement = connection.conectarBanco().prepareStatement(sql);
            statement.setString(1, titulo);
            ResultSet result = statement.executeQuery();
            
            while (result.next()) {
                System.out.println("Titulo : " + result.getString("titulo"));
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
    }


}
