package codigo.test;

import codigo.app.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClienteTest {
    Cliente clienteTeste;
    Serie serieTerror1;
    Serie serieTerror2;
    Serie serieComedia;
    Serie serieComedia2;
    Serie serieComedia3;
    Serie s1;
    Serie s2;
    Serie s3;

    @BeforeEach
    public void prepare(){
        clienteTeste = new ClienteRegular("Cteste", "login", "123");
        serieTerror1 = new Serie(1,"SerieTerror1", Genero.TERROR, Idioma.PORTUGUES, "10", 10);
        serieTerror2 = new Serie(2,"SerieTerror2", Genero.TERROR, Idioma.INGLES, "12",58);
        serieComedia = new Serie(3,"SerieComedia", Genero.COMEDIA, Idioma.PORTUGUES, "10",10);
        serieComedia2 = new Serie(4,"SerieComedia2", Genero.COMEDIA, Idioma.PORTUGUES, "10",10);
        serieComedia3 = new Serie(5,"SerieComedia3", Genero.COMEDIA, Idioma.PORTUGUES, "10",10);
        clienteTeste.adicionarNaLista(serieTerror1);
        clienteTeste.adicionarNaLista(serieTerror2);
        clienteTeste.adicionarNaLista(serieComedia);

        s1 = new Serie(6, "F.R.I.E.N.D.S", Genero.COMEDIA, Idioma.INGLES, "22/09/1994", 236);
        s2 = new Serie(7, "Stranger Things", Genero.DRAMA, Idioma.INGLES, "15/06/2016", 38);
        s3 = new Serie(8, "Game of Thrones", Genero.DRAMA, Idioma.INGLES, "17/04/2011", 73);
        clienteTeste.adicionarNaListaJaVistas(s1);
        clienteTeste.adicionarNaListaJaVistas(s2);
        clienteTeste.adicionarNaListaJaVistas(s3);
    }

    @Test
    public void deveAdicionarSerie(){
        Serie serieTerror3 = new Serie(4,"SerieTerror3", Genero.TERROR, Idioma.PORTUGUES, "10",5);
        clienteTeste.adicionarNaLista(serieTerror3);
        assertEquals(4, clienteTeste.getListaParaVer().size());
    }

    @Test
    public void testAdicionarASerieNaListaDeJaVistas(){
        assertEquals(3, clienteTeste.getListaJaVistas().size());
    }

    @Test
    public void deveRetirarSerie(){
        clienteTeste.retirarNaLista("SerieTerror1");
        assertEquals(2, clienteTeste.getListaParaVer().size());
    }

    @Test
    public void testRetirarSerieDaListaDeJaVistas(){
        clienteTeste.retirarNaListaJaVistas("F.R.I.E.N.D.S");
        assertEquals(2, clienteTeste.getListaJaVistas().size());
    }

    @Test
    public void deveFiltrarGenero(){
        assertEquals(2, clienteTeste.filtrarPorGenero(Genero.TERROR).size());
    }

    @Test
    public void deveFiltrarIdioma(){
        assertEquals(2, clienteTeste.filtrarPorIdioma(Idioma.PORTUGUES).size());
    }

    @Test
    public void deveFiltrarEpisodio(){
        assertEquals(2, clienteTeste.filtrarPorQtdEpisodios(10).size());
    }

    @Test
    public void deveAdicionarMidiaJaVista(){
        clienteTeste.registrarPorAudiencia(serieTerror1,"1999-08-01");
        assertEquals(1,clienteTeste.getDataQueFoiVista().size());

    }

    @Test
    public void testIsEspecalistaComApenasUmaMidiaAssistida(){
        clienteTeste.registrarPorAudiencia(serieTerror1,"1999-04-01");
        assertFalse(clienteTeste.isEspecialista());
    }

    @Test
    public void testIsEspecalistaComMaisDeCincoMidiasAssistidas(){
        clienteTeste.registrarPorAudiencia(serieTerror1,"2023-04-23");
        clienteTeste.registrarPorAudiencia(serieTerror2,"2023-04-24");
        clienteTeste.registrarPorAudiencia(serieComedia,"2023-04-25");
        clienteTeste.registrarPorAudiencia(serieComedia2,"2023-04-26");
        clienteTeste.registrarPorAudiencia(serieComedia3,"2023-04-27");
        clienteTeste.registrarPorAudiencia(s1, "2023-05-28");
        assertTrue(clienteTeste.isEspecialista());
    }

    @Test
    public void testIsEspecialistaComNenhumaMidiaAssistida(){
        assertFalse(clienteTeste.isEspecialista());
    }

    @Test
    public void testToStringCliente(){
        Cliente c1 = new ClienteRegular("Breno", "breno1", "breno123");
        assertEquals(c1.toString(), "Breno;breno1;breno123");
    }


//    @Test
//    public void deveAdicionarAvaliacao() throws Exception {
//        clienteTeste.registrarPorAudiencia(serieComedia,String dataVista);
//        clienteTeste.addAvaliacao(serieComedia, 5);
//    }

}