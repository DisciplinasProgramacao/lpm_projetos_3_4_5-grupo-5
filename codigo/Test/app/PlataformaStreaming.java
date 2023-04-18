package app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlataformaStreaming {
	
	private String nome;
	private Cliente clienteAtual;
	private HashMap<String, Serie> series;
	private HashMap<String,Cliente> clientes;
	
	
	/**
	 * Criar uma nova plataforma
	 * Cria uma tabela hash vazia 
	 * @param nome da plataforma
	 */
	public PlataformaStreaming (String nome) {
		this.nome = nome;
		clientes = new HashMap<>();
		series = new HashMap<>();
	}
	
	public HashMap<String,Cliente> getClientes(){
		return clientes;
	}
	public HashMap<String,Serie> getSeries(){
		return series;
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
	
	/**gera uma chave aleatória a patir da concatenação de nome e genero.
	 * adiciona na tabela hash uma chave associada à serie e serie.
	 * @param serie com os atributos nome, genero, idioma, numero de episodios
	 */
	public void adicionarSerie(Serie serie) {
		String chave = serie.getNome() +":"+serie.getGenero();
        series.put(chave, serie);
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
	 * Se o genero for correspondente, a serie é adicionada à lista "seriesPorGenero"
	 * A função retorna uma lista de serie que possuem aquele determinado genero
	 * @param genero para filtrar as series
	 * @return
	 */
	public List<Serie> filtrarPorGenero(String genero) {
		List<Serie> seriesPorGenero = new ArrayList<>();
		for (Map.Entry<String, Serie>entrada : series.entrySet()) {
            Serie serie = entrada.getValue();
            if (serie.getGenero().equalsIgnoreCase(genero)) {
                seriesPorGenero.add(serie);
            }
        }
		return seriesPorGenero;
	}
	
	/**Para cada entrada da tabela hash verifica se o idioma é correspondente ao passado por parametro.
	 * Se o idioma for correspondente, a serie é adicionada à lista "seriesPorIdioma"
	 * A função retorna uma lista de serie que possuem aquele determinado idioma
	 * @param idioma para filtrar as series
	 * @return
	 */
	public List<Serie> filtrarPorIdioma(String idioma) {
		List<Serie> seriesPorIdioma = new ArrayList<>();
		for (Map.Entry<String, Serie>entrada : series.entrySet()) {
            Serie serie = entrada.getValue();
            if (serie.getIdioma().equalsIgnoreCase(idioma)) {
                seriesPorIdioma.add(serie);
            }
        }
		return seriesPorIdioma;
	}
	
	/**Para cada entrada da tabela hash verifica se a quantidade de episodios é correspondente ao passado por parametro.
	 * Se a quantidade de episodios for correspondente, a serie é adicionada à lista "seriesPorEpisodio"
	 * A função retorna uma lista de serie que possuem aquela determinada quantidade de episodios
	 * @param quantidade de episodios para filtrar as series
	 * @return
	 */
	
	public List<Serie> filtrarPorQtdEpisodio(int quantidade) {
		List<Serie> seriesPorEpisodio = new ArrayList<>();
		for (Map.Entry<String, Serie>entrada : series.entrySet()) {
            Serie serie = entrada.getValue();
            if (serie.getQtdEpisodios()== quantidade) {
                seriesPorEpisodio.add(serie);
            }
        }
		return seriesPorEpisodio;
	}
	
	/**Para cada entrada da tabela hash verifica se o nome  é correspondente ao passado por parametro.
	 * Se o nome for correspondente, a serie é retornada
	 * Caso nenhuma serie possua o nome equivalente, a função retorna nulo
	 * @param nome da serie buscada
	 * @return
	 */
	public Serie buscarSerie(String nome) {
		for (Map.Entry<String, Serie>entrada : series.entrySet()) {
            Serie serie = entrada.getValue();
            if (serie.getNome().equalsIgnoreCase(nome)) {
                 return serie;
            }
        }
		return null;
	}
	
	/**
	 * Toda vez que um usuário assistir uma serie, será adicionado uma audiência à audiência da serie
	 * @param serie escolhida pelo usuário para assistir
	 */
	public void registrarAudiência(Serie serie) {
		for (Map.Entry<String, Serie>entrada : series.entrySet()) {
            Serie aux = entrada.getValue();
            if (aux.getNome().equalsIgnoreCase(serie.getNome())) {
                int x = serie.getAudiencia();
                x++;
                serie.setAudiencia(x);
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
