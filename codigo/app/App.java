package codigo.app;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class App {
    private static final Scanner teclado = new Scanner(System.in);

    private static int menu() {
        limparTela();
        System.out.println("""
                Menu:
                ---------------------
                0 - Sair
                1 - Login
                2 - Menu Plataforma de Streaming
                3 - Menu Usuário
                4 - Menu Administrador
                ---------------------
                Digite sua opção:\s""");

        return Integer.parseInt(teclado.nextLine());
    }

    private static int subMenuCliente() {
        limparTela();
        System.out.println("""

                ---------------------
                0 - Voltar
                1 - Visualizar histórico (mídias já assistidas)
                2 - Visualizar lista de mídias 'Para Ver'
                3 - Filtrar mídias por gênero
                4 - Filtrar mídias por idioma
                5 - Filtrar séries por quantidade de episódios
                6 - Avaliar mídia
                7 - Adicionar mídia na lista 'Para Ver'//'Desejos'
                8 - Remover mídia da lista 'Para Ver'
                9 - Assistir mídia (adiciona no hitórico)
                10 - Logoff
                ---------------------
                Digite sua opção:\s""");

        return Integer.parseInt(teclado.nextLine());
    }

    private static int subMenuPlataformaStreaming() {
        limparTela();
        System.out.println("""
                ---------------------
                0 - Voltar
                1 - Listar mídias
                2 - Carregar mídias
                3 - Carregar usuários
                4 - Carregar audiência
                5 - Salvar mídias
                6 - Salvar usuários
                7 - Buscar mídia
                8 - Filtrar mídias por gênero
                9 - Filtrar mídias por idioma
                10 - Filtrar series por quantidade de episódios
                11 - Relatórios
                ---------------------
                Digite sua opção:\s""");


        return Integer.parseInt(teclado.nextLine());
    }

    private static int subMenuAdministrador() {
        limparTela();
        System.out.println("""
                ---------------------
                0 - Voltar
                1 - Cadastrar série
                2 - Cadastrar filme
                3 - Cadastrar usuário
                ---------------------
                Digite sua opção:\s""");


        return Integer.parseInt(teclado.nextLine());
    }

    private static int subMenuLogin() {
        limparTela();
        System.out.println("""
                ---------------------
                0 - Voltar
                1 - Login usuário
                2 - Login administrador
                ---------------------
                Digite sua opção:\s""");


        return Integer.parseInt(teclado.nextLine());
    }

    private static int subMenuRelatorios() {
        limparTela();
        System.out.println("""
                ---------------------
                0 - Voltar
                1 - Qual cliente assistiu mais mídias, e quantas mídias
                2 - Qual cliente tem mais avaliações, e quantas avaliações
                3 - Qual a porcentagem dos clientes com pelo menos 15 avaliações
                4 - Quais são as 10 mídias de melhor avaliação, com pelo menos 100 avaliações, em ordem decrescente
                5 - Quais são as 10 mídias com mais visualizações, em ordem decrescente
                6 - Quais são as 10 mídias de melhor avaliação de um gênero específico, com pelo menos 100 avaliações, em ordem decrescente
                7 - Quais são as 10 mídias com mais visualizações de um gênero específico, em ordem decrescente
                ---------------------
                Digite sua opção:\s""");


        return Integer.parseInt(teclado.nextLine());
    }

    /**
     * Pausa para leitura de mensagens
     */
    private static void pausa() {
        System.out.println("Enter para continuar.");
        teclado.nextLine();
    }

    private static Genero escolherGenero() {

        System.out.println("Gêneros: \n---------------------");
        for (Genero genero : Genero.values()) {
            System.out.println(genero.getNome());
        }
        System.out.println("---------------------");

        System.out.println("\nEscolha um gênero:");
        String generoDigitado = teclado.nextLine();

        Genero generoEscolhido = null;
        for (Genero genero : Genero.values()) {
            if (genero.getNome().equalsIgnoreCase(generoDigitado)) {
                generoEscolhido = genero;
                break;
            }
        }

        return generoEscolhido;
    }

    private static Idioma escolherIdioma() {

        System.out.println("Idiomas: \n---------------------");
        for (Idioma idioma : Idioma.values()) {
            System.out.println(idioma.getNome());
        }
        System.out.println("---------------------");

        System.out.println("\nEscolha um idioma:");
        String idiomaDigitado = teclado.nextLine();

        Idioma idiomaEscolhido = null;
        for (Idioma idioma : Idioma.values()) {
            if (idioma.getNome().equalsIgnoreCase(idiomaDigitado)) {
                idiomaEscolhido = idioma;
                break;
            }
        }

        return idiomaEscolhido;

    }

    private static EstadoMidia escolherEstadoMidia() {

        System.out.println("Tipos de mídia: \n---------------------");
        for (EstadoMidia estadoMidia : EstadoMidia.values()) {
            System.out.println(estadoMidia.getNome());
        }
        System.out.println("---------------------");

        System.out.println("\nEscolha um estado:");
        String estadoDigitado = teclado.nextLine();

        EstadoMidia estadoEscolhido = null;
        for (EstadoMidia estado : EstadoMidia.values()) {
            if (estado.getNome().equalsIgnoreCase(estadoDigitado)) {
                estadoEscolhido = estado;
                break;
            }
        }

        return estadoEscolhido;

    }

    private static void listaParaVer(Cliente cliente) {
        List<Midia> lista = cliente.getListaParaVer();
        if (lista.isEmpty()) System.out.println("Não há nenhuma mídia cadastrada no seu 'Para Ver'!");
        else lista.forEach(System.out::println);
    }

    private static Midia buscarMidia(PlataformaStreaming plataformaStreaming) {
        System.out.println("Digite o nome da midia:");
        String aux = teclado.nextLine();

        Midia midia = plataformaStreaming.buscarMidia(aux);
        if (midia != null) System.out.println("Mídia encontrada: " + midia);
        return midia;
    }

    private static void historico(Cliente cliente) {
        List<Midia> lista = cliente.getListaJaVistas();
        if (lista.isEmpty()) System.out.println("Não há nenhuma mídia cadastrada no seu histórico!");
        else lista.forEach(System.out::println);
    }

    private static void listarMidias(PlataformaStreaming plataformaStreaming) {
        HashMap<String, Midia> lista = plataformaStreaming.getMidias();
        if (lista.isEmpty()) System.out.println("Não há nenhuma mídia cadastrada na plataforma!");
        else lista.values().forEach(System.out::println);
    }

    /**
     * Cadastra série com dados do teclado
     *
     * @return Retorna midia da série cadastrada
     */
    private static Midia cadastrarSerie() {

        System.out.println("Cadastro de Serie:");
        System.out.println("Digite o id:");
        int id = Integer.parseInt(teclado.nextLine());
        System.out.println("Digite o nome:");
        String nome = teclado.nextLine();
        Genero genero = escolherGenero();
        Idioma idioma = escolherIdioma();
        System.out.println("Digite a data de lançamento:");
        String dtLancamento = teclado.nextLine();
        System.out.println("Digite a quantidade de episodios:");
        int qtdEp = Integer.parseInt(teclado.nextLine());
        EstadoMidia estadoMidia = escolherEstadoMidia();

        return new Serie(id, nome, genero, idioma, dtLancamento, estadoMidia, qtdEp);
    }

    /**
     * Cadastra filme com dados do teclado
     *
     * @return Retorna midia do filme cadastrada
     */
    private static Midia cadastrarFilme() {

        System.out.println("Cadastro de Filme:");
        System.out.println("Digite o id:");
        int id = Integer.parseInt(teclado.nextLine());
        System.out.println("Digite o nome:");
        String nome = teclado.nextLine();
        Genero genero = escolherGenero();
        Idioma idioma = escolherIdioma();
        System.out.println("Digite a data de lançamento:");
        String dtLancamento = teclado.nextLine();
        System.out.println("Digite duração em minutos:");
        int duracao = Integer.parseInt(teclado.nextLine());
        EstadoMidia estadoMidia = escolherEstadoMidia();

        return new Filme(id, nome, genero, idioma, dtLancamento, estadoMidia, duracao);
    }

    /**
     * Cadastra usuário com dados do teclado
     *
     * @return Retorna usuario cadastrado
     */
    private static Cliente cadastrarUsuario() {

        System.out.println("Cadastro de Usuário:");
        System.out.println("Digite o nome de usuário:");
        String nome = teclado.nextLine();
        System.out.println("Digite o login do usuário:");
        String login = teclado.nextLine();
        System.out.println("Digite a senha:");
        String senha = teclado.nextLine();

        return new Cliente(nome, login, senha);
    }

    /**
     * "Limpa" a tela (códigos de terminal VT-100)
     */
    private static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    //carregar audiencia
    public static void main(String[] args) throws Exception {
        PlataformaStreaming plataformaStreaming = new PlataformaStreaming("Plataforma de Streaming");
        Cliente cliente = null;
        Administrador adm = null;
        int opcao;

        do {
            opcao = menu();

            switch (opcao) {

                // login
                case 1 -> {
                    int alter = subMenuLogin();
                    switch (alter) {
                        case 0 -> {
                        }
                        case 1 -> {
                            System.out.println("Digite o nome de usuário:");
                            String nameUser = teclado.nextLine();
                            System.out.println("Digite a senha:");
                            String senha = teclado.nextLine();
                            cliente = plataformaStreaming.loginCliente(nameUser, senha);
                            if (cliente != null)
                                System.out.println("Bem vindo(a) " + cliente.getUsuario());
                        }
                        case 2 -> {
                            System.out.println("Digite o nome de usuário:");
                            String nameUser = teclado.nextLine();
                            System.out.println("Digite a senha:");
                            String senha = teclado.nextLine();
                            adm = plataformaStreaming.loginAdministrador(nameUser, senha);
                            if (adm != null)
                                System.out.println("Bem vindo(a) " + adm.getUsuario());
                        }
                        default -> throw new Exception("Algo inesperado ocorreu! Tente novamente");
                    }
                    pausa();
                }

                // menu plataforma streaming
                case 2 -> {
                    int alteracao = subMenuPlataformaStreaming();
                    switch (alteracao) {
                        case 0 -> {
                        }
                        case 1 -> listarMidias(plataformaStreaming);
                        case 2 -> {
                            plataformaStreaming.carregarMidias("docs/arquivos/POO_Midias.csv");
                            System.out.println("Cadastro de mídias por arquivo concluído!");
                        }
                        case 3 -> {
                            plataformaStreaming.carregarClientes("docs/arquivos/POO_Espectadores.csv");
                            System.out.println("Cadastro de usuários por arquivo concluído!");
                        }
                        case 4 -> {
                            plataformaStreaming.carregarClientes("docs/arquivos/POO_Espectadores.csv"); // se audiencia não achar os ids não vai cadastrar nada
                            plataformaStreaming.carregarAudiencia("docs/arquivos/POO_Audiencia.csv");
                            System.out.println("Cadastro de audiência por arquivo concluído!");
                        }
                        case 5 -> {
                            try {
                                plataformaStreaming.salvarMidias("PS_Midias.csv");
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        case 6 -> {
                            try {
                                plataformaStreaming.salvarClientes("PS_Usuarios.csv");
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        case 7 -> buscarMidia(plataformaStreaming);
                        case 8 -> {
                            Genero generoEscolhido = escolherGenero();

                            if (generoEscolhido != null) {
                                List<Midia> lista = plataformaStreaming.filtrarPorGenero(generoEscolhido);
                                if (!(lista.isEmpty())) lista.forEach(System.out::println);
                            } else System.out.println("Gênero inválido!");
                        }
                        case 9 -> {
                            Idioma idiomaEscolhido = escolherIdioma();

                            if (idiomaEscolhido != null) {
                                List<Midia> lista = plataformaStreaming.filtrarPorIdioma(idiomaEscolhido);
                                if (!(lista.isEmpty())) lista.forEach(System.out::println);
                            } else System.out.println("Idioma inválido!");
                        }
                        case 10 -> {
                            System.out.println("Digite a quantidade de episódios:");
                            int qtdEp = Integer.parseInt(teclado.nextLine());

                            List<Serie> listaFiltrada = plataformaStreaming.filtrarPorQtdEpisodio(qtdEp);
                            if (!(listaFiltrada.isEmpty())) listaFiltrada.forEach(System.out::println);
                        }


                        // menu relatorio
                        case 11 -> {

                            int alt = subMenuRelatorios();
                            switch (alt) {
                                case 0 -> {
                                }
//                                case 1 -> method();
//                                case 2 -> {}                                }
                                default -> throw new Exception("Algo inesperado ocorreu! Tente novamente");
                            }
                            pausa();
                        }


                        default -> throw new Exception("Algo inesperado ocorreu! Tente novamente");
                    }
                    pausa();
                }

                // menu usuario
                case 3 -> {
                    if (cliente != null) {
                        int alt = subMenuCliente();

                        switch (alt) {
                            case 0 -> {
                            }
                            case 1 -> historico(cliente);
                            case 2 -> listaParaVer(cliente);
                            case 3 -> {
                                Genero genero = escolherGenero();
                                if (genero != null) {
                                    List<Midia> lista = cliente.filtrarPorGenero(genero);
                                    if (!(lista.isEmpty())) lista.forEach(System.out::println);
                                } else System.out.println("Gênero inválido!");
                            }
                            case 4 -> {
                                Idioma idioma = escolherIdioma();

                                if (idioma != null) {
                                    List<Midia> lista = cliente.filtrarPorIdioma(idioma);
                                    if (!(lista.isEmpty())) lista.forEach(System.out::println);
                                } else System.out.println("Idioma inválido!");
                            }
                            case 5 -> {
                                System.out.println("Digite a quantidade de episódios:");
                                int qtdEp = Integer.parseInt(teclado.nextLine());

                                List<Serie> lista = cliente.filtrarPorQtdEpisodios(qtdEp);
                                if (!(lista.isEmpty())) lista.forEach(System.out::println);
                            }
                            case 6 -> {
                                historico(cliente);
                                if (!(cliente.getListaJaVistas().isEmpty())) {
                                    Midia midia = buscarMidia(plataformaStreaming);
                                    if (midia != null) {
                                        System.out.println("Digite uma nota de 1 a 5 para a mídia:");
                                        int nota = Integer.parseInt(teclado.nextLine());
                                        System.out.println("Digite um comentário:");
                                        String comentario = teclado.nextLine();
                                        cliente.addAvaliacao(midia, nota, comentario);
                                    }
                                }
                            }
                            case 7 -> {
                                listarMidias(plataformaStreaming);
                                Midia midia = buscarMidia(plataformaStreaming);
                                if (midia != null) cliente.adicionarNaLista(midia);
                            }
                            case 8 -> {
                                listaParaVer(cliente);
                                System.out.println("Digite o nome da mídia para ser removida da lista:");
                                String nomeMidia = teclado.nextLine();
                                cliente.retirarNaLista(nomeMidia);
                            }
                            case 9 -> {
                                listarMidias(plataformaStreaming);
                                if (!(plataformaStreaming.getMidias().isEmpty())) {
                                    Midia midia = buscarMidia(plataformaStreaming);
                                    if (midia != null) cliente.registrarPorAudiencia(midia);
                                }
                            }
                            case 10 -> {
                                plataformaStreaming.logoffCliente();
                                cliente = null;
                            }
                        }
                    } else System.out.println("Não há nenhum usuário logado.");
                    pausa();
                }

                // menu administrador
                case 4 -> {
                    if (adm != null) {
                        int alteracao = subMenuAdministrador();
                        switch (alteracao) {
                            case 0 -> {
                            }
                            case 1 -> {
                                plataformaStreaming.adicionarMidia(cadastrarSerie());
                                System.out.println("Série cadastrada!");
                                plataformaStreaming.salvarMidias("docs/arquivos/POO_Midias.csv");
                            }
                            case 2 -> {
                                plataformaStreaming.adicionarMidia(cadastrarFilme());
                                System.out.println("Filme cadastrado!");
                                plataformaStreaming.salvarMidias("docs/arquivos/POO_Midias.csv");
                            }
                            case 3 -> {
                                plataformaStreaming.adicionarCliente(cadastrarUsuario());
                                System.out.println("Usuário cadastrado!");
                                plataformaStreaming.salvarClientes("docs/arquivos/POO_Espectadores.csv");
                            }
                            default -> throw new Exception("Algo inesperado ocorreu! Tente novamente");
                        }
                    } else System.out.println("Não há nenhum administrador logado.");
                    pausa();
                }

            }
        } while (opcao != 0);
    }

}