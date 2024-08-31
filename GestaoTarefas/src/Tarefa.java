import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Tarefa {
    private String titulo;
    private String descricao;
    private LocalDate dataVencimento;
    private Status status;

    public Tarefa() {
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public boolean setDataVencimento(String dataVencimento) {
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try{
            this.dataVencimento = LocalDate.parse(dataVencimento, formatador);
            return true;
        }catch (DateTimeParseException e) {
            System.out.println("Formato de data inválido. Por favor, use o formato yyyy-MM-dd.");
            return false;
        }
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Tarefa{" +
                "titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", dataVencimento=" + dataVencimento +
                ", status=" + status +
                '}';
    }
}
