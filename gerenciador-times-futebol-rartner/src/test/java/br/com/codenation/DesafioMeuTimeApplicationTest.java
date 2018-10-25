package br.com.codenation;

import br.com.codenation.desafio.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class DesafioMeuTimeApplicationTest {

    private DesafioMeuTimeApplication desafio;

    @Before
    public void setUp() {
        desafio = new DesafioMeuTimeApplication();

        incluirTimes();
        incluirJogadores();
    }

    private void incluirJogadores() {
        desafio.incluirJogador(12L, 1L, "Lugano", LocalDate.of(1980, 7, 11), 77, BigDecimal.valueOf(15000));
        desafio.incluirJogador(11L, 1L, "Ceni", LocalDate.of(1975, 1, 1), 90, BigDecimal.valueOf(27000));
        desafio.incluirJogador(13L, 1L, "Mineiro", LocalDate.of(1983, 1, 1), 68, BigDecimal.valueOf(8000));
        desafio.incluirJogador(14L, 1L, "Kaká", LocalDate.of(1980, 1, 1), 82, BigDecimal.valueOf(22000));
        desafio.incluirJogador(15L, 1L, "Amoroso",LocalDate.of(1977, 1, 1), 75, BigDecimal.valueOf(25000));

        desafio.incluirJogador(22L, 2L, "André Dias", LocalDate.of(1984, 1, 1), 60, BigDecimal.valueOf(3000));
        desafio.incluirJogador(25L, 2L, "Aloísio", LocalDate.of(1981, 1, 1), 62, BigDecimal.valueOf(7500));
        desafio.incluirJogador(21L, 2L, "Bosco", LocalDate.of(1981, 1, 1), 62, BigDecimal.valueOf(7000));
        desafio.incluirJogador(23L, 2L, "Josué", LocalDate.of(1981, 1, 1), 72, BigDecimal.valueOf(7500));
        desafio.incluirJogador(24L, 2L, "Hernanes", LocalDate.of(1990, 1, 1), 85, BigDecimal.valueOf(16000));

        desafio.incluirJogador(31L, 3L, "Zetti", LocalDate.of(1972, 1, 1), 80, BigDecimal.valueOf(15000));
        desafio.incluirJogador(32L, 3L, "Miranda", LocalDate.of(1988, 1, 1), 80, BigDecimal.valueOf(12000));
        desafio.incluirJogador(33L, 3L, "Jucilei", LocalDate.of(1995, 1, 1), 71, BigDecimal.valueOf(4000));
        desafio.incluirJogador(34L, 3L, "Ganso", LocalDate.of(1998, 1, 1), 69, BigDecimal.valueOf(4000));
        desafio.incluirJogador(35L, 3L, "Pato", LocalDate.of(1999, 1, 1), 70, BigDecimal.valueOf(5000));

        desafio.incluirJogador(42L, 4L, "Fabão", LocalDate.of(1981, 1, 1), 58, BigDecimal.valueOf(2300));
        desafio.incluirJogador(41L, 4L, "Denis", LocalDate.of(1982, 1, 1), 52, BigDecimal.valueOf(2300));
        desafio.incluirJogador(43L, 4L, "Petros", LocalDate.of(1983, 1, 1), 61, BigDecimal.valueOf(3700));
        desafio.incluirJogador(44L, 4L, "Nene", LocalDate.of(1981, 1, 1), 67, BigDecimal.valueOf(7999));
        desafio.incluirJogador(45L, 4L, "Luís Fabiano", LocalDate.of(1980, 1, 1), 72, BigDecimal.valueOf(12000));
    }

    private void incluirTimes() {
        desafio.incluirTime(1L, "Primeiro time", LocalDate.of(1970, 01, 01), "Primária", "Secundária");
        desafio.incluirTime(2L, "Segundo time", LocalDate.of(1972, 01, 01), "Secundária", "Primária");
        desafio.incluirTime(3L, "Terceiro time", LocalDate.of(1977, 01, 01), "Secundária", "Terciária");
        desafio.incluirTime(4L, "Quarto time", LocalDate.of(1982, 01, 01), "Primária", "Terciária");
    }

    @Test
    public void dadoNovoJogadorEntaoInserirJogador() {
        try {
            desafio.incluirJogador(16L, 1L, "Muller", LocalDate.of(1976, 01, 01), 81, BigDecimal.valueOf(16500.00));
        } catch (Exception e) {
            Assert.fail();
        }

        Assert.assertTrue(true);
    }

    @Test
    public void dadoNovoJogadorQuandoIdJaExisteEntaoExcecao() {
        try {
            desafio.incluirJogador(15L, 1L, "Muller", LocalDate.of(1976, 01, 01), 81, BigDecimal.valueOf(16500.00));
            Assert.fail();
        } catch (IdentificadorUtilizadoException e) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void dadoNovoJogadorQuandoTimeNaoExisteEntaoExcecao() {
        try {
            desafio.incluirJogador(16L, 5L, "Muller", LocalDate.of(1976, 01, 01), 81, BigDecimal.valueOf(16500.00));
            Assert.fail();
        } catch (TimeNaoEncontradoException e) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void dadoNovoTimeEntaoInserirTime() {
        try {
            desafio.incluirTime(5L, "Quinto time", LocalDate.of(2002, 01, 01), "Terciária", "Primária");
            Assert.assertTrue(true);
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void dadoNovoTimeQuandoIdJaExisteEntaoExcecao() {
        try {
            desafio.incluirTime(4L, "Quinto time", LocalDate.of(2002, 01, 01), "Terciária", "Primária");
            Assert.fail();
        } catch (IdentificadorUtilizadoException e) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void buscarTimes() {
        List<Long> esperado = Arrays.asList(1L, 2L, 3L, 4L);
        List<Long> times = desafio.buscarTimes();

        Assert.assertArrayEquals(esperado.toArray(), times.toArray());
    }

    @Test
    public void dadoIdJogadorQuandoJogadorExistenteEntaoDefinirCapitao() {
        desafio.definirCapitao(11L);

        Assert.assertEquals(11L, desafio.buscarCapitaoDoTime(1L), 0L);
    }

    @Test
    public void dadoIdCapitaoQuandoJogadorNaoExistenteEntaoRetornarExcecao() {
        try {
            desafio.definirCapitao(16L);
            Assert.fail();
        } catch (JogadorNaoEncontradoException e) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void dadoIdCapitaoQuandoTimeNaoExistenteEntaoRetornarExcecao() {
        try {
            desafio.buscarCapitaoDoTime(5L);
        } catch (TimeNaoEncontradoException e) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void dadoTimeExistenteQuandoCapitaoNaoInformadoEntaoRetornaExcecao() {
        try {
            desafio.buscarCapitaoDoTime(3L);
        } catch (CapitaoNaoInformadoException e) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void dadoCodigoQuandoJogadorExisteEntaoRetornaNome() {
        Assert.assertEquals("Ceni", desafio.buscarNomeJogador(11L));
    }

    @Test
    public void dadoCodigoQuandoJogadorNaoExisteEntaoRetornaExcecaoNome() {
        try {
            desafio.buscarNomeJogador(10L);
            Assert.fail();
        } catch (JogadorNaoEncontradoException e) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void dadoCodigoQuandoTimeExistenteEntaoRetornaNomeTime() {
        Assert.assertEquals("Primeiro time", desafio.buscarNomeTime(1L));
    }

    @Test
    public void dadoCodigoQuandoTimeNaoExistenteEntaoRetornaExcecaoNomeTime() {
        try {
            desafio.buscarNomeTime(6L);
            Assert.fail();
        } catch (TimeNaoEncontradoException e) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void dadoIdTimeQuandoTimeExisteEntaoRetornaListaJogadores() {
        List<Long> esperado = Arrays.asList(11L, 12L, 13L, 14L, 15L);
        List<Long> jogadores = desafio.buscarJogadoresDoTime(1L);

        Assert.assertArrayEquals(esperado.toArray(), jogadores.toArray());
    }

    @Test
    public void dadoIdTimeQuandoTimeNaoExisteEntaoRetornaExcecaoJogadores() {
        try {
            desafio.buscarJogadoresDoTime(10L);
            Assert.fail();
        } catch (TimeNaoEncontradoException e) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void dadoIdTimeQuandoTimeExisteEntaoRetornaMelhorJogador() {
        Assert.assertEquals(11L, desafio.buscarMelhorJogadorDoTime(1L), 0L);
    }

    @Test
    public void dadoIdTimeQuandoTimeNaoExisteEntaoRetornaExcecaoMelhorJogador() {
        try {
            desafio.buscarMelhorJogadorDoTime(10L);
            Assert.fail();
        } catch (TimeNaoEncontradoException e) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void dadoIdTimeQuandoTimeExisteEntaoRetornaMaisVelho() {
        Assert.assertEquals(31L, desafio.buscarJogadorMaisVelho(3L), 0L);
    }

    @Test
    public void dadoIdTimeQuandoTimeNaoExisteEntaoRetornaExcecaoMaisVelho() {
        try {
            desafio.buscarJogadorMaisVelho(10L);
            Assert.fail();
        } catch (TimeNaoEncontradoException e) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void dadoIdTimeQuandoTimeExisteEntaoRetornaJogadorMaiorSalario() {
        Assert.assertEquals(11L, desafio.buscarJogadorMaiorSalario(1L), 0L);
    }

    @Test
    public void dadoIdTimeQuandoTimeNaoExisteEntaoRetornaExcecaoMaiorSalario() {
        try {
            desafio.buscarJogadorMaiorSalario(10L);
            Assert.fail();
        } catch (TimeNaoEncontradoException e) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void dadoCodigoQuandoJogadorExisteEntaoRetornaSalario() {
        Assert.assertEquals(BigDecimal.valueOf(3000), desafio.buscarSalarioDoJogador(22L));
    }

    @Test
    public void dadoCodigoQuandoJogadorNaoExisteEntaoRetornaExcecaoSalario() {
        try {
            desafio.buscarSalarioDoJogador(10L);
            Assert.fail();
        } catch (JogadorNaoEncontradoException e) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void buscarTopJogadores() {
        List<Long> esperado = Arrays.asList(11L, 24L, 14L, 31L, 32L);
        List<Long> top = desafio.buscarTopJogadores(5);

        Assert.assertArrayEquals(esperado.toArray(), top.toArray());
    }

    @Test
    public void dadosDoisTimesQuandoUmNaoExisteEntaoRetornaExcecao() {
        try {
            desafio.buscarCorCamisaTimeDeFora(1L, 5L);
            Assert.fail();
        } catch (TimeNaoEncontradoException e) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void dadoDoisTimesQuandoCoresIguaisEntaoRetornaSecundaria() {
        String cor = desafio.buscarCorCamisaTimeDeFora(1L, 4L);
        Assert.assertEquals("Terciária", desafio.buscarCorCamisaTimeDeFora(1L, 4L));
    }

    @Test
    public void dadoDoisTimesQuandoCoresDiferentesEntaoRetornaPrimaria() {
        Assert.assertEquals("Secundária", desafio.buscarCorCamisaTimeDeFora(1L, 2L));
    }
}