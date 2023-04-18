package app;

public class Serie {
    private static final String[] GENEROS = {"comedia", "romance", "terror"};
    private String nome;
    private String genero;
    private String idioma;
    private int qtdEpisodios;
    private int audiencia;

    //getters
    public String getNome() {
        return nome;
    }
    public String getGenero() {
        return genero;
    }
    public String getIdioma() {
        return idioma;
    }
    public int getQtdEpisodios() {
        return qtdEpisodios;
    }
    public int getAudiencia() {
        return audiencia;
    }
    public void setAudiencia(int audiencia) {
        this.audiencia=audiencia;
    }


    /**
     * constructor
     * @param nome
     * @param genero
     * @param idioma
     * @param qtdEpisodios
     * @param audiencia
     */
    public Serie(String nome, String genero, String idioma, int qtdEpisodios) {
        verificaGenero(genero);
        this.nome = nome;
        this.genero = genero;
        this.idioma = idioma;
        this.qtdEpisodios = qtdEpisodios;
        this.audiencia = 0;
    }

    /**
     * verifica se genero existe
     * @param genero
     */
    private void verificaGenero(String genero) {
        for(String g: GENEROS){
            if(genero.equals(g)){
                this.genero = genero;
            }
        }
    }


    /**
     * aumenta audiencia em +1 para cada vez que o cliente assiste
     */
    public void registrarAudiencia(){
        this.audiencia++;
    }
}
