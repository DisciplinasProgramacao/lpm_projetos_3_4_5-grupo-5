package codigo.app;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class ClienteRegular implements IClienteState {

    public void addAvaliacao(String nomeDeUsuario, Midia midia, int nota, String comentario) throws Exception {
        Avaliacao avaliacao = new Avaliacao(nomeDeUsuario, nota, null);
        if (avaliacao.getNomeDeUsuario() == null) return;
        midia.addAvaliacao(avaliacao);
    }
}