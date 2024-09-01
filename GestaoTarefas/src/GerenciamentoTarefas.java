import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GerenciamentoTarefas {
    static List<Tarefa> tarefas = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void menu(){
        boolean sair = false;
        while(!sair){
            System.out.println();
            System.out.print("Escolha uma opção: ");
            int escolha = scanner.nextInt();
            switch(escolha){
                case 1:
                    adicionarTarefa();
                    break;
                case 2:
                    listarTarefas();
                    break;
                case 3:
                    System.out.print("Digite o código da tarefa: ");
                    excluirTarefa(scanner.nextInt() - 1);
                    break;
                case 4:
                    System.out.println("Saindo do sistema...");
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        }
    }

    public static void adicionarTarefa(){
        Tarefa tarefa = new Tarefa();

        scanner.nextLine();
        System.out.print("Digite o título da tarefa: ");
        String titulo = scanner.nextLine();

        while (titulo.length() > 50){
            System.out.print("Título com mais de 50 caracteres. Digite novamente: ");
            titulo = scanner.nextLine();
        }
        tarefa.setTitulo(titulo);

        System.out.print("Digite a descrição da tarefa: ");
        tarefa.setDescricao(scanner.nextLine());

        boolean dataValida = false;
        while (!dataValida) {
            System.out.print("Digite a data de vencimento (formato: YYYY-MM-DD): ");
            String data = scanner.next();
            LocalDate dataConvertida = converterData(data);

            try{
                if(dataConvertida.isAfter(LocalDate.now())){
                    dataValida = true;
                }else{
                    System.out.println("Data inválida. Digite uma data futura.");
                }
            }catch (DateTimeParseException e) {
                System.out.println("Formato de data inválido. Por favor, use o formato yyyy-MM-dd.");
            }
            tarefa.setDataVencimento(dataConvertida);
        }

        boolean statusValido = false;
        System.out.print("Digite o status da tarefa (Pendente, Em andamento, Concluída): ");
        scanner.nextLine();
        while (!statusValido) {
            String statusString = scanner.nextLine();
            try {
               tarefa.setStatus(Status.fromDescricao(statusString));
                statusValido = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Status inválido. Por favor, insira um status (Pendente, Em andamento, Concluída).");
            }
        }

        tarefas.add(tarefa);
        System.out.println("\nTarefa adicionada com sucesso!");

    }

    public static LocalDate converterData(String dataVencimento){
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        return LocalDate.parse(dataVencimento, formatador);
    }
    public static void listarTarefas(){
        System.out.println("Lista de tarefas: ");
        for(Tarefa tarefa : tarefas){
            System.out.println((tarefas.indexOf(tarefa) + 1) + ": " + tarefa);
        }
    }
    public static void excluirTarefa(int codigo){

        while(codigo >= tarefas.size() || codigo <= 0){
            System.out.println("Tarefa não existe. Digite novamente");
            codigo = scanner.nextInt() - 1;
        }
        tarefas.remove(codigo);
        System.out.println("Tarefa excluída com sucesso!");
    }
}
