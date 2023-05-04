package codigo.app;

public class Serie extends Midia {

    private int qtdEpisodios;

    public Serie(String nome, String genero, String idioma, String DataDeLancamento) {
        super(nome, genero, idioma, DataDeLancamento);
    }

    /**
     * Descrição da série em string: <nome> (<dataDeLancamento>) - <genero>, <idioma>, <audiencia> visualizacoes - quantidade de episódios.
     *
     * @return String com o formato descrito acima.
     */
    @Override
    public String toString() {
        StringBuilder desc = new StringBuilder(super.toString());
        desc.append(" - " + this.qtdEpisodios + " episodios.");
        return desc.toString();
    }

    public int getQtdEpisodios() {
        return qtdEpisodios;
    }

}