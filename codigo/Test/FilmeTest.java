package codigo.test;

import codigo.app.Filme;
import codigo.app.Midia;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FilmeTest {
    Midia f1;
    @BeforeEach
    public void prepare(){
        f1 = new Filme(1,"nome", "terror", "portugues", "2", 30);
    }

    @Test
    public void primeiroTeste(){

    }
}