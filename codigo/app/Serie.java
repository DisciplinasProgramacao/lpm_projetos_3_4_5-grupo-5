package codigo.app;

import java.io.FileWriter;
import java.io.IOException;

public class Serie extends Midia {

    private int qtdEpisodios;

    private void init(int qtdEpisodios) {
        this.qtdEpisodios = qtdEpisodios;
    }
    public Serie(int id, String nome, String genero, String idioma, String DataDeLancamento, int qtdEpisodios) {
        super(id, nome, genero, idioma, DataDeLancamento);
        init(qtdEpisodios);
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

    public boolean filtrarPorQtdEpisodios(int qtdEpisodios){
        if(this.qtdEpisodios == qtdEpisodios)
            return true;
        return false;
    }

    public void salvar(String caminhoArq){
        try {
            FileWriter writer = new FileWriter(caminhoArq, true);

            if(!caminhoArq.equals("")){
                writer.write(toString() + "\n");
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("Erro ao salvar dados no arquivo.");
        }
    }

}