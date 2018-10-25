package br.com.codenation;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.codenation.desafio.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;

public class DesafioMeuTimeApplicationTest {

	DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();

	// incluido times
	private void incluir_times() {
		desafio.incluirTime(1l, "São Paulo", LocalDate.now(), "Branco", "Preto");
		desafio.incluirTime(2l, "Flamengo", LocalDate.now(), "Vermelho", "Branco");
		desafio.incluirTime(3l, "Corinthians", LocalDate.now(), "Branco", "Preto");
		desafio.incluirTime(4l, "Santos", LocalDate.now(), "Branco", "Preto");
		desafio.incluirTime(5l, "Palmeiras", LocalDate.now(), "Verde", "Branco");
		desafio.incluirTime(6l, "Barcelona", LocalDate.now(), "Vermelho", "Preto");
		desafio.incluirTime(7l, "Vasco da Gama", LocalDate.now(), "Branco", "Preto");
		desafio.incluirTime(8l, "Grêmio", LocalDate.now(), "Azul", "Branco");
		desafio.incluirTime(9l, "Cruzeiro", LocalDate.now(), "Azul", "Branco");
	}

	// incluido jogadores
	private void incluir_jogadores() {
		desafio.incluirJogador(1l, 1l, "Rogério Ceni", LocalDate.of(1979, 5, 1), 70, BigDecimal.valueOf(3040));
		desafio.incluirJogador(2l, 1l, "Cafu", LocalDate.of(1975, 7, 1), 50, BigDecimal.valueOf(3000));
		desafio.incluirJogador(3l, 1l, "Pedro Luiz", LocalDate.of(1980, 5, 1), 50, BigDecimal.valueOf(3000));
		desafio.incluirJogador(4l, 1l, "Bordom", LocalDate.of(1982, 5, 10), 50, BigDecimal.valueOf(3000));
		desafio.incluirJogador(5l, 1l, "Mineiro", LocalDate.of(1983, 6, 1), 50, BigDecimal.valueOf(3000));
		desafio.incluirJogador(6l, 1l, "Maldonado", LocalDate.of(1979, 7, 18), 50, BigDecimal.valueOf(3000));
		desafio.incluirJogador(7l, 1l, "Kaká", LocalDate.of(1988, 2, 10), 90, BigDecimal.valueOf(33000));
		desafio.incluirJogador(8l, 1l, "Raí", LocalDate.of(1965, 5, 15), 90, BigDecimal.valueOf(3000));
		desafio.incluirJogador(9l, 1l, "Luís Fabiano", LocalDate.of(1987, 5, 1), 50, BigDecimal.valueOf(3000));
		desafio.incluirJogador(10l, 1l, "França", LocalDate.of(1997, 5, 4), 50, BigDecimal.valueOf(3000));
		desafio.incluirJogador(11l, 1l, "Sierra", LocalDate.of(1965, 5, 14), 50, BigDecimal.valueOf(3000));

		desafio.incluirJogador(12l, 2l, "Zico", LocalDate.of(1960, 6, 1), 50, BigDecimal.valueOf(50000));
		desafio.incluirJogador(13l, 2l, "Bruno", LocalDate.of(1980, 7, 3), 50, BigDecimal.valueOf(3000));

		desafio.incluirJogador(14l, 3l, "Marcelinho Carioca", LocalDate.of(1975, 4, 29), 70, BigDecimal.valueOf(11000));
		desafio.incluirJogador(15l, 3l, "Viola", LocalDate.of(1971, 5, 1), 50, BigDecimal.valueOf(11001));

	}

	@Test
	public void test1() {
		incluir_times();
		try {
			desafio.incluirTime(1l, "Real Madrid", LocalDate.of(2016, 1, 1), "Branco", "Azul");
			Assert.assertTrue("Deveria dar excecao por id repetido", false);
		} catch (Exception e) {
			if (e.getCause() instanceof IdentificadorUtilizadoException) {
				Assert.assertTrue(false);
			}
		}
	}

	@Test
	public void test2() {
		incluir_times();
		incluir_jogadores();
		try {
			desafio.incluirJogador(1l, 1l, "Aurélio", LocalDate.of(1990, 12, 22), 45, BigDecimal.valueOf(2000));
			Assert.assertTrue("Deveria dar excecao por id repetido", false);
		} catch (Exception e) {
			if (e.getCause() instanceof IdentificadorUtilizadoException) {
				Assert.assertTrue(false);
			}
		}
	}

