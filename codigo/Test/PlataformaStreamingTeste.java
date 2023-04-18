import static org.junit.jupiter.api.Assertions.assertEquals;
 
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import app.Cliente;
import app.Serie;
import app.PlataformaStreaming;
public class PlataformaStreamingTeste {

	    Cliente clienteTeste;
	    Serie serieTeste;
	    
	    PlataformaStreaming plataforma;
	    

	    @BeforeEach
	    public void prepare(){
	        clienteTeste = new Cliente("Cteste", "123");
	        serieTeste = new Serie("you","drama","ingles",20);
	        plataforma = new PlataformaStreaming ("netflix");
	        plataforma.adicionarCliente(clienteTeste);
	        plataforma.adicionarSerie(serieTeste);
	    }
	    
	    @Test
	    public void deveAdicionarCliente() {
	    	plataforma.adicionarCliente(clienteTeste);
	    	String chave = clienteTeste.getSenha()+":"+clienteTeste.getUsuario();
	    	Assert.assertNotNull(plataforma.getClientes().get(chave));
	    }
	    
	   // teste falhando
	    @Test
	    public void deveFazerLogin() {
	    	plataforma.adicionarCliente(clienteTeste);
	    	plataforma.login("Cteste", "123");
	    	assertEquals(clienteTeste,plataforma.getClienteAtual());
	    }
	    
	    @Test
	    public void deveAdicionarSerie() {
	    	plataforma.adicionarSerie(serieTeste);
	    	String chave = serieTeste.getNome()+":"+serieTeste.getGenero();
	    	Assert.assertNotNull(plataforma.getSeries().get(chave));
	    }
	    
	    @Test
	    public void deveFiltrarSeriePorGenero() {
	    	Serie serieTeste1 = new Serie("teste","drama","ingles",20);
		    Serie serieTeste2= new Serie("teste","suspense","ingles",20);
		    Serie serieTeste3= new Serie("teste","terroe","ingles",20);
		    List<Serie> listaTeste = new ArrayList<>();
	    	plataforma.adicionarSerie(serieTeste);
	    	plataforma.adicionarSerie(serieTeste1);
	    	plataforma.adicionarSerie(serieTeste2);
	    	plataforma.adicionarSerie(serieTeste3);
	    	
	    	listaTeste.add(serieTeste1);
	    	listaTeste.add(serieTeste);
	    
	    	assertEquals(listaTeste,plataforma.filtrarPorGenero("drama"));
	    	
	    	
	    }
	    
	    @Test
	    public void deveFiltrarSeriePorIdioma() {
	    	Serie serieTeste1 = new Serie("teste","drama","ingles",20);
		    Serie serieTeste2= new Serie("teste","suspense","portugues",20);
		    Serie serieTeste3= new Serie("teste","terroe","ingles",20);
		    List<Serie> listaTeste = new ArrayList<>();
	    	plataforma.adicionarSerie(serieTeste);
	    	plataforma.adicionarSerie(serieTeste1);
	    	plataforma.adicionarSerie(serieTeste2);
	    	plataforma.adicionarSerie(serieTeste3);
	    	
	    	listaTeste.add(serieTeste1);
	    	listaTeste.add(serieTeste3);
	    	listaTeste.add(serieTeste);
	  
	    	assertEquals(listaTeste,plataforma.filtrarPorIdioma("ingles"));	
	    }
	    
	    @Test
	    public void deveFiltrarSeriePorQtdEpisodio() {
	    	Serie serieTeste1 = new Serie("teste","drama","ingles",20);
		    Serie serieTeste2= new Serie("teste","suspense","portugues",10);
		    Serie serieTeste3= new Serie("teste","terroe","ingles",10);
		    List<Serie> listaTeste = new ArrayList<>();
	    	plataforma.adicionarSerie(serieTeste);
	    	plataforma.adicionarSerie(serieTeste1);
	    	plataforma.adicionarSerie(serieTeste2);
	    	plataforma.adicionarSerie(serieTeste3);
	    	
	    	listaTeste.add(serieTeste2);
	    	listaTeste.add(serieTeste3);
	  
	  
	    	assertEquals(listaTeste,plataforma.filtrarPorQtdEpisodio(10));	
	    }
	    
	    @Test
	    public void deveBuscarSerie() {
	    	Serie serieTeste1 = new Serie("teste","drama","ingles",20);
		    Serie serieTeste2= new Serie("teste","suspense","portugues",10);
		    Serie serieTeste3= new Serie("teste","terroe","ingles",10);
	    	plataforma.adicionarSerie(serieTeste);
	    	plataforma.adicionarSerie(serieTeste1);
	    	plataforma.adicionarSerie(serieTeste2);
	    	plataforma.adicionarSerie(serieTeste3);
	    	
	    	Serie serie = plataforma.buscarSerie("you");
	    	
	    	assertEquals(serieTeste.getNome(),serie.getNome());	
	    }
	    
	    @Test
	    public void deveRegistrarAudiencia() {
	    	plataforma.adicionarSerie(serieTeste);
	    	plataforma.registrarAudiência(serieTeste);
	    	
	    	assertEquals(1,serieTeste.getAudiencia());
	    }
	    
	    @Test
	    public void testaLogoff() {
	    	plataforma.adicionarCliente(clienteTeste);
	    	plataforma.login("Cteste", "123");
	    	plataforma.logoff();
	    	Assert.assertNull(plataforma.getClienteAtual());
	    }
}
