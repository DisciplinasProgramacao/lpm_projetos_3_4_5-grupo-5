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
        desc.append(" - " + this.qtdEpisodios + " episodios.");
        return desc.toString();
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

    public HashMap<Integer, Serie> lerArquivosSeries() {
        HashMap<Integer, Serie> arqSeries = new HashMap<Integer, Serie>();

        try {
            File arquivo = new File("POO_Series.csv");
            Scanner scanner = new Scanner(arquivo);
            String t = scanner.nextLine();
            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                String[] dados = linha.split(";");

                int idSerie = Integer.parseInt(dados[0].trim());
                String nomeSerie = dados[1];
                String dataDeLancamento = dados[2];

                Serie novaSerie = new Serie(idSerie, nomeSerie, "", "", dataDeLancamento, 0);
                arqSeries.put(novaSerie.getId(), novaSerie);
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return arqSeries;
    }

}
