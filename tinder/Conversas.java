import java.util.ArrayList;
import java.util.List;

public class Conversas {
    private List<String> mensagens;

    public Conversas() {
        mensagens = new ArrayList<>();
    }

    public void enviarMensagem(Usuario remetente, String mensagem) {
        mensagens.add(remetente.getNome() + ": " + mensagem);
    }

    public void exibirConversas() {
        for (String mensagem : mensagens) {
            System.out.println(mensagem);
        }
    }
}
