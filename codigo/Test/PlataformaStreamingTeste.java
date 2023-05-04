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
        serieTeste = new Serie("you", "drama", "ingles", "20");
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
        Serie serieTeste1 = new Serie("teste", "drama", "ingles", "20");
        Serie serieTeste2 = new Serie("teste", "suspense", "ingles", "20");
        Serie serieTeste3 = new Serie("teste", "terroe", "ingles", "20");
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
        Serie serieTeste1 = new Serie("teste", "drama", "ingles", "20");
        Serie serieTeste2 = new Serie("teste", "suspense", "portugues", "20");
        Serie serieTeste3 = new Serie("teste", "terroe", "ingles", "20");
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
        Serie serieTeste1 = new Serie("teste", "drama", "ingles", "20");
        Serie serieTeste2 = new Serie("teste", "suspense", "portugues", "10");
        Serie serieTeste3 = new Serie("teste", "terroe", "ingles", "10");
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
        Serie serieTeste1 = new Serie("teste", "drama", "ingles", "20");
        Serie serieTeste2 = new Serie("teste", "suspense", "portugues", "10");
        Serie serieTeste3 = new Serie("teste", "terroe", "ingles", "10");
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