package dominio;

import java.time.LocalDate;

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
            System.out.println("Usuário não encontrado");
            return;
        }
        if(!biblioteca.validarLivro(livro)){
            System.out.println("Livro não encontrado");
            return;
        }

        if (livro.isEmprestado()) {
            System.out.println("Este livro já está emprestado!");
            return;
        }


        livro.setEmprestado(true);
        usuario.addEmprestimoHistorico(this);
        System.out.println("Empréstimo realizado com sucesso do livro : " + livro.getTitulo());
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
        System.out.println("Devolução feita com sucesso do livro : " + livro.getTitulo());
    }
}