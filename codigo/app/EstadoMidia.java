package codigo.app;

public enum EstadoMidia {

    LANCAMENTO("Lançamento"),
    MODIFICAVEL("Modificável");

    private final String nome;

    EstadoMidia(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

}
