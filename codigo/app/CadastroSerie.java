package codigo.app;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CadastroSerie {
    private List<Serie> series;

    //#region Contrutores

    /**
     * Cria um novo cadastro de series
     */
    public CadastroSerie() {
        this.series = new ArrayList<>();
    }
    //#endregion

    //#region Métodos de negócio

    /**
     * Adiciona a série a ser cadastrada na lista de séries
     * @param serie série a ser cadastrada
     */
    public void cadastrarSerie(Serie serie) {
        this.series.add(serie);
    }

    /**
     * Remove uma série da lista de séries
     * @param posicao posição da série a ser removida na lista
     * @return A série removida
     */
    public Serie removerSerie(int posicao) {
        return this.series.remove(posicao);
    }

//    public void updateSerie(int posicao, String novoNome, String novoGenero, String novoIdioma, int novaQtdEp){
//    }

    /**
     * Busca uma série pelo seu nome
     * @param nome Nome da série
     * @return A séries buscada ou null caso não exista a série na lista
     */
    public Serie findSerie(String nome) {
        for (Serie serie : this.series) {
            if (serie.getNome().equals(nome)) {
                return serie;
            }
        }
        return null;
    }

    /**
     * Salva todas as séries da lista de séries em um arquivo .txt, no formato:
     * <series.indexOf + 1>;<serie.getNome>;<serie.getGenero>;<serie.getIdioma>
     */
    public void salvar() {
        String path = "seriesCadastradas.txt";
//
//        try {
//            FileWriter writer = new FileWriter(path, true);
//            if (findSerie(salvarSerie.getNome()).equals(salvarSerie)) {
//                writer.write(salvarSerie.getNome() + ";" + salvarSerie.getIdioma() + ";" + salvarSerie.getGenero());
//                writer.write("\n");
//            } else {
//                System.out.println("erro");
//            }
//            writer.close();
//        } catch (IOException e) {
//            System.out.println("Erro ao salvar dados no arquivo.");
//        }

        try {
            FileWriter writer = new FileWriter(path);
            for (Serie serie : series) {
                writer.write((series.indexOf(serie) + 1) + ";" + serie.getNome() + ";" + serie.getGenero() + ";" + serie.getIdioma());
                writer.write("\n");
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("Erro ao salvar dados no arquivo.");
        }
    }

    /**
     * Lista as séries
     * @return Lista de séries
     */
    public List<Serie> listaSeries(){
        return series;
    }
}