package codigo.app;

import java.time.LocalDate;
import java.util.Map;

public class ClienteRegular implements IClienteState {

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
        Avaliacao avaliacao = new Avaliacao(login, nota, null);
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
        return "Regular";
    }

    @Override
    public IClienteState verificarEstado(Map<Integer, LocalDate> dataQueFoiVista) {
        // Pega a data atual e subtrai um mês para obter a data de um mês atrás
        LocalDate umMesAtras = LocalDate.now().minusMonths(1);


        long totalMidiasUltimoMes = dataQueFoiVista.values().stream()
                .filter(date -> date.getYear() == umMesAtras.getYear() && date.getMonthValue() == umMesAtras.getMonthValue())
                .count();

//        if (!(this.state instanceof ClienteProfissional) && totalMidiasUltimoMes < 5 && (this.state instanceof ClienteEspecialista)) {
//            this.state = new ClienteEspecialista();
//        }
        return null;
    }
}