	@Test
	public void test3() {
		incluir_times();
		incluir_jogadores();
		try {
			desafio.incluirJogador(16l, 10l, "Aurélio", LocalDate.of(1990, 12, 22), 45, BigDecimal.valueOf(2000));
			Assert.assertTrue("Deveria dar excecao por time nao encontrado", false);
		} catch (Exception e) {
			if (e.getCause() instanceof TimeNaoEncontradoException) {
				Assert.assertTrue(false);
			}
		}
	}

	@Test
	public void test4() {
		incluir_times();
		incluir_jogadores();
		try {
			desafio.definirCapitao(16l);
			Assert.assertTrue("Deveria dar excecao por jogador nao encontrado", false);
		} catch (Exception e) {
			if (e.getCause() instanceof JogadorNaoEncontradoException) {
				Assert.assertTrue(false);
			}
		}
	}

	@Test
	public void test5() {
		incluir_times();
		incluir_jogadores();
		desafio.definirCapitao(4l);
		try {
			desafio.buscarCapitaoDoTime(10l);
			Assert.assertTrue("Deveria dar excecao por time nao encontrado", false);
		} catch (Exception e) {
			if (e.getCause() instanceof TimeNaoEncontradoException) {
				Assert.assertTrue(false);
			}
		}
	}

	@Test
	public void test6() {
		incluir_times();
		incluir_jogadores();
		desafio.definirCapitao(2l);
		try {
			desafio.buscarCapitaoDoTime(2l);
			Assert.assertTrue("Deveria dar excecao por capitao nao informado", false);
		} catch (Exception e) {
			if (e.getCause() instanceof CapitaoNaoInformadoException) {
				Assert.assertTrue(false);
			}
		}
	}

	@Test
	public void test7() {
		incluir_times();
		incluir_jogadores();
		desafio.definirCapitao(6l);
		Assert.assertEquals(6l, desafio.buscarCapitaoDoTime(1l), 0);
	}

	@Test
	public void tesst8() {
		incluir_times();
		incluir_jogadores();
		try {
			desafio.buscarNomeJogador(16l);
			Assert.assertTrue("Deveria dar excecao por jogador nao encontrado", false);
		} catch (Exception e) {
			if (e.getCause() instanceof JogadorNaoEncontradoException) {
				Assert.assertTrue(false);
			}
		}
	}

	@Test
	public void test9() {
		incluir_times();
		incluir_jogadores();
		Assert.assertEquals("Nome do jogador pelo id", "Marcelinho Carioca", desafio.buscarNomeJogador(14l));
	}
	
	@Test
	public void test10() {
		incluir_times();
		incluir_jogadores();
		try {
			desafio.buscarNomeTime(10l);
			Assert.assertTrue("Deveria dar excecao por time nao encontrado", false);
		} catch (Exception e) {
			if (e.getCause() instanceof TimeNaoEncontradoException) {
				Assert.assertTrue(false);
			}
		}
	}
	
	@Test
	public void test11() {
		incluir_times();
		incluir_jogadores();
		Assert.assertEquals("Nome do time pelo id",	"Santos", desafio.buscarNomeTime(4l));
	}
	
	@Test
	public void test12() {
		incluir_times();
		incluir_jogadores();
		try {
			desafio.buscarJogadoresDoTime(10l);
			Assert.assertTrue("Deveria dar excecao por time nao encontrado", false);
		} catch (Exception e) {
			if (e.getCause() instanceof TimeNaoEncontradoException) {
				Assert.assertTrue(false);
			}
		}
	}
	
	@Test
	public void test13() {
		incluir_times();
		incluir_jogadores();
		try {
			desafio.buscarJogadoresDoTime(5l);
			Assert.assertTrue("Deveria dar excecao por jogador nao encontrado", false);
		} catch (Exception e) {
			if (e.getCause() instanceof JogadorNaoEncontradoException) {
				Assert.assertTrue(false);
			}
		}
	}
	
	@Test
	public void test14() {
		incluir_times();
		incluir_jogadores();
		List<Long> jogadores = Arrays.asList(14l, 15l);
		Assert.assertEquals("Lista de id de jogadores pelo id do time", jogadores, desafio.buscarJogadoresDoTime(3l));
	}
	
