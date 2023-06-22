package codigo.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

public class PlataformaStreaming {

    private final String nome;
    private Cliente clienteAtual;
    private Administrador admAtual;
    private HashMap<String, Midia> midias;
    private HashMap<String, Cliente> clientes;
    private HashMap<String, Administrador> administradores;
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
        administradores = new HashMap<>();
        midias = new HashMap<>();
    }

    /**
     * gera uma chave aleatória a patir da concatenação de usuário e senha.
     * adiciona na tabela hash uma chave associada ao cliente e o cliente
     *
     * @param cliente com os atributos senha e usuario
     */
    public void adicionarCliente(Cliente cliente) {
        String chave = cliente.getLogin() + ":" + cliente.getSenha();
        clientes.put(chave, cliente);
    }

    /**
     * gera uma chave aleatória a patir da concatenação de usuário e senha.
     * adiciona na tabela hash uma chave associada ao administrador
     *
     * @param adm com os atributos senha e usuario
     */
    public void adicionarAdministrador(Administrador adm) {
        String chave = adm.getUsuario() + ":" + adm.getSenha();
        administradores.put(chave, adm);
    }

    /**
     * Gera uma chave aleatória a patir da concatenação de nome e genero.
     * Adiciona na tabela hash uma chave associada à midia.
     *
     * @param midia com os atributos nome, genero, idioma, audiencia, dataDeLancamento
     */
    public void adicionarMidia(Midia midia) {
        String chave = midia.getNome() + ":" + midia.getId();
        midias.put(chave, midia);
    }

    /**
     * Encontrar um cliente que possui a mesma chave que essa pesquisa: concatenação entre usuario e senha fornecidos por parametro
     *
     * @param login Nome de usuario
     * @param senha senha
     * @return Cliente da plataforma
     */
    public Cliente loginCliente(String login, String senha) {
        String chave = login + ":" + senha;

        try {
            clienteAtual = clientes.get(chave);
            if (clienteAtual != null)
                return clienteAtual;
        } catch (NullPointerException e) {
            System.out.println("Usuário não encontrado!");
        }
        return null;
    }

    /**
     * Encontrar um administrador que possui a mesma chave que essa pesquisa: concatenação entre usuario e senha fornecidos por parametro
     *
     * @param nomeUsuario Nome de usuario
     * @param senha       senha
     * @return Administrador da plataforma
     */
    public Administrador loginAdministrador(String nomeUsuario, String senha) {
        String chave = nomeUsuario + ":" + senha;

        try {
            admAtual = administradores.get(chave);
            if (admAtual != null)
                return admAtual;

        } catch (NullPointerException e) {
            System.out.println("Administrador não encontrado!");
        }
        return null;
    }

    /**
     * Para cada entrada da tabela hash verifica se o genero é correspondente ao passado por parametro.
     * Se o genero for correspondente, a midia é adicionada à lista "midiasPorGenero"
     * A função retorna uma lista de midias que possuem aquele determinado genero
     *
     * @param genero para filtrar as midias
     * @return Lista com midias do genero selecionado
     */
    public List<Midia> filtrarPorGenero(Genero genero) {
        List<Midia> midiasPorGenero = new ArrayList<>();

        try {
            for (Map.Entry<String, Midia> entrada : midias.entrySet()) {
                Midia midia = entrada.getValue();
                if (midia.filtrarPorGenero(genero))
                    midiasPorGenero.add(midia);
            }

            if (!(midiasPorGenero.isEmpty()))
                return midiasPorGenero;

        } catch (NullPointerException e) {
            System.out.println("Não há mídias para essa seleção!");
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
    public List<Midia> filtrarPorIdioma(Idioma idioma) {
        List<Midia> midiasPorIdioma = new ArrayList<>();

        try {
            for (Map.Entry<String, Midia> entrada : midias.entrySet()) {
                Midia midia = entrada.getValue();
                if (midia.filtrarPorIdioma(idioma))
                    midiasPorIdioma.add(midia);
            }

            if (!(midiasPorIdioma.isEmpty()))
                return midiasPorIdioma;

        } catch (NullPointerException e) {
            System.out.println("Não há mídias para essa seleção!");
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

        try {

            for (Map.Entry<String, Midia> entrada : midias.entrySet()) {
                try {
                    Midia midia = entrada.getValue();
                    if (midia instanceof Serie serie) {
                        if (serie.filtrarPorQtdEpisodios(quantidadeEpisodios))
                            seriesPorEpisodio.add(serie);
                    }
                } catch (ClassCastException e) {
                }
            }

            if (!(seriesPorEpisodio.isEmpty()))
                return seriesPorEpisodio;

        } catch (NullPointerException e) {
            System.out.println("Não há séries para essa seleção!");
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

        try {
            for (Map.Entry<String, Midia> entrada : midias.entrySet()) {
                Midia midia = entrada.getValue();
                if (midia.getNome().equalsIgnoreCase(nome)) {
                    return midia;
                }
            }

        } catch (NullPointerException e) {
            System.out.println("Mídia não encontrada!");
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
    public void logoffCliente() {
        clienteAtual = null;
    }

    /**
     * Quando o administrador fizer logout de sua conta
     */
    public void logoffAdministrador() {
        admAtual = null;
    }

    /**
     * @param caminho caminho em que o arquivo sera salvo
     * @throws IOException
     */
    public void salvarMidias(String caminho) throws IOException {
        for (Map.Entry<String, Midia> entrada : midias.entrySet()) {
            Midia midia = entrada.getValue();
            midia.salvar(caminho);
        }
    }


    /**
     * Salvar Clientes da plataforma
     *
     * @param caminho
     */
    public void salvarClientes(String caminho) throws IOException {

        for (Map.Entry<String, Cliente> entrada : clientes.entrySet()) {
            entrada.getValue().salvarClientes(caminho);
        }
    }

    /**
     * Salvar listas de midias 'Para Ver' e historico de midias assistidas os clientes
     *
     * @param caminho
     */
    public void salvarAudiencia(String caminho) throws IOException {

        for (Map.Entry<String, Cliente> entrada : clientes.entrySet()) {
            entrada.getValue().salvarAudiencia(caminho);
        }
    }

    /**
     * * Le arquivo e separa cada atributo
     * * cria midia, sendo filme e serie
     * * Adiciona cada tipo de midia ao hash
     * *
     *
     * @param arquivo
     * @throws FileNotFoundException
     */
    public void carregarMidias(String arquivo) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File(arquivo));

        try {
            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                String[] campos = linha.split(";");
                String tipo = campos[0];
                int id = Integer.parseInt(campos[1]);
                String nome = campos[2];
                String lancamento = campos[3];

                Genero genero = null;
                String generoDigitado = campos[4];

                for (Genero valor : Genero.values()) {
                    if (valor.getNome().equalsIgnoreCase(generoDigitado)) {
                        genero = valor;
                        break;
                    }
                }

                Idioma idioma = null;
                String idiomaDigitado = campos[5];
                for (Idioma valor : Idioma.values()) {
                    if (valor.getNome().equalsIgnoreCase(idiomaDigitado)) {
                        idioma = valor;
                        break;
                    }
                }

                EstadoMidia estadoMidia = null;
                String estadoDigitado = campos[6];
                for (EstadoMidia valor : EstadoMidia.values()) {
                    if (valor.getNome().equalsIgnoreCase(estadoDigitado)) {
                        estadoMidia = valor;
                        break;
                    }
                }

                if (tipo.equals("F")) {
                    int duracao = Integer.parseInt(campos[7]);
                    Midia filme = new Filme(id, nome, genero, idioma, lancamento, estadoMidia, duracao);
                    adicionarMidia(filme);

                } else if (tipo.equals("S")) {
                    int qtdEp = Integer.parseInt(campos[7]);
                    Midia serie = new Serie(id, nome, genero, idioma, lancamento, estadoMidia, qtdEp);
                    adicionarMidia(serie);
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Algo de inesperado aconteceu ao carregar os dados das Mídias");
        }
        scanner.close();
    }

    /**
     * ler
     *
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
            String tipoUser = dados[3];

            boolean isProfissional = false;
            if (tipoUser.equals("Profissional")) isProfissional = true;

            Cliente novoCliente = new Cliente(nomeDeUsuario, login, senha, isProfissional);

            adicionarCliente(novoCliente);
        }

        scanner.close();
    }

    /**
     * carrega os ADMs do arquivo
     *
     * @throws FileNotFoundException
     */
    public void carregarAdministradores(String arquivo) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File(arquivo));

        while (scanner.hasNextLine()) {
            String linha = scanner.nextLine();
            String[] dados = linha.split(";");

            String nomeDeUsuario = dados[0];
            String login = dados[1];
            String senha = dados[2];

            Administrador adm = new Administrador(nomeDeUsuario, login, senha);

            adicionarAdministrador(adm);
        }

        scanner.close();
    }

    /**
     * carrega dados de audiencia do arquivo
     *
     * @param arquivo
     * @throws FileNotFoundException
     */
    public void carregarAudiencia(String arquivo) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File(arquivo));

        try {
            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();

                String[] dados = linha.split(";");
                Cliente cliente = buscarCliente(dados[0], dados[1]);

                if (cliente != null) {
                    Midia midia = buscarMidia(dados[3], Integer.parseInt(dados[4]));
                    if (midia != null) {

                        if (midia.getEstadoMidia() == EstadoMidia.LANCAMENTO && cliente.state instanceof ClienteProfissional) {

                            if (dados[2].equals("F"))  // quero ver
                                cliente.adicionarNaLista(midia);

                            else if (dados[2].equals("A")) { // historico
                                cliente.registrarPorAudiencia(midia);
                            }
                        } else if (midia.getEstadoMidia() != EstadoMidia.LANCAMENTO) {
                            if (dados[2].equals("F"))  // quero ver
                                cliente.adicionarNaLista(midia);

                            else if (dados[2].equals("A")) { // historico
                                cliente.registrarPorAudiencia(midia);
                            }
                        }
                    }
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Algo de inesperado aconteceu ao carregar os dados da Audiência");
        }
        scanner.close();
    }

    /**
     * Relatório de qual cliente assistiu mais mídias, e quantas mídias
     *
     * @return String como nome do cliente e a quantidade de mídias assistidas
     */
    public String relatorioClienteMaisMidias() {

        return this.clientes.values().stream()
                .max(Comparator.comparingInt(cliente -> cliente.getListaJaVistas().size()))
                .filter(c -> !c.getListaJaVistas().isEmpty())
                .map(cliente -> cliente.getUsuario() + ", " + cliente.getListaJaVistas().size() + " mídias assistidas")
                .orElse("");
    }

    /**
     * Frequência de avaliações por Cliente
     */
    private Map<String, Integer> getFrequencyMap() {
        return midias.values().stream()
                .filter(m -> !m.getAvaliacoes().isEmpty())
                .flatMap(midia -> midia.getAvaliacoes().stream())
                .collect(Collectors.groupingBy(Avaliacao::getLogin, Collectors.summingInt(e -> 1)));
    }

    /**
     * Relatorio qual cliente tem mais avaliações, e quantas avaliações
     *
     * @return String com o nome do cliente e quantas avaliações foram feitas por ele
     */
    public String relatorioClienteMaisAvaliacoes() {
        Optional<Map.Entry<String, Integer>> maxEntry = getFrequencyMap().entrySet().stream()
                .max(Map.Entry.comparingByValue());

        return maxEntry.map(entry -> entry.getKey() + ", " + entry.getValue() + " avaliações")
                .orElse("");
    }

    /**
     * Relatório de qual a porcentagem dos clientes com pelo menos x avaliações
     *
     * @param qtdAvaliacoes quantidade de avaliações
     * @return String com a porcentagem dos clientes que possuem determinado número de avaliações
     */
    public String relatorioClientesPorNumeroAvaliacoes(int qtdAvaliacoes) {
        long cont = getFrequencyMap().values().stream()
                .filter(frequencia -> frequencia >= qtdAvaliacoes)
                .count();

        double totalClientes = getFrequencyMap().size();
        double porcentagem = (cont / totalClientes) * 100;

        if (Double.isNaN(porcentagem))
            return "";
        else return String.format("%.2f", porcentagem) + "% dos clientes possuem "
                + qtdAvaliacoes + " ou mais avaliações";
    }

    /**
     * Relatório de quais são as x mídias de melhor avaliação, com pelo menos y avaliações, em ordem decrescente
     *
     * @param qtdMidias      Quantidade de mídias a serem mostradas
     * @param qtdAavaliacoes Quantidade mínima de avaliações que cada mídia deve ter
     * @return String com nome das mídias
     */
    public String relatorioMidiasMaisBemAvaliadas(int qtdMidias, int qtdAavaliacoes) {
        StringBuilder aux = new StringBuilder();

        double mediaAvaliacoesMidias = this.midias.values().stream().mapToDouble(Midia::getMedia)
                .average().orElse(0);

        Deque<Midia> deque = this.midias.values().stream()
                .filter(
                        m -> m.getAvaliacoes().size() > qtdAavaliacoes && m.getMedia() > mediaAvaliacoesMidias
                                && !m.getAvaliacoes().isEmpty()
                )
                .sorted(Comparator.comparing(Midia::getMedia))
                .collect(ArrayDeque::new, ArrayDeque::addFirst, ArrayDeque::addAll);

        deque.stream().limit(qtdMidias).forEach(m -> aux.append(m.getNome()).append("\n"));
        return aux.toString();
    }

    /**
     * Relatório de quais são as x mídias de determinado gênero de melhor avaliação, com pelo menos y avaliações, em ordem decrescente
     *
     * @param qtdMidias      Quantidade de mídias a serem mostradas
     * @param qtdAavaliacoes Quantidade mínima de avaliações que cada mídia deve ter
     * @param genero         Gênero das mídias
     * @return String com nome das mídias
     */
    public String relatorioMidiasMaisBemAvaliadas(int qtdMidias, int qtdAavaliacoes, Genero genero) {
        StringBuilder aux = new StringBuilder();
        List<Midia> midiasFiltradas = filtrarPorGenero(genero);

        double mediaAvaliacoesMidias = midiasFiltradas.stream().mapToDouble(Midia::getMedia)
                .average().orElse(0);

        Deque<Midia> deque = midiasFiltradas.stream()
                .filter(
                        m -> m.getAvaliacoes().size() > qtdAavaliacoes && m.getMedia() > mediaAvaliacoesMidias
                                && !m.getAvaliacoes().isEmpty()
                )
                .sorted(Comparator.comparing(Midia::getMedia))
                .collect(ArrayDeque::new, ArrayDeque::addFirst, ArrayDeque::addAll);

        deque.stream().limit(qtdMidias).forEach(m -> aux.append(m.getNome()).append("\n"));
        return aux.toString();
    }

    /**
     * Relatório quais são as x mídias com mais visualizações, em ordem decrescente
     *
     * @param qtdMidias quantidade de mídias a serem mostradas
     * @return String com nome das mídias e a audiência de cada uma
     */
    public String relatorioMidiasMaisVizualizadas(int qtdMidias) {
        StringBuilder aux = new StringBuilder();

        Deque<Midia> deque = this.midias.values().stream().filter(m -> m.getAudiencia() > 0)
                .sorted(Comparator.comparing(Midia::getAudiencia))
                .collect(ArrayDeque::new, ArrayDeque::addFirst, ArrayDeque::addAll);

        deque.stream().limit(qtdMidias).forEach(m -> aux.append(m.getNome()).append(" - ")
                .append(m.getAudiencia())
                .append("\n"));

        return aux.toString();
    }

    /**
     * Relatório quais são as x mídias de determinado gênero com mais visualizações, em ordem decrescente
     *
     * @param qtdMidias quantidade de mídias a serem mostradas
     * @return String com nome das mídias e a audiência de cada uma
     */
    public String relatorioMidiasMaisVizualizadas(int qtdMidias, Genero genero) {
        StringBuilder aux = new StringBuilder();
        List<Midia> midiasFiltradas = filtrarPorGenero(genero);

        Deque<Midia> deque = midiasFiltradas.stream().filter(m -> m.getAudiencia() > 0)
                .sorted(Comparator.comparing(Midia::getAudiencia))
                .collect(ArrayDeque::new, ArrayDeque::addFirst, ArrayDeque::addAll);

        deque.stream().limit(qtdMidias).forEach(m -> aux.append(m.getNome()).append(" - ")
                .append(m.getAudiencia())
                .append("\n"));

        return aux.toString();
    }

    /**
     * busca cliente pelo login e senha
     *
     * @param login
     * @param senha
     * @return
     */
    public Cliente buscarCliente(String login, String senha) {
        String chave = login + ":" + senha;
        return clientes.get(chave);
    }

    /**
     * buscar midia pelo nome e id
     *
     * @param nome
     * @param id
     * @return
     */
    public Midia buscarMidia(String nome, int id) {
        String chave = nome + ":" + id;
        return midias.get(chave);
    }

    public HashMap<String, Cliente> getClientes() {
        return (HashMap<String, Cliente>) clientes.clone();
    }

    public Cliente getClienteAtual() {
        return clienteAtual;
    }

    public HashMap<String, Midia> getMidias() {
        return (HashMap<String, Midia>) midias.clone();
    }
}
