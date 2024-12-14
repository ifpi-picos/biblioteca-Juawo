import dominio.Biblioteca;
import dominio.Livro;
import dominio.Usuario;

public class App {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        Livro livro1 = new Livro("C.S Lewis", "Crônicas de Nárnia", "ED", 1950);
        Usuario usuario1 = new Usuario("Jp", "00011133345", "jp@email.com");
        
        biblioteca.cadastrarLivro(livro1);
        biblioteca.cadastrarUsuario(usuario1);
    }
}
