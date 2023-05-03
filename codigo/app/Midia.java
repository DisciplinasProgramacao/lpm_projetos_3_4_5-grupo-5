package codigo.app;

/**
 * Midia: tem nome, gênero, idioma e audiência. Classe abstrata que recebe por parâmetro os valores necessários da classe filha.
 */
public abstract class Midia {
    //#region atributos
    private static final String[] GENEROS = {"aventura", "drama", "comedia", "romance", "terror"};
    private String nome;
    private String genero;
    private String idioma;
    private int audiencia;
    private String dataDeLancamento;
    //#endregion

    //#region construtores

    /**
     * Inicializador para os construtores da mídia.
     *
     * @param nome             Nome da midia
     * @param genero           Gênero da midia, que pode ser: aventura, drama, comedia, romance ou terror
     * @param idioma           Idioma da midia
     * @param audiencia        Audiência da midia
     * @param dataDeLancamento Data de lançamento da midia
     */
    private void init(String nome, String genero, String idioma, int audiencia, String dataDeLancamento) {
        this.nome = nome;
        this.genero = verificaGenero(genero) ? genero : "indefinido";
        this.idioma = idioma.isEmpty() ? "indefinido" : idioma;
        this.audiencia = audiencia > 0 ? audiencia : 0;
        this.dataDeLancamento = dataDeLancamento;
    }

    /**
     * Construtor de mídia sem audiencia.
     *
     * @param nome             Nome da midia
     * @param genero           Gênero da midia, que pode ser: aventura, drama, comedia, romance ou terror
     * @param idioma           Idioma da midia
     * @param dataDeLancamento Data de lançamento da midia
     */
    public Midia(String nome, String genero, String idioma, String dataDeLancamento) {
        init(nome, genero, idioma, 0, dataDeLancamento);
    }

    /**
     * Construtor de mídia com audiencia.
     *
     * @param nome             Nome da midia
     * @param genero           Gênero da midia, que pode ser: aventura, drama, comedia, romance ou terror
     * @param idioma           Idioma da midia
     * @param audiencia        Audiência da midia
     * @param dataDeLancamento Data de lançamento da midia
     */
    public Midia(String nome, String genero, String idioma, int audiencia, String dataDeLancamento) {
        init(nome, genero, idioma, audiencia, dataDeLancamento);
    }

    //#endregion

    //#region metodos da classe

    /**
     * verifica se genero existe
     *
     * @param genero Genero
     */
    private boolean verificaGenero(String genero) {
        for (String g : GENEROS) {
            if (genero.equals(g)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Aumenta audiencia em +1 para cada vez que o cliente assiste
     */
    public void registrarAudiencia() {
        this.audiencia++;
    }

    @Override
    /**
     * Retorna caracteristicas da midia. Formato:
     * <nome> (<dataDeLancamento>) - <genero>, <idioma>, <audiencia> visualizacoes - <duracao>
     * @return String no formato indicado.
     */
    public String toString() {
        return this.nome + " (" + this.dataDeLancamento + ") - " + this.genero + ", " + this.idioma + ", " + this.audiencia + " visualizações";
    }

    public String getNome() {
        return this.nome;
    }

    public String getGenero() {
        return this.genero;
    }

    public String getIdioma() {
        return this.idioma;
    }

    public int getAudiencia() {
        return this.audiencia;
    }

    //#endregion
}
