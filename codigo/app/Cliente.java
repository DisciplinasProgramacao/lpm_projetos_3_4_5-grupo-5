package codigo.app;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class Cliente {
    protected String nomeDeUsuario;
    protected String login;
    protected String senha;
    protected List<Midia> listaParaVer;
    protected List<Midia> listaJaVistas;
    protected Map<Integer, LocalDate> dataQueFoiVista;
    protected IClienteState state;

    /**
     * Cria um novo usuario
     *
     * @param nomeDeUsuario Nome de usuario pode ter ate 30 caracteres
     * @param login         Login pode ter até 20 caracteres
     * @param senha         Senha pode ter ate 10 caracteres
     * @param profissional  valida se o cliente deverá ser criado como profissional
     */
    public Cliente(String nomeDeUsuario, String login, String senha, boolean profissional) {
        try {
            if (validaParametrosConstrutor(nomeDeUsuario, login, senha)) {
                this.listaParaVer = new ArrayList<>();
                this.listaJaVistas = new ArrayList<>();
                this.dataQueFoiVista = new HashMap<>();
                if (profissional) this.state = new ClienteProfissional();
                else this.state = new ClienteRegular();
            } else throw new Exception("Não foi possível criar o usuário!");

        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    /**
     * valida parametros para criar um novo cliente
     *
     * @param nomeDeUsuario Nome de usuario pode ter ate 30 caracteres
     * @param login         Login pode ter até 20 caracteres
     * @param senha         Senha pode ter ate 10 caracteres
     */
    public boolean validaParametrosConstrutor(String nomeDeUsuario, String login, String senha) {
        if (!(login.isEmpty() || login.length() > 20)) this.login = login;
        else return false;
        if (!(nomeDeUsuario.isEmpty() || nomeDeUsuario.length() > 30)) this.nomeDeUsuario = nomeDeUsuario;
        else return false;
        if (!(senha.isEmpty() || senha.length() > 10)) this.senha = senha;
        else return false;
        return true;
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
     * Verifica se é um cliente especialista
     * Caso tenha assistido 5 ou mais midias mes passado, retornara true
     * sempre verifica o estado do cliente caso tenha se tornado especialista.
     */
    public void verificarEstado() {
        // Pega a data atual e subtrai um mês para obter a data de um mês atrás
        LocalDate umMesAtras = LocalDate.now().minusMonths(1);


        long totalMidiasUltimoMes = dataQueFoiVista.values().stream()
                .filter(date -> date.getYear() == umMesAtras.getYear() && date.getMonthValue() == umMesAtras.getMonthValue())
                .count();

        if (!(this.state instanceof ClienteProfissional) && totalMidiasUltimoMes < 5 && (this.state instanceof ClienteEspecialista)) {
            this.state = new ClienteEspecialista();
        }
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
            if (midia.getEstadoMidia() == EstadoMidia.LANCAMENTO && !(this.state instanceof ClienteProfissional)) {
                throw new Exception("Não foi possível assistir pois a mídia é lançamento e o usuário não é profissional!");
            }

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

        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }


    public String toSaveString() {
        StringBuilder aux = new StringBuilder((this.nomeDeUsuario + ";" + this.login + ";" + this.senha + ";" + state.toString()));
        return aux.toString();
    }

    public String toString() {
        StringBuilder aux = new StringBuilder("Usuário " + state.toString() + " - Nome de usuário: " + this.nomeDeUsuario + " Login: " + this.login + " - Senha:" + this.senha);
        return aux.toString();
    }

    /**
     * @param caminhoArq
     */
    public void salvarClientes(String caminhoArq) {
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

    /**
     * @param caminhoArq
     */
    public void salvarAudiencia(String caminhoArq) {
        try {
            FileWriter writer = new FileWriter(caminhoArq, true);

            if (!caminhoArq.equals("")) {

                for (Midia midia : listaParaVer) {
                    writer.write(this.login + ";" + this.senha + ";F;" + midia.getNome() + ";" + midia.getId() + "\n");
                }

                for (Midia midia : listaJaVistas) {
                    writer.write(this.login + ";" + this.senha + ";A;" + midia.getNome() + ";" + midia.getId() + "\n");
                }

            }

            writer.close();
        } catch (IOException e) {
            System.out.println("Erro ao salvar dados no arquivo.");
        }
    }

//    public void salvarAa(String caminhoArq, HashMap<String, Midia> midias) {
//        try {
//            FileWriter writer = new FileWriter(caminhoArq, true);
//
//            if (!caminhoArq.equals("")) {
//
//                Random random = new Random();
//                int quantidadeMidias = random.nextInt(101) + 100; // Gera um número aleatório entre 100 e 200
//
//                int contador = 0;
//                for (Map.Entry<String, Midia> entrada : midias.entrySet()) {
//                    if (entrada.getValue().getEstadoMidia() == EstadoMidia.MODIFICAVEL) {
//                        String randomValue = random.nextBoolean() ? "A" : "F";
//                        writer.write(this.login + ";" + this.senha + ";" + randomValue + ";" + entrada.getValue().getNome() + ";" + entrada.getValue().getId() + "\n");
//                    }
//                    contador++;
//
//                    if (contador == quantidadeMidias) {
//                        break;
//                    }
//                }
//            }
//
//            writer.close();
//        } catch (IOException e) {
//            System.out.println("Erro ao salvar dados no arquivo.");
//        }
//    }

    /**
     * Adiciona a avaliação de acordo com o state do cliente
     *
     * @param midia
     * @param comentario
     */
    public void addAvaliacao(Midia midia, int nota, String comentario) throws Exception {
        this.verificarEstado();
        try {
            int index = listaJaVistas.indexOf(midia);
            try {
                if (index != -1) {
                    state.addAvaliacao(this.login, midia, nota, comentario);
                } else throw new Exception("Não foi possível avaliar pois a mídia não foi assistida!");
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public String getUsuario() {
        return this.nomeDeUsuario;
    }

    public IClienteState getState() {
        return this.state;
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

    public String getLogin() {
        return login;
    }


}