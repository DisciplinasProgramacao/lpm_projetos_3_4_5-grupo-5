package codigo.app;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Scanner;

public class App {
    static Scanner teclado = new Scanner(System.in);

    private static int menu() {
        limparTela();
        System.out.println("Menu:" +
                "\n---------------------" +
                "\n0 - Sair" +
                "\n1 - Criar usuário" +
                "\n2 - Carregar Usuários" +
                "\n3 - Login" +
                "\n---------------------" +
                "\nDigite sua opção: ");

        return Integer.parseInt(teclado.nextLine());
    }

    private static int subMenuCliente() {
        limparTela();
        System.out.println("" +
                "\n---------------------" +
                "\n4 - Visualizar histórico (mídias já assistidas)" +
                "\n5 - Visualizar lista de mídias 'Para Ver'" +
                "\n6 - Filtrar midias por genero" +
                "\n7 - Filtrar midias por idioma" +
                "\n8 - Filtrar séries por quantidade de episódios" +
                "\n9 - Avaliar mídia" +
                "\n10 - Adicionar mídia na lista 'Para Ver'" +
                "\n11 - Remover mídia da lista 'Para Ver'" +
                "\n12 - Assistir mídia (adiciona no hitórico)" +
                "\n13 - Salvar" +
                "\n14 - Logoff" +
                "\n---------------------" +
                "\nDigite sua opção: ");

        return Integer.parseInt(teclado.nextLine());
    }

    private static int subMenuPlataformaStreaming() {

        System.out.println("" +
                "\n---------------------" +
                "\n15 - Listar mídias" +
                "\n16 - Carregar mídias" +
                "\n17 - Carregar usuários" +
                "\n18 - Cadastrar série" +
                "\n19 - Cadastrar filme" +
                "\n20 - Cadastrar usuário" +
                "\n21 - Salvar mídias" +
                "\n22 - Salvar usuários" +
                "\n23 - Buscar mídia" +
                "\n24 - Filtrar mídias por gênero" +
                "\n25 - Filtrar mídias por idioma" +
                "\n26 - Filtrar series por quantidade de episodios" +
                "\n27 - Filtrar filmes por duração" +
                "\n---------------------" +
                "\nDigite sua opção: ");


        return Integer.parseInt(teclado.nextLine());
    }

    /**
     * @return
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

        Midia serie = new Serie(id, nome, genero, idioma, dtLancamento, qtdEp);

        return serie;
    }

    /**
     * @return
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

        Midia filme = new Filme(id, nome, genero, idioma, dtLancamento, duracao);

        return filme;
    }

    /**
     * @return
     */
    private static Cliente cadastrarUsuario() {

        System.out.println("Cadastro de Usuário:");
        System.out.println("Digite o nome de usuário:");
        String nome = teclado.nextLine();
        System.out.println("Digite o login do usuário:");
        String login = teclado.nextLine();
        System.out.println("Digite a senha:");
        String senha = teclado.nextLine();

        Cliente usuario = new Cliente(nome, login, senha);

        return usuario;
    }

