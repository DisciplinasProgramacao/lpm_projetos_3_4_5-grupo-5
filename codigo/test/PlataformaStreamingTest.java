package codigo.test;

import codigo.app.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

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
        serieTeste = new Serie(4,"you", Genero.DRAMA, Idioma.FRANCES, "20",  EstadoMidia.MODIFICAVEL, 10);
        serie1 = new Serie(15, "F.R.I.E.N.D.S", Genero.COMEDIA, Idioma.PORTUGUES, "22/09/1994",  EstadoMidia.MODIFICAVEL, 236);
        serie2 = new Serie(16, "Stranger Things", Genero.AVENTURA, Idioma.ITALIANO, "22/09/1994", EstadoMidia.MODIFICAVEL,  18);
        serie3 = new Serie(17, "Breaking Bad", Genero.ROMANCE, Idioma.INGLES, "22/09/1994",  EstadoMidia.MODIFICAVEL, 12);

        //Filmes
        f1 = new Filme(18, "f1", Genero.DRAMA, Idioma.INGLES, "2020",  EstadoMidia.MODIFICAVEL, 120);
        f2 = new Filme(19, "f2", Genero.DRAMA, Idioma.PORTUGUES, "2020",  EstadoMidia.MODIFICAVEL, 120);
        f3 = new Filme(13, "f3", Genero.DRAMA, Idioma.PORTUGUES, "2020",  EstadoMidia.MODIFICAVEL, 120);

        //Midias
        midiaTest = new Serie(14, "mTest", Genero.COMEDIA, Idioma.ITALIANO, "22/09/1994",  EstadoMidia.MODIFICAVEL, 236);

        //Avaliacoes
        a1 = new Avaliacao("u1", 2,"bom");
        a2 = new Avaliacao("u2", 3,"bom");
        a3 = new Avaliacao("u3", 5,"bom");


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

        //Ações Clientes
        clienteTeste2.adicionarNaLista(serie1);
        clienteTeste2.adicionarNaLista(serie2);
        clienteTeste2.registrarPorAudiencia(f1);
        clienteTeste2.registrarPorAudiencia(f2);
        clienteTeste.registrarPorAudiencia(f1);
        clienteTeste.registrarPorAudiencia(serie1);
        clienteTeste.registrarPorAudiencia(serie2);
        clienteTeste.registrarPorAudiencia(serie3);
        clienteTeste3.registrarPorAudiencia(f1);
        clienteTeste3.registrarPorAudiencia(f2);
        clienteTeste3.registrarPorAudiencia(f3);

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
    public void adicionarMidia_DeveAdicionarMidiaNaPlataforma() {
        Serie serie = new Serie(33,"Serie1", Genero.DRAMA, Idioma.FRANCES, "20",  EstadoMidia.MODIFICAVEL, 10);
        plataforma.adicionarMidia(serie);

        Midia serieObtida = plataforma.buscarMidia("Serie1");

        assertEquals(serie, serieObtida);
    }

    @Test
    public void testLogin(){
        String chave1 = clienteTeste2.getUsuario() + ":" + clienteTeste2.getSenha();
        clientes.put(chave1, clienteTeste2);
        plataforma.adicionarCliente(clienteTeste2);
        plataforma.login("Cteste2", "1234");
        assertEquals(clienteTeste2, plataforma.getClienteAtual());
    }

    @Test
    public void deveFazerLogin() {
        plataforma.adicionarCliente(clienteTeste);
        plataforma.login("Cteste", "123");
        assertEquals(clienteTeste, plataforma.getClienteAtual());
    }

    @Test
    public void deveFiltrarSeriePorGenero() {
        Genero genero = Genero.DRAMA;
        assertEquals(4, plataforma.filtrarPorGenero(genero).size());
    }

    @Test
    public void deveFiltrarSeriePorIdioma() {
        Idioma idioma = Idioma.INGLES;
        assertEquals(2, plataforma.filtrarPorIdioma(idioma).size());
    }

    @Test
    public void deveFiltrarSeriePorQtdEpisodio() {
        Serie serieTeste1 = new Serie(8,"teste", Genero.DRAMA, Idioma.INGLES, "20", EstadoMidia.MODIFICAVEL, 89);
        Serie serieTeste2 = new Serie(9,"teste", Genero.AVENTURA, Idioma.PORTUGUES, "10", EstadoMidia.MODIFICAVEL, 57);
        Serie serieTeste3 = new Serie(10,"teste", Genero.DRAMA, Idioma.INGLES, "10", EstadoMidia.MODIFICAVEL, 10);
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
        Serie serieTeste1 = new Serie(11,"teste", Genero.DRAMA, Idioma.INGLES, "20", EstadoMidia.MODIFICAVEL, 20);
        Serie serieTeste2 = new Serie(12,"teste", Genero.AVENTURA, Idioma.PORTUGUES, "10", EstadoMidia.MODIFICAVEL, 4);
        Serie serieTeste3 = new Serie(13,"teste", Genero.DRAMA, Idioma.INGLES, "10", EstadoMidia.MODIFICAVEL, 6);
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
    public void relatorioClienteMaisMidias(){
        assertTrue(plataforma.relatorioClienteMaisMidias().contains(
                "Cteste, 4 mídias assistidas"
        ));
    }

    @Test
    public void relatorioClienteMaisAvalicoes(){
        assertTrue(plataforma.relatorioClienteMaisAvaliacoes().contains(
                "u2, 3 avaliações"
        ));
    }

    @Test
    public void relatorioClientesPorNumeroAvaliacoes() throws Exception {
        Midia  f4 = new Filme(19, "f4", Genero.DRAMA, Idioma.PORTUGUES, "2020", EstadoMidia.MODIFICAVEL,120);
        Midia  f5 = new Filme(20, "f5", Genero.DRAMA, Idioma.PORTUGUES, "2020", EstadoMidia.MODIFICAVEL,120);
        Midia  f6 = new Filme(21, "f6", Genero.DRAMA, Idioma.PORTUGUES, "2020", EstadoMidia.MODIFICAVEL,120);
        Midia  f7 = new Filme(22, "f7", Genero.DRAMA, Idioma.PORTUGUES, "2020", EstadoMidia.MODIFICAVEL,120);
        Midia  f8 = new Filme(23, "f8", Genero.DRAMA, Idioma.PORTUGUES, "2020", EstadoMidia.MODIFICAVEL,120);
        Midia  f9 = new Filme(24, "f9", Genero.DRAMA, Idioma.PORTUGUES, "2020", EstadoMidia.MODIFICAVEL,120);
        Midia  f10 = new Filme(25, "f10", Genero.DRAMA, Idioma.PORTUGUES, "2020", EstadoMidia.MODIFICAVEL,120);
        Midia  f11 = new Filme(26, "f11", Genero.DRAMA, Idioma.PORTUGUES, "2020", EstadoMidia.MODIFICAVEL,120);
        Midia  f12 = new Filme(27, "f12", Genero.DRAMA, Idioma.PORTUGUES, "2020", EstadoMidia.MODIFICAVEL,120);
        Midia  f13 = new Filme(28, "f13", Genero.DRAMA, Idioma.PORTUGUES, "2020", EstadoMidia.MODIFICAVEL,120);
        Midia  f14 = new Filme(29, "f14", Genero.DRAMA, Idioma.PORTUGUES, "2020", EstadoMidia.MODIFICAVEL,120);
        Midia  f15 = new Filme(30, "f15", Genero.DRAMA, Idioma.PORTUGUES, "2020", EstadoMidia.MODIFICAVEL,120);
        Midia  f16 = new Filme(31, "f16", Genero.DRAMA, Idioma.PORTUGUES, "2020", EstadoMidia.MODIFICAVEL,120);
        Midia  f17 = new Filme(32, "f17", Genero.DRAMA, Idioma.PORTUGUES, "2020", EstadoMidia.MODIFICAVEL,120);

        plataforma.adicionarMidia(f4);
        plataforma.adicionarMidia(f5);
        plataforma.adicionarMidia(f6);
        plataforma.adicionarMidia(f7);
        plataforma.adicionarMidia(f8);
        plataforma.adicionarMidia(f9);
        plataforma.adicionarMidia(f10);
        plataforma.adicionarMidia(f11);
        plataforma.adicionarMidia(f12);
        plataforma.adicionarMidia(f13);
        plataforma.adicionarMidia(f14);
        plataforma.adicionarMidia(f15);
        plataforma.adicionarMidia(f16);
        plataforma.adicionarMidia(f17);

        f4.addAvaliacao(a2);
        f4.addAvaliacao(a3);
        f4.addAvaliacao(a1);

        f5.addAvaliacao(a2);
        f5.addAvaliacao(a1);
        f5.addAvaliacao(a3);

        f6.addAvaliacao(a2);
        f6.addAvaliacao(a1);
        f6.addAvaliacao(a3);

        f7.addAvaliacao(a2);
        f7.addAvaliacao(a1);
        f7.addAvaliacao(a3);

        f8.addAvaliacao(a2);
        f8.addAvaliacao(a1);
        f8.addAvaliacao(a3);

        f9.addAvaliacao(a2);
        f9.addAvaliacao(a1);
        f9.addAvaliacao(a3);

        f10.addAvaliacao(a1);
        f10.addAvaliacao(a3);
        f10.addAvaliacao(a2);

        f11.addAvaliacao(a1);
        f11.addAvaliacao(a3);
        f11.addAvaliacao(a2);

        f12.addAvaliacao(a1);
        f12.addAvaliacao(a3);
        f12.addAvaliacao(a2);

        f13.addAvaliacao(a1);
        f13.addAvaliacao(a3);
        f13.addAvaliacao(a2);

        f14.addAvaliacao(a1);
        f14.addAvaliacao(a3);
        f14.addAvaliacao(a2);

        f15.addAvaliacao(a1);
        f15.addAvaliacao(a3);
        f15.addAvaliacao(a2);

        f16.addAvaliacao(a3);
        f16.addAvaliacao(a2);

        f17.addAvaliacao(a2);

        assertTrue(plataforma.relatorioClientesPorNumeroAvaliacoes(15).contains(
                "66,67% dos clientes possuem 15 ou mais avaliações"
        ));
    }

    @Test
    public void relatorioMidiasMaisBemAvaliadas() throws Exception {
        Avaliacao a4 = new Avaliacao("u4", 4,"bom");
        Avaliacao a5 = new Avaliacao("u5", 5,"bom");
        Avaliacao a6 = new Avaliacao("u6", 1,"bom");

        f1.addAvaliacao(a1);
        f1.addAvaliacao(a2);
        f1.addAvaliacao(a3);
        f1.addAvaliacao(a4);

        f2.addAvaliacao(a2);
        f2.addAvaliacao(a3);
        f2.addAvaliacao(a1);
        f2.addAvaliacao(a4);
        f2.addAvaliacao(a5);
        f2.addAvaliacao(a6);

        f3.addAvaliacao(a2);
        f3.addAvaliacao(a3);
        f3.addAvaliacao(a1);
        f3.addAvaliacao(a4);
        f3.addAvaliacao(a5);

        serie1.addAvaliacao(a4);

        serie2.addAvaliacao(a4);
        serie2.addAvaliacao(a5);

        serie3.addAvaliacao(a4);
        serie3.addAvaliacao(a5);
        serie3.addAvaliacao(a6);

        serieTeste.addAvaliacao(a2);
        midiaTest.addAvaliacao(a1);

        assertTrue(plataforma.relatorioMidiasMaisBemAvaliadas(6, 4).contains(
                "f3\nBreaking Bad\nf2"
        ));
    }

    @Test
    public void relatorioMidiasMaisVizualizadas(){
        plataforma.registrarAudiencia(f1);
        plataforma.registrarAudiencia(f1);
        plataforma.registrarAudiencia(f1);
        plataforma.registrarAudiencia(f1);

        plataforma.registrarAudiencia(f2);
        plataforma.registrarAudiencia(f2);

        plataforma.registrarAudiencia(f3);
        plataforma.registrarAudiencia(f3);

        plataforma.registrarAudiencia(serie1);
        plataforma.registrarAudiencia(serie1);
        plataforma.registrarAudiencia(serie1);
        plataforma.registrarAudiencia(serie1);
        plataforma.registrarAudiencia(serie1);

        plataforma.registrarAudiencia(serie2);

        plataforma.registrarAudiencia(serie3);
        plataforma.registrarAudiencia(serie3);
        plataforma.registrarAudiencia(serie3);
        plataforma.registrarAudiencia(serie3);

        assertTrue(plataforma.relatorioMidiasMaisVizualizadas(5).contains(
                "f1 - 7\nF.R.I.E.N.D.S - 6\nBreaking Bad - 5\n"
        ));
    }

    @Test
    public void relatorioMidiasFiltradasPorGeneroMaisBemAvaliadas() throws Exception {
        Avaliacao a4 = new Avaliacao("u4", 4,"bom");
        Avaliacao a5 = new Avaliacao("u5", 5,"bom");
        Avaliacao a6 = new Avaliacao("u6", 1,"bom");

        f1.addAvaliacao(a1);
        f1.addAvaliacao(a2);
        f1.addAvaliacao(a3);
        f1.addAvaliacao(a4);

        f2.addAvaliacao(a2);
        f2.addAvaliacao(a3);
        f2.addAvaliacao(a1);
        f2.addAvaliacao(a4);
        f2.addAvaliacao(a5);
        f2.addAvaliacao(a6);


        f3.addAvaliacao(a2);
        f3.addAvaliacao(a3);
        f3.addAvaliacao(a1);
        f3.addAvaliacao(a4);
        f3.addAvaliacao(a5);

        serie1.addAvaliacao(a4);

        serie2.addAvaliacao(a4);
        serie2.addAvaliacao(a5);

        serie3.addAvaliacao(a4);
        serie3.addAvaliacao(a5);
        serie3.addAvaliacao(a6);

        serieTeste.addAvaliacao(a2);
        midiaTest.addAvaliacao(a1);

        assertTrue(plataforma.relatorioMidiasMaisBemAvaliadas(6, 4, Genero.DRAMA).contains(
                "f3\n"
        ));
    }

    @Test
    public void relatorioMidiasFiltradasPorGeneroMaisVizualizadas(){
        plataforma.registrarAudiencia(f1);
        plataforma.registrarAudiencia(f1);
        plataforma.registrarAudiencia(f1);
        plataforma.registrarAudiencia(f1);

        plataforma.registrarAudiencia(f2);
        plataforma.registrarAudiencia(f2);

        plataforma.registrarAudiencia(f3);
        plataforma.registrarAudiencia(f3);
        plataforma.registrarAudiencia(f3);

        plataforma.registrarAudiencia(serie1);
        plataforma.registrarAudiencia(serie1);
        plataforma.registrarAudiencia(serie1);
        plataforma.registrarAudiencia(serie1);
        plataforma.registrarAudiencia(serie1);

        plataforma.registrarAudiencia(serie2);

        plataforma.registrarAudiencia(serie3);
        plataforma.registrarAudiencia(serie3);
        plataforma.registrarAudiencia(serie3);
        plataforma.registrarAudiencia(serie3);

        assertTrue(plataforma.relatorioMidiasMaisVizualizadas(5, Genero.DRAMA).contains(
                "f1 - 7\nf3 - 4\nf2 - 4"
        ));
    }
}