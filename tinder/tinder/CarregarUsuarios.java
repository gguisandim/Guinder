import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class CarregarUsuarios {
    public static void carregarUsuariosDoArquivo(String filePath, List<Usuario> usuarios) {
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
}

