package codigo.app;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class App {
    static Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) throws FileNotFoundException {
        PlataformaStreaming plataformaStreaming = new PlataformaStreaming("Plataforma de Streaming");
        int opcao = -1;

        do {
            opcao = menu();
            switch (opcao) {
                case 1:
//                    plataformaStreaming.lerArquivosSeries();
                    break;
                case 2:
//                    plataformaStreaming.
                    break;
                case 3:
//                    plataformaStreaming.carregarUsuarios(caminho);
                    break;
                case 4:
                    cadastrarSerie();
                    break;
                case 5:
                    cadastrarFilme();
                    break;
                case 6:
                    cadastrarUsuario();
                    break;
                case 7:
//                    plataformaStreaming.salvarSeries(caminho);
                    break;
                case 8:
//                    plataformaStreaming.salvarFilmes(caminho);
                    break;
                case 9:
//                    plataformaStreaming.salvarUsuarios(caminho);
                    break;
                case 10:
                    System.out.println("Digite o genero:");
                    String genero = teclado.nextLine();
                    System.out.println(plataformaStreaming.filtrarPorGenero(genero).toString());
                    break;
                case 11:
                    System.out.println("Digite o idioma:");
                    String idioma = teclado.nextLine();
                    System.out.println(plataformaStreaming.filtrarPorIdioma(idioma).toString());
                    break;
                case 12:
                    System.out.println("Digite a quantidade de episódios:");
                    int qtdEp = Integer.parseInt(teclado.nextLine());
                    System.out.println(plataformaStreaming.filtrarPorQtdEpisodio(qtdEp).toString());
                    break;
            }
        } while (opcao != 0);
    }

    private static void cadastrarSerie() {

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

    }

    private static void cadastrarFilme() {

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

    }

    private static void cadastrarUsuario() {

        System.out.println("Cadastro de Usuário:");
        System.out.println("Digite o nome de usuário:");
        String nome = teclado.nextLine();
        System.out.println("Digite o login do usuário:");
        String login = teclado.nextLine();
        System.out.println("Digite a senha:");
        String senha = teclado.nextLine();

        Cliente usuario = new Cliente(nome, login, senha);

    }

    private static int menu() {

        System.out.println("Menu:" +
                "\n---------------------" +
                "\n0 - Sair" +
                "\n1 - Carregar Séries" +
                "\n2 - Carregar Filmes" +
                "\n3 - Carregar Usuários" +
                "\n4 - Cadastrar Série" +
                "\n5 - Cadastrar Filme" +
                "\n6 - Cadastrar Usuário" +
                "\n7 - Salvar Séries" +
                "\n8 - Salvar Filmes" +
                "\n9 - Salvar Usuários" +
                "\n10 - Filtrar midias por genero" +
                "\n11 - Filtrar midias por idioma" +
                "\n12 - Filtrar séries por quantidade de episódios" +
                "\n---------------------" +
                "\nDigite sua opção: ");

        return Integer.parseInt(teclado.nextLine());
    }

}
