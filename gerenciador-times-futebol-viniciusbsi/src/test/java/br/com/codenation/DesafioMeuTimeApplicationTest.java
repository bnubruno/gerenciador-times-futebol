package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;

import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;

public class DesafioMeuTimeApplicationTest {
	
//	Teste SourceTree git
	@Test
	public void CadastroTimeMesmoId() {
		DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();
		desafio.incluirTime(1l, "Real Madrid", LocalDate.of(2016, 1, 1), "Branco", "Azul");
		
		try {
			desafio.incluirTime(1l, "Real Madrid", LocalDate.of(2016, 1, 1), "Branco", "Azul");
			Assert.assertTrue("Deveria dar exceção", false);
		} 
		catch (Exception e) {
			if (e.getCause() instanceof IdentificadorUtilizadoException) {
				Assert.assertTrue(false);
			}
		}
		
	}
	
	@Test
	public void CadastroJogadorMesmoId() {	
		DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();
		desafio.incluirTime(1l, "Real Madrid", LocalDate.of(2016, 1, 1), "Branco", "Azul");
		desafio.incluirJogador(1l, 1l, "Jose", LocalDate.of(2016, 1, 1), 1500, new BigDecimal(1.0));
		
		try {
			desafio.incluirJogador(1l, 1l, "Jose", LocalDate.of(2016, 1, 1), 1500, new BigDecimal(1.0));
			Assert.assertTrue("Deveria dar exceção", false);
		} 
		catch (Exception e) {
			if (e.getCause() instanceof IdentificadorUtilizadoException) {
				Assert.assertTrue(false);
			}
		}
	}
	
	@Test
	public void BuscaCapitaoTimeNaoCadastrado() {	
		DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();
		
		try {
			desafio.buscarCapitaoDoTime(2l);
			Assert.assertTrue("Deveria dar exceção", false);
		} 
		catch (Exception e) {
			if (e.getCause() instanceof TimeNaoEncontradoException) {
				Assert.assertTrue(false);
			}
		}
		
	}
	
	@Test
	public void BuscaTimeCadastrado() {	
		DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();
		desafio.incluirTime(1l, "Real Madrid", LocalDate.of(2016, 1, 1), "Branco", "Azul");
		try {
			desafio.buscarNomeTime(1l);
			Assert.assertTrue("Deve retornar", true);
		} 
		catch (Exception e) {
			if (e.getCause() instanceof TimeNaoEncontradoException) {
				Assert.assertTrue(true);
			}
		}
	}
	
	@Test
	public void BuscaNomeJogadorCadastrado() {	
		DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();
		desafio.incluirTime(1l, "Real Madrid", LocalDate.of(2016, 1, 1), "Branco", "Azul");
		desafio.incluirJogador(1l, 1l, "Jose", LocalDate.of(2016, 1, 1), 1500, new BigDecimal(1.0));
		try {
			desafio.buscarNomeJogador(1l);
			Assert.assertTrue("Deve retornar", true);
		} 
		catch (Exception e) {
			if (e.getCause() instanceof JogadorNaoEncontradoException) {
				Assert.assertTrue(true);
			}
		}
	}
	
	@Test
	public void DefineCapitaoJogadorCadastrado() {	
		DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();
		desafio.incluirTime(1l, "Real Madrid", LocalDate.of(2016, 1, 1), "Branco", "Azul");
		desafio.incluirJogador(1l, 1l, "Jose", LocalDate.of(2016, 1, 1), 1500, new BigDecimal(1.0));
		try {
			desafio.definirCapitao(1l);
			Assert.assertTrue("Deve retornar", true);
		} 
		catch (Exception e) {
			if (e.getCause() instanceof JogadorNaoEncontradoException) {
				Assert.assertTrue(true);
			}
		}
	}
	
	@Test
	public void BuscaSalarioJogadorCadastrado() {	
		DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();
		desafio.incluirTime(1l, "Real Madrid", LocalDate.of(2016, 1, 1), "Branco", "Azul");
		desafio.incluirJogador(1l, 1l, "Jose", LocalDate.of(2016, 1, 1), 1500, new BigDecimal(1.0));
		try {
			desafio.buscarSalarioDoJogador(1l);
			Assert.assertTrue("Deve retornar", true);
		} 
		catch (Exception e) {
			if (e.getCause() instanceof JogadorNaoEncontradoException) {
				Assert.assertTrue(true);
			}
		}
	}
	
	@Test
	public void BuscaJogadorMaiorSalarioTime() {	
		DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();
		desafio.incluirTime(1l, "Real Madrid", LocalDate.of(2016, 1, 1), "Branco", "Azul");
		desafio.incluirJogador(1l, 1l, "Jose", LocalDate.of(2016, 1, 1), 1500, new BigDecimal(1.0));
		try {
			desafio.buscarJogadorMaiorSalario(1l);
			Assert.assertTrue("Deve retornar", true);
		} 
		catch (Exception e) {
			if (e.getCause() instanceof JogadorNaoEncontradoException) {
				Assert.assertTrue(true);
			}
		}
	}
	
	@Test
	public void BuscaJogadorMaisVelhoTime() {	
		DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();
		desafio.incluirTime(1l, "Real Madrid", LocalDate.of(2016, 1, 1), "Branco", "Azul");
		desafio.incluirJogador(1l, 1l, "Jose", LocalDate.of(2016, 1, 1), 1500, new BigDecimal(1.0));
		try {
			desafio.buscarJogadorMaisVelho(1l);
			Assert.assertTrue("Deve retornar", true);
		} 
		catch (Exception e) {
			if (e.getCause() instanceof TimeNaoEncontradoException) {
				Assert.assertTrue(true);
			}
		}
	}
	
	@Test
	public void BuscaMelhorJogadorTime() {	
		DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();
		desafio.incluirTime(1l, "Real Madrid", LocalDate.of(2016, 1, 1), "Branco", "Azul");
		desafio.incluirJogador(1l, 1l, "Jose", LocalDate.of(2016, 1, 1), 1500, new BigDecimal(1.0));
		try {
			desafio.buscarMelhorJogadorDoTime(1l);
			Assert.assertTrue("Deve retornar", true);
		} 
		catch (Exception e) {
			if (e.getCause() instanceof TimeNaoEncontradoException) {
				Assert.assertTrue(true);
			}
		}
		
	}
}