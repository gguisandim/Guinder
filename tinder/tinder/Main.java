import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Mova o scanner para antes do cadastro
        AppTinder app = new AppTinder();
        
        // Passa o scanner ao cadastrar o usuário
        Usuario usuarioAtual = app.cadastrarUsuario(scanner);

        int opcao = -1;

        do {
            System.out.println("\nEscolha uma opção:");
            System.out.println("1. Início (Ver perfis)");
            System.out.println("2. Matchs (Ver matchs)");
            System.out.println("3. Conversa (Conversa com matchs)");
            System.out.println("4. Perfil (Ver perfil)");
            System.out.println("0. Sair");

            // Lê a opção do usuário com tratamento de exceção
            try {
                System.out.print("Digite sua escolha: ");
                if (scanner.hasNextInt()) { // Verifica se há um próximo int
                    opcao = scanner.nextInt();
                    scanner.nextLine(); // Limpa o buffer após a leitura do número
                } else {
                    System.out.println("Entrada inválida. Por favor, insira um número.");
                    scanner.nextLine(); // Limpa a entrada inválida
                    opcao = -1; // Define a opção como inválida para continuar o loop
                    continue; // Reinicia o loop
                }

                switch (opcao) {
                    case 1:
                        app.iniciar(usuarioAtual, scanner); // Passa o scanner como argumento
                        break;
                    case 2:
                        app.exibirMatchs(usuarioAtual);
                        break;
                    case 3:
                        app.exibirConversa(usuarioAtual, scanner); // Passa o scanner como argumento
                        break;
                    case 4:
                        app.exibirPerfil(usuarioAtual);
                        break;
                    case 0:
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, insira um número.");
                scanner.nextLine(); // Limpa o buffer se a entrada não for um número
            }
        } while (opcao != 0);

        scanner.close(); // Fecha o scanner ao final do programa
    }
}
