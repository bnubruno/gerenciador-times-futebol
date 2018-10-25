package br.com.codenation;

import br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class DesafioMeuTimeApplicationTestWithoutBefore {

    private DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();

    @Test
    public void dadoInteiroQuandoNenhumJogadorEntaoRetornaListaVazia() {
        if (desafio.buscarTopJogadores(10).isEmpty()) {
            Assert.assertTrue(true);
        } else {
            Assert.fail();
        }
    }

    @Test
    public void dadoIdTimeQuandoNenhumJogadorEntaoRetornaExcecaoMelhorJogador() {
        desafio.incluirTime(1L, "Primeiro time", LocalDate.of(1970, 01, 01), "Primária", "Secundária");

        try {
            desafio.buscarMelhorJogadorDoTime(1L);
            Assert.fail();
        } catch (JogadorNaoEncontradoException e) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void dadoIdTimeQuandoNenhumJogadorEntaoRetornaExcecaoMaisVelho() {
        desafio.incluirTime(1L, "Primeiro time", LocalDate.of(1970, 01, 01), "Primária", "Secundária");

        try {
            desafio.buscarJogadorMaisVelho(1L);
            Assert.fail();
        } catch (JogadorNaoEncontradoException e) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void dadoIdTimeQuandoNenhumJogadorEntaoRetornaExcecaoMaiorSalario() {
        desafio.incluirTime(1L, "Primeiro time", LocalDate.of(1970, 01, 01), "Primária", "Secundária");

        try {
            desafio.buscarJogadorMaiorSalario(1L);
            Assert.fail();
        } catch (JogadorNaoEncontradoException e) {
            Assert.assertTrue(true);
        }
    }
}
