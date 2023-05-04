package codigo.test;

import codigo.app.Cliente;
import codigo.app.Serie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClitenteTeste {
    Cliente clienteTeste;
    Serie serieTerror1;
    Serie serieTerror2;
    Serie serieComedia;

    @BeforeEach
    public void prepare(){
        clienteTeste = new Cliente("Cteste", "123");
        serieTerror1 = new Serie("SerieTerror1", "terror", "portugues", "10");
        serieTerror2 = new Serie("SerieTerror2", "terror", "ingles", "12");
        serieComedia = new Serie("SerieComedia", "comedia", "portugues", "10");
        clienteTeste.adicionarNaLista(serieTerror1);
        clienteTeste.adicionarNaLista(serieTerror2);
        clienteTeste.adicionarNaLista(serieComedia);
    }

    @Test
    public void deveAdicionarSerie(){
        Serie serieTerror3 = new Serie("SerieTerror3", "terror", "portugues", "10");
        clienteTeste.adicionarNaLista(serieTerror3);
        assertEquals(4, clienteTeste.getListaParaVer().size());
    }

    @Test
    public void deveRetirarSerie(){
        clienteTeste.retirarNaLista("SerieTerror1");
        assertEquals(2, clienteTeste.getListaParaVer().size());
    }

    @Test
    public void deveFiltrarGenero(){
        assertEquals(2, clienteTeste.filtrarPorGenero("terror").size());
    }

    @Test
    public void deveFiltrarIdioma(){
        assertEquals(2, clienteTeste.filtrarPorIdioma("portugues").size());
    }

    @Test
    public void deveFiltrarEpisodio(){
        assertEquals(2, clienteTeste.filtrarPorQtdEpisodios(10).size());
    }
}