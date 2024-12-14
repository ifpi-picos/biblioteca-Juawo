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

    public void cadastrarUsuario(Usuario novoUsario){
        System.out.println("Usuário cadastrado : " + novoUsario.getNome());
        this.usuariosCadastrados.add(novoUsario);
    }
}
