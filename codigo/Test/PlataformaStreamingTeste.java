package codigo.test;

import codigo.app.Cliente;
import codigo.app.PlataformaStreaming;
import codigo.app.Serie;
import codigo.app.Midia;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PlataformaStreamingTeste {

    Cliente clienteTeste;
    Midia serieTeste;

    PlataformaStreaming plataforma;


    @BeforeEach
    public void prepare() {
        clienteTeste = new Cliente("Cteste", "123");
        serieTeste = new Serie(4,"you", "drama", "ingles", "20", 20);
        plataforma = new PlataformaStreaming("netflix");
        plataforma.adicionarCliente(clienteTeste);
        plataforma.adicionarMidia(serieTeste);
    }

    @Test
    public void deveAdicionarCliente() {
        plataforma.adicionarCliente(clienteTeste);
        String chave = clienteTeste.getUsuario() + ":" + clienteTeste.getSenha();
        assertNotNull(plataforma.getClientes().get(chave));
    }

    // teste falhando
    @Test
    public void deveFazerLogin() {
        plataforma.adicionarCliente(clienteTeste);
        plataforma.login("Cteste", "123");
        assertEquals(clienteTeste, plataforma.getClienteAtual());
    }

    @Test
    public void deveAdicionarSerie() {
        plataforma.adicionarMidia(serieTeste);
        String chave = serieTeste.getNome() + ":" + serieTeste.getGenero();
//        assertNotNull(plataforma.getSeries().get(chave));
    }

    @Test
    public void deveFiltrarSeriePorGenero() {
        Serie serieTeste1 = new Serie(1,"teste", "drama", "ingles", "20",6);
        Serie serieTeste2 = new Serie(2,"teste", "suspense", "ingles", "20",8);
        Serie serieTeste3 = new Serie(3,"teste", "terroe", "ingles", "20",9);
        List<Midia> listaTeste = new ArrayList<>();
        plataforma.adicionarMidia(serieTeste);
        plataforma.adicionarMidia(serieTeste1);
        plataforma.adicionarMidia(serieTeste2);
        plataforma.adicionarMidia(serieTeste3);

        listaTeste.add(serieTeste1);
        listaTeste.add(serieTeste);

        assertEquals(listaTeste, plataforma.filtrarPorGenero("drama"));


    }

    @Test
    public void deveFiltrarSeriePorIdioma() {
        Serie serieTeste1 = new Serie(5,"teste", "drama", "ingles", "20",5);
        Serie serieTeste2 = new Serie(6,"teste", "suspense", "portugues", "20",9);
        Serie serieTeste3 = new Serie(7,"teste", "terroe", "ingles", "20",3);
        List<Midia> listaTeste = new ArrayList<>();
        plataforma.adicionarMidia(serieTeste);
        plataforma.adicionarMidia(serieTeste1);
        plataforma.adicionarMidia(serieTeste2);
        plataforma.adicionarMidia(serieTeste3);

        listaTeste.add(serieTeste1);
        listaTeste.add(serieTeste3);
        listaTeste.add(serieTeste);

        assertEquals(listaTeste, plataforma.filtrarPorIdioma("ingles"));
    }

    @Test
    public void deveFiltrarSeriePorQtdEpisodio() {
        Serie serieTeste1 = new Serie(8,"teste", "drama", "ingles", "20",89);
        Serie serieTeste2 = new Serie(9,"teste", "suspense", "portugues", "10",57);
        Serie serieTeste3 = new Serie(10,"teste", "terroe", "ingles", "10",23);
        List<Serie> listaTeste = new ArrayList<>();
        plataforma.adicionarMidia(serieTeste);
        plataforma.adicionarMidia(serieTeste1);
        plataforma.adicionarMidia(serieTeste2);
        plataforma.adicionarMidia(serieTeste3);

        listaTeste.add(serieTeste2);
        listaTeste.add(serieTeste3);


        assertEquals(listaTeste, plataforma.filtrarPorQtdEpisodio(10));
    }

    @Test
    public void deveBuscarSerie() {
        Serie serieTeste1 = new Serie(11,"teste", "drama", "ingles", "20",20);
        Serie serieTeste2 = new Serie(12,"teste", "suspense", "portugues", "10",4);
        Serie serieTeste3 = new Serie(13,"teste", "terroe", "ingles", "10",6);
        plataforma.adicionarMidia(serieTeste);
        plataforma.adicionarMidia(serieTeste1);
        plataforma.adicionarMidia(serieTeste2);
        plataforma.adicionarMidia(serieTeste3);

        Midia midia = plataforma.buscarMidia("you");

        assertEquals(serieTeste.getNome(), midia.getNome());
    }

    @Test
    public void deveRegistrarAudiencia() {
        plataforma.adicionarMidia(serieTeste);
        plataforma.registrarAudiência(serieTeste);

        assertEquals(1, serieTeste.getAudiencia());
    }

    @Test
    public void testaLogoff() {
        plataforma.adicionarCliente(clienteTeste);
        plataforma.login("Cteste", "123");
        plataforma.logoff();
        assertNull(plataforma.getClienteAtual());
    }
}