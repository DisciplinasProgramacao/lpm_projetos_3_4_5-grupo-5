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
