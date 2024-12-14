package dominio;

import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private List<Livro> livrosCadastrados;
    private List<Usuario> usuariosCadastrados;

    public Biblioteca() {
        this.livrosCadastrados = new ArrayList<>();
        this.usuariosCadastrados = new ArrayList<>();
    }

    public List<Livro> getLivrosCadastrados() {
        return livrosCadastrados;
    }

    public void setLivrosCadastrados(List<Livro> livrosCadastrados) {
        this.livrosCadastrados = livrosCadastrados;
    }

    public List<Usuario> getUsuariosCadastrados() {
        return usuariosCadastrados;
    }

    public void setUsuariosCadastrados(List<Usuario> usuariosCadastrados) {
        this.usuariosCadastrados = usuariosCadastrados;
    }

    public void cadastrarLivro(Livro novoLivro) {
        System.out.println("Livro cadastrado : " + novoLivro.getTitulo());
        this.livrosCadastrados.add(novoLivro);
    }

    public void cadastrarUsuario(Usuario novoUsario) {
        System.out.println("Usuário cadastrado : " + novoUsario.getNome());
        this.usuariosCadastrados.add(novoUsario);
    }

    public void listarLivros() {
        System.out.println("--- Livros Cadastrados na Biblioteca ---");
        for (Livro livro : livrosCadastrados) {
            System.out.println("Livro : " + livro.getTitulo());
        }
    }

    public void listarLivrosStatus() {
        System.out.println("--- Lista de Livros Disponíveis ---");
        for (Livro livro : livrosCadastrados) {
            if (!livro.isEmprestado()) {
                System.out.println("Livro : " + livro.getTitulo() + " - Disponível");
            }
        }
        System.out.println("--- Lista de Livros Reservados ---");
        for (Livro livro : livrosCadastrados) {
            if (livro.isEmprestado()) {
                System.out.println("Livro : " + livro.getTitulo() + " - Reservado");
            }
        }
    }

    public boolean validarUsuario(Usuario usuario){
        if (usuariosCadastrados.contains(usuario)) {
            return true;
        } else {
            return false;
        }
    }
}