	@Test
	public void test15() {
		incluir_times();
		incluir_jogadores();
		try {
			desafio.buscarMelhorJogadorDoTime(10l);
			Assert.assertTrue("Deveria dar excecao por time nao encontrado", false);
		} catch (Exception e) {
			if (e.getCause() instanceof TimeNaoEncontradoException) {
				Assert.assertTrue(false);
			}
		}
	}
	
	@Test
	public void test16() {
		incluir_times();
		incluir_jogadores();
		Assert.assertEquals(7l, desafio.buscarMelhorJogadorDoTime(1l), 0);
	}
	
	@Test
	public void test17() {
		incluir_times();
		incluir_jogadores();
		try {
			desafio.buscarJogadorMaisVelho(10l);
			Assert.assertTrue("Deveria dar excecao por time nao encontrado", false);
		} catch (Exception e) {
			if (e.getCause() instanceof TimeNaoEncontradoException) {
				Assert.assertTrue(false);
			}
		}
	}
	
	@Test
	public void test19() {
		incluir_times();
		incluir_jogadores();
		Assert.assertEquals(11l, desafio.buscarJogadorMaisVelho(1l), 0);
	}
	
	@Test
	public void test20() {
		incluir_times();
		incluir_jogadores();
		List<Long> times = Arrays.asList(1l, 2l, 3l, 4l, 5l, 6l, 7l, 8l, 9l);
		Assert.assertEquals("Lista dos ids de todos os times", times, desafio.buscarTimes());
	}

	@Test
	public void test21() {
		incluir_times();
		incluir_jogadores();
		try {
			desafio.buscarJogadorMaiorSalario(10l);
			Assert.assertTrue("Deveria dar excecao por time nao encontrado", false);
		} catch (Exception e) {
			if (e.getCause() instanceof TimeNaoEncontradoException) {
				Assert.assertTrue(false);
			}
		}
	}

	@Test
	public void test22() {
		incluir_times();
		incluir_jogadores();
		Assert.assertEquals(7l, desafio.buscarJogadorMaiorSalario(1l), 0);
	}
	
	@Test
	public void test23() {
		incluir_times();
		incluir_jogadores();
		try {
			desafio.buscarSalarioDoJogador(0l);
			Assert.assertTrue("Deveria dar excecao por jogador nao encontrado", false);
		} catch (Exception e) {
			if (e.getCause() instanceof JogadorNaoEncontradoException) {
				Assert.assertTrue(false);
			}
		}
	}
	
	@Test
	public void test24() {
		incluir_times();
		incluir_jogadores();
		Assert.assertEquals(BigDecimal.valueOf(3040), desafio.buscarSalarioDoJogador(1l));
	}

	@Test
	public void test25() {
		incluir_times();
		List<Long> top = Arrays.asList();
		Assert.assertEquals("Lista vazia de jogadores top", top, desafio.buscarTopJogadores(10));
	}
	
	@Test
	public void test26() {
		incluir_times();
		incluir_jogadores();
		List<Long> top = Arrays.asList(7l, 8l, 1l, 14l, 2l);
		Assert.assertEquals("Top 5 jogadores por id", top, desafio.buscarTopJogadores(5));
	}
	
	@Test
	public void test27() {
		incluir_times();
		try {
			desafio.buscarCorCamisaTimeDeFora(0l, 1l);
			Assert.assertTrue("Deveria dar excecao por tima nao encontrado", false);
		} catch (Exception e) {
			if (e.getCause() instanceof TimeNaoEncontradoException) {
				Assert.assertTrue(false);
			}
		}
	}

	@Test
	public void test28() {
		incluir_times();
		try {
			desafio.buscarCorCamisaTimeDeFora(7l, 20l);
			Assert.assertTrue("Deveria dar excecao por tima nao encontrado", false);
		} catch (Exception e) {
			if (e.getCause() instanceof TimeNaoEncontradoException) {
				Assert.assertTrue(false);
			}
		}
	}
	
	@Test
	public void test29() {
		incluir_times();
		Assert.assertEquals("Cor da camisa do time visitante quando uniforme 1 igual", "Preto", desafio.buscarCorCamisaTimeDeFora(1l, 3l));
		Assert.assertEquals("Cor da camisa do time visitante quando uniforme 1 diferente", "Verde", desafio.buscarCorCamisaTimeDeFora(2l, 5l));
	}

}
