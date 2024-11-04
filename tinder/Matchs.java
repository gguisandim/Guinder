public class Matchs {
    private Usuario usuario1;
    private Usuario usuario2;
    private Conversas conversa;

    public Matchs(Usuario usuario1, Usuario usuario2) {
        this.usuario1 = usuario1;
        this.usuario2 = usuario2;
        this.conversa = new Conversas();
    }

    public boolean envuelveUsuarios(Usuario u1, Usuario u2) {
        return (usuario1 == u1 && usuario2 == u2) || (usuario1 == u2 && usuario2 == u1);
    }

    public Usuario getOutroUsuario(Usuario usuario) {
        return (usuario == usuario1) ? usuario2 : usuario1;
    }

    public Conversas getConversa() {
        return conversa;
    }
}
