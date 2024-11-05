import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class AcoesUsuario {
    public static Usuario cadastrarUsuario(Scanner scanner) {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Idade: ");
        int idade = scanner.nextInt();
        scanner.nextLine(); // Limpa o buffer
    
        System.out.print("Bio: ");
        String bio = scanner.nextLine();
    
        System.out.print("Gênero: ");
        String genero = scanner.nextLine();
    
        System.out.print("Orientação Sexual: ");
        String sexualidade = scanner.nextLine();
    
        List<String> respostas = new ArrayList<>();
        String[] perguntas = {
            "Você gosta de viajar?",
            "Você gosta de cozinhar?",
            "Você gosta de esportes?",
            "Você gosta de filmes?",
            "Você gosta de música?",
            "Você gosta de ler?",
            "Você gosta de animais?",
            "Você gosta de dançar?",
            "Você gosta de atividades ao ar livre?",
            "Você gosta de tecnologia?"
        };
    
        System.out.println("Responda com 'Sim' ou 'Não':");
        for (String pergunta : perguntas) {
            System.out.print(pergunta + " ");
            respostas.add(scanner.nextLine().trim());
        }
    
        return new Usuario(nome, idade, bio, genero, sexualidade, respostas);
    }

    public static boolean exibirProximoPerfil(Usuario usuarioAtual, List<Usuario> usuarios, List<Matchs> matchs, Scanner scanner) {
        Random random = new Random();
        for (Usuario perfil : usuarios) {
            if (perfil != usuarioAtual && !usuarioAtual.getCurtidos().contains(perfil) &&
                    !usuarioAtual.getDescurtidos().contains(perfil)) {

                System.out.println("Exibindo perfil de " + perfil.getNome() + "...");
                // Exibir interesses, bio, etc...

                System.out.print("Você deseja curtir este perfil? (Sim/Não/Sair): ");
                String resposta = scanner.nextLine().trim();

                if (resposta.equalsIgnoreCase("Sair")) {
                    return false;
                } else if (resposta.equalsIgnoreCase("Sim")) {
                    usuarioAtual.curtirUsuario(perfil);
                    if (random.nextInt(3) + 1 == 1) {
                        matchs.add(new Matchs(usuarioAtual, perfil));
                        System.out.println("É um match com " + perfil.getNome() + "!");
                    } else {
                        System.out.println("Você curtiu " + perfil.getNome() + ".");
                    }
                } else {
                    usuarioAtual.descurtirUsuario(perfil);
                    System.out.println("Você não curtiu " + perfil.getNome() + ".");
                }
                return true; // Continua exibindo perfis
            }
        }
        System.out.println("Não há mais perfis para exibir.");
        return false; // Para a exibição
    }

    public static void exibirMatchs(Usuario usuarioAtual, List<Matchs> matchs, Scanner scanner) {
        System.out.println("Seus Matchs:");
        List<Matchs> matchesDoUsuario = new ArrayList<>();
        for (Matchs match : matchs) {
            if (match.envolveUsuario(usuarioAtual)) {
                matchesDoUsuario.add(match);
                Usuario outroUsuario = match.getOutroUsuario(usuarioAtual);
                System.out.println("Match com: " + outroUsuario.getNome());
            }
        }
        
        // Adicione as opções para visualizar ou excluir matches...
    }

    public static void exibirPerfil(Usuario usuarioAtual) {
        System.out.println("Seu Perfil:");
        System.out.println("Nome: " + usuarioAtual.getNome());
        System.out.println("Idade: " + usuarioAtual.getIdade());
        System.out.println("Gênero: " + usuarioAtual.getGenero());
        System.out.println("Orientação Sexual: " + usuarioAtual.getSexualidade());
        System.out.println("Interesses: " + usuarioAtual.getRespostas());
    }
}
