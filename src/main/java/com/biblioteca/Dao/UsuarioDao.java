package com.biblioteca.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.biblioteca.dominio.Usuario;

public class UsuarioDao {
    private Connection connection;

    public UsuarioDao(Connection connection){
        this.connection = connection;
    }

    public void cadastrarUsuario(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuarios(nome, cpf, email) VALUES (?,?,?)";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1,usuario.getNome());
            statement.setString(2,usuario.getCpf());
            statement.setString(3,usuario.getEmail());
            statement.executeUpdate();
            System.out.println("Usuáiro cadastrado com sucesso!");

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void removerUsuario(Usuario usuario) throws SQLException {
        String sql = "";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void atualizarUsuario(Usuario usuario) throws SQLException {
        String sql = "";
        
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
        
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Usuario pesquisarUsuarioCpf(String cpf) throws SQLException {
        String sql = "SELECT id,nome,cpf,email FROM usuarios WHERE cpf = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,cpf);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                Usuario user = new Usuario(
                    result.getString("nome"),
                    result.getString("cpf"),
                    result.getString("email")
                );
                user.setId_usuario(result.getInt("id"));
                return user;
            } 
        } catch (SQLException e){
            System.out.println("Erro ao tentar procurar por cpf " + e.getMessage());
        }


        System.out.println("Usuário não encontrado, tente novamente.");
        return null;
    }

}