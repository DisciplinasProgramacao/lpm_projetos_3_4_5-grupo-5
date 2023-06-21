package codigo.app;

public class Avaliacao {

    private String nomeDeUsuario;
    private int avaliacao;
    private String comentario;

    /**
     * Adiciona avaliacao para clientes especialistas
     *
     * @param nomeDeUsuario
     * @param avaliacao
     * @param comentario
     */
    public Avaliacao(String nomeDeUsuario, int avaliacao, String comentario) throws Exception {
        if (validaAvaliacao(avaliacao)) {
            this.nomeDeUsuario = nomeDeUsuario;
            this.avaliacao = avaliacao;
            this.comentario = comentario;
        }
    }

    public boolean validaAvaliacao(int avaliacao) {
        try {
            if (avaliacao >= 1 && avaliacao <= 5)
                return true;
            else
                throw new Exception("Valor inválido. A avaliação deve ser um número inteiro entre 1 e 5.");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return false;
    }

    public String getNomeDeUsuario() {
        return this.nomeDeUsuario;
    }

    public int getAvaliacao() {
        return this.avaliacao;
    }

}
