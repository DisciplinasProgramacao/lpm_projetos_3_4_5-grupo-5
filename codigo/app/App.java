package codigo.app;

import java.util.Scanner;

public class App {
    static Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {
        PlataformaStreaming plataformaStreaming = new PlataformaStreaming("Plataforma de Streaming");
        int opcao = -1;

        do {
            opcao = menu();
            switch (opcao) {
                case 1:
//                    plataformaStreaming.carregarSeries(caminho);
                    break;
                case 2:
//                    plataformaStreaming.carregarFilmes(caminho);
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
            }
        } while (opcao != 0);
    }

    private static void cadastrarSerie() {

        System.out.println("Cadastro de Serie:");
        System.out.println("Digite o nome:");
        System.out.println("Digite o gênero:");
        System.out.println("Digite o idioma:");
        System.out.println("Digite a data de lançamento:");
        System.out.println("Digite a quantidade de episodios:");

//        Midia serie = new Serie();

    }

    private static void cadastrarFilme() {

        System.out.println("Cadastro de Filme:");
        System.out.println("Digite o nome:");
        System.out.println("Digite o gênero:");
        System.out.println("Digite o idioma:");
        System.out.println("Digite a data de lançamento:");
        System.out.println("Digite a duração (em minutos):");

//        Midia filme = new Filme();

    }

    private static void cadastrarUsuario() {
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
                "\n10 - Exibir catálago de filmes e séries" +
                "\n---------------------" +
                "\nDigite sua opção: ");

        return Integer.parseInt(teclado.nextLine());
    }

}
