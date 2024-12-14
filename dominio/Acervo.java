package dominio;

import java.util.List;

public class Acervo {
    private List<Livro> livrosCadastrados;

    public Acervo(List<Livro> livrosCadastrados) {
        this.livrosCadastrados = livrosCadastrados;
    }

    public List<Livro> getLivrosCadastrados() {
        return livrosCadastrados;
    }

    public void setLivrosCadastrados(List<Livro> livrosCadastrados) {
        this.livrosCadastrados = livrosCadastrados;
    }

    public void cadastrarLivro(Livro novoLivro){
        System.out.println("Livro cadastrado : " + novoLivro.getTitulo());
        this.livrosCadastrados.add(novoLivro);
    }
}
