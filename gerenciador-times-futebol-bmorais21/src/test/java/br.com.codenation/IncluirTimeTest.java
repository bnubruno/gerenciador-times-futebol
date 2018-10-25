package br.com.codenation;

import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class IncluirTimeTest {

    @Test
    public void idExistente(){
        DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();

        desafio.incluirTime(1l, "Real Madrid", LocalDate.of(2016, 1, 1), "Branco", "Azul");

        try {
            desafio.incluirTime(1l, "Real Madrid", LocalDate.of(2016, 1, 1), "Branco", "Azul");
            Assert.assertTrue("Deveria dar exceção!", false);
        } catch (Exception e) {
            if (e.getCause() instanceof IdentificadorUtilizadoException) {
                Assert.assertTrue(false);
            }
        }
    }

}
