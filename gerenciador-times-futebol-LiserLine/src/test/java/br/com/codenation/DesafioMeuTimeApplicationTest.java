package br.com.codenation;

import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;
import org.junit.Assert;
import org.junit.Test;
import sun.security.krb5.internal.crypto.Des;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

public class DesafioMeuTimeApplicationTest {

    @Test
    public void incluirTime() {
        DesafioMeuTimeApplication des = new DesafioMeuTimeApplication();
        des.incluirTime(1L,"Benanas", LocalDate.of(2018,10,03),
                        "amarelo", "preto");

        try{
            des.incluirTime(1L,"Bonanas", LocalDate.of(2018,10,03),
                    "amarelo", "preto");
            Assert.fail("Deveria dar exceção");
        }catch (Exception ex){
            Assert.assertTrue(ex instanceof IdentificadorUtilizadoException);
        }
    }

    @Test
    public void incluirJogadorT() {
        //Incluir jogador sem time.

        DesafioMeuTimeApplication des = new DesafioMeuTimeApplication();

        try{
            des.incluirJogador(1L,1L,"Faustão", LocalDate.now(), 30, new BigDecimal(10000.0));
            Assert.fail("Já avisei que vai dar merda isso!");
        }catch (Exception ex){
            Assert.assertTrue(ex instanceof TimeNaoEncontradoException);
        }

    }

    @Test
    public void incluirJogadorI(){
        //Tenta incluir jogador com id já existente.

        DesafioMeuTimeApplication des = new DesafioMeuTimeApplication();

        des.incluirTime(1L,"Benanas", LocalDate.of(2018,10,03),
                "amarelo", "preto");
        des.incluirJogador(1L,1L,"Faustão", LocalDate.now(), 30, new BigDecimal(10000.0));

        try{
            des.incluirJogador(1L,1L,"Faustão", LocalDate.now(), 30, new BigDecimal(10000.0));
            Assert.fail("Já avisei que vai dar merda isso!");

        }catch (Exception ex){
            Assert.assertTrue(ex instanceof IdentificadorUtilizadoException);
        }

    }

    @Test
    public void definirCapitao() {
        DesafioMeuTimeApplication des = new DesafioMeuTimeApplication();

        try{
            des.definirCapitao(1L);
            Assert.fail("Já avisei que vai dar merda isso");
        }
        catch (Exception ex){
            Assert.assertTrue(ex instanceof JogadorNaoEncontradoException);
        }
    }

    @Test
    public void buscarCapitaoDoTime() {
        DesafioMeuTimeApplication des = new DesafioMeuTimeApplication();

        try{
            des.buscarCapitaoDoTime(1L);
            Assert.fail("Já avisei que vai dar merda isso");
        }catch (Exception ex){
            Assert.assertTrue(ex instanceof TimeNaoEncontradoException);
        }

    }

    @Test
    public void buscarNomeJogador() {
        DesafioMeuTimeApplication des = new DesafioMeuTimeApplication();

        try{
            des.buscarNomeJogador(1L);
            Assert.fail("Já avisei que vai dar merda isso");
        }
        catch (Exception ex){
            Assert.assertTrue(ex instanceof JogadorNaoEncontradoException);
        }


    }

    @Test
    public void buscarNomeTime() {
        DesafioMeuTimeApplication des = new DesafioMeuTimeApplication();

        try{
            des.buscarNomeTime(1L);
            Assert.fail("Já avisei que vai dar merda isso!");
        }
        catch (Exception ex){
            Assert.assertTrue(ex instanceof TimeNaoEncontradoException);
        }

    }

    @Test
    public void buscarJogadoresDoTime() {
        DesafioMeuTimeApplication des = new DesafioMeuTimeApplication();

        try{
            des.buscarJogadoresDoTime(1L);
            Assert.fail("Já avisei que vai dar merda isso!");
        }
        catch (Exception ex){
            Assert.assertTrue(ex instanceof TimeNaoEncontradoException);
        }
    }

    @Test
    public void buscarMelhorJogadorDoTime() {
        DesafioMeuTimeApplication des = new DesafioMeuTimeApplication();

        try{
            des.buscarMelhorJogadorDoTime(1l);
            Assert.fail("Já avisei que vai dar merda isso!");
        }
        catch (Exception ex){
            Assert.assertTrue(ex instanceof TimeNaoEncontradoException);
        }
    }

    @Test
    public void buscarJogadorMaisVelho() {
        DesafioMeuTimeApplication des = new DesafioMeuTimeApplication();

        try{
            des.buscarJogadorMaisVelho(1L);
            Assert.fail("Já avisei que vai dar merda isso!");
        } catch (Exception ex){
            Assert.assertTrue(ex instanceof TimeNaoEncontradoException);
        }
    }

    @Test
    public void buscarTimes() {
        DesafioMeuTimeApplication des = new DesafioMeuTimeApplication();

        des.incluirTime(1L,"Benanas", LocalDate.of(2018,10,03),
                "amarelo", "preto");
        des.incluirTime(2L,"Bonanas", LocalDate.of(2018,10,03),
                "verde", "amarelo");

        List<Long> times = des.buscarTimes();

        if(times.size() != 2) Assert.fail("Já avisei que vai dar merda isso!");
    }

    @Test
    public void buscarJogadorMaiorSalario() {
        DesafioMeuTimeApplication des = new DesafioMeuTimeApplication();

        try{
            des.buscarJogadorMaiorSalario(1L);
            Assert.fail("Já avisei que vai dar merda isso!");
        } catch (Exception ex){
            Assert.assertTrue(ex instanceof TimeNaoEncontradoException);
        }
    }

    @Test
    public void buscarSalarioDoJogador() {
        DesafioMeuTimeApplication des = new DesafioMeuTimeApplication();

        try {
            des.buscarSalarioDoJogador(1L);
            Assert.fail("Já avisei que vai dar merda isso!");
        } catch (Exception ex){
            Assert.assertTrue(ex instanceof JogadorNaoEncontradoException);
        }
    }

    @Test
    public void buscarTopJogadores() {
        DesafioMeuTimeApplication des = new DesafioMeuTimeApplication();

        des.incluirTime(1L,"Benanas", LocalDate.of(2018,10,03),
                "amarelo", "preto");

        des.incluirJogador(1L,1L,"Faustão", LocalDate.now(), 30, new BigDecimal(10000.0));

        List<Long> top =  des.buscarTopJogadores(1);

        if(top.size() != 1) Assert.fail("Já avisei que vai dar merda isso!");

    }

    @Test
    public void buscarCorCamisaTimeDeForaC() {
        //Time da casa não encontrado
        DesafioMeuTimeApplication des = new DesafioMeuTimeApplication();

        des.incluirTime(2L,"Benanas", LocalDate.of(2018,10,03),
                "amarelo", "preto");

        try{
            des.buscarCorCamisaTimeDeFora(1L,2L);
            Assert.fail("Já avisei que vai dar merda isso!");
        }
        catch (Exception ex){
            Assert.assertTrue(ex instanceof TimeNaoEncontradoException);
        }


    }

    @Test
    public void buscarCorCamisaTimeDeForaF() {
        //Time de fora não encontrado
        DesafioMeuTimeApplication des = new DesafioMeuTimeApplication();

        des.incluirTime(1L,"Benanas", LocalDate.of(2018,10,03),
                "amarelo", "preto");

        try{
            des.buscarCorCamisaTimeDeFora(1L,2L);
            Assert.fail("Já avisei que vai dar merda isso!");
        }
        catch (Exception ex){
            Assert.assertTrue(ex instanceof TimeNaoEncontradoException);
        }
    }
}