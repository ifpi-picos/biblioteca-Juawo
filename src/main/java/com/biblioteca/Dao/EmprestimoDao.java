package com.biblioteca.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

import com.biblioteca.dominio.Emprestimo;
import com.biblioteca.dominio.Livro;
import com.biblioteca.dominio.Usuario;

public class EmprestimoDao {
    private Connection connection;

    public EmprestimoDao(Connection connection) {
        this.connection = connection;
    }
    
    public void cadastrarEmprestimo(Emprestimo emprestimo) throws SQLException {
        Livro livro = new LivroDao(connection).pesquisarLivroId(emprestimo.getId_livro());
        if (livro.isEmprestado()) {
            System.out.println("O Livro : " + livro.getTitulo() + " já está emprestado!");
            return;
        }
        
        String sql = "INSERT INTO emprestimos(id_usuario, id_livro, data_emprestimo, data_devolucao) VALUES (?,?,?,?)";
        
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
    }

    public void devolverEmprestimo(Emprestimo emprestimo) throws SQLException {
       String sql = "UPDATE emprestimos SET devolvido = TRUE WHERE id = ? AND devolvido = FALSE";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, emprestimo.getId_emprestimo());
        statement.executeUpdate();  
        Livro livroAtt = new LivroDao(connection).pesquisarLivroId(emprestimo.getId_livro());
        livroAtt.setEmprestado(false);
        
        LivroDao livroDao = new LivroDao(connection);
        livroDao.atualizarLivro(livroAtt);
        System.out.println("Emprestimo devolvido com sucesso!");
    }

    public void removerEmprestimo(Emprestimo emprestimo) throws SQLException {
        String sql = "DELETE FROM emprestimos WHERE id = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, emprestimo.getId_emprestimo());
        statement.executeUpdate();
        System.out.println("Emprestimo removido com sucesso!");   
    }

    public Emprestimo buscarEmprestimoPorUsuarioELivro(int id_usuario, int id_livro) throws SQLException{
        String sql = "SELECT id, id_usuario, id_livro, data_emprestimo, data_devolucao, devolvido FROM emprestimos WHERE id_usuario = ? AND id_livro = ? AND devolvido = FALSE";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id_usuario);
        statement.setInt(2, id_livro);
        ResultSet result = statement.executeQuery();
        
        if (result.next()) {
            Usuario usuario = new UsuarioDao(connection).pesquisarUsuarioId(result.getInt("id_usuario"));
            Livro livro = new LivroDao(connection).pesquisarLivroId(result.getInt("id_livro"));
            Emprestimo emprestimo = new Emprestimo(usuario, livro);
            emprestimo.setId_emprestimo(result.getInt("id"));
            emprestimo.setDataEmprestimo(result.getDate("data_emprestimo").toLocalDate());
            emprestimo.setDataDevolucao(result.getDate("data_devolucao").toLocalDate());
            emprestimo.setDevolvido(result.getBoolean("devolvido"));
            return emprestimo;
        }

        return null;   
    }

    public Emprestimo pesquisaEmprestimoPorId(int id) throws SQLException {
        String sql = "SELECT id, id_usuario, id_livro, data_emprestimo, data_devolucao, devolvido FROM emprestimos WHERE id = ?";
        
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet result = statement.executeQuery();
        if(result.next()){
            Usuario usuario = new UsuarioDao(connection).pesquisarUsuarioId(result.getInt("id_usuario"));
            Livro livro = new LivroDao(connection).pesquisarLivroId(result.getInt("id_livro"));
            Emprestimo emprestimo = new Emprestimo(usuario, livro);
            emprestimo.setId_emprestimo(result.getInt("id"));
            emprestimo.setDataEmprestimo(result.getDate("data_emprestimo").toLocalDate());
            emprestimo.setDataDevolucao(result.getDate("data_devolucao").toLocalDate());
            emprestimo.setDevolvido(result.getBoolean("devolvido"));
            return emprestimo;
        }

        return null;
    }

    public List<Emprestimo> listarHistoricoEmprestimos(Usuario usuario) throws SQLException {
        String sql = "SELECT id, id_usuario, id_livro, data_emprestimo, data_devolucao, devolvido FROM emprestimos WHERE id_usuario = ?";
        List<Emprestimo> historicoEmprestimos = new ArrayList<>();

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, usuario.getId_usuario());
        ResultSet result = statement.executeQuery();
        
        while(result.next()){
            Usuario usuarioBusca = new UsuarioDao(connection).pesquisarUsuarioId(result.getInt("id_usuario"));
            Livro livro = new LivroDao(connection).pesquisarLivroId(result.getInt("id_livro"));
            Emprestimo emprestimo = new Emprestimo(usuarioBusca, livro);
            emprestimo.setId_emprestimo(result.getInt("id"));
            emprestimo.setDataEmprestimo(result.getDate("data_emprestimo").toLocalDate());
            emprestimo.setDataDevolucao(result.getDate("data_devolucao").toLocalDate());
            emprestimo.setDevolvido(result.getBoolean("devolvido"));
            historicoEmprestimos.add(emprestimo);
        }
        
        return historicoEmprestimos;
    }

}
