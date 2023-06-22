package codigo.app;

import java.io.FileWriter;
import java.io.IOException;

public class Filme extends Midia {

    //#region atributos
    private long duracao;
    private final String TIPO_MIDIA = "F";
    //#endregion

    //#region construtores
    private void init(int duracaoMin) {
        this.duracao = duracaoMin;
    }

    public Filme(int id, String nome, Genero genero, Idioma idioma, String dataDeLancamento, EstadoMidia estadoMidia, int duracao) {
        super(id, nome, genero, idioma, dataDeLancamento, estadoMidia);
        init(duracao);
    }
    //#endregion

    //#region metodos da classe

    /**
     * Descrição da filme em string: <id> | <nome> (<dataDeLancamento>) - <genero>, <idioma>, <audiencia> visualizacoes - - duração.
     *
     * @return String com o formato descrito acima.
     */
    @Override
    public String toSaveString() {
        StringBuilder desc = new StringBuilder(super.toSaveString());
        desc.append(";" + this.duracao);
        return desc.toString();
    }

    @Override
    public String toString() {
        StringBuilder desc = new StringBuilder(super.toString());
        desc.append(" • " + this.duracao + " min.");
        return desc.toString();
    }

    @Override
    public String definirTipoMidia() {
        return this.TIPO_MIDIA;
    }

    /**
     * Salva um filme no arquivo
     *
     * @param caminhoArq Caminho do arquivo a ser salvo
     */
    public void salvar(String caminhoArq) {
        try {
            FileWriter writer = new FileWriter(caminhoArq, true);

            if (!caminhoArq.equals("")) {
                writer.write(this.toSaveString() + "\n");
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("Erro ao salvar dados no arquivo.");
        }
    }


}