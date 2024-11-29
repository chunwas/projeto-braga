import java.util.Scanner;

public class Visao {
    private Scanner scanner;

    public Visao() {
        this.scanner = new Scanner(System.in);
    }

    public int menu() {
        System.out.println("\nMenu - Gerenciamento de Horta Comunitária:");
        System.out.println("1. Adicionar Parcela");
        System.out.println("2. Regar Parcela");
        System.out.println("3. Adubar Parcela");
        System.out.println("4. Marcar Tratamento Especial");
        System.out.println("5. Sugerir Ações");
        System.out.println("6. Gerar Relatório");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine(); 
        return opcao;
    }

    public String solicitarString(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine();
    }

    public int solicitarInt(String mensagem) {
        System.out.print(mensagem);
        while (!scanner.hasNextInt()) {
            System.out.println("Por favor, insira um número válido.");
            scanner.next(); 
        }
        int valor = scanner.nextInt();
        scanner.nextLine(); 
        return valor;
    }

    public void exibirMensagem(String mensagem) {
        System.out.println(mensagem);
    }
}