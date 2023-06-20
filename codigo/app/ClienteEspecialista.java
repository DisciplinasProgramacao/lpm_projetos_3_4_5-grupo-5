package codigo.app;

public class ClienteEspecialista implements IClienteState {

    public  void addAvaliacao( String nomeDeUsuario, Midia midia, int nota, String comentario) throws Exception {
        Avaliacao avaliacao = new Avaliacao( nomeDeUsuario, nota, " ");
        midia.addAvaliacao(avaliacao);
    }

//    public ClienteEspecialista(String nomeDeUsuario, String login, String senha, List<Midia> listaParaVer, List<Midia> listaJaVistas, Map<Integer, LocalDate> dataQueFoiVista, boolean logado) {
//        super(nomeDeUsuario, login, senha);
//        this.listaParaVer = listaParaVer;
//        this.dataQueFoiVista = dataQueFoiVista;
//        this.listaJaVistas = listaJaVistas;
//        this.logado = logado;
//    }
//
//    /**
//     * Caso o cliente tenha sido um cliente especialista, mas não é mais, um objeto clienteRegular será criado n e o cliente terá acesso apenas a avaliações do cleitne regular
//     *
//     * @return
//     */
//    public ClienteRegular downgradeParaRegular() {
//        if (!isEspecialista()) {
//            return new ClienteRegular(nomeDeUsuario, login, senha, listaParaVer, listaJaVistas, dataQueFoiVista, logado);
//        } else {
//            throw new RuntimeException("O ClienteEspecialista ainda cumpre os critérios para ser um ClienteEspecialista.");
//        }
//    }


//    public void addAvaliacao(Midia midia, int nota, String comentario) {
//
//        Avaliacao avaliacao = null;
//        try {
//            avaliacao = new Avaliacao(this.nomeDeUsuario, nota, comentario);
//
//            if(avaliacao.getNomeDeUsuario() == null) return;
//
//            int index = listaJaVistas.indexOf(midia);
//
//            try {
//                if (index != -1)
//                    midia.addAvaliacao(avaliacao);
//                else
//                    throw new Exception("Não foi possível avaliar pois a mídia não foi assistida!");
//            } catch (Exception e) {
//                System.out.println(e.toString());
//            }
//
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        // comentar com 2 (se cair no catch) e interface com 3 caso o cast dê certo
//
//    }
}
