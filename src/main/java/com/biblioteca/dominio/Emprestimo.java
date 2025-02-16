package com.biblioteca.dominio;

import java.time.LocalDate;

import com.biblioteca.dominio.Notificacao.INotificacao;
import com.biblioteca.dominio.Notificacao.NotificaoEmail;

public class Emprestimo {
    private int id_emprestimo;
    private int id_usuario;
    private int id_livro;

    private Usuario usuario;
    private Livro livro;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;
    
    private Boolean devolvido = false;

    private Biblioteca biblioteca;

    public Emprestimo(Biblioteca biblioteca, int id_usuario, int id_livro) {
        this.id_usuario = id_usuario;
        this.id_livro = id_livro;
        this.dataEmprestimo = LocalDate.now();
        this.dataDevolucao = dataEmprestimo.plusDays(7);
        this.devolvido = false;
        this.biblioteca = biblioteca;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getId_livro() {
        return id_livro;
    }

    public void setId_livro(int id_livro) {
        this.id_livro = id_livro;
    }

    public int getId_emprestimo() {
        return id_emprestimo;
    }
    
    public void setId_emprestimo(int id_emprestimo) {
        this.id_emprestimo = id_emprestimo;
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
    
    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }
    
    public Boolean getDevolvido() {
        return devolvido;
    }

    public void setDevolvido(Boolean devolvido) {
        this.devolvido = devolvido;
    }

    public void realizarEmprestimo() {
        if (!biblioteca.validarUsuario(usuario)) {
            System.out.println("Usuário não encontrado para realizar o emprestimo");
            return;
        }
        if (!biblioteca.validarLivro(livro)) {
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
        String texto = "Empréstimo do livro : " + livro.getTitulo() + " realizado com sucesso" + "Data de devolução : "
                + this.dataDevolucao;
        notificacao.enviarNotificacao(texto, usuario);
    }

    public void devolverEmprestimo() {
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
        this.devolvido = true;
        String texto = "Devolução do livro : " + livro.getTitulo() + " realizado com sucesso!";
        INotificacao notificacao = new NotificaoEmail();
        notificacao.enviarNotificacao(texto, usuario);

    }
}