package codigo.app;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class App {
//    static Scanner teclado = new Scanner(System.in);
//
//    private static int menu() {
//        limparTela();
//        System.out.println("Menu:" +
//                "\n---------------------" +
//                "\n0 - Sair" +
//                "\n1 - Criar usuário" +
//                "\n2 - Carregar Usuários" +
//                "\n3 - Login" +
//                "\n---------------------" +
//                "\nDigite sua opção: ");
//
//        return Integer.parseInt(teclado.nextLine());
//    }
//
//    private static int subMenuCliente() {
//        limparTela();
//        System.out.println("" +
//                "\n---------------------" +
//                "\n4 - Visualizar histórico (mídias já assistidas)" +
//                "\n5 - Visualizar lista de mídias 'Para Ver'" +
//                "\n6 - Filtrar midias por genero" +
//                "\n7 - Filtrar midias por idioma" +
//                "\n8 - Filtrar séries por quantidade de episódios" +
//                "\n9 - Avaliar mídia" +
//                "\n10 - Adicionar mídia na lista 'Para Ver'" +
//                "\n11 - Remover mídia da lista 'Para Ver'" +
//                "\n12 - Assistir mídia (adiciona no hitórico)" +
//                "\n13 - Salvar" +
//                "\n14 - Logoff" +
//                "\n---------------------" +
//                "\nDigite sua opção: ");
//
//        return Integer.parseInt(teclado.nextLine());
//    }
//
//    private static int subMenuPlataformaStreaming() {
//
//        System.out.println("" +
//                "\n---------------------" +
//                "\n15 - Listar mídias" +
//                "\n16 - Carregar mídias" +
//                "\n17 - Carregar usuários" +
//                "\n18 - Cadastrar série" +
//                "\n19 - Cadastrar filme" +
//                "\n20 - Cadastrar usuário" +
//                "\n21 - Salvar mídias" +
//                "\n22 - Salvar usuários" +
//                "\n23 - Buscar mídia" +
//                "\n24 - Filtrar mídias por gênero" +
//                "\n25 - Filtrar mídias por idioma" +
//                "\n26 - Filtrar filmes por duração" +
//                "\n---------------------" +
//                "\nDigite sua opção: ");
//
//
//        return Integer.parseInt(teclado.nextLine());
//    }
//
//    /**
//     * @return
//     */
//    private static Midia cadastrarSerie() {
//
//        System.out.println("Cadastro de Serie:");
//        System.out.println("Digite o id:");
//        int id = Integer.parseInt(teclado.nextLine());
//        System.out.println("Digite o nome:");
//        String nome = teclado.nextLine();
//        System.out.println("Digite o gênero:");
//        String genero = teclado.nextLine();
//        System.out.println("Digite o idioma:");
//        String idioma = teclado.nextLine();
//        System.out.println("Digite a data de lançamento:");
//        String dtLancamento = teclado.nextLine();
//        System.out.println("Digite a quantidade de episodios:");
//        int qtdEp = Integer.parseInt(teclado.nextLine());
//
//        Midia serie = new Serie(id, nome, genero, idioma, dtLancamento, qtdEp);
//
//        return serie;
//    }
//
//    /**
//     * @return
//     */
//    private static Midia cadastrarFilme() {
//
//        System.out.println("Cadastro de Filme:");
//        System.out.println("Digite o id:");
//        int id = Integer.parseInt(teclado.nextLine());
//        System.out.println("Digite o nome:");
//        String nome = teclado.nextLine();
//        System.out.println("Digite o gênero:");
//        String genero = teclado.nextLine();
//        System.out.println("Digite o idioma:");
//        String idioma = teclado.nextLine();
//        System.out.println("Digite a data de lançamento:");
//        String dtLancamento = teclado.nextLine();
//        System.out.println("Digite duração em minutos:");
//        int duracao = Integer.parseInt(teclado.nextLine());
//
//        Midia filme = new Filme(id, nome, genero, idioma, dtLancamento, duracao);
//
//        return filme;
//    }
//
//    /**
//     * @return
//     */
//    private static Cliente cadastrarUsuario() {
//
//        System.out.println("Cadastro de Usuário:");
//        System.out.println("Digite o nome de usuário:");
//        String nome = teclado.nextLine();
//        System.out.println("Digite o login do usuário:");
//        String login = teclado.nextLine();
//        System.out.println("Digite a senha:");
//        String senha = teclado.nextLine();
//
//        Cliente usuario = new Cliente(nome, login, senha);
//
//        return usuario;
//    }
//
//    /**
//     * "Limpa" a tela (códigos de terminal VT-100)
//     */
//    public static void limparTela() {
//        System.out.print("\033[H\033[2J");
//        System.out.flush();
//    }
//
//    public static void main(String[] args) throws FileNotFoundException {
//        PlataformaStreaming plataformaStreaming = new PlataformaStreaming("Plataforma de Streaming");
//        Cliente cliente;
//        int opcao = -1;
//
//        do {
//            opcao = menu();
//            switch (opcao) {
//                case 1:
//                    Cliente usuario = cadastrarUsuario();
//                    plataformaStreaming.adicionarCliente(usuario);
//                    break;
//                case 2:
//                    System.out.println("Digite o caminho do arquivo a ser lido:");
//                    String caminho = teclado.nextLine();
//                    plataformaStreaming.carregarClientes(caminho);
//                    break;
//                case 3:
//                    System.out.println("Digite o caminho do arquivo a ser lido:");
//                    String caminho = teclado.nextLine();
//                    break;
//                case 4:
//                    Cliente
//                    break;
//                case 5:
//
//                    break;
//                case 6:
//
//                    break;
//                case 7:
//                    // plataformaStreaming.salvarSeries(caminho);
//                    break;
//                case 8:
//                    // plataformaStreaming.salvarFilmes(caminho);
//                    break;
//                case 9:
//                    // plataformaStreaming.salvarUsuarios(caminho);
//                    break;
//                case 10:
//                    System.out.println("Digite o gênero:");
//                    String genero = teclado.nextLine();
//                    System.out.println(plataformaStreaming.filtrarPorGenero(genero).toString());
//                    break;
//                case 11:
//                    System.out.println("Digite o idioma:");
//                    String idioma = teclado.nextLine();
//                    System.out.println(plataformaStreaming.filtrarPorIdioma(idioma).toString());
//                    break;
//                case 12:
//                    System.out.println("Digite a quantidade de episódios:");
//                    int qtdEp = Integer.parseInt(teclado.nextLine());
//                    System.out.println(plataformaStreaming.filtrarPorQtdEpisodio(qtdEp).toString());
//                    break;
//                case 13:
//                    // Caso 13
//                    break;
//                case 14:
//                    // Caso 14
//                    break;
//                case 15:
//                    // Caso 15
//                    break;
//                case 16:
//                    System.out.println("Digite o caminho do arquivo a ser lido:");
//                    String caminho = teclado.nextLine();
//                    plataformaStreaming.carregarMidias(caminho);
//                    break;
//                case 17:
//                    // Caso 17
//                    break;
//                case 18:
//                    Midia serie = cadastrarSerie();
//                    plataformaStreaming.adicionarMidia(serie);
//                    break;
//                case 19:
//                    Midia filme = cadastrarFilme();
//                    plataformaStreaming.adicionarMidia(filme);
//                    break;
//                case 20:
//                    // Caso 20
//                    break;
//                case 21:
//                    // Caso 21
//                    break;
//                case 22:
//                    // Caso 22
//                    break;
//                case 23:
//                    // Caso 23
//                    break;
//                case 24:
//                    // Caso 24
//                    break;
//                case 25:
//                    // Caso 25
//                    break;
//                case 26:
//                    // Caso 25
//                    break;
//            }
//        } while (opcao != 0);
//    }

}
