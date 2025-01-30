package com.biblioteca.dominio;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String nome;
    private String cpf;
    private String email;
    private static int idCount = 1;
    private int id;
    private List<Emprestimo> historicoEmprestimos;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario(String nome, String cpf, String email) {
        this.id = idCount++;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.historicoEmprestimos = new ArrayList<>();
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