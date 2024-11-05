import java.util.List;

public class Matchs {
    private Usuario usuario1;
    private Usuario usuario2;

    public Matchs(Usuario usuario1, Usuario usuario2) {
        this.usuario1 = usuario1;
        this.usuario2 = usuario2;
    }

    // Retorna o outro usuário envolvido no match
    public Usuario getOutroUsuario(Usuario usuario) {
        return usuario.equals(usuario1) ? usuario2 : usuario1;
    }

    // Verifica se o match envolve um usuário específico
    public boolean envolveUsuario(Usuario usuario) {
        return usuario.equals(usuario1) || usuario.equals(usuario2);
    }

    // Exibe o perfil do outro usuário
    public void exibirPerfilDoOutroUsuario(Usuario usuario) {
        Usuario outroUsuario = getOutroUsuario(usuario);
        System.out.println("Perfil de " + outroUsuario.getNome() + ":");
        System.out.println("Idade: " + outroUsuario.getIdade());
        System.out.println("Bio: " + outroUsuario.getBio());
        System.out.println("Gênero: " + outroUsuario.getGenero());
        System.out.println("Orientação Sexual: " + outroUsuario.getSexualidade());
        System.out.println("Interesses: " + outroUsuario.getRespostas());
    }

    // Remove o match entre os usuários
    public void excluirMatch(List<Matchs> matchs) {
        usuario1.getCurtidos().remove(usuario2);
        usuario2.getCurtidos().remove(usuario1);
        matchs.remove(this);
        System.out.println("Match excluído com sucesso.");
    }
}
