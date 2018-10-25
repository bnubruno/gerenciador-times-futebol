package br.com.codenation;

import br.com.codenation.desafio.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class BuscarCapitaoTest {

    @Test
    public void timeInexistente(){
        DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();

        try {
            desafio.buscarCapitaoDoTime(1l);
            Assert.assertTrue("Deveria dar exceção", false);
        }catch (Exception e){
            if(e.getCause() instanceof TimeNaoEncontradoException){
                Assert.assertTrue(false);
            }
        }

    }

    @Test
    public void timeSemCapitao(){
        DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();

        desafio.incluirTime(1l, "Real Madrid", LocalDate.of(2016, 1, 1), "Branco", "Azul");

        try{
            desafio.buscarCapitaoDoTime(1l);
            Assert.assertTrue("Deveria dar exceção", false);
        }catch (Exception e){
            if(e.getCause() instanceof CapitaoNaoInformadoException){
                Assert.assertTrue(false);
            }
        }
    }

}
