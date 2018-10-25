package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;
import org.junit.Assert;
import org.junit.Test;

public class JogadorTest {

    @Test
    public void getNivelHabilidadeTest() {
        Jogador jogador = new Jogador(1l, 1l,"Pedro", LocalDate.of(1990,1,1), 5, new BigDecimal(1500));

        int habilidade = jogador.getNivelHabilidade();
        if (habilidade != jogador.nivelHabilidade)
            Assert.fail();
    }

    @Test
    public void setIdTest() {
        Jogador jogador = new Jogador(1l, 1l,"Pedro", LocalDate.of(1990,1,1), 5, new BigDecimal(1500));

        jogador.setId(2l);
        if (jogador.getId() != 2l)
            Assert.fail();
    }

    @Test
    public void setIdTimeTest() {
        Jogador jogador = new Jogador(1l, 1l,"Pedro", LocalDate.of(1990,1,1), 5, new BigDecimal(1500));

        jogador.setIdTime(2l);
        if (jogador.getIdTime() != 2l)
            Assert.fail();
    }

    @Test
    public void setDataNascimentoTest() {
        Jogador jogador = new Jogador(1l, 1l,"Pedro", LocalDate.of(1990,1,1), 5, new BigDecimal(1500));
        jogador.setDataNascimento(LocalDate.of(1980,1,1));

        if (! jogador.getDataNascimento().isEqual(LocalDate.of(1980,1,1)))
            Assert.fail();
    }

    @Test
    public void compareToTest() {
        Jogador jogador = new Jogador(1l, 1l,"Pedro", LocalDate.of(1990,1,1), 5, new BigDecimal(1500));
        Jogador jogador2 = new Jogador(2l, 1l,"Lucas", LocalDate.of(1990,1,1), 6, new BigDecimal(1500));

        int comparacao = jogador.compareTo(jogador2);
        if (comparacao != 1)
            Assert.fail();
    }

}
