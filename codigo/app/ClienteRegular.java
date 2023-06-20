package codigo.app;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class ClienteRegular implements IClienteState{

    public  void addAvaliacao(String nomeDeUsuario, Midia midia, int nota, String comentario) throws Exception {
        Avaliacao avaliacao = new Avaliacao( nomeDeUsuario, nota, " ");
        midia.addAvaliacao(avaliacao);
    }
//    public ClienteRegular(String nomeDeUsuario,String login,String senha){
//        super(nomeDeUsuario,login,senha);
//    }
//
//
//
//
//
//    /**
//     * Foi feita a sobrecarga para possibilitar a clonagem dos objetos, a fim de fazer  upgrade de regular para especialista
//     * @param nomeDeUsuario
//     * @param login
//     * @param senha
//     * @param listaParaVer
//     * @param listaJaVistas
//     * @param dataQueFoiVista
//     * @param logado
//     */
//    public ClienteRegular(String nomeDeUsuario, String login, String senha, List<Midia> listaParaVer, List<Midia> listaJaVistas, Map<Integer, LocalDate> dataQueFoiVista, boolean logado){
//        super(nomeDeUsuario,login,senha);
//        this.listaParaVer = listaParaVer;
//        this.dataQueFoiVista = dataQueFoiVista;
//        this.listaJaVistas = listaJaVistas;
//        this.logado = logado;
//    }
//
//
//
//
//    /**
//  * O cliente regular é o unico que não pode adicionar comentarios. O comentario do cliente regular vai vazio para  a avaliacao
//  * @param midia Mídia a ser avaliada
//  * @param nota  Avaliacao da mídia (número inteiro de 0 a 10)
//  */
//
// public  void addAvaliacao(Midia midia, int nota, String comentario) {
//
//     Avaliacao avaliacao = null;
//     try {
//         avaliacao = new Avaliacao(this.nomeDeUsuario, nota, " ");
//
//         if(avaliacao.getNomeDeUsuario() == null) return;
//
//         int index = listaJaVistas.indexOf(midia);
//
//         try {
//             if (index != -1)
//                 midia.addAvaliacao(avaliacao);
//             else
//                 throw new Exception("Não foi possível avaliar pois a mídia não foi assistida!");
//         } catch (Exception e) {
//             System.out.println(e.toString());
//         }
//
//     } catch (Exception e) {
//         throw new RuntimeException(e);
//     }
//     // comentar com 2 (se cair no catch) e interface com 3 caso o cast dê certo
//
// }
//    /**
//     * Verifica se o usuario é cliente especialista, se sim, um cliente especialista é criado e o cliente passa a ter acesso de especialista
//     */
////    public ClienteEspecialista upgradeParaEspecialista() {
////        if (isEspecialista()) {
////            return new ClienteEspecialista(nomeDeUsuario, login, senha, listaParaVer, listaJaVistas,  dataQueFoiVista,logado);
////        } else {
////            throw new RuntimeException("O ClienteRegular não cumpre os critérios para se tornar um ClienteEspecialista.");
////        }
////    }
}

