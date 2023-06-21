package codigo.app;

public interface IClienteState {
    /**
     * Adiciona avaliação  uma midia, contanto que ela já tenha sido assistida pelo cliente
     * @param midia Mídia a ser avaliada
     * @param nota  Avaliacao da mídia (número inteiro de 0 a 10)
     * @param comentario Comentario que acompanha a avaliação
     */
    void addAvaliacao(String nomeUsuario, Midia midia, int nota, String comentario)throws Exception;
}