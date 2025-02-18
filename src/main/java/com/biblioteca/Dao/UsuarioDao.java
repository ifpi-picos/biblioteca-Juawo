package com.biblioteca.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.biblioteca.dominio.Emprestimo;
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
        String sql = "DELETE FROM usuarios WHERE id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,usuario.getId_usuario());
            statement.executeUpdate();
            System.out.println("Usuário removido com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao remover usuário " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void atualizarUsuario(Usuario usuario) throws SQLException {
        String sql = "UPDATE usuarios SET nome = ?, cpf = ?, email = ? WHERE id = ?";
        
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, usuario.getNome());
            statement.setString(2, usuario.getCpf());
            statement.setString(3, usuario.getEmail());
            statement.executeUpdate();

            System.out.println("Usuário atualizado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar usuário " + e.getMessage());
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

    public Usuario pesquisarUsuarioId(int ind){
        String sql = "SELECT id, nome, cpf, email FROM usuarios WHERE id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,ind);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                Usuario user = new Usuario(
                    result.getString("nome"),
                    result.getString("cpf"),
                    result.getString("email")
                );
                user.setId_usuario(ind);
                return user;
            }
        } catch (SQLException e){
            System.out.println("Erro ao tentar procurar por id " + e.getMessage());
        }

        System.out.println("Usuário não encontrado, tente novamente.");
        return null;
    }

    public List<Emprestimo> listarHistoricoEmprestimos(Usuario usuario) throws SQLException {
        String sql = "SELECT id, id_usuario, id_livro, data_emprestimo, data_devolucao, devolvido FROM emprestimos WHERE id_usuario = ?";
        List<Emprestimo> historicoEmprestimos = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, usuario.getId_usuario());
            ResultSet result = statement.executeQuery();
            if(result.next()){
                Emprestimo emprestimo = new Emprestimo(null,
                result.getInt("id_usuario"),
                result.getInt("id_livro"));
                emprestimo.setId_emprestimo(result.getInt("id"));
                emprestimo.setDataEmprestimo(result.getDate("data_emprestimo").toLocalDate());
                emprestimo.setDataDevolucao(result.getDate("data_devolucao").toLocalDate());
                emprestimo.setDevolvido(result.getBoolean("devolvido"));
                historicoEmprestimos.add(emprestimo);
            }
            return historicoEmprestimos;

        } catch(SQLException e){
            System.out.println("Erro ao listar historico de emprestimos " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

}