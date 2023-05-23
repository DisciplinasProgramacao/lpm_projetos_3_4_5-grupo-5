package codigo.test;

import codigo.app.Cliente;
import codigo.app.Serie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class ClienteTest {
    Cliente clienteTeste;
    Serie serieTerror1;
    Serie serieTerror2;
    Serie serieComedia;
    Serie serieComedia2;
    Serie serieComedia3;

    @BeforeEach
    public void prepare(){
        clienteTeste = new Cliente("Cteste", "login", "123");
        serieTerror1 = new Serie(1,"SerieTerror1", "terror", "portugues", "10", 10);
        serieTerror2 = new Serie(2,"SerieTerror2", "terror", "ingles", "12",58);
        serieComedia = new Serie(3,"SerieComedia", "comedia", "portugues", "10",10);
        serieComedia2 = new Serie(4,"SerieComedia2", "comedia", "portugues", "10",10);
        serieComedia3 = new Serie(5,"SerieComedia3", "comedia", "portugues", "10",10);
        clienteTeste.adicionarNaLista(serieTerror1);
        clienteTeste.adicionarNaLista(serieTerror2);
        clienteTeste.adicionarNaLista(serieComedia);

    }

    @Test
    public void deveAdicionarSerie(){
        Serie serieTerror3 = new Serie(4,"SerieTerror3", "terror", "portugues", "10",5);
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

    @Test
    public void deveAdicionarMidiaJaVista(){
        clienteTeste.registrarPorAudiencia(serieTerror1,"1999-08-01");
        assertEquals(1,clienteTeste.getDataQueFoiVista().size());

    }

    @Test
    public void isNotEspecalista(){
        clienteTeste.registrarPorAudiencia(serieTerror1,"1999-04-01");
        assertFalse(clienteTeste.isEspecialista());
    }

    @Test
    public void isEspecalista(){
        clienteTeste.registrarPorAudiencia(serieTerror1,"2023-04-23");
        clienteTeste.registrarPorAudiencia(serieTerror2,"2023-04-24");
        clienteTeste.registrarPorAudiencia(serieComedia,"2023-04-25");
        clienteTeste.registrarPorAudiencia(serieComedia2,"2023-04-26");
        clienteTeste.registrarPorAudiencia(serieComedia3,"2023-04-27");
        assertTrue(clienteTeste.isEspecialista());
    }


//    @Test
//    public void deveAdicionarAvaliacao() throws Exception {
//        clienteTeste.registrarPorAudiencia(serieComedia,String dataVista);
//        clienteTeste.addAvaliacao(serieComedia, 5);
//    }

}