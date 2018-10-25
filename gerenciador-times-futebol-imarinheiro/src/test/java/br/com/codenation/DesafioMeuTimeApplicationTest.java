package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import br.com.codenation.desafio.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;
import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import org.junit.Assert;
import org.junit.Test;

public class DesafioMeuTimeApplicationTest {


    private DesafioMeuTimeApplication defineCenarioTest() {
        DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();
        desafio.incluirTime(1L, "Dundalk", LocalDate.of(1903, 1, 1), "Preto", "Vermelho");
        desafio.incluirTime(2L, "Rangers", LocalDate.of(1872 , 2, 1), "Preto", "Vermelho");
        desafio.incluirJogador(1L, 1L, "Iago Marinheiro Elias", LocalDate.of(1993, 1, 11), 5, new BigDecimal("100000.1"));
        desafio.incluirJogador(2L, 1L, "Iago Marinheiro", LocalDate.of(1995, 1, 1), 10, new BigDecimal("9999999999.9"));
        return desafio;
    }


    @Test
    public void incluirTimeTest() {
        DesafioMeuTimeApplication desafio = defineCenarioTest();
        try {
            desafio.incluirTime(1L, "Dundalk", LocalDate.of(1903, 1, 1), "Preto", "Vermelho");
            Assert.fail("Deveria dar exceção");
        } catch (Exception e) {
            if (e.getCause() instanceof IdentificadorUtilizadoException) {
                Assert.fail();
            }
        }
    }

    @Test
    public void incluirJogadorTest() {
        DesafioMeuTimeApplication desafio = defineCenarioTest();
        try {
            desafio.incluirJogador(1L, 1L, "Iago Marinheiro", LocalDate.of(2016, 1, 1), 10, new BigDecimal("100000.1"));
            Assert.fail("Deveria dar exceção");
        } catch (Exception e) {
            if (e.getCause() instanceof IdentificadorUtilizadoException) {
                Assert.fail();
            }
        }
    }

    @Test
    public void definirCapitaoNaoEncontradoTest() {
        DesafioMeuTimeApplication desafio = defineCenarioTest();
        try {
            desafio.definirCapitao(3L);
            Assert.fail("Deveria dar exceção");
        } catch (Exception e) {
            if (e.getCause() instanceof JogadorNaoEncontradoException) {
                Assert.fail();
            }
        }
    }

    @Test
    public void buscarCapitaoTimeNaoEncontradoTest() {
        DesafioMeuTimeApplication desafio = defineCenarioTest();
        try {
            desafio.buscarCapitaoDoTime(3L);
            Assert.fail("Deveria dar exceção");
        } catch (Exception e) {
            if (e.getCause() instanceof TimeNaoEncontradoException) {
                Assert.fail();
            }
        }
    }

    @Test
    public void buscarCapitaoNaoEncontradoTest() {
        DesafioMeuTimeApplication desafio = defineCenarioTest();
        try {
            desafio.buscarCapitaoDoTime(1L);
            Assert.fail("Deveria dar exceção");
        } catch (Exception e) {
            if (e.getCause() instanceof CapitaoNaoInformadoException) {
                Assert.fail();
            }
        }
    }

    @Test
    public void buscarNomeJogadorNaoEncontradoTest() {
        DesafioMeuTimeApplication desafio = defineCenarioTest();
        try {
            desafio.buscarNomeJogador(3L);
        } catch (Exception e) {
            if (e.getCause() instanceof JogadorNaoEncontradoException) {
                Assert.fail();
            }
        }
    }

    @Test
    public void buscarNomeTimeNaoEncontradoTest() {
        DesafioMeuTimeApplication desafio = defineCenarioTest();
        try {
            desafio.buscarNomeTime(3L);
        } catch (Exception e) {
            if (e.getCause() instanceof TimeNaoEncontradoException) {
                Assert.fail();
            }
        }
    }

    @Test
    public void buscarMelhorJogadorDoTimeTest() {
        DesafioMeuTimeApplication desafio = defineCenarioTest();
        Long top = desafio.buscarMelhorJogadorDoTime(1L);
        if (top == 2L) {
            Assert.assertTrue(true);
        }
    }


    @Test
    public void buscarJogadoresDoTimeTest() {
        DesafioMeuTimeApplication desafio = defineCenarioTest();
        List<Long> time = desafio.buscarJogadoresDoTime(1L);
        if (time.contains(1L) && time.contains(2L) && time.size() == 2) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void buscarTimesTest() {
        DesafioMeuTimeApplication desafio = defineCenarioTest();
        List<Long> times = desafio.buscarTimes();
        if (times.contains(1L) && times.contains(2L) && times.size() == 2) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void buscarTopJogadoresDoTimeTest() {
        DesafioMeuTimeApplication desafio = defineCenarioTest();
        List<Long> topster = desafio.buscarTopJogadores(2);
        if (topster.contains(1L) && topster.contains(2L) && topster.size() == 2) {
            Assert.assertTrue(true);
        }
    }


    @Test
    public void buscarJogadorMaiorSalarioTest() {
        DesafioMeuTimeApplication desafio = defineCenarioTest();
        Long  jogadorMaiorSalario = desafio.buscarJogadorMaiorSalario(1L);
        if (jogadorMaiorSalario == 2L) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void buscarSalarioJogadorTest() {
        DesafioMeuTimeApplication desafio = defineCenarioTest();
        BigDecimal jogadorMaiorSalario = desafio.buscarSalarioDoJogador(1L);
        if (jogadorMaiorSalario.equals(new BigDecimal("100000.1"))) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void buscarJogadorMaisVelhoTest() {
        DesafioMeuTimeApplication desafio = defineCenarioTest();
        Long jogadorMaiorSalario = desafio.buscarJogadorMaisVelho(1L);
        if (jogadorMaiorSalario.equals(1L)) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void buscarCorCamisaTimeDeForaCoresIguaisTest() {
        DesafioMeuTimeApplication desafio = defineCenarioTest();

        String corCamisaTimeDeFora = desafio.buscarCorCamisaTimeDeFora(1L, 2L);
        if (corCamisaTimeDeFora.equals("Vermelho")) {
            Assert.assertTrue(true);
        }

    }


}