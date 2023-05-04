package codigo.test;

import codigo.app.Serie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SerieTest {
    Serie s1;
    @BeforeEach
    public void prepare(){
        s1 = new Serie(1,"nome", "terror", "portugues", "2",6);
    }

    @Test
    public void deveRegistrarAudiencia(){
        s1.registrarAudiencia();
        assertEquals(1, s1.getAudiencia());
    }
}