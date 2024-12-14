package dominio;

import java.util.ArrayList;
import java.util.List;

public class Acervo {
    private List<Livro> livrosCadastrados;

    public Acervo() {
        this.livrosCadastrados = new ArrayList<>();
    }

    public List<Livro> getLivrosCadastrados() {
        return livrosCadastrados;
    }

    public void setLivrosCadastrados(List<Livro> livrosCadastrados) {
        this.livrosCadastrados = livrosCadastrados;
    }

    public void cadastrarLivro(Livro novoLivro) {
        System.out.println("Livro cadastrado : " + novoLivro.getTitulo());
        this.livrosCadastrados.add(novoLivro);
    }
}
