package codigo.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

public class PlataformaStreaming {

    private String nome;
    private Cliente clienteAtual;
    private HashMap<String, Midia> midias;
    private HashMap<String, Cliente> clientes;
    private List<Midia> catalogoMidias;
    private Map<Cliente, List<Midia>> seriesPorCliente;
    /**
     * Criar uma nova plataforma
     * Cria uma tabela hash vazia
     *
     * @param nome da plataforma
     */
    public PlataformaStreaming(String nome) {
        this.nome = nome;
        clientes = new HashMap<>();
        midias = new HashMap<>();
        this.catalogoMidias = new ArrayList<>();
        seriesPorCliente = new HashMap<>();
    }

    /**
     * gera uma chave aleatória a patir da concatenação de usuário e senha.
     * adiciona na tabela hash uma chave associada ao cliente e o cliente
     *
     * @param cliente com os atributos senha e usuario
     */
    public void adicionarCliente(Cliente cliente) {
        String chave = cliente.getUsuario() + ":" + cliente.getSenha();
        clientes.put(chave, cliente);
    }

    /**
     * Gera uma chave aleatória a patir da concatenação de nome e genero.
     * Adiciona na tabela hash uma chave associada à midia.
     *
     * @param midia com os atributos nome, genero, idioma, audiencia, dataDeLancamento
     */
    public void adicionarMidia(Midia midia) {
        String chave = midia.getNome() + ":" + midia.getGenero();
        midias.put(chave, midia);
    }

    /**
     * Encontrar um cliente que possui a mesma chave que essa pesquisa: concatenação entre usuario e senha fornecidos por parametro
     *
     * @param nomeUsuario
     * @param senha
     * @return
     */
    public Cliente login(String nomeUsuario, String senha) {
        String chave = nomeUsuario + ":" + senha;
        clienteAtual = clientes.get(chave);
        return clientes.get(chave);
    }

    /**
     * Para cada entrada da tabela hash verifica se o genero é correspondente ao passado por parametro.
     * Se o genero for correspondente, a midia é adicionada à lista "midiasPorGenero"
     * A função retorna uma lista de midias que possuem aquele determinado genero
     *
     * @param genero para filtrar as midias
     * @return
     */
    public List<Midia> filtrarPorGenero(String genero) {
        List<Midia> midiasPorGenero = new ArrayList<>();
        for (Map.Entry<String, Midia> entrada : midias.entrySet()) {
            Midia midia = entrada.getValue();
            if (midia.filtrarPorGenero(genero))
                midiasPorGenero.add(midia);
        }
        return midiasPorGenero;
    }

    /**
     * Para cada entrada da tabela hash verifica se o idioma é correspondente ao passado por parametro.
     * Se o idioma for correspondente, a midia é adicionada à lista "midiasPorIdioma"
     * A função retorna uma lista de midia que possuem aquele determinado idioma
     *
     * @param idioma para filtrar as midias
     * @return
     */
    public List<Midia> filtrarPorIdioma(String idioma) {
        List<Midia> midiasPorIdioma = new ArrayList<>();
        for (Map.Entry<String, Midia> entrada : midias.entrySet()) {
            Midia midia = entrada.getValue();
            if (midia.filtrarPorIdioma(idioma))
                midiasPorIdioma.add(midia);
        }
        return midiasPorIdioma;
    }

    /**
     * Para cada entrada da tabela hash verifica se a quantidade de episodios é correspondente ao passado por parametro.
     * Se a quantidade de episodios for correspondente, a serie é adicionada à lista "seriesPorEpisodio"
     * A função retorna uma lista de serie que possuem aquela determinada quantidade de episodios
     *
     * @param quantidadeEpisodios de episodios para filtrar as series
     * @return
     */
    public List<Serie> filtrarPorQtdEpisodio(int quantidadeEpisodios) {
        List<Serie> seriesPorEpisodio = new ArrayList<>();
        for (Map.Entry<String, Midia> entrada : midias.entrySet()) {
            try {
                Midia midia = entrada.getValue();
                if (midia instanceof Serie) {
                    Serie serie = (Serie) midia;
                    if (((Serie) serie).filtrarPorQtdEpisodios(quantidadeEpisodios))
                        seriesPorEpisodio.add(serie);
                }
            } catch (ClassCastException e) {
            }
        }
        return seriesPorEpisodio;
    }

    /**
     * Para cada entrada da tabela hash verifica se o nome é correspondente ao passado por parametro.
     * Se o nome for correspondente, a midia é retornada
     * Caso nenhuma midia possua o nome equivalente, a função retorna nulo
     *
     * @param nome da midia buscada
     * @return
     */
    public Midia buscarMidia(String nome) {
        for (Map.Entry<String, Midia> entrada : midias.entrySet()) {
            Midia midia = entrada.getValue();
            if (midia.getNome().equalsIgnoreCase(nome)) {
                return midia;
            }
        }
        return null;
    }

