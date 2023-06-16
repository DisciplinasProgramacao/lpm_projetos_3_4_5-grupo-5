package codigo.app;

public enum Genero {
    AVENTURA("Aventura"),
    DRAMA("Drama"),
    COMEDIA("Comedia"),
    ROMANCE("Romance"),
    TERROR("Terror");

    private final String nome;

    Genero(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
