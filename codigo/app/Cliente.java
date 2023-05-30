package codigo.app;

import java.io.*;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Cliente {
    private String nomeDeUsuario;
    private String login;
    private String senha;
    private List<Midia> listaParaVer;
    private List<Midia> listaJaVistas;
    private Map<Integer, LocalDate> dataQueFoiVista;


    /**
     * Cria um novo usuario
     *
     * @param nomeDeUsuario Nome de usuario pode ter ate 30 caracteres
     * @param login         Login pode ter até 20 caracteres
     * @param senha         Senha pode ter ate 10 caracteres
     */
    public Cliente(String nomeDeUsuario, String login, String senha) {
        if (!(login.isEmpty() || login.length() > 20))
            this.login = login;
        if (!(nomeDeUsuario.isEmpty() || nomeDeUsuario.length() > 30))
            this.nomeDeUsuario = nomeDeUsuario;
        if (!(senha.isEmpty() || senha.length() > 10))
            this.senha = senha;
        this.listaParaVer = new ArrayList<>();
        this.listaJaVistas = new ArrayList<>();
        this.dataQueFoiVista = new HashMap<>();
    }

    /**
     * Adiciona midia na lista de mídias "Para Ver"
     *
     * @param midia Midia a ser adicionada na lista
     */
    public void adicionarNaLista(Midia midia) {
        if ((!this.listaParaVer.contains(midia)))
            this.listaParaVer.add(midia);
    }

    /**
     * Adiciona midia na lista de mídias "Ja Vistas"
     *
     * @param midia Midia a ser adicionada na lista ja vistas
     */
    public void adicionarNaListaJaVistas(Midia midia) {
        if ((!this.listaJaVistas.contains(midia)))
            this.listaJaVistas.add(midia);
    }

    /**
     * Retira midia da lista de mídias "Para Ver"
     *
     * @param nomeMidia Nome da midia a ser removida da lista
     */
    public void retirarNaLista(String nomeMidia) {
        for (Midia midia : listaParaVer) {
            if (midia.getNome().equals(nomeMidia)) {
                this.listaParaVer.remove(midia);
                return;
            }
        }
    }

    /**
     * Retira midia da lista de mídias "Ja Vistas"
     *
     * @param nomeMidia Nome da midia a ser removida da lista
     */
    public void retirarNaListaJaVistas(String nomeMidia) {
        for (Midia midia : listaJaVistas) {
            if (midia.getNome().equals(nomeMidia)) {
                this.listaJaVistas.remove(midia);
                return;
            }
        }
    }

    /**
     * Filtra midias por genero
     *
     * @param genero
     */
    public List<Midia> filtrarPorGenero(String genero) {
        List<Midia> listaFiltrada = new ArrayList<>();

        for (Midia midia : listaParaVer) {
            if (midia.getGenero().equals(genero) && !(listaFiltrada.contains(midia)))
                listaFiltrada.add(midia);
        }

        for (Midia midia : listaJaVistas) {
            if (midia.getGenero().equals(genero) && !(listaFiltrada.contains(midia)))
                listaFiltrada.add(midia);
        }

        return listaFiltrada;
    }

    /**
     * Filtra midias por idioma
     *
     * @param idioma
     */
    public List<Midia> filtrarPorIdioma(String idioma) {
        List<Midia> listaFiltrada = new ArrayList<>();

        for (Midia midia : listaParaVer) {
            if (midia.getIdioma().equals(idioma) && !(listaFiltrada.contains(midia)))
                listaFiltrada.add(midia);
        }

        for (Midia midia : listaJaVistas) {
            if (midia.getIdioma().equals(idioma) && !(listaFiltrada.contains(midia)))
                listaFiltrada.add(midia);
        }

        return listaFiltrada;
    }

    /**
     * Filtra series por quantidade de epiodios
     *
     * @param quantEpisodios
     */
    public List<Serie> filtrarPorQtdEpisodios(int quantEpisodios) {
        List<Serie> listaFiltrada = new ArrayList<>();

        for (Midia midia : listaParaVer) {
            try {
                if (midia instanceof Serie) {
                    Serie serie = (Serie) midia;
                    if (serie.filtrarPorQtdEpisodios(quantEpisodios) && !(listaFiltrada.contains(midia)))
                        listaFiltrada.add(serie);
                }
            } catch (ClassCastException e) {
            }
        }

        for (Midia midia : listaJaVistas) {
            try {
                if (midia instanceof Serie) {
                    Serie serie = (Serie) midia;
                    if (serie.filtrarPorQtdEpisodios(quantEpisodios) && !(listaFiltrada.contains(midia)))
                        listaFiltrada.add(serie);
                }
            } catch (ClassCastException e) {
            }
        }

        return listaFiltrada;
    }

    /**
     * Adiciona midia na lista de midias assistidas
     * Associa midia assistida com a data em que foi assistida
     *
     * @param midia
     * @param dataVista insere a data em que o usuario assistiu a midia
     */
    public void registrarPorAudiencia(Midia midia, String dataVista) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate data = LocalDate.parse(dataVista, formatter);
        if (!(this.listaJaVistas.contains(midia))) {
            this.listaJaVistas.add(midia);
            midia.registrarAudiencia();
            this.dataQueFoiVista.put(midia.getId(), data);
        }
    }

    /**
     * Verifica se é um cliente especialista
     * Caso tenha assistido  5 ou mais midias mes passado, retornara true
     */
    public boolean isEspecialista() {
        // Pega a data atual e subtrai um mês para obter a data de um mês atrás
        LocalDate umMesAtras = LocalDate.now().minusMonths(1);

        // Conta quantas mídias foram assistidas no mês anterior
        long totalMidiasUltimoMes = dataQueFoiVista.values().stream()
                .filter(date -> date.getYear() == umMesAtras.getYear() && date.getMonthValue() == umMesAtras.getMonthValue())
                .count();

        // Retorna true se 5 ou mais mídias foram assistidas no mês anterior
        return totalMidiasUltimoMes >= 5;
    }

    @Override
    public String toString() {
        StringBuilder aux = new StringBuilder((this.nomeDeUsuario + " - " + this.login + ", " + this.senha));
        return aux.toString();
    }

    /**
     * @param caminhoArq
     * @author Breno
     */
    public void salvar(String caminhoArq) {
        try {
            FileWriter writer = new FileWriter(caminhoArq, true);

            if (!caminhoArq.equals("")) {
                writer.write(toString() + "\n");

                writer.write("Lista de midias 'PARA VER': \n");
                for (Midia midia : listaParaVer) {
                    writer.write(midia.toString() + "\n");
                }

                writer.write("Historico de midias: \n");
                for (Midia midia : listaJaVistas) {
                    writer.write(midia.toString() + "\n");
                }

            }

            writer.close();
        } catch (IOException e) {
            System.out.println("Erro ao salvar dados no arquivo.");
        }
    }

    /**
     * Adiciona avaliação à uma mídia, contanto que ela já tenha sido assistida pelo usuário
     *
     * @param midia Mídia a ser avaliada
     * @param nota  Avaliacao da mídia (número inteiro de 0 a 10)
     */
    public void addAvaliacao(Midia midia, int nota, String comentario) throws Exception {

        if (!isEspecialista())
            comentario = "";

        Avaliacao avaliacao = new Avaliacao(this.nomeDeUsuario, nota, comentario);

        int index = listaJaVistas.indexOf(midia);
        if (index != -1) {
            midia.addAvaliacao(avaliacao);
            return;
        } // comentar com 2 (se cair no catch) e interface com 3 caso o cast dê certo
    }

    public String getUsuario() {
        return this.nomeDeUsuario;
    }

    public String getLogin() {
        return this.login;
    }

    public Map<Integer, LocalDate> getDataQueFoiVista() {
        return this.dataQueFoiVista;
    }

    public String getSenha() {
        return this.senha;
    }

    public List<Midia> getListaParaVer() {
        return listaParaVer;
    }

    public List<Midia> getListaJaVistas() {
        return listaJaVistas;
    }


}