package codigo.app;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Cliente {
    protected String nomeDeUsuario;
    protected String login;
    protected String senha;
    protected List<Midia> listaParaVer;
    protected List<Midia> listaJaVistas;
    protected Map<Integer, LocalDate> dataQueFoiVista;
    protected boolean logado;
    protected IClienteState state;


    /**
     * Cria um novo usuario
     * inicialmente todos os clientes são regulares
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
        this.logado = false;
        this.state = new ClienteRegular();
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
        try {

            for (Midia midia : listaParaVer) {
                if (midia.getNome().equals(nomeMidia)) {
                    this.listaParaVer.remove(midia);
                    return;
                }
            }

            throw new Exception("Essa mídia não existe na sua lista 'Para Ver'!");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    /**
     * Filtra midias por genero
     *
     * @param genero
     */
    public List<Midia> filtrarPorGenero(Genero genero) {
        List<Midia> listaFiltrada = new ArrayList<>();

        try {

            for (Midia midia : listaParaVer) {
                if (midia.getGenero().equals(genero) && !(listaFiltrada.contains(midia)))
                    listaFiltrada.add(midia);
            }

            for (Midia midia : listaJaVistas) {
                if (midia.getGenero().equals(genero) && !(listaFiltrada.contains(midia)))
                    listaFiltrada.add(midia);
            }

            if (!(listaFiltrada.isEmpty()))
                return listaFiltrada;

            throw new Exception("Não há mídias para essa seleção nas suas listas 'Para Ver' e 'Histórico'!");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return listaFiltrada;
    }

    /**
     * Filtra midias por idioma
     *
     * @param idioma
     */
    public List<Midia> filtrarPorIdioma(Idioma idioma) {
        List<Midia> listaFiltrada = new ArrayList<>();

        try {

            for (Midia midia : listaParaVer) {
                if (midia.getIdioma().equals(idioma) && !(listaFiltrada.contains(midia)))
                    listaFiltrada.add(midia);
            }

            for (Midia midia : listaJaVistas) {
                if (midia.getIdioma().equals(idioma) && !(listaFiltrada.contains(midia)))
                    listaFiltrada.add(midia);
            }

            if (!(listaFiltrada.isEmpty()))
                return listaFiltrada;

            throw new Exception("Não há mídias para essa seleção nas suas listas 'Para Ver' e 'Histórico'!");
        } catch (Exception e) {
            System.out.println(e.toString());
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

        try {

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

            if (!(listaFiltrada.isEmpty()))
                return listaFiltrada;

            throw new Exception("Não há mídias para essa seleção nas suas listas 'Para Ver' e 'Histórico'!");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return listaFiltrada;
    }

    /**
     * Adiciona midia na lista de midias assistidas
     * Associa midia assistida com a data em que foi assistida
     *
     * @param midia
     */
    public void registrarPorAudiencia(Midia midia) {
        try {
            if (!(this.listaJaVistas.contains(midia))) {
                this.listaJaVistas.add(midia);
                midia.registrarAudiencia();
                this.dataQueFoiVista.put(midia.getId(), LocalDate.now());
            } else throw new Exception("Mídia já assistida!");

            this.verificarEstado();

        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    /**
     * o usuario vira cliente profissional
     * depois que o cliente vira profissinal ele não pode sofrer downgrade
     */
    public void upgradeParaProfissional() {
        this.state = new ClienteProfissional();
    }

    /**
     * Verifica se é um cliente especialista
     * Caso tenha assistido  5 ou mais midias mes passado, retornara true
     * sempre verifica o estado do cliente caso tenha se tornado especialista.
     */
    public void verificarEstado() {
        // Pega a data atual e subtrai um mês para obter a data de um mês atrás
        LocalDate umMesAtras = LocalDate.now().minusMonths(1);


        long totalMidiasUltimoMes = dataQueFoiVista.values().stream()
                .filter(date -> date.getYear() == umMesAtras.getYear() && date.getMonthValue() == umMesAtras.getMonthValue())
                .count();

        if (!(this.state instanceof ClienteProfissional) && totalMidiasUltimoMes >= 5) {
            this.state = new ClienteEspecialista();
        }
    }


    @Override
    public String toString() {
        StringBuilder aux = new StringBuilder((this.nomeDeUsuario + ";" + this.login + ";" + this.senha));
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
                writer.write(this.toString() + "\n");

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
     * Adiciona a avaliação de acordo com o state do cliente
     *
     * @param midia
     * @param comentario
     * @author jordana
     */
    public void addAvaliacao(Midia midia, int nota, String comentario) throws Exception {
        this.verificarEstado();
        state.addAvaliacao(this.nomeDeUsuario, midia, nota, comentario);
    }

//    /**
//     * Adiciona avaliação à uma mídia, contanto que ela já tenha sido assistida pelo usuário
//     *
//     * @param midia Mídia a ser avaliada
//     * @param nota  Avaliacao da mídia (número inteiro de 0 a 10)
//     */
//    public void addAvaliacao(Midia midia, int nota, String comentario) {
//
//        Avaliacao avaliacao = null;
//        try {
//            avaliacao = new Avaliacao(this.nomeDeUsuario, nota, comentario);
//
//            if(avaliacao.getNomeDeUsuario() == null) return;
//
//            int index = listaJaVistas.indexOf(midia);
//
//            try {
//                if (index != -1)
//                    midia.addAvaliacao(avaliacao);
//                else
//                    throw new Exception("Não foi possível avaliar pois a mídia não foi assistida!");
//            } catch (Exception e) {
//                System.out.println(e.toString());
//            }
//
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        // comentar com 2 (se cair no catch) e interface com 3 caso o cast dê certo
//
//    }

    public String getUsuario() {
        return this.nomeDeUsuario;
    }

    public String getLogin() {
        return this.login;
    }

    public IClienteState getState() {
        return this.state;
    }

    public boolean isLogado() {
        return logado;
    }

    public void setLogado(boolean logado) {
        this.logado = logado;
    }


    public Map<Integer, LocalDate> getDataQueFoiVista() {
        return this.dataQueFoiVista;
    }

    public String getSenha() {
        return this.senha;
    }

    public List<Midia> getListaParaVer() {
        return List.copyOf(listaParaVer);
    }

    public List<Midia> getListaJaVistas() {
        return List.copyOf(listaJaVistas);
    }


}