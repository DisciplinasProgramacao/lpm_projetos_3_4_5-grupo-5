package codigo.test;

import codigo.app.Filme;
import codigo.app.Midia;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FilmeTest {
    Midia f1;
    Midia f2;
    Midia f3;
    @BeforeEach
    public void prepare(){
        f1 = new Filme(1,"Inception", "aventura", "ingles", "16/07/2010", 120);
        f2 = new Filme(2,"The Shawshank Redemption", "drama", "ingles", "23/05/2010", 130);
        f3 = new Filme(3,"The Dark Knight", "aventura", "ingles", "18/07/2008", 180);
    }

    @Test
    public void testToStringFilme(){
        assertEquals(f1.toString(), "F;1;Inception;aventura;ingles;16/07/2010;120");
    }

    @Test
    public void testDefinirTipoMidia(){
        String expected = "F";
        assertEquals(expected, f1.definirTipoMidia());
    }

}