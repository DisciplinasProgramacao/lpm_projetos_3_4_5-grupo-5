package codigo.app;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private String nomeDeUsuario;
    private String login;
    private String senha;
    private List<Midia> listaParaVer;
    private List<Midia> listaJaVistas;


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

    public List<Midia> getListaParaVer() {
        return listaParaVer;
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
     *
     * @param midia
     */
    public void registrarPorAudiencia(Midia midia) {
        if (!(this.listaJaVistas.contains(midia))) {
            this.listaJaVistas.add(midia);
            midia.registrarAudiencia();
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

    /**
     * Adiciona avaliação à uma mídia
     *
     * @param midia Mídia a ser avaliada
     * @param nota  Avaliacao da mídia (número inteiro de 0 a 10)
     */
    public void addAvaliacao(Midia midia, int nota) throws Exception {
        Avaliacao avaliacao = new Avaliacao(this.nomeDeUsuario, nota);
        for (Midia m : listaJaVistas) {
            if (midia.equals(m)) {
                midia.addAvaliacao(avaliacao.getAvaliacao());
                return;
            }
        }

    }
}