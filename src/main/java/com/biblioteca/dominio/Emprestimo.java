package com.biblioteca.dominio;

import java.time.LocalDate;

import com.biblioteca.dominio.Notificacao.INotificacao;
import com.biblioteca.dominio.Notificacao.NotificaoEmail;

public class Emprestimo {
    private Usuario usuario;
    private Livro livro;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;
    private Biblioteca biblioteca;

    
    public Emprestimo(Biblioteca biblioteca, Usuario usuario, Livro livro) {
        this.usuario = usuario;
        this.livro = livro;
        this.dataEmprestimo = LocalDate.now();
        this.dataDevolucao = dataEmprestimo.plusDays(7);
        this.biblioteca = biblioteca;
    }
    
    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }
    
    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }
    public String getTituloLivroEmprestado() {
        return livro.getTitulo();
    }

    public void realizarEmprestimo() {
        if(!biblioteca.validarUsuario(usuario)){
            System.out.println("Usuário não encontrado para realizar o emprestimo");
            return;
        }
        if(!biblioteca.validarLivro(livro)){
            System.out.println("Livro não encontrado para realizar o emprestimo");
            return;
        }

        if (livro.isEmprestado()) {
            System.out.println("Este livro já está emprestado!");
            return;
        }


        livro.setEmprestado(true);
        usuario.addEmprestimoHistorico(this);
        INotificacao notificacao = new NotificaoEmail();
        String texto = "Empréstimo do livro : " + livro.getTitulo() + " realizado com sucesso" + "Data de devolução : " + this.dataDevolucao;
        notificacao.enviarNotificacao(texto,usuario);
    }
    
    public void devolverEmprestimo(){
        if (!biblioteca.validarUsuario(usuario)) {
            System.out.println("Usuário não Encontrado");
            return;
        }
        if (!biblioteca.validarLivro(livro)) {
            System.out.println("Livro não Encontrado");
            return;
        }
        
        if (!livro.isEmprestado()) {
            System.out.println("Este livro não está emprestado");
            return;
        }
        
        livro.setEmprestado(false);
        String texto = "Devolução do livro : " + livro.getTitulo() + " realizado com sucesso!";
        INotificacao notificacao = new NotificaoEmail();
        notificacao.enviarNotificacao(texto,usuario);
    }
}