    /**
     * "Limpa" a tela (códigos de terminal VT-100)
     */
    public static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void main(String[] args) throws FileNotFoundException {
        PlataformaStreaming plataformaStreaming = new PlataformaStreaming("Plataforma de Streaming");
        Cliente cliente = null;
        int opcao = -1;

        do {
            opcao = menu();

            // ATENCAO
            //mudar chamada dos menus -> os outros dois só podem ser chamados caso o
            //cliente esteja logado


            switch (opcao) {
                case 1:
                    Cliente usuario = cadastrarUsuario();
                    plataformaStreaming.adicionarCliente(usuario);
                    break;
                case 2, 17:
                    plataformaStreaming.carregarClientes("docs/arquivos/POO_Espectadores.csv");
                    break;
                case 3:
                    System.out.println("Digite o nome de usuário:");
                    String nameUser = teclado.nextLine();
                    System.out.println("Digite a senha:");
                    String senha = teclado.nextLine();
                    cliente = plataformaStreaming.login(nameUser, senha);
                    break;
                case 4:
                    System.out.println(cliente.getListaJaVistas().toString());
                    break;
                case 5:
                    System.out.println(cliente.getListaParaVer().toString());
                    break;
                case 6:
                    System.out.println("Digite o gênero:");
                    String genero = teclado.nextLine();
                    System.out.println(cliente.filtrarPorGenero(genero));
                    break;
                case 7:
                    System.out.println("Digite o idioma:");
                    String idioma = teclado.nextLine();
                    System.out.println(cliente.filtrarPorIdioma(idioma));
                    break;
                case 8:
                    System.out.println("Digite a quantidade de episódios:");
                    int qtdEp = Integer.parseInt(teclado.nextLine());
                    System.out.println(cliente.filtrarPorQtdEpisodios(qtdEp));
                    break;
                case 9:

                    //dica-> encapsular metodo para mostrar listas e selecionar o id

                    // avaliar -> escolher midia da lista das midias ja assistidas, mostrar midias -> perguntar id
                    // selecionar midia pelo id -> perguntar nota e comentario
                    // chamar metodo
                    break;
                case 10:
                    // escolher midia da lista das midias da plataforma, mostrar midias -> perguntar id
                    // selecionar midia pelo id
                    // chamar metodo
                    System.out.println(plataformaStreaming.getMidias().toString());
                    System.out.println("Digite o nome da mídia para ser adicionada na lista:");
                    String nomeMidia = teclado.nextLine();
                    cliente.adicionarNaLista(plataformaStreaming.buscarMidia(nomeMidia));
                    break;
                case 11:
                    // escolher midia da lista das midias da lista para ver, mostrar midias -> perguntar id
                    // selecionar midia pelo id
                    // chamar metodo
                    System.out.println(cliente.getListaParaVer().toString());
                    System.out.println("Digite o nome da mídia para ser removida da lista:");
                    nomeMidia = teclado.nextLine();
                    cliente.retirarNaLista(nomeMidia);
                    break;
                case 12:
                    // escolher midia da lista das midias da plataforma, mostrar midias -> perguntar id
                    // selecionar midia pelo id
                    // chamar metodo
                    System.out.println("Lista de Mídias:");
                    System.out.println(plataformaStreaming.getMidias().toString());
                    System.out.println("Escolha o NOME de uma mídia para assistir:");
                    nomeMidia = teclado.nextLine();
                    cliente.adicionarNaListaJaVistas(plataformaStreaming.buscarMidia(nomeMidia));
                    break;
                case 13:
                    // nao sei se pode fazer isso ou a gnt escolhe o caminho pra ele
                    System.out.println("Digite o caminho para salvar o arquivo:");
                    String caminho = teclado.nextLine();
                    cliente.salvar(caminho);
                    break;
                case 14:
                    plataformaStreaming.logoff();
                    break;
                case 15:
                    // pode chamar o metodo encapsulado ou fazer os cases do cliente chamarem esse
                    System.out.println(plataformaStreaming.getMidias().toString());
                    break;
                case 16:
                    plataformaStreaming.carregarMidias("docs/arquivos/POO_Filmes.csv");
                    plataformaStreaming.carregarMidias("docs/arquivos/POO_Series.csv");
                    break;
                case 18:
                    Midia serie = cadastrarSerie();
                    plataformaStreaming.adicionarMidia(serie);
                    break;
                case 19:
                    Midia filme = cadastrarFilme();
                    plataformaStreaming.adicionarMidia(filme);
                    break;
                case 20:
                    usuario = cadastrarUsuario();
                    plataformaStreaming.adicionarCliente(usuario);
                    break;
                case 21: //REVISAR
                    try {
                        plataformaStreaming.salvarMidias("MinhasMidias");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    break;
                case 22: //REVISAR
                    try {
                        plataformaStreaming.salvarClientes("MeusClientes");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 23:
                    System.out.println("Digite o nome da midia:");
                    String midia = teclado.nextLine();
                    System.out.println(plataformaStreaming.buscarMidia(midia).toString());
                    // se n achar, dar alguma mensagem?
                    break;
                case 24:
                    System.out.println("Digite o gênero:");
                    genero = teclado.nextLine();
                    System.out.println(plataformaStreaming.filtrarPorGenero(genero));
                    break;
                case 25:
                    System.out.println("Digite o idioma:");
                    idioma = teclado.nextLine();
                    System.out.println(plataformaStreaming.filtrarPorIdioma(idioma));
                    break;
                case 26:
                    System.out.println("Digite a quantidade de episódios:");
                    qtdEp = Integer.parseInt(teclado.nextLine());
                    System.out.println(plataformaStreaming.filtrarPorQtdEpisodio(qtdEp));
                    break;
                case 27:
                    // fazer metodo no filme? nao sei se precisa
                    // chamar pela plataforma
                    break;
            }
        } while (opcao != 0);
    }

}
