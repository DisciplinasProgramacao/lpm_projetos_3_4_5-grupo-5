package codigo.test;

import codigo.app.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FilmeTest {
    Midia f1;
    Midia f2;
    Midia f3;
    @BeforeEach
    public void prepare(){
        f1 = new Filme(1,"Inception", Genero.AVENTURA, Idioma.PORTUGUES, "16/07/2010", EstadoMidia.MODIFICAVEL, 120);
        f2 = new Filme(2,"The Shawshank Redemption", Genero.DRAMA, Idioma.PORTUGUES, "23/05/2010", EstadoMidia.MODIFICAVEL, 130);
        f3 = new Filme(3,"The Dark Knight", Genero.AVENTURA, Idioma.PORTUGUES, "18/07/2008", EstadoMidia.MODIFICAVEL, 180);
    }

    @Test
    public void testToStringFilme(){
        assertEquals(f1.toString(), "F;1;Inception;16/07/2010;Aventura;Português;Modificável;120");
    }

    @Test
    public void testDefinirTipoMidia(){
        String expected = "F";
        assertEquals(expected, f1.definirTipoMidia());
    }

    @Test
    public void testMedia() throws Exception {
        Cliente usuario = new Cliente("nome", "login", "senha");
        Avaliacao avaliacao = new Avaliacao("nome",4,"Bom");
        Cliente usuario2 = new Cliente("nome2", "login2", "senha");
        Avaliacao avaliacao2 = new Avaliacao("nome2",5,"Top");
        Cliente usuario3 = new Cliente("nome3", "login3", "senha");
        Avaliacao avaliacao3 = new Avaliacao("nome3",3,"Dahora");
        f1.addAvaliacao(avaliacao);
        f1.addAvaliacao(avaliacao2);
        f1.addAvaliacao(avaliacao3);
        f1.addAvaliacao(avaliacao3);
        assertEquals(4.0, f1.getMedia());
    }

}