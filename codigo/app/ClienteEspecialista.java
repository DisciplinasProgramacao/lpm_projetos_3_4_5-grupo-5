package codigo.app;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class ClienteEspecialista extends Cliente {
    public ClienteEspecialista(String nomeDeUsuario, String login, String senha, List<Midia> listaParaVer, List<Midia> listaJaVistas, Map<Integer, LocalDate> dataQueFoiVista, boolean logado) {
        super(nomeDeUsuario, login, senha);
        this.listaParaVer = listaParaVer;
        this.dataQueFoiVista = dataQueFoiVista;
        this.listaJaVistas = listaJaVistas;
        this.logado = logado;
    }

    /**
     * Caso o cliente tenha sido um cliente especialista, mas não é mais, um objeto clienteRegular será criado n e o cliente terá acesso apenas a avaliações do cleitne regular
     *
     * @return
     */
    public ClienteRegular downgradeParaRegular() {
        if (!isEspecialista()) {
            return new ClienteRegular(nomeDeUsuario, login, senha, listaParaVer, listaJaVistas, dataQueFoiVista, logado);
        } else {
            throw new RuntimeException("O ClienteEspecialista ainda cumpre os critérios para ser um ClienteEspecialista.");
        }
    }

    @Override
    public void addAvaliacao(Midia midia, int nota) throws Exception {

    }
}
