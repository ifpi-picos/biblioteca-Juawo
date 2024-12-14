package dominio;
import java.time.LocalDate;

public class Emprestimo {
    private LocalDate dt_emprestimo;
    private LocalDate dt_devolucao;
    
    public Emprestimo(LocalDate dt_emprestimo, LocalDate dt_devolucao) {
        this.dt_emprestimo = dt_emprestimo;
        this.dt_devolucao = dt_devolucao;
        System.out.println("AAAA" + dt_emprestimo);
    }   
    
    public LocalDate getDt_emprestimo() {
        return dt_emprestimo;
    }
    public void setDt_emprestimo(LocalDate dt_emprestimo) {
        this.dt_emprestimo = dt_emprestimo;
    }
    public LocalDate getDt_devolucao() {
        return dt_devolucao;
    }
    public void setDt_devolucao(LocalDate dt_devolucao) {
        this.dt_devolucao = dt_devolucao;
    }


}