package dominio;

import java.time.LocalDate;

public class Emprestimo {
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;

    public Emprestimo() {
        this.dataEmprestimo = LocalDate.now();
        this.dataDevolucao = dataEmprestimo.plusDays(7);
    }   

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void realizarEmprestimo(Usuario usuarioAssociado, Livro livroAssociado){
        /*
        1 - Verificar disponibilidade do livroAssociado e do cadastro do Usuário
        2 - Passar isEmprestado para True para o livroAssociado
        3 - Adicionar esse empréstimo ao histórico de empréstimo do usuário
        */

    }

}