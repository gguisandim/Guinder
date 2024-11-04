import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AppTinder {
    private List<Usuario> usuarios;
    private List<Matchs> matchs;

    public AppTinder() {
        usuarios = new ArrayList<>();
        matchs = new ArrayList<>();
        carregarUsuariosDoArquivo("usuarios.txt"); // Carregar os usuários do arquivo de texto
    }

    // Método para carregar usuários de um arquivo de texto
    private void carregarUsuariosDoArquivo(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                String nome = dados[0];
                int idade = Integer.parseInt(dados[1]);
                String bio = dados[2];
                String genero = dados[3];
                String sexualidade = dados[4];
                List<String> interesses = List.of(
                    dados[5], dados[6], dados[7], dados[8], dados[9], 
                    dados[10], dados[11], dados[12], dados[13], dados[14]
                );
    
                Usuario usuario = new Usuario(nome, idade, bio, genero, sexualidade, interesses);
                usuarios.add(usuario);
            }
            System.out.println("Usuários carregados com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao carregar usuários do arquivo: " + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Erro: Formato do arquivo inválido. Verifique se cada linha possui todos os campos necessários.");
        }
    }
    
    // Método para iniciar e exibir perfis para o usuário curtir ou não
    public void iniciar(Usuario usuarioAtual, Scanner scanner) {
        boolean encontrouPerfil = false;
    
        for (Usuario perfil : usuarios) {
            // Verifica se o perfil não é o do usuário atual e não foi curtido/descurtido
            if (perfil != usuarioAtual && 
                !usuarioAtual.getCurtidos().contains(perfil) && 
                !usuarioAtual.getDescurtidos().contains(perfil)) {
                
                // Aplica filtro de exibição com base no gênero e orientação sexual do usuário
                if (usuarioAtual.getGenero().equalsIgnoreCase("Feminino") && 
                    usuarioAtual.getSexualidade().equalsIgnoreCase("Heterossexual") && 
                    !perfil.getGenero().equalsIgnoreCase("Masculino")) {
                    continue; // Ignora o perfil se for mulher hetero e o perfil não for de um homem
                }
                
                if (usuarioAtual.getGenero().equalsIgnoreCase("Masculino") && 
                    usuarioAtual.getSexualidade().equalsIgnoreCase("Heterossexual") && 
                    !perfil.getGenero().equalsIgnoreCase("Feminino")) {
                    continue; // Ignora o perfil se for homem hetero e o perfil não for de uma mulher
                }
    
                // Exibe o perfil se passar nas condições de filtro
                encontrouPerfil = true;
                System.out.println("Exibindo perfil de " + perfil.getNome());
                System.out.println("Idade: " + perfil.getIdade());
                System.out.println("Bio: " + perfil.getBio());
                System.out.println("Gênero: " + perfil.getGenero());
                System.out.println("Orientação Sexual: " + perfil.getSexualidade());
                System.out.println("Interesses:");
    
                // Exibe os 10 interesses
                List<String> interesses = perfil.getRespostas();
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
    
                for (int i = 0; i < perguntas.length; i++) {
                    System.out.println(perguntas[i] + " " + interesses.get(i));
                }
    
                System.out.print("Você deseja curtir este perfil? (Sim/Não): ");
                String resposta = scanner.nextLine().trim();
    
                if (resposta.equalsIgnoreCase("Sim")) {
                    usuarioAtual.curtirUsuario(perfil);
                    if (perfil.getCurtidos().contains(usuarioAtual)) {
                        Matchs novoMatch = new Matchs(usuarioAtual, perfil);
                        matchs.add(novoMatch);
                        System.out.println("É um match com " + perfil.getNome() + "!");
                    } else {
                        System.out.println("Você curtiu " + perfil.getNome() + ", aguardando para ver se ele(a) também curte você.");
                    }
                } else {
                    usuarioAtual.descurtirUsuario(perfil);
                    System.out.println("Você não curtiu " + perfil.getNome() + ".");
                }
                break; // Exibe um perfil por vez
            }
        }
    
        if (!encontrouPerfil) {
            System.out.println("Não há mais perfis para exibir no momento.");
        }
    }    
    
    // Método para exibir e enviar mensagens nas conversas
    public void exibirConversa(Usuario usuarioAtual, Scanner scanner) {
        System.out.print("Digite o nome do match para conversar: ");
        String nome = scanner.nextLine();
    
        Usuario destinatario = null;
        for (Matchs match : matchs) {
            if (match.envuelveUsuarios(usuarioAtual, match.getOutroUsuario(usuarioAtual)) &&
                match.getOutroUsuario(usuarioAtual).getNome().equalsIgnoreCase(nome)) {
                destinatario = match.getOutroUsuario(usuarioAtual);
                break;
            }
        }
    
        if (destinatario != null) {
            System.out.println("Conversando com " + destinatario.getNome());
            Conversas conversa = getConversa(usuarioAtual, destinatario);
            conversa.exibirConversas();
            System.out.print("Digite sua mensagem: ");
            String mensagem = scanner.nextLine();
            conversa.enviarMensagem(usuarioAtual, mensagem);
        } else {
            System.out.println("Nenhum match encontrado com o nome " + nome);
        }
    }
    
    // Método para exibir a lista de matchs do usuário
    public void exibirMatchs(Usuario usuarioAtual) {
        System.out.println("Seus Matchs:");
        for (Matchs match : matchs) {
            if (match.envuelveUsuarios(usuarioAtual, match.getOutroUsuario(usuarioAtual))) {
                Usuario outroUsuario = match.getOutroUsuario(usuarioAtual);
                System.out.println("Match com: " + outroUsuario.getNome());
            }
        }
    }

    // Método para cadastrar o usuário
    public Usuario cadastrarUsuario(Scanner scanner) {
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
    
        Usuario usuario = new Usuario(nome, idade, bio, genero, sexualidade, respostas);
        usuarios.add(usuario); // Adiciona o novo usuário à lista de usuários
        return usuario;
    }
    

    // Método para exibir o perfil do usuário
    public void exibirPerfil(Usuario usuarioAtual) {
        System.out.println("Seu Perfil:");
        System.out.println("Nome: " + usuarioAtual.getNome());
        System.out.println("Idade: " + usuarioAtual.getIdade());
        System.out.println("Gênero: " + usuarioAtual.getGenero());
        System.out.println("Orientação Sexual: " + usuarioAtual.getSexualidade());
        System.out.println("Interesses: " + usuarioAtual.getRespostas());
    }

    // Método auxiliar para obter a conversa entre dois usuários
    private Conversas getConversa(Usuario u1, Usuario u2) {
        for (Matchs match : matchs) {
            if (match.envuelveUsuarios(u1, u2)) {
                return match.getConversa();
            }
        }
        return null;
    }
}
