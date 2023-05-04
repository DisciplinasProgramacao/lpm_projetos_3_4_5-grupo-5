package codigo.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlataformaStreaming {

	private String nome;
	private Cliente clienteAtual;
	private HashMap<String, Midia> midias;
	private HashMap<String,Cliente> clientes;


	/**
	 * Criar uma nova plataforma
	 * Cria uma tabela hash vazia
	 * @param nome da plataforma
	 */
	public PlataformaStreaming (String nome) {
		this.nome = nome;
		clientes = new HashMap<>();
		midias = new HashMap<>();
	}

	public HashMap<String,Cliente> getClientes(){
		return clientes;
	}
	public HashMap<String,Midia> getMidias(){
		return midias;
	}
	public Cliente getClienteAtual(){
		return clienteAtual;
	}
	/** gera uma chave aleatória a patir da concatenação de usuário e senha.
	 * adiciona na tabela hash uma chave associada ao cliente e o cliente
	 * @param cliente com os atributos senha e usuario
	 */
	public void adicionarCliente(Cliente cliente) {
		String chave = cliente.getUsuario()+":"+cliente.getSenha();
		clientes.put(chave, cliente);
	}

	/**
	 * Gera uma chave aleatória a patir da concatenação de nome e genero.
	 * Adiciona na tabela hash uma chave associada à midia.
	 * @param midia com os atributos nome, genero, idioma, audiencia, dataDeLancamento
	 */
	public void adicionarMidia(Midia midia) {
		String chave = midia.getNome() +":"+midia.getGenero();
		Midia nova = new Filme("a","n","c","10",100); //to-do -> filme ou serie? metodos separados?

	}

	/**
	 * Encontrar um cliente que possui a mesma chave que essa pesquisa: concatenação entre usuario e senha fornecidos por parametro
	 * @param nomeUsuario
	 * @param senha
	 * @return
	 */
	//TesteFalhando
	public Cliente login (String nomeUsuario , String senha) {
		String chave = nomeUsuario+":"+senha;
		clienteAtual = clientes.get(chave);
		return clientes.get(chave);
	}

	/**
	 * Para cada entrada da tabela hash verifica se o genero é correspondente ao passado por parametro.
	 * Se o genero for correspondente, a midia é adicionada à lista "midiasPorGenero"
	 * A função retorna uma lista de midias que possuem aquele determinado genero
	 * @param genero para filtrar as midias
	 * @return
	 */
	public List<Midia> filtrarPorGenero(String genero) {
		List<Midia> midiasPorGenero = new ArrayList<>();
		for (Map.Entry<String, Midia>entrada : midias.entrySet()) {
			Midia midia = entrada.getValue();
			if (midia.getGenero().equalsIgnoreCase(genero)) {
				midiasPorGenero.add(midia);
			}
		}
		return midiasPorGenero;
	}

	/**Para cada entrada da tabela hash verifica se o idioma é correspondente ao passado por parametro.
	 * Se o idioma for correspondente, a midia é adicionada à lista "midiasPorIdioma"
	 * A função retorna uma lista de midia que possuem aquele determinado idioma
	 * @param idioma para filtrar as midias
	 * @return
	 */
	public List<Midia> filtrarPorIdioma(String idioma) {
		List<Midia> midiasPorIdioma = new ArrayList<>();
		for (Map.Entry<String, Midia>entrada : midias.entrySet()) {
			Midia midia = entrada.getValue();
			if (midia.getIdioma().equalsIgnoreCase(idioma)) {
				midiasPorIdioma.add(midia);
			}
		}
		return midiasPorIdioma;
	}

	/**Para cada entrada da tabela hash verifica se a quantidade de episodios é correspondente ao passado por parametro.
	 * Se a quantidade de episodios for correspondente, a serie é adicionada à lista "seriesPorEpisodio"
	 * A função retorna uma lista de serie que possuem aquela determinada quantidade de episodios
	 * @param quantidade de episodios para filtrar as series
	 * @return
	 */

	public List<Serie> filtrarPorQtdEpisodio(int quantidade) {
		List<Serie> seriesPorEpisodio = new ArrayList<>();
		// como pegar as series da midia?
//		HashMap<String, Serie> series = midias.getSeries();
//		for (Map.Entry<String, Serie>entrada : series.entrySet()) {
//			Serie serie = entrada.getValue();
//			if (serie.getQtdEpisodios()== quantidade) {
//				seriesPorEpisodio.add(serie);
//			}
//		}
		return seriesPorEpisodio;
	}

	/**Para cada entrada da tabela hash verifica se o nome  é correspondente ao passado por parametro.
	 * Se o nome for correspondente, a midia é retornada
	 * Caso nenhuma midia possua o nome equivalente, a função retorna nulo
	 * @param nome da midia buscada
	 * @return
	 */
	public Midia buscarMidia(String nome) {
		for (Map.Entry<String, Midia>entrada : midias.entrySet()) {
			Midia midia = entrada.getValue();
			if (midia.getNome().equalsIgnoreCase(nome)) {
				return midia;
			}
		}
		return null;
	}

	/**
	 * Toda vez que um usuário assistir uma midia, será adicionado uma audiência à audiência da midia
	 * @param midia escolhida pelo usuário para assistir
	 */
	public void registrarAudiência(Midia midia) {
		for (Map.Entry<String, Midia>entrada : midias.entrySet()) {
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
}