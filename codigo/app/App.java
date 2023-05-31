package codigo.app;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class App {
    static Scanner teclado = new Scanner(System.in);

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
    static void pausa() {
        System.out.println("Enter para continuar.");
        teclado.nextLine();
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
        System.out.println("Digite o gênero:");
        String genero = teclado.nextLine();
        System.out.println("Digite o idioma:");
        String idioma = teclado.nextLine();
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
        System.out.println("Digite o gênero:");
        String genero = teclado.nextLine();
        System.out.println("Digite o idioma:");
        String idioma = teclado.nextLine();
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
    public static void limparTela() {
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
                    else
                        System.out.println("Usuário não encontrado!");
                    pausa();
                    break;

                // menu plataforma streaming
                case 4:
                    int alteracao = subMenuPlataformaStreaming();

                    switch (alteracao) {
                        case 0 -> {
                        }
                        case 1 -> System.out.println(plataformaStreaming.getMidias().toString());
                        case 2 -> {
                            plataformaStreaming.carregarMidias("docs/arquivos/POO_Filmes.csv");
                            plataformaStreaming.carregarMidias("docs/arquivos/POO_Series.csv");
                            System.out.println("Cadastro de mídias por arquivo concluído!");
                        }
                        case 3 -> {
                            plataformaStreaming.carregarClientes("docs/arquivos/POO_Espectadores.csv");
                            System.out.println("Cadastro de usuários por arquivo concluído!");
                        }
                        case 4 -> {
                            plataformaStreaming.carregarAudiencia("docs/arquivos/POO_Audiencia.csv");
                            System.out.println("Cadastro de audiência por arquivo concluído!");
                        }
                        case 5 -> {
                            Midia serie = cadastrarSerie();
                            plataformaStreaming.adicionarMidia(serie);
                        }
                        case 6 -> {
                            Midia filme = cadastrarFilme();
                            plataformaStreaming.adicionarMidia(filme);
                        }
                        case 7 -> {
                            usuario = cadastrarUsuario();
                            plataformaStreaming.adicionarCliente(usuario);
                        }
                        case 8 -> {
                            try {
                                plataformaStreaming.salvarMidias("Midias");
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        case 9 -> {
                            try {
                                plataformaStreaming.salvarClientes("Usuarios");
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        case 10 -> {
                            System.out.println("Digite o nome da midia:");
                            aux = teclado.nextLine();
                            System.out.println(plataformaStreaming.buscarMidia(aux).toString());
                            //revisar
                        }
                        case 11 -> {
                            System.out.println("Digite o gênero:");
                            aux = teclado.nextLine();
                            System.out.println(plataformaStreaming.filtrarPorGenero(aux));
                        }
                        case 12 -> {
                            System.out.println("Digite o idioma:");
                            aux = teclado.nextLine();
                            System.out.println(plataformaStreaming.filtrarPorIdioma(aux));
                        }
                        case 13 -> {
                            System.out.println("Digite a quantidade de episódios:");
                            int qtdEp = Integer.parseInt(teclado.nextLine());
                            System.out.println(plataformaStreaming.filtrarPorQtdEpisodio(qtdEp));
                        }
                    }
                    pausa();
                    break;

                // menu usuario
                case 5:
                    if (cliente != null) {
                        int alt = subMenuCliente();

                        switch (alt) {
                            case 0:
                                break;
                            case 1:
                                System.out.println(cliente.getListaJaVistas().toString());
                                break;
                            case 2:
                                System.out.println(cliente.getListaParaVer().toString());
                                break;
                            case 3:
                                System.out.println("Digite o gênero:");
                                String genero = teclado.nextLine();
                                System.out.println(cliente.filtrarPorGenero(genero));
                                break;
                            case 4:
                                System.out.println("Digite o idioma:");
                                String idioma = teclado.nextLine();
                                System.out.println(cliente.filtrarPorIdioma(idioma));
                                break;
                            case 5:
                                System.out.println("Digite a quantidade de episódios:");
                                int qtdEp = Integer.parseInt(teclado.nextLine());
                                System.out.println(cliente.filtrarPorQtdEpisodios(qtdEp));
                                break;
                            case 6:
                                System.out.println(cliente.getListaJaVistas().toString());
                                System.out.println("Digite o nome da mídia:");
                                aux = teclado.nextLine();
                                Midia midia = plataformaStreaming.buscarMidia(aux);
                                System.out.println("Digite uma nota de 0 a 10 para a mídia:");
                                int nota = teclado.nextInt();
                                System.out.println("Digite um comentário:");
                                String comentario = teclado.nextLine();
                                Avaliacao avaliacao = new Avaliacao(cliente.getUsuario(), nota, comentario);
                                midia.addAvaliacao(avaliacao);
                                break;
                            case 7:
                                System.out.println(plataformaStreaming.getMidias().toString());
                                System.out.println("Digite o nome da mídia para ser adicionada na lista:");
                                String nomeMidia = teclado.nextLine();
                                cliente.adicionarNaLista(plataformaStreaming.buscarMidia(nomeMidia));
                                break;
                            case 8:
                                System.out.println(cliente.getListaParaVer().toString());
                                System.out.println("Digite o nome da mídia para ser removida da lista:");
                                nomeMidia = teclado.nextLine();
                                cliente.retirarNaLista(nomeMidia);
                                break;
                            case 9:
                                System.out.println("Lista de Mídias:");
                                System.out.println(plataformaStreaming.getMidias().toString());
                                System.out.println("Digite o NOME da mídia para assistir:");
                                nomeMidia = teclado.nextLine();
                                cliente.adicionarNaListaJaVistas(plataformaStreaming.buscarMidia(nomeMidia));
                                break;
                            case 10:
                                plataformaStreaming.logoff();
                                cliente = null;
                                break;
                        }
                    } else
                        System.out.println("Não há nenhum usuário logado.");
                    pausa();
                    break;

            }
        } while (opcao != 0);
    }

}