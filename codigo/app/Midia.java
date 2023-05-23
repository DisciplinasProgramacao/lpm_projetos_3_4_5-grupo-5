package codigo.app;

import java.util.ArrayList;
import java.util.List;

/**
 * Midia: tem nome, gênero, idioma e audiência. Classe abstrata que recebe por parâmetro os valores necessários da classe filha.
 */
public abstract class Midia implements ISalvar{
    //#region atributos
    private static final String[] GENEROS = {"aventura", "drama", "comedia", "romance", "terror"};
    private int id;
    private String nome;
    private String genero;
    private String idioma;
    private int audiencia;
    private String dataDeLancamento;
    private List<Avaliacao> avaliacoes;
    private double nota;
    private String tipoMidia;

    //#endregion

    //#region construtores

    /**
     * Inicializador para os construtores da mídia.
     *
     * @param id               Id da mídia
     * @param nome             Nome da midia
     * @param genero           Gênero da midia, que pode ser: aventura, drama, comedia, romance ou terror
     * @param idioma           Idioma da midia
     * @param dataDeLancamento Data de lançamento da midia
     */
    private void init(int id, String nome, String genero, String idioma, String dataDeLancamento) {
        this.id = id;
        this.nome = nome;
        this.genero = verificaGenero(genero) ? genero : "indefinido";
        this.idioma = idioma.isEmpty() ? "indefinido" : idioma;
        this.audiencia = 0;
        this.dataDeLancamento = dataDeLancamento;
        avaliacoes = new ArrayList<Avaliacao>();
    }

    /**
     * Construtor de mídia sem audiencia.
     *
     * @param nome             Nome da midia
     * @param genero           Gênero da midia, que pode ser: aventura, drama, comedia, romance ou terror
     * @param idioma           Idioma da midia
     * @param dataDeLancamento Data de lançamento da midia
     */
    public Midia(int id, String nome, String genero, String idioma, String dataDeLancamento) {
        init(id, nome, genero, idioma, dataDeLancamento);
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
    public String toString() {
        return definirTipoMidia() + ";" + this.id + ";" + this.nome + ";" + this.genero + ";" + this.idioma + ";" + this.dataDeLancamento;
    }

    public abstract String definirTipoMidia();

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


    public boolean filtrarPorGenero(String genero){
        if(this.genero.equals(genero))
            return true;
        return false;
    }

    public boolean filtrarPorIdioma(String idioma){
        if(this.idioma.equals(idioma))
            return true;
        return false;
    }

    /**
     * Adiciona avaliação às avaliações da mídia
     *
     * @param avaliacao Nota da mídia
     */
    public void addAvaliacao(Avaliacao avaliacao) {
        this.avaliacoes.add(avaliacao);
        //this.nota = this.avaliacoes.stream().mapToDouble(f -> f).average().orElse(0);
    }

    public int getId() {
        return this.id;
    }

    //#endregion
}
