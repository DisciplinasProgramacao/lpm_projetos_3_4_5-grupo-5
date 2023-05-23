package codigo.test;

import codigo.app.Avaliacao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

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

    @Test
    void testeValorEntreOsValoresDeterminados() {
        Assertions.assertThrows(Exception.class, () -> avaliacao.validaAvaliacao(5));
    }

    @Test
    void testeValorAcimaDosValoresEsperados() {
        Assertions.assertThrows(Exception.class, () -> avaliacao.validaAvaliacao(15));
    }

    @Test
    void testeValorAbaixoDosValoresEsperados() {
        Assertions.assertThrows(Exception.class, () -> avaliacao.validaAvaliacao(-5));
    }
}
