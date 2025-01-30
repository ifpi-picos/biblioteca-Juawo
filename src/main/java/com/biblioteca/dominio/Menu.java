package com.biblioteca.dominio;
import java.util.Scanner;

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

    public void iniciarMenu() {
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

                    break;

                case 2:
                    System.out.println("Digite o titulo do Livro : ");
                    String tituloLivro = scanner.next();

                    System.out.println("Digite o nome do Autor : ");
                    String nomeAutor = scanner.next();

                    System.out.println("Digite o nome da Editora : ");
                    String nomeEditora = scanner.next();

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
                    System.out.println("Digite o CPF do usuário:");
                    String cpfPesquisadoHistorico = scanner.next();
                    Usuario userHistorico = biblioteca.pesquisarUsuarioCpf(cpfPesquisadoHistorico);
                    userHistorico.listarHistoricoEmprestimos();
                    break;
                case 6:
                    // Sistema de busca por Chave para escolher o usuário e poder realizar o
                    // empréstimo no nome dele
                    System.out.println("Digite o  título do livro que deseja pegar emprestado : ");
                    String tituloPesquisa = scanner.next();

                    Livro livroPesquisado = biblioteca.pesquisarLivroTitulo(tituloPesquisa);
                    System.out.println("Digite o CPF do usuário:");

                    String cpfPesquisadoEmprestimo = scanner.next();
                    Usuario userEmprestimo = biblioteca.pesquisarUsuarioCpf(cpfPesquisadoEmprestimo);

                    if (userEmprestimo == null) {
                        System.out.println("User Null");
                        break;
                    }
                    if (livroPesquisado == null) {
                        System.out.println("Livro Null");
                        break;
                    }

                    Emprestimo emprestimo = new Emprestimo(biblioteca, userEmprestimo, livroPesquisado);
                    emprestimo.realizarEmprestimo();
                    break;

                case 7:
                    // Sistema de busca por Chave para escolher o usuário e poder realizar a
                    // devolução no nome dele
                    System.out.println("Digite o  título do livro que deseja pegar emprestado : ");
                    String tituloPesquisaDevolucao = scanner.next();
                    Livro livroPesquisadoDevolucao = biblioteca.pesquisarLivroTitulo(tituloPesquisaDevolucao);
                    System.out.println("Digite o CPF do usuário:");
                    String cpfPesquisadoDevolucao = scanner.next();
                    Usuario userDevolucao = biblioteca.pesquisarUsuarioCpf(cpfPesquisadoDevolucao);
                    if (userDevolucao == null) {
                        return;
                    }
                    if (livroPesquisadoDevolucao == null) {
                        return;
                    }
                    Emprestimo devolucao = new Emprestimo(biblioteca, userDevolucao, livroPesquisadoDevolucao);
                    devolucao.devolverEmprestimo();
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
