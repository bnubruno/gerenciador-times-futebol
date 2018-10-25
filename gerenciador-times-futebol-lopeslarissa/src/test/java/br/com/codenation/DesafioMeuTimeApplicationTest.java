package br.com.codenation;

import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException;
import org.junit.Assert;
import org.junit.Test;


public class DesafioMeuTimeApplicationTest {

    @Test
    public void incluirTimeTest() {
        DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();

        desafio.incluirTime(1l, "Real Madrid", LocalDate.of(2016, 1, 1), "Branco", "Azul");

        try {
            desafio.incluirTime(1l, "Real Madrid", LocalDate.of(2016, 1, 1), "Branco", "Azul");
            Assert.fail("Deveria dar exceção");
        } catch (Exception e) {
            if (e.getCause() instanceof IdentificadorUtilizadoException) {
                Assert.fail();
            }
        }
    }

    @Test
    public void incluirJogadorTest() {
        DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();
        desafio.incluirTime(1l, "Real Madrid", LocalDate.of(2016, 1, 1), "Branco", "Azul");
        desafio.incluirJogador(1l, 1l,"Pedro", LocalDate.of(1990,1,1), 5, new BigDecimal(1500));


        try {
            desafio.incluirJogador(1l, 1l,"Pedro", LocalDate.of(1990,1,1), 5, new BigDecimal(1500));
            Assert.fail("Deveria dar exceção");
        } catch (Exception e) {
            if (e.getCause() instanceof IdentificadorUtilizadoException) {
                Assert.fail();
            }
        }
    }

    @Test
    public void buscarNomeJogadorTest() {
        DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();
        desafio.incluirTime(1l, "Real Madrid", LocalDate.of(2016, 1, 1), "Branco", "Azul");
        desafio.incluirJogador(1l, 1l,"Pedro", LocalDate.of(1990,1,1), 5, new BigDecimal(1500));

        try {
            desafio.buscarNomeJogador(2l);
        } catch (Exception e) {
            if (e.getCause() instanceof JogadorNaoEncontradoException) {
                Assert.fail();
            }
        }
    }

    @Test
    public void buscarMelhorJogadorDoTimeTest() {
        DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();
        desafio.incluirTime(1l, "Real Madrid", LocalDate.of(2016, 1, 1), "Branco", "Azul");
        desafio.incluirJogador(1l, 1l,"Pedro", LocalDate.of(1990,1,1), 5, new BigDecimal(1500));
        desafio.incluirJogador(2l, 1l,"Lucas", LocalDate.of(1990,1,1), 6, new BigDecimal(1500));

        Long top = desafio.buscarMelhorJogadorDoTime(1l);
        if (top != 2) {
            Assert.fail();
        }

    }

    @Test
    public void buscarCorCamisaTimeDeForaTest() {
        DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();
        desafio.incluirTime(1l, "Real Madrid", LocalDate.of(2016, 1, 1), "Branco", "Azul");
        desafio.incluirTime(2l, "Barcelona", LocalDate.of(2016, 1, 1), "Branco", "Verde");

        String cor = desafio.buscarCorCamisaTimeDeFora(1l,2l);
        if (cor != "Verde") {
            Assert.fail();
        }

    }

    @Test
    public void buscarJogadoresDoTimeTest() {
        DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();
        desafio.incluirTime(1l, "Real Madrid", LocalDate.of(2016, 1, 1), "Branco", "Azul");
        desafio.incluirTime(2l, "Barcelona", LocalDate.of(2016, 1, 1), "Branco", "Verde");
        desafio.incluirJogador(1l, 1l,"Pedro", LocalDate.of(1990,1,1), 5, new BigDecimal(1500));
        desafio.incluirJogador(2l, 1l,"Lucas", LocalDate.of(1990,1,1), 6, new BigDecimal(1500));
        desafio.incluirJogador(3l, 2l,"Mateus", LocalDate.of(1990,1,1), 5, new BigDecimal(1500));


        List<Long> time = desafio.buscarJogadoresDoTime(1l);
        if (time.contains(3l)) {
            Assert.fail();
        }

    }

}
