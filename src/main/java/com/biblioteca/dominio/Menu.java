package com.biblioteca.dominio;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.biblioteca.Dao.UsuarioDao;
import com.biblioteca.Dao.LivroDao;
import com.biblioteca.Dao.EmprestimoDao;

public class Menu {
    private Scanner scanner = new Scanner(System.in);
    private Connection connection;

    public Menu(Connection connection) {
        this.connection = connection;
    }

    public void menuText() {
        System.out.println("--- Sistema de Biblioteca ---");
        System.out.println(
                "\n 1 - Cadastrar Usuário \n 2 - Cadastrar Livro \n 3 - Listar todos os Livros \n 4 - Listar Livros Emprestados e Disponíveis \n 5 - Listar Histórico de Empréstimo de Usuário \n 6 - Realizar Empréstimo de Livro \n 7 - Devolver Livro Emprestado \n 0 - Sair");
    }

    public void cadastrarUsuario() throws SQLException {
        System.out.println("Digite o nome do usuário:");
        String nome = scanner.nextLine();

        System.out.println("Digite o CPF do usuário:");
        String cpf = scanner.nextLine();

        System.out.println("Digite o e-mail do usuário:");
        String email = scanner.nextLine();

        Usuario usuario = new Usuario(nome, cpf, email);
        UsuarioDao usuarioDao = new UsuarioDao(connection);
        usuarioDao.cadastrarUsuario(usuario);
    }

    public void cadastrarLivro() throws SQLException {
        System.out.println("Digite o titulo do Livro : ");
        String titulo = scanner.nextLine();

        System.out.println("Digite o nome do Autor : ");
        String nomeAutor = scanner.nextLine();

        System.out.println("Digite o nome da Editora : ");
        String nomeEditora = scanner.nextLine();

        System.out.println("Digite o Ano de Publicação : ");
        int anoPublicacao = scanner.nextInt();

        Livro novoLivro = new Livro(titulo, nomeAutor, nomeEditora, anoPublicacao);
        LivroDao livroDao = new LivroDao(connection);
        livroDao.cadastrarLivro(novoLivro);
    }

    public void listarLivros() throws SQLException {
        LivroDao livroDao = new LivroDao(connection);
        List<Livro> livros = livroDao.listarLivros();
        for (Livro livro : livros) {
            System.out.println("Título -- " + livro.getTitulo());
            System.out.println("    Autor -- " + livro.getAutor());
            System.out.println("    Editora -- " + livro.getEditora());
            System.out.println("    Ano -- " + livro.getAno());
            System.out.println(" \n");
        }
    }

    public void listarLivrosStatus() throws SQLException {
        LivroDao livroDao = new LivroDao(connection);
        List<Livro> livros = livroDao.listarLivroStatus();
        System.out.println("---- Livros Disponíveis ----");
        for (Livro livro : livros) {
            if (!livro.isEmprestado()) {
                System.out.println("Título -- " + livro.getTitulo());
                System.out.println("    Autor -- " + livro.getAutor());
                System.out.println("    Editora -- " + livro.getEditora());
                System.out.println("    Ano -- " + livro.getAno());
                System.out.println(" \n");
            }
        }
        System.out.println("---- Livros Emprestados ----");
        for (Livro livro : livros) {
            if (livro.isEmprestado()) {
                System.out.println("Título -- " + livro.getTitulo());
                System.out.println("    Autor -- " + livro.getAutor());
                System.out.println("    Editora -- " + livro.getEditora());
                System.out.println("    Ano -- " + livro.getAno());
                System.out.println(" \n");
                
            }
        }
    }

    public void listarHistoricoEmprestimos() throws SQLException {
        System.out.println("Digite o CPF do usuário:");
        String cpf = scanner.nextLine();
        UsuarioDao usuarioDao = new UsuarioDao(connection);
        Usuario usuario = usuarioDao.pesquisarUsuarioCpf(cpf);
        if (usuario != null) {
            printarHistoricoEmprestimos(usuario, usuarioDao.listarHistoricoEmprestimos(usuario));
        } else {
            System.out.println("Usuário não encontrado.");
        }
    }

