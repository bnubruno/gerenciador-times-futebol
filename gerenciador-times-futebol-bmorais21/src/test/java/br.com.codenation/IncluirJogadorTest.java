package br.com.codenation;

import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

public class IncluirJogadorTest {


    @Test
    public void idExistente(){
        DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();

        desafio.incluirTime(1l, "Real Madrid", LocalDate.of(2016, 1, 1), "Branco", "Azul");
        desafio.incluirJogador(1l, 1l, "Cristiano Ronaldo",LocalDate.of(1980,8,23),99, new BigDecimal(1000.00));

        try {
            desafio.incluirJogador(1l, 1l, "Ronaldinho Gaúcho",LocalDate.of(1985,10,21),999, new BigDecimal(20000.00));
            Assert.assertTrue("Deveria dar exceção!", false);
        } catch (Exception e) {
            if (e.getCause() instanceof IdentificadorUtilizadoException) {
                Assert.assertTrue(false);
            }
        }
    }

    @Test
    public void timeInexistente(){
        DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();

        try {
            desafio.incluirJogador(1l, 1l, "Ronaldinho Gaúcho",LocalDate.of(1985,10,21),999, new BigDecimal(20000.00));
            Assert.assertTrue("Deveria dar exceção!", false);
        } catch (Exception e) {
            if (e.getCause() instanceof TimeNaoEncontradoException) {
                Assert.assertTrue(false);
            }
        }
    }

}
