package codigo.app;

public class Filme extends Midia {

    //#region atributos
    private long duracaoSeg;
    //#endregion

    //#region construtores
    private void init(int duracaoMin) {
        this.duracaoSeg = duracaoMin > 0 ? converteDuracaoSeg(duracaoMin) : 0;
    }

    public Filme(String nome, String genero, String idioma, String DataDeLancamento, int duracaoMin) {
        super(nome, genero, idioma, DataDeLancamento);
        init(duracaoMin);
    }

    public Filme(String nome, String genero, String idioma, int audiencia, String DataDeLancamento, int duracaoMin) {
        super(nome, genero, idioma, audiencia, DataDeLancamento);
        init(duracaoMin);
    }
    //#endregion

    //#region metodos da classe

    /**
     * Converte a duração do filme de minutos para segundos
     *
     * @param duracaoMin Duração do filme em minutos
     * @return Duração do filme em segundos
     */
    private long converteDuracaoSeg(int duracaoMin) {
        return duracaoMin * 60;
    }

    /**
     * Converte a duração do filme de segundos para minutos
     *
     * @return Duração do filme em minutos
     */
    private long converteDuracaoMin() {
        return (int) this.duracaoSeg / 60;
    }

    /**
     * Descrição da filme em string: caracteristicas - duração.
     *
     * @return String com o formato descrito acima.
     */
    @Override
    public String toString() {
        StringBuilder desc = new StringBuilder(super.toString());
        desc.append(" - " + converteDuracaoMin() + " min.");
        return desc.toString();
    }
    //#endregion
}