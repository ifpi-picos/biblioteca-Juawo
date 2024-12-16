package dominio;

import java.time.LocalDate;

public class Emprestimo {
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;
    private Biblioteca biblioteca;
    private String tituloLivroEmprestado;

    
    public Emprestimo(Biblioteca biblioteca) {
        this.dataEmprestimo = LocalDate.now();
        this.dataDevolucao = dataEmprestimo.plusDays(7);
        this.biblioteca = biblioteca;
        this.tituloLivroEmprestado = null;
    }
    
    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }
    
    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }
    public String getTituloLivroEmprestado() {
        return tituloLivroEmprestado;
    }
        
    public void setTituloLivroEmprestado(String tituloLivroEmprestado) {
        this.tituloLivroEmprestado = tituloLivroEmprestado;
    }

    public void realizarEmprestimo(Usuario usuarioAssociado, Livro livroAssociado) {
        if(!biblioteca.validarUsuario(usuarioAssociado)){
            System.out.println("Usuário não encontrado");
            return;
        }
        if(!biblioteca.validarLivro(livroAssociado)){
            System.out.println("Livro não encontrado");
            return;
        }

        this.tituloLivroEmprestado = livroAssociado.getTitulo();

        livroAssociado.setEmprestado(true);
        usuarioAssociado.addEmprestimoHistorico(this);
        System.out.println("Empréstimo realizado com sucesso do livro : " + livroAssociado.getTitulo());
    }

    public void devolverEmprestimo(Usuario usuarioAssociado, Livro livroAssociado){
        if (!biblioteca.validarUsuario(usuarioAssociado)) {
            System.out.println("Usuário não Encontrado");
            return;
        }
        if (!biblioteca.validarLivro(livroAssociado)) {
            System.out.println("Livro não Encontrado");
            return;
        }
        
        livroAssociado.setEmprestado(false);
        System.out.println("Devolução feita com sucesso do livro : " + livroAssociado.getTitulo());
    }
}