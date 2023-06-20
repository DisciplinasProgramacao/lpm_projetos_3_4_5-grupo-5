package codigo.app;

public class ClienteProfissional implements IClienteState {

    public  void addAvaliacao(String nomeDeUsuario, Midia midia, int nota, String comentario) throws Exception {
        Avaliacao avaliacao = new Avaliacao( nomeDeUsuario, nota, " ");
        midia.addAvaliacao(avaliacao);
    }




    /**
     * Como o cliente profissional nunca sera especialista, o metodo sobrescreve ao da classe cliente
     * @return falso
     */


}
