import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String nome;
    private int idade;
    private String bio;
    private String genero;
    private String sexualidade;
    private List<String> respostas;
    private List<Usuario> curtidos;
    private List<Usuario> descurtidos;

    // Construtor completo com bio, gênero e sexualidade
    public Usuario(String nome, int idade, String bio, String genero, String sexualidade, List<String> respostas) {
        this.nome = nome;
        this.idade = idade;
        this.bio = bio;
        this.genero = genero;
        this.sexualidade = sexualidade;
        this.respostas = respostas;
        this.curtidos = new ArrayList<>();
        this.descurtidos = new ArrayList<>();
    }

    // Construtor sem bio, gênero e sexualidade (caso deseje uma versão simplificada)
    public Usuario(String nome, int idade, List<String> respostas) {
        this(nome, idade, null, null, null, respostas);
    }

    // Getters e outros métodos
    public String getNome() { return nome; }
    public int getIdade() { return idade; }
    public String getBio() { return bio; }
    public String getGenero() { return genero; }
    public String getSexualidade() { return sexualidade; }
    public List<String> getRespostas() { return respostas; }
    public List<Usuario> getCurtidos() { return curtidos; }
    public List<Usuario> getDescurtidos() { return descurtidos; }

    public void curtirUsuario(Usuario usuario) { curtidos.add(usuario); }
    public void descurtirUsuario(Usuario usuario) { descurtidos.add(usuario); }
}