    /**
     * Toda vez que um usuário assistir uma midia, será adicionado uma audiência à audiência da midia
     *
     * @param midia escolhida pelo usuário para assistir
     */
    public void registrarAudiencia(Midia midia) {
        for (Map.Entry<String, Midia> entrada : midias.entrySet()) {
            Midia aux = entrada.getValue();
            if (aux.getNome().equalsIgnoreCase(midia.getNome())) {
                midia.registrarAudiencia();
            }
        }
    }

    /**
     * Quando o cliente fizer logout de sua conta, o clienteAtual será setado para null
     */
    public void logoff() {
        clienteAtual = null;
    }

    /**
     * @param caminho
     * @throws IOException
     */
    public void salvar(String caminho) throws IOException {
        FileWriter writer = new FileWriter(caminho, false);

        for (Map.Entry<String, Midia> entrada : midias.entrySet()) {
            Midia midia = entrada.getValue();
            midia.salvar(caminho);
        }
    }

    /**
     * Adicionar a Serie que o cliente assistiu ou futuramente vai assistir.
     * @param cliente
     * @param serie
     */
    public void adicionarSerieParaCliente(Cliente cliente, Serie serie) {
        List<Midia> seriesCliente = seriesPorCliente.getOrDefault(cliente, new ArrayList<>());
        seriesCliente.add(serie);
        seriesPorCliente.put(cliente, seriesCliente);
    }

    /**
     * Este código Java salva uma lista de programas de TV (Midia) associados a cada usuário (Cliente) em um arquivo.
     * Ele percorre um mapa contendo os dados e os grava no arquivo usando um BufferedWriter. O método usa o nome do
     * arquivo como um parâmetro de entrada e imprime uma mensagem de sucesso ou mensagem de erro se uma exceção for
     * lançada.
     * @param nomeArquivo
     */
    public void salvarSeriesPorClienteEmArquivo(String nomeArquivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
            for (Map.Entry<Cliente, List<Midia>> entry : seriesPorCliente.entrySet()) {
                Cliente cliente = entry.getKey();
                List<Midia> series = entry.getValue();

                for (Midia serie : series) {
                    String lista = "";
                    if (cliente.getListaParaVer().contains(serie)) {
                        lista = "F";
                    } else if (cliente.getListaJaVistas().contains(serie)) {
                        lista = "A";
                    }

                    String linha = cliente.getLogin() + ";" + lista + ";" + serie.getId() + "\n";
                    writer.write(linha);
                }
            }

            System.out.println("Séries por cliente salvas com sucesso em: " + nomeArquivo);
        } catch (IOException e) {
            System.out.println("Erro ao salvar as séries por cliente em arquivo: " + e.getMessage());
        }
    }

    /**
     * @param arquivo
     * @throws FileNotFoundException
     */
    public void carregarMidias(String arquivo) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File(arquivo));
//        scanner.nextLine();

        while (scanner.hasNextLine()) {
            String linha = scanner.nextLine();
            String[] campos = linha.split(";");
            String tipoMidia = campos[0];
            int id = Integer.parseInt(campos[1]);
            String nome = campos[2];
            String genero = campos[3];
            String idioma = campos[4];
            String lancamento = campos[5];

            if (tipoMidia.equals("F")) {
                int duracao = Integer.parseInt(campos[6]);
                Midia filme = new Filme(id, nome, genero, idioma, lancamento, duracao);
                catalogoMidias.add(filme);

            } else if (tipoMidia.equals("S")) {
                int qtdEpisodios = Integer.parseInt(campos[6]);
                Midia serie = new Serie(id, nome, genero, idioma, lancamento, qtdEpisodios);
                catalogoMidias.add(serie);
            }
        }
        scanner.close();
    }

    /**
     * ler
     *
     * @return
     * @throws FileNotFoundException
     */
    public void carregarClientes(String arquivo) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File(arquivo));

        while (scanner.hasNextLine()) {
            String linha = scanner.nextLine();
            String[] dados = linha.split(";");

            String nomeDeUsuario = dados[0];
            String login = dados[1];
            String senha = dados[2];

            Cliente novoCliente = new Cliente(nomeDeUsuario, login, senha);

            clientes.put(login, novoCliente);
        }

        scanner.close();
    }

    public void carregarAudiencia(String arquivo) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File(arquivo));

        while (scanner.hasNextLine()) {
            String linha = scanner.nextLine();

            String[] dados = linha.split(";");
            Cliente cliente = buscarCliente(dados[0]);

            if (cliente != null) {

                Midia midia = midias.get(dados[2]);
                if (midia != null) {

                    if (dados[1].equals("F"))  // quero ver
                        cliente.adicionarNaLista(midia);

                    else if (dados[1].equals("A")) { // historico

                        Date dataAtual = new Date();
                        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
                        String dataFormatada = formatoData.format(dataAtual);

                        cliente.registrarPorAudiencia(midia, dataFormatada);
                    }
                }

            }
        }
        scanner.close();
    }

    private Cliente buscarCliente(String login) {
        return clientes.get(login);
    }

    /**
     * @return
     */
    public String midias() {
        return catalogoMidias.toString();
    }

    public HashMap<String, Cliente> getClientes() {
        return clientes;
    }

    public Cliente getClienteAtual() {
        return clienteAtual;
    }
}