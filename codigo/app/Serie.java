package codigo.app;

import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Serie extends Midia {

    private int qtdEpisodios;
    private String TIPO_MIDIA = "S";
    private void init(int qtdEpisodios) {
        this.qtdEpisodios = qtdEpisodios;
    }

    public Serie(int id, String nome, String genero, String idioma, String DataDeLancamento, int qtdEpisodios) {
        super(id, nome, genero, idioma, DataDeLancamento);
        init(qtdEpisodios);
    }

    /**
     * Descrição da série em string: <id> | <nome> (<dataDeLancamento>) - <genero>, <idioma>, <audiencia> visualizacoes - quantidade de episódios.
     *
     * @return String com o formato descrito acima.
     */
    @Override
    public String toString() {
        StringBuilder desc = new StringBuilder(super.toString());
        desc.append(";" + this.qtdEpisodios);
        return desc.toString();
    }

    @Override
    public String definirTipoMidia() {
        return this.TIPO_MIDIA;
    }

    public int getQtdEpisodios() {
        return qtdEpisodios;
    }

    public boolean filtrarPorQtdEpisodios(int qtdEpisodios) {
        if (this.qtdEpisodios == qtdEpisodios)
            return true;
        return false;
    }

    public void salvar(String caminhoArq) {
        try {
            FileWriter writer = new FileWriter(caminhoArq, true);

            if (!caminhoArq.equals("")) {
                writer.write(toString() + "\n");
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("Erro ao salvar dados no arquivo.");
        }
    }

}
