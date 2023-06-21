package codigo.test;

import codigo.app.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClienteTest {
    Cliente clienteTeste;
    Serie serieDRAMA1;
    Serie serieDRAMA2;
    Serie serieComedia;
    Serie serieComedia2;
    Serie serieComedia3;
    Serie s1;
    Serie s2;
    Serie s3;

    @BeforeEach
    public void prepare(){
        clienteTeste = new Cliente("Cteste", "login", "123", false);
        serieDRAMA1 = new Serie(1,"SerieDRAMA1", Genero.DRAMA, Idioma.PORTUGUES, "10",  EstadoMidia.MODIFICAVEL, 10);
        serieDRAMA2 = new Serie(2,"SerieDRAMA2", Genero.DRAMA, Idioma.INGLES, "12", EstadoMidia.MODIFICAVEL, 58);
        serieComedia = new Serie(3,"SerieComedia", Genero.COMEDIA, Idioma.PORTUGUES, "10", EstadoMidia.MODIFICAVEL, 10);
        serieComedia2 = new Serie(4,"SerieComedia2", Genero.COMEDIA, Idioma.PORTUGUES, "10", EstadoMidia.MODIFICAVEL, 10);
        serieComedia3 = new Serie(5,"SerieComedia3", Genero.COMEDIA, Idioma.PORTUGUES, "10", EstadoMidia.MODIFICAVEL, 30);
        clienteTeste.adicionarNaLista(serieDRAMA1);
        clienteTeste.adicionarNaLista(serieDRAMA2);
        clienteTeste.adicionarNaLista(serieComedia);
        clienteTeste.adicionarNaLista(serieComedia2);
        clienteTeste.adicionarNaLista(serieComedia3);

        s1 = new Serie(6, "F.R.I.E.N.D.S", Genero.COMEDIA, Idioma.INGLES, "22/09/1994",  EstadoMidia.MODIFICAVEL, 236);
        s2 = new Serie(7, "Stranger Things", Genero.DRAMA, Idioma.INGLES, "15/06/2016",  EstadoMidia.MODIFICAVEL, 38);
        s3 = new Serie(8, "Game of Thrones", Genero.DRAMA, Idioma.INGLES, "17/04/2011",  EstadoMidia.MODIFICAVEL, 73);
        clienteTeste.registrarPorAudiencia(s1);
        clienteTeste.registrarPorAudiencia(s2);
        clienteTeste.registrarPorAudiencia(s3);
    }

    @Test
    public void testValidaParametrosConstrutor() {
        Cliente cliente = new Cliente("NomeDeUsuario", "login", "senha");
        assertTrue(cliente.validaParametrosConstrutor("NomeDeUsuario", "login", "senha"));
        assertFalse(cliente.validaParametrosConstrutor("NomeDeUsuario", "loginloginloginloginlogin", "senha"));
        assertFalse(cliente.validaParametrosConstrutor("NomeDeUsuarioNomeDeUsuarioNomeDeUsuario", "login", "senha"));
        assertFalse(cliente.validaParametrosConstrutor("NomeDeUsuario", "login", "senhasenhasenha"));
        assertFalse(cliente.validaParametrosConstrutor("", "login", "senha"));
        assertFalse(cliente.validaParametrosConstrutor("NomeDeUsuario", "", "senha"));
        assertFalse(cliente.validaParametrosConstrutor("NomeDeUsuario", "login", ""));
    }

    @Test
    public void deveAdicionarSerie(){
        Serie serieDRAMA3 = new Serie(4,"SerieDRAMA3", Genero.DRAMA, Idioma.PORTUGUES, "10", EstadoMidia.MODIFICAVEL, 5);
        clienteTeste.adicionarNaLista(serieDRAMA3);
        assertEquals(6, clienteTeste.getListaParaVer().size());
    }

    @Test
    public void testAdicionarASerieNaListaDeJaVistas(){
        assertEquals(3, clienteTeste.getListaJaVistas().size());
    }

    @Test
    public void deveRetirarSerie(){
        clienteTeste.retirarNaLista("SerieDRAMA1");
        assertEquals(4, clienteTeste.getListaParaVer().size());
    }

    @Test
    public void deveFiltrarGenero(){
        assertEquals(4, clienteTeste.filtrarPorGenero(Genero.DRAMA).size());
        assertEquals(4, clienteTeste.filtrarPorGenero(Genero.COMEDIA).size());
    }

    @Test
    public void deveFiltrarIdioma(){
        assertEquals(4, clienteTeste.filtrarPorIdioma(Idioma.PORTUGUES).size());
        assertEquals(4, clienteTeste.filtrarPorIdioma(Idioma.INGLES).size());
    }

    @Test
    public void deveFiltrarEpisodio(){
        assertEquals(3, clienteTeste.filtrarPorQtdEpisodios(10).size());
    }

    @Test
    public void deveAdicionarMidiaJaVista(){
        clienteTeste.registrarPorAudiencia(serieDRAMA1);
        assertEquals(4,clienteTeste.getDataQueFoiVista().size());

    }

    @Test
    public void testIsEspecalistaComApenasUmaMidiaAssistida(){
        clienteTeste.registrarPorAudiencia(serieDRAMA1);
        clienteTeste.verificarEstado();

        assertTrue(clienteTeste.getState() instanceof ClienteRegular);
    }

    @Test
    public void testIsEspecalistaComMaisDeCincoMidiasAssistidas(){
        clienteTeste.registrarPorAudiencia(serieDRAMA1);
        clienteTeste.registrarPorAudiencia(serieDRAMA2);
        clienteTeste.registrarPorAudiencia(serieComedia);
        clienteTeste.registrarPorAudiencia(serieComedia2);
        clienteTeste.registrarPorAudiencia(serieComedia3);

        assertFalse(clienteTeste.getState() instanceof ClienteEspecialista);
    }

    @Test
    public void testIsEspecialistaComNenhumaMidiaAssistida(){
        assertTrue(clienteTeste.getState() instanceof ClienteRegular);
    }

    @Test
    public void testToStringCliente(){
        Cliente c1 = new Cliente("Breno", "breno1", "breno123", false);
        assertEquals(c1.toString(), "Breno;breno1;breno123;Regular");
    }


    @Test
    public void deveAdicionarAvaliacao() throws Exception {
        clienteTeste.registrarPorAudiencia(serieDRAMA1);
        clienteTeste.addAvaliacao(serieDRAMA1,3, "testeteste");
        assertEquals(1,serieDRAMA1.getAvaliacoes().size());
    }


}