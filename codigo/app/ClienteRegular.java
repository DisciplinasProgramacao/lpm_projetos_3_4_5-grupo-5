package codigo.app;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class ClienteRegular implements IClienteState {

    /**
     * Cria e Adiciona uma avaliacao
     * @param nomeDeUsuario
     * @param midia Mídia a ser avaliada
     * @param nota  Avaliacao da mídia (número inteiro de 0 a 10)
     * @param comentario Comentario que acompanha a avaliação
     * @throws Exception
     */
    public void addAvaliacao(String nomeDeUsuario, Midia midia, int nota, String comentario) throws Exception {
        Avaliacao avaliacao = new Avaliacao(nomeDeUsuario, nota, null);
        if (avaliacao.getNomeDeUsuario() == null) return;
        midia.addAvaliacao(avaliacao);
    }
}