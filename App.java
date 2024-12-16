import dominio.Biblioteca;
import dominio.Emprestimo;
import dominio.Livro;
import dominio.Usuario;

public class App {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
    
        Livro livro1 = new Livro("C.S Lewis", "Crônicas de Nárnia", "ED", 1950);
        Livro livro2 = new Livro("C.S Lewis", "Senhor dos Anéis", "ED", 1950);

        Usuario usuario1 = new Usuario("Jp", "00011133345", "jp@email.com");

        biblioteca.cadastrarLivro(livro1);
        biblioteca.cadastrarLivro(livro2);
        
        biblioteca.cadastrarUsuario(usuario1);

        Emprestimo emprestimo = new Emprestimo(biblioteca);
        Emprestimo emprestimo1 = new Emprestimo(biblioteca);

        emprestimo.realizarEmprestimo(usuario1, livro1);
        emprestimo.devolverEmprestimo(usuario1, livro1);
        
        emprestimo1.realizarEmprestimo(usuario1, livro2);
        emprestimo1.devolverEmprestimo(usuario1, livro2);

        usuario1.listarHistoricoEmprestimos();
    }
}