    public void printarHistoricoEmprestimos(Usuario usuario, List<Emprestimo> emprestimos) throws SQLException {
        System.out.println(" -- Histórico de Empréstimos do usuário " + usuario.getNome());
        LivroDao livroDao = new LivroDao((connection));
        for (Emprestimo emprestimo : emprestimos) {
            System.out.println("Título do Livro Emprestado -- " + livroDao.pesquisarLivroId(emprestimo.getId_livro()).getTitulo());
            System.out.println("   - Data do Empréstimo -- " + emprestimo.getDataEmprestimo());
            System.out.println("   - Data de Devolução -- " + emprestimo.getDataDevolucao());
            System.out.println("   - Status Devolução -- " + emprestimo.getDevolvido());
            System.out.println(" \n");
        }
    }

    public void realizarEmprestimo() throws SQLException {
        System.out.println("Digite o título do livro que deseja pegar emprestado : ");
        String tituloPesquisa = scanner.nextLine();

        LivroDao livroDao = new LivroDao(connection);
        Livro livroPesquisado = livroDao.pesquisarLivroTitulo(tituloPesquisa);

        System.out.println("Digite o CPF do usuário:");
        String cpfPesquisadoEmprestimo = scanner.nextLine();
        UsuarioDao usuarioDao = new UsuarioDao(connection);
        Usuario userEmprestimo = usuarioDao.pesquisarUsuarioCpf(cpfPesquisadoEmprestimo);

        if (userEmprestimo == null) {
            System.out.println("Usuário não encontrado.");
            return;
        }
        if (livroPesquisado == null) {
            System.out.println("Livro não encontrado.");
            return;
        }

        Emprestimo emprestimo = new Emprestimo(null, userEmprestimo.getId_usuario(), livroPesquisado.getId_livro());
        EmprestimoDao emprestimoDao = new EmprestimoDao(connection);
        emprestimoDao.cadastrarEmprestimo(emprestimo);
    }

    public void devolverEmprestimo() throws SQLException {
        System.out.println("Digite o título do livro que deseja devolver : ");
        String tituloPesquisaDevolucao = scanner.nextLine();
        LivroDao livroDao = new LivroDao(connection);
        Livro livroPesquisadoDevolucao = livroDao.pesquisarLivroTitulo(tituloPesquisaDevolucao);

        System.out.println("Digite o CPF do usuário:");
        String cpfPesquisadoDevolucao = scanner.nextLine();
        UsuarioDao usuarioDao = new UsuarioDao(connection);
        Usuario userDevolucao = usuarioDao.pesquisarUsuarioCpf(cpfPesquisadoDevolucao);

        if (userDevolucao == null) {
            System.out.println("Usuário não encontrado.");
            return;
        }
        if (livroPesquisadoDevolucao == null) {
            System.out.println("Livro não encontrado.");
            return;
        }

        EmprestimoDao emprestimoDao = new EmprestimoDao(connection);
        Emprestimo emprestimo = emprestimoDao.buscarEmprestimoPorUsuarioELivro(userDevolucao.getId_usuario(), livroPesquisadoDevolucao.getId_livro());
        if (emprestimo != null) {
            emprestimoDao.devolverEmprestimo(emprestimo);
        } else {
            System.out.println("Empréstimo não encontrado.");
        }
    }

    public void iniciarMenu() throws SQLException {
        boolean isLoop = true;

        while (isLoop) {
            menuText();
            System.out.println("Escolha uma opção : ");
            int opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                case 1:
                    cadastrarUsuario();
                    break;
                case 2:
                    cadastrarLivro();
                    break;
                case 3:
                    listarLivros();
                    break;
                case 4:
                    listarLivrosStatus();
                    break;
                case 5:
                    listarHistoricoEmprestimos();
                    break;
                case 6:
                    realizarEmprestimo();
                    break;
                case 7:
                    devolverEmprestimo();
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
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Erro ao fechar a conexão com o banco de dados: " + e.getMessage());
        }
    }
}