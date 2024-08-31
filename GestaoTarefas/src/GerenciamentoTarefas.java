import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GerenciamentoTarefas {
    List<Tarefa> tarefas = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    public void menu(int escolha){
        switch(escolha){
            case 1:
                adicionarTarefa();
                break;
            case 2:
                listarTarefas();
                break;
        }
    }

    public void adicionarTarefa(){
        Tarefa tarefa = new Tarefa();

        System.out.print("Digite o título da tarefa: ");
        String titulo = scanner.nextLine();
        System.out.println();

        while (titulo.length() > 50){
            System.out.print("Título com mais de 50 caracteres. Digite novamente: ");
            titulo = scanner.nextLine();
        }
        tarefa.setTitulo(titulo);

        System.out.print("Digite a descrição da tarefa: ");
        tarefa.setDescricao(scanner.nextLine());
        System.out.println();

        boolean dataValida = false;
        while (!dataValida) {
            System.out.print("Digite a data de vencimento (formato: YYYY-MM-DD): ");
            dataValida = tarefa.setDataVencimento(scanner.nextLine());
        }

        boolean statusValido = false;
        System.out.print("Digite o status da tarefa (Pendente, Em andamento, Concluída): ");
        while (!statusValido) {
            String statusString = scanner.nextLine();
            try {
                Status status = Status.valueOf(statusString);
                tarefa.setStatus(status);
                statusValido = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Status inválido. Por favor, insira um status (Pendente, Em andamento, Concluída).");
            }
        }

        tarefas.add(tarefa);

    }
    public void listarTarefas(){
        for(Tarefa tarefa : tarefas){
            System.out.println(tarefa);
        }
    }
}
