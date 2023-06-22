package codigo.app;

public class Avaliacao {

    private String login;
    private int avaliacao;
    private String comentario;

    /**
     * Adiciona avaliacao para clientes especialistas
     *
     * @param login
     * @param avaliacao
     * @param comentario
     */
    public Avaliacao(String login, int avaliacao, String comentario) throws Exception {
        if (validaAvaliacao(avaliacao)) {
            this.login = login;
            this.avaliacao = avaliacao;
            this.comentario = comentario;
        }
    }

    /**
     * Verifica se a avaliacao esta entre 1 e 5
     * @param avaliacao
     * @return
     */
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


    public String getLogin() {
        return login;
    }

    public int getAvaliacao() {
        return this.avaliacao;
    }

}
