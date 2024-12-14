package dominio;
import java.time.LocalDate;

public class Livro {
    private String autor;
    private String titulo;
    private String editora;
    private LocalDate ano;
    private boolean isEmprestado;
    
    public Livro(String autor, String titulo, String editora, LocalDate ano, boolean isEmprestado) {
        this.autor = autor;
        this.titulo = titulo;
        this.editora = editora;
        this.ano = ano;
        this.isEmprestado = isEmprestado;
    }
    
    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }
    
    public LocalDate getAno() {
        return ano;
    }

    public void setAno(LocalDate ano) {
        this.ano = ano;
    }

    public boolean isEmprestado() {
        return isEmprestado;
    }
    
    public void setEmprestado(boolean isEmprestado) {
        this.isEmprestado = isEmprestado;
    }

}