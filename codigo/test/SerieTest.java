package codigo.test;

import codigo.app.Genero;
import codigo.app.Idioma;
import codigo.app.Serie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SerieTest {
    Serie s1;
    Serie s2;

    @BeforeEach
    public void prepare() {
        s1 = new Serie(1, "nome", Genero.DRAMA, Idioma.PORTUGUES, "2", 6);
        s2 = new Serie(2, "F.R.I.E.N.D.S", Genero.COMEDIA, Idioma.INGLES, "22/09/1994", 236);
    }

    @Test
    public void deveRegistrarAudiencia() {
        s1.registrarAudiencia();
        assertEquals(1, s1.getAudiencia());
    }

    @Test
    void testToString() {
        String expected = "S;2;F.R.I.E.N.D.S;22/09/1994;Comédia;Inglês;236";
        Assertions.assertEquals(expected, s2.toString());
    }

    @Test
    void testDefinirTipoMidia() {
        Assertions.assertEquals("S", s2.definirTipoMidia());
    }

    @Test
    void testPegarQtdEpisodios() {
        Assertions.assertEquals(236, s2.getQtdEpisodios());
    }

    @Test
    void testFiltrarPorQtdEpisodiosVerdadeiro() {
        Assertions.assertTrue(s1.filtrarPorQtdEpisodios(6));
        Assertions.assertTrue(s2.filtrarPorQtdEpisodios(236));
    }

    @Test
    void testFiltrarPorQtdEpisodiosFalso() {
        Assertions.assertFalse(s1.filtrarPorQtdEpisodios(12));
        Assertions.assertFalse(s2.filtrarPorQtdEpisodios(8));
    }

}