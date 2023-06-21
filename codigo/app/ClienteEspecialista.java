package codigo.app;

public class ClienteEspecialista implements IClienteState {

    public  void addAvaliacao( String nomeDeUsuario, Midia midia, int nota, String comentario) throws Exception {
        Avaliacao avaliacao = new Avaliacao( nomeDeUsuario, nota, comentario);
        if(avaliacao.getNomeDeUsuario() == null) return;
        midia.addAvaliacao(avaliacao);
    }
}
