package codigo.app;

import java.io.FileWriter;
import java.io.IOException;

public class Filme extends Midia {

    //#region atributos
    private long duracaoSeg;
    //#endregion

    //#region construtores
    private void init(int duracaoMin) {
        this.duracaoSeg = duracaoMin > 0 ? converteDuracaoSeg(duracaoMin) : 0;
    }

    public Filme(int id, String nome, String genero, String idioma, String DataDeLancamento, int duracao) {
        super(id, nome, genero, idioma, DataDeLancamento);
        init(duracao);
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
     * Descrição da filme em string: <nome> (<dataDeLancamento>) - <genero>, <idioma>, <audiencia> visualizacoes - - duração.
     *
     * @return String com o formato descrito acima.
     */
    @Override
    public String toString() {
        StringBuilder desc = new StringBuilder(super.toString());
        desc.append(" - " + converteDuracaoMin() + " min.");
        return desc.toString();
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