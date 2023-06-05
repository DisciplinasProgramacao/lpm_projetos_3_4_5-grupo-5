package codigo.test;

import codigo.app.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PlataformaStreamingTest {

    Cliente clienteTeste;
    Cliente clienteTeste2;
    Cliente clienteTeste3;
    HashMap<String, Cliente> clientes;
    Midia serieTeste;
    Serie serie1;
    Serie serie2;
    Serie serie3;
    Filme f1;
    Filme f2;
    Filme f3;
    Midia midiaTest;
    Avaliacao a1;
    Avaliacao a2;
    Avaliacao a3;
    Map<Cliente, List<Midia>> seriesPorCliente;
    List<Midia> catalogoMidias;
    PlataformaStreaming plataforma;

    @BeforeEach
    public void prepare() throws Exception {
        //Plataforma
        plataforma = new PlataformaStreaming("netflix");

        //Inicialização de listas
        seriesPorCliente = new HashMap<>();
        clientes = new HashMap<>();
        catalogoMidias = new ArrayList<>();

        //Clientes
        clienteTeste = new Cliente("Cteste", "login", "123");
        clienteTeste2 = new Cliente("Cteste2","usuario1", "1234");
        clienteTeste3 = new Cliente("Cteste3","usuario2", "12345");

        //Series
        serieTeste = new Serie(4,"you", "drama", "ingles", "20", 10);
        serie1 = new Serie(15, "F.R.I.E.N.D.S", "comedia", "inglês", "22/09/1994", 236);
        serie2 = new Serie(16, "Stranger Things", "drama", "inglês", "22/09/1994", 18);
        serie3 = new Serie(17, "Breaking Bad", "drama", "inglês", "22/09/1994", 12);

        //Filmes
        f1 = new Filme(18, "f1", "terror", "portugues", "2020", 120);
        f2 = new Filme(19, "f2", "terror", "portugues", "2020", 120);
        f3 = new Filme(13, "f3", "terror", "portugues", "2020", 120);

        //Midias
        midiaTest = new Serie(14, "mTest", "comedia", "inglês", "22/09/1994", 236);

        //Avaliacoes
        a1 = new Avaliacao("u1", 4,"bom");
        a2 = new Avaliacao("u2", 4,"bom");
        a3 = new Avaliacao("u3", 4,"bom");


        //Ações Plataforma
        plataforma.adicionarMidia(serieTeste);
        plataforma.adicionarMidia(midiaTest);
        plataforma.adicionarMidia(serie1);
        plataforma.adicionarMidia(serie2);
        plataforma.adicionarMidia(serie3);
        plataforma.adicionarMidia(f1);
        plataforma.adicionarMidia(f2);
        plataforma.adicionarMidia(f3);
        plataforma.adicionarCliente(clienteTeste);
        plataforma.adicionarCliente(clienteTeste2);
        plataforma.adicionarCliente(clienteTeste3);
//        plataforma.adicionarSerieParaCliente(clienteTeste2, serie1);
//        plataforma.adicionarSerieParaCliente(clienteTeste2, serie2);
//        plataforma.adicionarSerieParaCliente(clienteTeste2, serie3);

        //Ações Clientes
        clienteTeste2.adicionarNaLista(serie1);
        clienteTeste2.adicionarNaLista(serie2);
        clienteTeste2.adicionarNaListaJaVistas(f1);
        clienteTeste2.adicionarNaListaJaVistas(f2);
        clienteTeste.adicionarNaListaJaVistas(f1);
        clienteTeste.adicionarNaListaJaVistas(serie1);
        clienteTeste.adicionarNaListaJaVistas(serie2);
        clienteTeste.adicionarNaListaJaVistas(serie3);
        clienteTeste3.adicionarNaListaJaVistas(f1);
        clienteTeste3.adicionarNaListaJaVistas(f2);
        clienteTeste3.adicionarNaListaJaVistas(f3);

        //Ações Mídias
        serie1.addAvaliacao(a1);
        serie1.addAvaliacao(a2);
        serie1.addAvaliacao(a3);
        serie2.addAvaliacao(a1);
        serie2.addAvaliacao(a2);
        serie3.addAvaliacao(a2);
        serie3.addAvaliacao(a3);
    }

    @Test
    public void deveRetornarSerieComQtdEpisodios(){
        assertEquals(1,  plataforma.filtrarPorQtdEpisodio(10).size());
    }

    @Test
    public void deveAdicionarCliente() {
        plataforma.adicionarCliente(clienteTeste);
        String chave = clienteTeste.getUsuario() + ":" + clienteTeste.getSenha();
        assertNotNull(plataforma.getClientes().get(chave));
    }

    @Test
    public void testAdicionarMidia(){
        assertEquals(midiaTest, plataforma.getMidias().get("mTest:comedia"));
    }

    @Test
    public void testLogin(){
        String chave1 = clienteTeste2.getUsuario() + ":" + clienteTeste2.getSenha();
        clientes.put(chave1, clienteTeste2);
        plataforma.adicionarCliente(clienteTeste2);
        plataforma.login("Cteste2", "1234");
        assertEquals(clienteTeste2, plataforma.getClienteAtual());
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
        String chave = serieTeste.getNome() + ":" + serieTeste.getGenero();
//        assertNotNull(plataforma.getSeries().get(chave));
    }

    @Test
    public void deveFiltrarSeriePorGenero() {
        Serie serieTeste1 = new Serie(1,"teste", "drama", "ingles", "20",6);
        Serie serieTeste2 = new Serie(2,"teste", "suspense", "ingles", "20",8);
        Serie serieTeste3 = new Serie(3,"teste", "terroe", "ingles", "20",9);
        List<Midia> listaTeste = new ArrayList<>();
        plataforma.adicionarMidia(serieTeste1);
        plataforma.adicionarMidia(serieTeste2);
        plataforma.adicionarMidia(serieTeste3);

        listaTeste.add(serieTeste1);
        listaTeste.add(serie2);
        listaTeste.add(serie3);
        listaTeste.add(serieTeste);

        assertEquals(listaTeste, plataforma.filtrarPorGenero("drama"));
    }

    @Test
    public void deveFiltrarSeriePorIdioma() {
        Serie serieTeste1 = new Serie(5,"teste", "drama", "ingles", "20",5);
        Serie serieTeste2 = new Serie(6,"teste", "suspense", "portugues", "20",9);
        Serie serieTeste3 = new Serie(7,"teste", "terroe", "ingles", "20",3);
        plataforma.adicionarMidia(serieTeste1);
        plataforma.adicionarMidia(serieTeste2);
        plataforma.adicionarMidia(serieTeste3);

        assertEquals(3, plataforma.filtrarPorIdioma("ingles").size());
        assertEquals(serieTeste1, plataforma.filtrarPorIdioma("ingles").get(0));
    }

    @Test
    public void deveFiltrarSeriePorQtdEpisodio() {
        Serie serieTeste1 = new Serie(8,"teste", "drama", "ingles", "20",89);
        Serie serieTeste2 = new Serie(9,"teste", "suspense", "portugues", "10",57);
        Serie serieTeste3 = new Serie(10,"teste", "terroe", "ingles", "10",10);
        List<Serie> listaTeste = new ArrayList<>();
        plataforma.adicionarMidia(serieTeste1);
        plataforma.adicionarMidia(serieTeste2);
        plataforma.adicionarMidia(serieTeste3);

        listaTeste.add(serieTeste2);
        listaTeste.add(serieTeste3);


        assertEquals(listaTeste.size(), plataforma.filtrarPorQtdEpisodio(10).size());
    }

    @Test
    public void deveBuscarSerie() {
        Serie serieTeste1 = new Serie(11,"teste", "drama", "ingles", "20",20);
        Serie serieTeste2 = new Serie(12,"teste", "suspense", "portugues", "10",4);
        Serie serieTeste3 = new Serie(13,"teste", "terroe", "ingles", "10",6);
        plataforma.adicionarMidia(serieTeste1);
        plataforma.adicionarMidia(serieTeste2);
        plataforma.adicionarMidia(serieTeste3);

        Midia midia = plataforma.buscarMidia("you");

        assertEquals(serieTeste.getNome(), midia.getNome());
    }

    @Test
    public void deveRegistrarAudiencia() {
        plataforma.registrarAudiencia(serieTeste);

        assertEquals(1, serieTeste.getAudiencia());
    }

    @Test
    public void testaLogoff() {
        plataforma.login("Cteste", "123");
        plataforma.logoff();
        assertNull(plataforma.getClienteAtual());
    }

    @Test
    public void testAdicionarSerieParaCliente(){
        assertTrue(clienteTeste2.getListaParaVer().contains(serie1));
        assertTrue(clienteTeste2.getListaParaVer().contains(serie2));
        assertTrue(clienteTeste2.getListaJaVistas().contains(serie3));
    }

    @Test
    public void testAdicionarSerieParaClienteSobrescrevendoASerieExistente(){
        clienteTeste2.adicionarNaLista(serie1);
        clienteTeste2.adicionarNaListaJaVistas(serie3);
//        plataforma.adicionarSerieParaCliente(clienteTeste2, serie1);
//        plataforma.adicionarSerieParaCliente(clienteTeste2, serie3);

        assertTrue(clienteTeste2.getListaParaVer().contains(serie1));
        assertTrue(clienteTeste2.getListaJaVistas().contains(serie3));
        assertEquals(2, clienteTeste2.getListaParaVer().size());
        assertEquals(3, clienteTeste2.getListaJaVistas().size());
    }

//    @Test
//    public void testBuscarCliente(){
//        clientes.put(clienteTeste.getLogin(), clienteTeste);
//
//        assertEquals(clienteTeste, plataforma.buscarCliente("login"));
//    }

    @Test
    public void testGetClientes(){
        HashMap<String, Cliente> expectedClientes = new HashMap<>();
        Cliente cliente1 = new Cliente("Breno", "breno1", "breno123");
        Cliente cliente2 = new Cliente("Arthur", "arthur1", "arthur123");
        String chave1 = cliente1.getUsuario() + ":" + cliente1.getSenha();
        String chave2 = cliente2.getUsuario() + ":" + cliente2.getSenha();
        String chave3 = clienteTeste.getUsuario() + ":" + clienteTeste.getSenha();
        String chave4 = clienteTeste2.getUsuario() + ":" + clienteTeste2.getSenha();
        String chave5 = clienteTeste3.getUsuario() + ":" + clienteTeste3.getSenha();

        expectedClientes.put(chave1, cliente1);
        expectedClientes.put(chave2, cliente2);
        expectedClientes.put(chave3, clienteTeste);
        expectedClientes.put(chave4, clienteTeste2);
        expectedClientes.put(chave5, clienteTeste3);

        plataforma.adicionarCliente(cliente1);
        plataforma.adicionarCliente(cliente2);
        assertEquals(expectedClientes, plataforma.getClientes());
    }

    @Test
    public void relatorioClienteMaisMidiasAssistidas(){
        assertTrue(plataforma.relatorioMaisMidias().contains(
                "Cteste, 4 mídias assistidas"
        ));
    }

   @Test
    public void relatorioClienteMaisAvalicoes(){
        assertTrue(plataforma.relatorioMaisAvaliacoes().contains(
                "u2, 3 avaliações"
        ));
    }

}