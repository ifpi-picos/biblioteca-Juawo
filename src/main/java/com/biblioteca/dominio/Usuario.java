package com.biblioteca.dominio;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private int id_usuario;
    
    private String nome;
    private String cpf;
    private String email;
    private List<Emprestimo> historicoEmprestimos;
    
    public Usuario(String nome, String cpf, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.historicoEmprestimos = new ArrayList<>();
    }
    
    public int getId_usuario() {
            return id_usuario;
    }
    
    public void setId_usuario(int id_usuario) {
            this.id_usuario = id_usuario;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void addEmprestimoHistorico(Emprestimo novEmprestimo) {
        historicoEmprestimos.add(novEmprestimo);
    }

    public void listarHistoricoEmprestimos() {
        System.out.println(" -- Histórico de Empréstimos do usuário " + this.nome);
        for (Emprestimo emprestimo : historicoEmprestimos) {
            System.out.println("Título do Livro Emprestado -- " + emprestimo.getTituloLivroEmprestado());
            System.out.println("   - Data do Empréstimo -- " + emprestimo.getDataEmprestimo());
            System.out.println("   - Data de Devolução -- " + emprestimo.getDataDevolucao());
            System.out.println(" \n");
        }
    }

}