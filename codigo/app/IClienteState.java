package codigo.app;

import java.time.LocalDate;
import java.util.Map;

public interface IClienteState {
    /**
     * Adiciona avaliação à uma midia, contanto que ela já tenha sido assistida pelo cliente
     * @param midia Mídia a ser avaliada
     * @param nota  Avaliacao da mídia (número inteiro de 0 a 10)
     * @param comentario Comentario que acompanha a avaliação
     */
    void addAvaliacao(String nomeUsuario, Midia midia, int nota, String comentario)throws Exception;
    String toString();

    /**
     * Verifica se é um cliente especialista
     * Caso tenha assistido 5 ou mais midias mes passado, retornara true
     * sempre verifica o estado do cliente caso tenha se tornado especialista.
     */
    IClienteState verificarEstado(Map<Integer, LocalDate> dataQueFoiVista);
}
