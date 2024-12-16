import java.util.Scanner;

import dominio.Biblioteca;
import dominio.Livro;
import dominio.Usuario;

public class Menu {
    private Biblioteca biblioteca;

    public Menu(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }

    public void menuText() {
        System.out.println("--- Sistema de Biblioteca ---");
        System.out.println(
                "\n 1 - Cadastrar Usuário \n 2 - Cadastrar Livro \n 3 - Listar todos os Livros \n 4 - Listar Livros Emprestados e Disponíveis \n 5 - Listar Histórico de Empréstimo de Usuário \n 6 - Realizar Empréstimo de Livro \n 7 - Devolver Livro Emprestado \n 0 - Sair");
    }

    public void iniciarMenu(){
        boolean isLoop = true;
        Scanner scanner = new Scanner(System.in);
        
        while (isLoop) {
            menuText();
            System.out.println("Escolha uma opção : ");
            int opcao = scanner.nextInt();
            switch (opcao) {
                case 1:
                    System.out.println("Digite o nome do usuário:");
                    String nome = scanner.next();

                    System.out.println("Digite o CPF do usuário:");
                    String cpf = scanner.next();

                    System.out.println("Digite o e-mail do usuário:");
                    String email = scanner.next();

                    Usuario usuario = new Usuario(nome, cpf, email);
                    biblioteca.cadastrarUsuario(usuario);

                    System.out.println("Usuário cadastrado com sucesso!");
                    break;

                case 2:
                    System.out.println("Digite o titulo do Livro : ");
                    String tituloLivro = scanner.nextLine();

                    System.out.println("Digite o nome do Autor : ");
                    String nomeAutor = scanner.nextLine();

                    System.out.println("Digite o nome da Editora : ");
                    String nomeEditora = scanner.nextLine();

                    System.out.println("Digite o Ano de Publicação : ");
                    int anoPublicacao = scanner.nextInt();

                    Livro novoLivro = new Livro(tituloLivro, nomeAutor, nomeEditora, anoPublicacao);
                    biblioteca.cadastrarLivro(novoLivro);
                    break;
                case 3:
                    biblioteca.listarLivros();
                    break;
                case 4:
                    biblioteca.listarLivrosStatus();
                    break;
                case 5:
                    // Sistema de Senha pra verificar o usuário

                    break;
                case 6:
                    // Sistema de busca por Chave para escolher o usuário e poder realizar o empréstimo no nome dele
                    break;
                case 7:
                     // Sistema de busca por Chave para escolher o usuário e poder realizar a devolução no nome dele
                    break;
                case 0:
                    System.out.println("Obrigado por usar nosso sistema!");
                    isLoop = false;
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente");
                    break;
            }
        }
        scanner.close();
    }
}
