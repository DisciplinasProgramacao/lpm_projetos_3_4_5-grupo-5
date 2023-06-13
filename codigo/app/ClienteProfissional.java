package codigo.app;

public class ClienteProfissional extends Cliente {

    public ClienteProfissional(String nomeDeUsuario,String login,String senha){
        super(nomeDeUsuario,login,senha);
    }

    /**
     * Como o cliente profissional nunca sera especialista, o metodo sobrescreve ao da classe cliente
     * @return falso
     */
    @Override
    public boolean isEspecialista() {
        return false;
    }

    @Override
    public void addAvaliacao(Midia midia, int nota) throws Exception {

    }
}
