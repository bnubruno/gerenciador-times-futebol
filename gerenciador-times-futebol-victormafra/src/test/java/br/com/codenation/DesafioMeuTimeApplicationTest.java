package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;

import br.com.codenation.desafio.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;
 
public class DesafioMeuTimeApplicationTest {

	@Test
	public void incluirTime() {
		DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();
		try {
			desafio.incluirTime(1l, "Time", LocalDate.of(2000, 1, 1), "Branco", "Azul");
		} catch (Exception e) {
			Assert.fail("Não conseguiu incluir");
		}
	}
	@Test
	public void incluirTimeRepetido() {
		DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();

		desafio.incluirTime(1l, "Time", LocalDate.of(2000, 1, 1), "Branco", "Azul");

		try {
			desafio.incluirTime(1l, "Time", LocalDate.of(2000, 1, 1), "Branco", "Azul");
			Assert.assertTrue("Deveria dar exceção", false);
		} catch (Exception e) {
			if (e.getCause() instanceof IdentificadorUtilizadoException) {
				Assert.assertTrue(false);
			}
		}
	}

	
	@Test
	public void incluirJogadorRepetido() {
		DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();
		desafio.incluirTime(1l, "Time", LocalDate.of(2000, 1, 1), "Branco", "Azul");
		desafio.incluirJogador(10l, 1l, "Alvaro", LocalDate.of(2000, 1, 1), 50, BigDecimal.valueOf(2000));
  
		try {
			desafio.incluirJogador(10l, 1l, "Alvaro", LocalDate.of(2000, 1, 1), 50, BigDecimal.valueOf(2000));
			Assert.assertTrue("Deveria dar exceção", false);
		} catch (Exception e) {
			if (e.getCause() instanceof IdentificadorUtilizadoException) {
				Assert.assertTrue(false);
			}
		}
	} 
	@Test
	public void incluirJogadorSemTime() {
		DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();

		try {
			desafio.incluirJogador(1l, 1l, "Alvaro", LocalDate.of(2000, 1, 1), 50, BigDecimal.valueOf(2000));
		} catch (Exception e) {
			if (e.getCause() instanceof TimeNaoEncontradoException) {
				Assert.assertTrue(false);
			}
		}
	}
	
	@Test
	public void definirCapitao() {
		DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();
		desafio.incluirTime(1l, "Time", LocalDate.of(2000, 1, 1), "Branco", "Azul");
		desafio.incluirJogador(10l, 1l, "Alvaro", LocalDate.of(2000, 1, 1), 50, BigDecimal.valueOf(2000));
		    
		try {
			desafio.definirCapitao(10l);
		} catch (Exception e) {
			if (e.getCause() instanceof JogadorNaoEncontradoException) {
				Assert.assertTrue(false); 
			}
		}
	}
	@Test
	public void buscarCapitaoInexistente() {
		DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();
		desafio.incluirTime(1l, "Time", LocalDate.of(2000, 1, 1), "Branco", "Azul");
		desafio.incluirJogador(10l, 1l, "Alvaro", LocalDate.of(2000, 1, 1), 50, BigDecimal.valueOf(2000));
		try {
			desafio.buscarCapitaoDoTime(1l);
		} catch (Exception e) {
			if (e.getCause() instanceof CapitaoNaoInformadoException) {
				Assert.assertTrue(false);
			}
		}
	}
	@Test
	public void buscarCapitaoSemTime() {
		DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();
		desafio.incluirTime(1l, "Time", LocalDate.of(2000, 1, 1), "Branco", "Azul");
		desafio.incluirJogador(10l, 1l, "Alvaro", LocalDate.of(2000, 1, 1), 50, BigDecimal.valueOf(2000));
		try {
			desafio.buscarCapitaoDoTime(5l);
		} catch (Exception e) {
			if (e.getCause() instanceof TimeNaoEncontradoException) {
				Assert.assertTrue(false);
			}
		}
	}
	
	@Test
	public void buscarJogadorInexistente() {
		DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();
		try {
			desafio.buscarNomeJogador(5l);
		} catch (Exception e) {
			if (e.getCause() instanceof JogadorNaoEncontradoException) {
				Assert.assertTrue(false);
			}
		}
	}
	@Test
	public void buscarJogador() {
		DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();
		desafio.incluirTime(1l, "Time", LocalDate.of(2000, 1, 1), "Branco", "Azul");
		desafio.incluirJogador(10l, 1l, "Alvaro", LocalDate.of(2000, 1, 1), 50, BigDecimal.valueOf(2000));
		try {
			String nomeDoJogador = desafio.buscarNomeJogador(10l);
			Assert.assertEquals("Alvaro", nomeDoJogador);
		} catch (Exception e) {
			if (e.getCause() instanceof JogadorNaoEncontradoException) {
				Assert.fail("Nao encontrou o jogador");
			}
		}
	}
	
	@Test
	public void buscarTimeInexistente() {
		DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();
		try {
			desafio.buscarNomeJogador(5l);
		} catch (Exception e) {
			if (e.getCause() instanceof TimeNaoEncontradoException) {
				Assert.assertTrue(false);
			}
		}
	}
	@Test
	public void buscarTime() {
		DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();
		desafio.incluirTime(1l, "Time", LocalDate.of(2000, 1, 1), "Branco", "Azul");
		try {
			String nomeDoTime = desafio.buscarNomeTime(1l);
			Assert.assertEquals("Time", nomeDoTime);
		} catch (Exception e) {
			if (e.getCause() instanceof TimeNaoEncontradoException) {
				Assert.fail("Não encontrou o time");
			}
		}
	}
	
	@Test
	public void buscarMelhorJogadorInexistente() {
		DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();
		try {
			desafio.buscarMelhorJogadorDoTime(5l);
		} catch (Exception e) {
			if (e.getCause() instanceof TimeNaoEncontradoException) {
				Assert.assertTrue(false);
			}
		}
	}
	@Test
	public void buscarMelhorJogador() {
		DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();
		desafio.incluirTime(1l, "Time", LocalDate.of(2000, 1, 1), "Branco", "Azul");
		desafio.incluirJogador(10l, 1l, "Alvaro", LocalDate.of(2000, 1, 1), 50, BigDecimal.valueOf(2000));
		desafio.incluirJogador(11l, 1l, "Beto", LocalDate.of(2000, 1, 1), 100, BigDecimal.valueOf(2000));
		desafio.incluirJogador(12l, 1l, "Claudio", LocalDate.of(2000, 1, 1), 75, BigDecimal.valueOf(2000));

		try {
			Long idDoJogador = desafio.buscarMelhorJogadorDoTime(1l);
			Assert.assertEquals(Long.valueOf(11), idDoJogador);
		} catch (Exception e) {
			if (e.getCause() instanceof TimeNaoEncontradoException) {
				Assert.assertTrue(false);
			}
		}
	}
	
	@Test
	public void buscarJogadorMaisVelhoInexistente() {
		DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();
		try {
			desafio.buscarJogadorMaisVelho(5l);
		} catch (Exception e) {
			if (e.getCause() instanceof TimeNaoEncontradoException) {
				Assert.assertTrue(false);
			}
		}
	}
	@Test
	public void buscarJogadorMaoirSalarioInexistente() {
		DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();
		try {
			desafio.buscarJogadorMaiorSalario(5l);
		} catch (Exception e) {
			if (e.getCause() instanceof TimeNaoEncontradoException) {
				Assert.assertTrue(false);
			} 
		}
	}
}
