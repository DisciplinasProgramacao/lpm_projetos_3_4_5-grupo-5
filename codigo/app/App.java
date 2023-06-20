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
                1 - Criar usuário
                2 - Carregar Usuários
                3 - Login
                4 - menu Plataforma de Streaming
                5 - menu Usuário
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
                3 - Filtrar midias por genero
                4 - Filtrar midias por idioma
                5 - Filtrar séries por quantidade de episódios
                6 - Avaliar mídia
                7 - Adicionar mídia na lista 'Para Ver'
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
                5 - Cadastrar série
                6 - Cadastrar filme
                7 - Cadastrar usuário
                8 - Salvar mídias
                9 - Salvar usuários
                10 - Buscar mídia
                11 - Filtrar mídias por gênero
                12 - Filtrar mídias por idioma
                13 - Filtrar series por quantidade de episódios
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

        return new Serie(id, nome, genero, idioma, dtLancamento, qtdEp);
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

        return new Filme(id, nome, genero, idioma, dtLancamento, duracao);
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
        String aux;
        int opcao;

        do {
            opcao = menu();

            switch (opcao) {
                case 1:
                    Cliente usuario = cadastrarUsuario();
                    plataformaStreaming.adicionarCliente(usuario);
                    System.out.println("Usuário cadastrado!");
                    pausa();
                    break;

                case 2:
                    plataformaStreaming.carregarClientes("docs/arquivos/POO_Espectadores.csv");
                    System.out.println("Cadastro de usuários por arquivo concluído!");
                    pausa();
                    break;

                case 3:
                    System.out.println("Digite o nome de usuário:");
                    String nameUser = teclado.nextLine();
                    System.out.println("Digite a senha:");
                    String senha = teclado.nextLine();
                    cliente = plataformaStreaming.login(nameUser, senha);
                    if (cliente != null)
                        System.out.println("Bem vindo(a) " + cliente.getUsuario());
                    pausa();
                    break;

                // menu plataforma streaming
                case 4:
                    int alteracao = subMenuPlataformaStreaming();

                    switch (alteracao) {
                        case 0 -> {
                        }
                        case 1 -> {
                            listarMidias(plataformaStreaming);
                        }
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
                            plataformaStreaming.adicionarMidia(cadastrarSerie());
                            System.out.println("Série cadastrada!");
                        }
                        case 6 -> {
                            plataformaStreaming.adicionarMidia(cadastrarFilme());
                            System.out.println("Filme cadastrado!");
                        }
                        case 7 -> {
                            plataformaStreaming.adicionarCliente(cadastrarUsuario());
                            System.out.println("Usuário cadastrado!");
                        }
                        case 8 -> {
                            try {
                                plataformaStreaming.salvarMidias("PS_Midias.csv");
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        case 9 -> {
                            try {
                                plataformaStreaming.salvarClientes("PS_Usuarios.csv");
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        case 10 -> {
                            buscarMidia(plataformaStreaming);
                        }
                        case 11 -> {
                            Genero generoEscolhido = escolherGenero();

                            if (generoEscolhido != null) {
                                List<Midia> lista = plataformaStreaming.filtrarPorGenero(generoEscolhido);
                                if (!(lista.isEmpty())) lista.forEach(System.out::println);
                            } else System.out.println("Gênero inválido!");
                        }
                        case 12 -> {
                            Idioma idiomaEscolhido = escolherIdioma();

                            if (idiomaEscolhido != null) {
                                List<Midia> lista = plataformaStreaming.filtrarPorIdioma(idiomaEscolhido);
                                if (!(lista.isEmpty())) lista.forEach(System.out::println);
                            } else System.out.println("Idioma inválido!");
                        }
                        case 13 -> {
                            System.out.println("Digite a quantidade de episódios:");
                            int qtdEp = Integer.parseInt(teclado.nextLine());

                            List<Serie> listaFiltrada = plataformaStreaming.filtrarPorQtdEpisodio(qtdEp);
                            if (!(listaFiltrada.isEmpty())) listaFiltrada.forEach(System.out::println);
                        }
                        default -> throw new Exception("Algo inesperado ocorreu! Tente novamente");
                    }
                    pausa();
                    break;

                // menu usuario
                case 5:
                    if (cliente != null) {
                        int alt = subMenuCliente();

                        switch (alt) {
                            case 0 -> {
                            }
                            case 1 -> {
                                historico(cliente);
                            }
                            case 2 -> {
                                listaParaVer(cliente);
                            }
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
                                    if (midia != null) cliente.adicionarNaListaJaVistas(midia);
                                }
                            }
                            case 10 -> {
                                plataformaStreaming.logoff();
                                cliente = null;
                            }
                        }
                    } else
                        System.out.println("Não há nenhum usuário logado.");
                    pausa();
                    break;

            }
        } while (opcao != 0);
    }

}