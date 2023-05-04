package codigo.app;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private String nomeDeUsuario;
    private String login;
    private String senha;
    private List<Serie> listaParaVer;
    private List<Serie> listaJaVistas;


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
    }

    public String getUsuario() {
        return this.nomeDeUsuario;
    }

    public String getLogin() {
        return this.login;
    }

    public String getSenha() {
        return this.senha;
    }

    public List<Serie> getListaParaVer() {
        return listaParaVer;
    }

    /**
     * Adiciona serie na lista de séries "Para Ver"
     *
     * @param serie Serie a ser adicionada na lista
     */
    public void adicionarNaLista(Serie serie) {
        if ((!this.listaParaVer.contains(serie)))
            this.listaParaVer.add(serie);
    }

    /**
     * Retira serie da lista de séries "Para Ver"
     *
     * @param nomeSerie Nome da serie a ser removida da lista
     */
    public void retirarNaLista(String nomeSerie) {
        for (Serie serie : listaParaVer) {
            if (serie.getNome().equals(nomeSerie)) {
                this.listaParaVer.remove(serie);
                return;
            }
        }
    }

    /**
     * Filtra series por genero
     *
     * @param genero
     */
    public List<Serie> filtrarPorGenero(String genero) {
        List<Serie> listaFiltrada = new ArrayList<>();

        for (Serie serie : listaParaVer) {
            if (serie.getGenero().equals(genero) && !(listaFiltrada.contains(serie)))
                listaFiltrada.add(serie);
        }

        for (Serie serie : listaJaVistas) {
            if (serie.getGenero().equals(genero) && !(listaFiltrada.contains(serie)))
                listaFiltrada.add(serie);
        }

        return listaFiltrada;
    }

    /**
     * Filtra series por idioma
     *
     * @param idioma
     */
    public List<Serie> filtrarPorIdioma(String idioma) {
        List<Serie> listaFiltrada = new ArrayList<>();

        for (Serie serie : listaParaVer) {
            if (serie.getIdioma().equals(idioma) && !(listaFiltrada.contains(serie)))
                listaFiltrada.add(serie);
        }

        for (Serie serie : listaJaVistas) {
            if (serie.getIdioma().equals(idioma) && !(listaFiltrada.contains(serie)))
                listaFiltrada.add(serie);
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

        for (Serie serie : listaParaVer) {
            if (serie.getQtdEpisodios() == quantEpisodios && !(listaFiltrada.contains(serie)))
                listaFiltrada.add(serie);
        }

        for (Serie serie : listaJaVistas) {
            if (serie.getQtdEpisodios() == quantEpisodios && !(listaFiltrada.contains(serie)))
                listaFiltrada.add(serie);
        }

        return listaFiltrada;
    }

    /**
     * Adiciona serie na lista de series assistidas
     *
     * @param serie
     */
    public void registrarPorAudiencia(Serie serie) {
        if (!(this.listaJaVistas.contains(serie))) {
            this.listaJaVistas.add(serie);
            serie.registrarAudiencia();
        }
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
            PrintWriter writer = new PrintWriter(new FileWriter("Usuarios.txt", true));
            writer.print("\n" + this.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}