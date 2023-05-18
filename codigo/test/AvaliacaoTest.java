package codigo.test;

import codigo.app.Avaliacao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AvaliacaoTest {

    Avaliacao avaliacao;
    String nomeUsuario;
    int nota;

    @BeforeEach
    public void prepare() {
        nomeUsuario = "nome";
        nota = 5;
    }

    @Test
    public void deveValidarAvaliacao() throws Exception {
        avaliacao = new Avaliacao(nomeUsuario, 5);
    }

}
