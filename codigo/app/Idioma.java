package codigo.app;

public enum Idioma {
    INGLES("Inglês"),
    PORTUGUES("Português"),
    ESPANHOL("Espanhol"),
    RUSSO("Russo"),
    ITALIANO("Italiano"),
    FRANCES("Francês");

    private final String nome;

    Idioma(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}

