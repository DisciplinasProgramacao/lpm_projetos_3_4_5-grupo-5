package codigo.app;

public class Filme extends Midia {

    //#region atributos
    private long duracao;
    //#endregion

    //#region construtores
    private void init(int duracaoMin) {
        this.duracao = duracaoMin;
    }

    public Filme(int id, String nome, String genero, String idioma, String DataDeLancamento, int duracao) {
        super(id, nome, genero, idioma, DataDeLancamento);
        init(duracao);
    }
    //#endregion

    //#region metodos da classe

    /**
     * Descrição da filme em string: <nome> (<dataDeLancamento>) - <genero>, <idioma>, <audiencia> visualizacoes - - duração.
     *
     * @return String com o formato descrito acima.
     */
    @Override
    public String toString() {
        StringBuilder desc = new StringBuilder(super.toString());
        desc.append(" - " + this.duracao + " min");
        return desc.toString();
    }

    //#endregion
}