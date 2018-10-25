package br.com.codenation;

import br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;
import org.junit.Assert;
import org.junit.Test;

public class BuscarMaiorSalarioTest {
    @Test
    public void timeInexistente(){
        DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();

        try{
            desafio.buscarJogadorMaiorSalario(1l);
            Assert.assertTrue("Deveria dar exceção", false);
        }catch (Exception e){
            if(e.getCause() instanceof TimeNaoEncontradoException){
                Assert.assertTrue(false);
            }
        }
    }
}
