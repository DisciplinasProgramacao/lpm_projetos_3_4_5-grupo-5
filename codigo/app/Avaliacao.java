package codigo.app;

public class Avaliacao {

    private String nomeDeUsuario;
    private int avaliacao;

    /**
     * Adiciona avaliacao
     *
     * @param nomeDeUsuario
     * @param avaliacao
     */
    public Avaliacao(String nomeDeUsuario, int avaliacao) throws Exception {
        this.nomeDeUsuario = nomeDeUsuario;

        if (validaAvaliacao(avaliacao))
            this.avaliacao = avaliacao;

    }

    public boolean validaAvaliacao(int avaliacao) {
        try {
            if (avaliacao >= 0 && avaliacao <= 10)
                return true;
            else
                throw new Exception("Valor inválido. A avaliação deve ser um número inteiro entre 0 e 10.");
        } catch (Exception e) {
            System.out.println(e.toString());
            System.exit(1);
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
