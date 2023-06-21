package codigo.app;

import java.util.HashSet;
import java.util.Random;

/**
 * Midia: tem nome, gênero, idioma e audiência. Classe abstrata que recebe por parâmetro os valores necessários da classe filha.
 */
public abstract class Midia implements ISalvar {
    //#region atributos
    private int id;
    private String nome;
    private Genero genero;
    private Idioma idioma;
    private int audiencia;
    private String dataDeLancamento;
    private EstadoMidia estadoMidia;
    private HashSet<Avaliacao> avaliacoes;
    private double media;

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
     * @param estadoMidia      Se é Lançamento
     */
    private void init(int id, String nome, Genero genero, Idioma idioma, String dataDeLancamento, EstadoMidia estadoMidia) {

        Random random = new Random();

        this.id = id;
        this.nome = nome;
        this.genero = genero != null ? genero : Genero.values()[random.nextInt(Genero.values().length)];
        this.idioma = idioma != null ? idioma : Idioma.values()[random.nextInt(Idioma.values().length)];
        this.audiencia = 0;
        this.dataDeLancamento = dataDeLancamento;
        this.estadoMidia = estadoMidia;
        avaliacoes = new HashSet<>();
    }

    /**
     * Construtor de mídia.
     *
     * @param nome             Nome da midia
     * @param genero           Gênero da midia, que pode ser: aventura, drama, comedia, romance ou terror
     * @param idioma           Idioma da midia
     * @param dataDeLancamento Data de lançamento da midia
     * @param estadoMidia      Se é Lançamento
     */
    public Midia(int id, String nome, Genero genero, Idioma idioma, String dataDeLancamento, EstadoMidia estadoMidia) {
        init(id, nome, genero, idioma, dataDeLancamento, estadoMidia);
    }

    //#endregion

    //#region metodos da classe

    /**
     * Aumenta audiencia em +1 para cada vez que o cliente assiste
     */
    public void registrarAudiencia() {
        this.audiencia++;
    }

    @Override
    public String toString() {
        return definirTipoMidia() + ";" + this.id + ";" + this.nome + ";" + this.dataDeLancamento + ";" + this.genero.getNome() + ";" + this.idioma.getNome() + ";" + this.estadoMidia.getNome() ;
    }

    public abstract String definirTipoMidia();

    public String getNome() {
        return this.nome;
    }

    public Genero getGenero() {
        return this.genero;
    }

    public Idioma getIdioma() {
        return this.idioma;
    }

    public int getAudiencia() {
        return this.audiencia;
    }


    public boolean filtrarPorGenero(Genero genero) {
        return this.genero.equals(genero);
    }

    public boolean filtrarPorIdioma(Idioma idioma) {
        return this.idioma.equals(idioma);
    }

    /**
     * Adiciona avaliação às avaliações da mídia
     *
     * @param avaliacao Nota da mídia
     */
    public void addAvaliacao(Avaliacao avaliacao) {

        String nomeUsuario = avaliacao.getNomeDeUsuario();

        try {
            for (Avaliacao a : this.avaliacoes) {
                if (a.getNomeDeUsuario().equals(nomeUsuario)) {
                    throw new Exception("Mídia já avaliada!");
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            return;
        }

        this.avaliacoes.add(avaliacao);
        this.media = this.avaliacoes.stream().mapToDouble(Avaliacao::getAvaliacao).average().orElse(0);
    }

    public int getId() {
        return this.id;
    }

    public HashSet<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public double getMedia() {
        return media;
    }

    public EstadoMidia getEstadoMidia() {
        return estadoMidia;
    }


    //#endregion
}
