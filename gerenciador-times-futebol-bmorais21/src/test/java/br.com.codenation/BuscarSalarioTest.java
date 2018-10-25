package br.com.codenation;

import br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException;
import org.junit.Assert;
import org.junit.Test;

public class BuscarSalarioTest {
    @Test
    public void jogadorInexistente(){
        DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();

        try {
            desafio.buscarSalarioDoJogador(1l);
            Assert.assertTrue("Deveria dar exceção", false);
        }catch (Exception e){
            if (e.getCause() instanceof JogadorNaoEncontradoException){
                Assert.assertTrue(false);
            }
        }
    }
}
