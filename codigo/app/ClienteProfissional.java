package codigo.app;

import java.time.LocalDate;
import java.util.Map;

public class ClienteProfissional implements IClienteState {


    /**
     * Cria e Adiciona uma avaliacao
     * @param login
     * @param midia Mídia a ser avaliada
     * @param nota  Avaliacao da mídia (número inteiro de 0 a 10)
     * @param comentario Comentario que acompanha a avaliação
     * @throws Exception
     */

    public  void addAvaliacao(String login, Midia midia, int nota, String comentario) throws Exception {
        Avaliacao avaliacao = new Avaliacao( login, nota, comentario);
        if(avaliacao.getLogin() == null) return;
        midia.addAvaliacao(avaliacao);
    }

    @Override
    public String toString() {
        return "Profissional";
    }



}
