package codigo.app;

import com.sun.tools.jconsole.JConsoleContext;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLClientInfoException;
import java.sql.SQLOutput;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class PlataformaStreaming {

    private final String nome;
    private Cliente clienteAtual;
    private HashMap<String, Midia> midias;
    private HashMap<String, Cliente> clientes;
//    private Map<Cliente, List<Midia>> seriesPorCliente;
    DecimalFormat formatter = new DecimalFormat("#.0");
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
//        seriesPorCliente = new HashMap<>();
        this.clienteAtual = clienteAtual;
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
     * @param nomeUsuario Nome de usuario
     * @param senha senha
     * @return Cliente da plataforma
     */
    public Cliente login(String nomeUsuario, String senha) {
        String chave = nomeUsuario + ":" + senha;
        clienteAtual = clientes.get(chave);
        return clienteAtual;
    }

    /**
     * Para cada entrada da tabela hash verifica se o genero é correspondente ao passado por parametro.
     * Se o genero for correspondente, a midia é adicionada à lista "midiasPorGenero"
     * A função retorna uma lista de midias que possuem aquele determinado genero
     *
     * @param genero para filtrar as midias
     * @return Lista com midias do genero selecionado
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
     * @return Lista com midias do idioma selecionado
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
     * @return Lista com series que tem a quantidade de episodios selecionada
     */
    public List<Serie> filtrarPorQtdEpisodio(int quantidadeEpisodios) {
        List<Serie> seriesPorEpisodio = new ArrayList<>();
        for (Map.Entry<String, Midia> entrada : midias.entrySet()) {
            try {
                Midia midia = entrada.getValue();
                if (midia instanceof Serie) {
                    Serie serie = (Serie) midia;
                    if (serie.filtrarPorQtdEpisodios(quantidadeEpisodios))
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
     * @return Midia com o nome selecionado
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
     * @param caminho caminho em que o arquivo sera salvo
     * @throws IOException
     */
    public void salvarMidias(String caminho) throws IOException {
        FileWriter writer = new FileWriter(caminho, false);

        for (Map.Entry<String, Midia> entrada : midias.entrySet()) {
            Midia midia = entrada.getValue();
            midia.salvar(caminho);
        }
    }


    /**
     * Salvar Clientes da plataforma e suas respectivas listas de midias 'Para Ver' e historico de midias assistidas
     *
     * @param caminho
     */
    public void salvarClientes(String caminho) throws IOException {
        FileWriter writer = new FileWriter(caminho, false);

        for (Map.Entry<String, Cliente> entrada : clientes.entrySet()) {
            Cliente cliente = entrada.getValue();
            cliente.salvar(caminho);
        }
    }


    /**
     * @param arquivo
     * @throws FileNotFoundException
     */
    public void carregarMidias(String arquivo) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File(arquivo));
        scanner.nextLine();

        while (scanner.hasNextLine()) {
            String linha = scanner.nextLine();
            String[] campos = linha.split(";");
            int id = Integer.parseInt(campos[0]);
            String nome = campos[1];
            String lancamento = campos[2];

            if (arquivo.equals("docs/arquivos/POO_Filmes.csv")) {
                int duracao = Integer.parseInt(campos[3]);
                Midia filme = new Filme(id, nome, "", "", lancamento, duracao);
                adicionarMidia(filme);

            } else if (arquivo.equals("docs/arquivos/POO_Series.csv")){
                Midia serie = new Serie(id, nome, "", "", lancamento, 0);
                adicionarMidia(serie);
            }
        }
        scanner.close();
    }

    /**
     * ler
     *
     * @return
     * @throws FileNotFoundException
     * @author Breno
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

            adicionarCliente(novoCliente);
        }

        scanner.close();
    }

    /**
     * @param arquivo
     * @throws FileNotFoundException
     * @author Breno
     */
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

    public String relatorioClienteMaisMidias(){
        int maisMidias = 0;
        int numMidias = 0;
        Cliente cliente = null;
        String clienteMaisMidias = null;

        for (Map.Entry<String, Cliente> entrada : clientes.entrySet()) {
            cliente = entrada.getValue();
            numMidias = cliente.getListaJaVistas().size();

            if (numMidias > maisMidias) {
                maisMidias = numMidias;
                clienteMaisMidias = cliente.getUsuario();
            }
        }

        StringBuilder aux = new StringBuilder( clienteMaisMidias +
                ", " + maisMidias + " mídias assistidas");
        return aux.toString();
    }

    public String relatorioClienteMaisAvaliacoes(){
        int maisAvaliacoes = 0;
        Midia midia = null;
        HashSet<Avaliacao> avaliacoes = null;
        String  cliente = null;
        String  clienteMaisAvaliacoes = null;

        HashMap<String, Integer> frequencyMap = new HashMap<>();

        for (Map.Entry<String, Midia> entrada : midias.entrySet()) {
            midia = entrada.getValue();
            avaliacoes = midia.getAvaliacoes();


            for (Avaliacao avaliacao : avaliacoes) {
                cliente = avaliacao.getNomeDeUsuario();
                frequencyMap.put(cliente, frequencyMap.getOrDefault(cliente, 0) + 1);
            }
        }

        for (String nomeCliente : frequencyMap.keySet()) {
            int frequency = frequencyMap.get(nomeCliente);
            if (frequency > maisAvaliacoes) {
                maisAvaliacoes = frequency;
                clienteMaisAvaliacoes = nomeCliente;
            }
        }

        StringBuilder aux = new StringBuilder(clienteMaisAvaliacoes +
                ", " + maisAvaliacoes + " avaliações");
        return aux.toString();
    }
    public String relatorioClientes15Avaliacoes(){
        Midia midia = null;
        HashSet<Avaliacao> avaliacoes = null;
        String  cliente = null;
        Integer frequencia = null;
        int cont = 0;
        int numAvaliacoes = 15;
        HashMap<String, Integer> frequencyMap = new HashMap<>();

        for (Map.Entry<String, Midia> entrada : midias.entrySet()) {
            midia = entrada.getValue();
            avaliacoes = midia.getAvaliacoes();


            for (Avaliacao avaliacao : avaliacoes) {
                cliente = avaliacao.getNomeDeUsuario();
                frequencyMap.put(cliente, frequencyMap.getOrDefault(cliente, 0) + 1);
            }
        }

        for (Map.Entry<String, Integer> entrada : frequencyMap.entrySet()) {
            frequencia = entrada.getValue();

            if(frequencia >= numAvaliacoes)  {
                cont++;
            }
        }

        double totalClientes = frequencyMap.size();
        double porcentagem = (cont / totalClientes) * 100;

        StringBuilder aux = new StringBuilder(formatter.format(porcentagem) + "% dos clientes possuem " + numAvaliacoes + " ou mais avaliações");
        return aux.toString();
    }

    public Cliente buscarCliente(String login) {
        return clientes.get(login);
    }

    public HashMap<String, Cliente> getClientes() {
        return clientes;
    }

    public Cliente getClienteAtual() {
        return clienteAtual;
    }

    public HashMap<String, Midia> getMidias() {
        return midias;
    }
}