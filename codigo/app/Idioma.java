package codigo.app;

public enum Idioma {
    INGLES("Ingles"),
    PORTUGUES("Portugues"),
    ESPANHOL("Espanhol"),
    RUSSO("Russo"),
    ITALIANO("Italiano"),
    FRANCES("Frances");

    private final String nome;

    Idioma(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}

