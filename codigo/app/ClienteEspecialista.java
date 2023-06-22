package codigo.app;

import java.time.LocalDate;
import java.util.Map;

public class ClienteEspecialista implements IClienteState {

    /**
     * Cria e Adiciona uma avaliacao
     *
     * @param login
     * @param midia         Mídia a ser avaliada
     * @param nota          Avaliacao da mídia (número inteiro de 0 a 10)
     * @param comentario    Comentario que acompanha a avaliação
     * @throws Exception
     */
    public void addAvaliacao(String login, Midia midia, int nota, String comentario) throws Exception {
        Avaliacao avaliacao = new Avaliacao(login, nota, comentario);
        if (avaliacao.getLogin() == null) return;

        try {
            if (midia.getEstadoMidia() != EstadoMidia.LANCAMENTO) midia.addAvaliacao(avaliacao);
            else throw new Exception("Não foi possível avaliar pois a mídia é lançamento e o usuário não é profissional!");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    @Override
    public String toString() {
        return "Especialista";
    }


}
