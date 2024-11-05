import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AppTinder {
    private List<Usuario> usuarios;
    private List<Matchs> matchs;

    public AppTinder() {
        usuarios = new ArrayList<>();
        matchs = new ArrayList<>();
        FileLoader.carregarUsuariosDoArquivo("usuarios.txt", usuarios); // Carregar os usuários do arquivo de texto
    }

    public void iniciar(Usuario usuarioAtual, Scanner scanner) {
        boolean exibindoPerfis = true;
        while (exibindoPerfis) {
            exibindoPerfis = UsuarioActions.exibirProximoPerfil(usuarioAtual, usuarios, matchs, scanner);
        }
    }

    public void exibirMatchs(Usuario usuarioAtual, Scanner scanner) {
        UsuarioActions.exibirMatchs(usuarioAtual, matchs, scanner);
    }

    public void exibirConversa(Usuario usuarioAtual, Scanner scanner) {
        ConversasActions.exibirConversa(usuarioAtual, matchs, scanner);
    }

    public Usuario cadastrarUsuario(Scanner scanner) {
        Usuario usuario = UsuarioActions.cadastrarUsuario(scanner);
        usuarios.add(usuario);
        return usuario;
    }

    public void exibirPerfil(Usuario usuarioAtual) {
        UsuarioActions.exibirPerfil(usuarioAtual);
    }
}
