package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import br.com.codenation.desafio.exceptions.*;

public class DesafioMeuTimeApplicationTest {

	public void incluirTimes(DesafioMeuTimeApplication teste) {
		teste.incluirTime(1l, "Real Madrid", LocalDate.of(2016, 1, 1), "Branco", "Azul");
		teste.incluirTime(2L, "Barcelona", LocalDate.of(2018, 2, 20), "Prata", "Preto");
		teste.incluirTime(3L, "Fluminense", LocalDate.of(2000, 03, 14), "Branco", "Vermelho");
	}

	public void incluirJogadores(DesafioMeuTimeApplication teste) {
		teste.incluirJogador(1L, 1L, "Allan1", LocalDate.of(1993, 03, 24), 30, BigDecimal.valueOf(2000));
		teste.incluirJogador(2L, 1L, "Allan2", LocalDate.of(1993, 03, 24), 20, BigDecimal.valueOf(3000));
		teste.incluirJogador(3L, 1L, "Allan3", LocalDate.of(1993, 03, 24), 10, BigDecimal.valueOf(1000));
		teste.incluirJogador(5L, 2L, "Leonardo1", LocalDate.of(1996, 10, 1), 90, BigDecimal.valueOf(0));
		teste.incluirJogador(4L, 2L, "Leonardo2", LocalDate.of(1995, 10, 1), 90, BigDecimal.valueOf(100));
		teste.incluirJogador(6L, 2L, "Leonardo3", LocalDate.of(1994, 10, 1), 45, BigDecimal.valueOf(200));
		teste.incluirJogador(7L, 3L, "Perion1", LocalDate.of(1995, 12, 8), 4, BigDecimal.valueOf(400));
		teste.incluirJogador(9L, 3L, "Perion2", LocalDate.of(1995, 12, 8), 2, BigDecimal.valueOf(500));
		teste.incluirJogador(8L, 3L, "Perion3", LocalDate.of(1995, 12, 8), 5, BigDecimal.valueOf(300));
	}

	@Test
	public void testeIncluirTime() {
		DesafioMeuTimeApplication teste = new DesafioMeuTimeApplication();

		incluirTimes(teste);

		Time timeTeste = teste.listaTime.get(1L);

		Assert.assertEquals("Nome Incorreto", "Real Madrid", timeTeste.getNome());
		Assert.assertEquals("Data incorreta", LocalDate.of(2016, 1, 1), timeTeste.getDataCriacao());
		Assert.assertEquals("Cor Principal incorreta", "Branco", timeTeste.getCorUniformePrincipal());
		Assert.assertEquals("Cor Secundária incorreta", "Azul", timeTeste.getCorUniformeSecundario());

		try {
			teste.incluirTime(1l, "Real Madrid", LocalDate.of(2016, 1, 1), "Branco", "Azul");
			Assert.assertTrue("Deveria dar exceção", false);
		} catch (Exception e) {
			Assert.assertTrue("Exceção Incorreta", e instanceof IdentificadorUtilizadoException);
		}
	}

	@Test
	public void testeIncluirJogador() {
		DesafioMeuTimeApplication teste = new DesafioMeuTimeApplication();

		try {
			teste.incluirJogador(1L, 1L, "Allan Krueger", LocalDate.of(1993, 03, 24), 3, BigDecimal.valueOf(2000));
			Assert.assertTrue("Deveria dar exceção de time não encontrado", false);
		} catch (Exception e) {
			Assert.assertTrue("Deveria ser TimeNãoEncontradoException, mas é " + e,
					e instanceof TimeNaoEncontradoException);
		}

		incluirTimes(teste);
		incluirJogadores(teste);

		Jogador jogadorTeste = teste.listaJogador.get(3L);

		Assert.assertEquals("Id do Time Incorreto", Long.valueOf(1), jogadorTeste.getIdTime());
		Assert.assertEquals("Nome Incorreto", "Allan3", jogadorTeste.getNome());
		Assert.assertEquals("Data Nasc. Incorreta", LocalDate.of(1993, 03, 24), jogadorTeste.getDataNascimento());
		Assert.assertEquals("Habilidade Incorreta", Integer.valueOf(10), jogadorTeste.getNivelHabilidade());
		Assert.assertEquals("Salário Incorreto", BigDecimal.valueOf(1000), jogadorTeste.getSalario());

		try {
			teste.incluirJogador(1L, 1L, "Allan Krueger", LocalDate.of(1993, 03, 24), 3, BigDecimal.valueOf(2000));
			Assert.assertTrue("Deveria dar exceção", false);
		} catch (Exception e) {
			Assert.assertTrue("Exceção deveria ser IdentificadorUtilizadoException, mas é" + e,
					e instanceof IdentificadorUtilizadoException);
		}

	}

	@Test
	public void testeDefinirCapitao() {
		DesafioMeuTimeApplication teste = new DesafioMeuTimeApplication();

		incluirTimes(teste);
		incluirJogadores(teste);

		try {
			teste.definirCapitao(Long.valueOf(10));
			Assert.assertTrue("Deveria dar Exceção", false);
		} catch (Exception e) {
			Assert.assertTrue("Deveria ser JogadorNaoEncontradoException", e instanceof JogadorNaoEncontradoException);
		}

		teste.definirCapitao(Long.valueOf(5));
		Assert.assertEquals("Deveria ser Capitão", Long.valueOf(5),
				teste.listaTime.get(teste.listaJogador.get(5L).getIdTime()).getJogadorCapitao().get());

	}

	@Test
	public void testeBuscaCapitaoTime() {
		DesafioMeuTimeApplication teste = new DesafioMeuTimeApplication();

		try {
			teste.buscarCapitaoDoTime(1L);
			Assert.assertTrue("Deveria dar Exceção", false);
		} catch (Exception e) {
			Assert.assertTrue("Deveria ser TimeNaoEncontradoException", e instanceof TimeNaoEncontradoException);
		}

		incluirTimes(teste);
		incluirJogadores(teste);

		try {
			teste.buscarCapitaoDoTime(1L);
			Assert.assertTrue("Deveria dar Exceção", false);
		} catch (Exception e) {
			Assert.assertTrue("Deveria ser CapitaoNaoInformadoException", e instanceof CapitaoNaoInformadoException);
		}

		teste.definirCapitao(5L);
		Assert.assertEquals("Deveria ser capitao", Long.valueOf(5), teste.buscarCapitaoDoTime(2L));
	}

	@Test
	public void testeBuscarNomeJogador() {
		DesafioMeuTimeApplication teste = new DesafioMeuTimeApplication();

		try {
			teste.buscarNomeJogador(3L);
			Assert.assertTrue("Deveria dar exceção", false);
		} catch (Exception e) {
			Assert.assertTrue("Deveria ser JogadorNaoEncontradoException", e instanceof JogadorNaoEncontradoException);
		}

		incluirTimes(teste);
		incluirJogadores(teste);

		Assert.assertEquals("Deveria ser Allan1", "Allan1", teste.buscarNomeJogador(1L));
	}

	@Test
	public void testeBuscarNomeTime() {
		DesafioMeuTimeApplication teste = new DesafioMeuTimeApplication();

		try {
			teste.buscarNomeTime(3L);
			Assert.assertTrue("Deveria dar exceção", false);
		} catch (Exception e) {
			Assert.assertTrue("Deveria ser TimeNaoEncontradoException", e instanceof TimeNaoEncontradoException);
		}

		incluirTimes(teste);

		Assert.assertEquals("Deveria ser Real Madrid", "Real Madrid", teste.buscarNomeTime(1L));
	}

	@Test
	public void testeBuscarJogadoresDoTime() {
		DesafioMeuTimeApplication teste = new DesafioMeuTimeApplication();

		try {
			teste.buscarJogadoresDoTime(3L);
			Assert.assertTrue("Deveria dar Exceção", false);
		} catch (Exception e) {
			Assert.assertTrue("Deveria ser TimeNaoEncontradoException", e instanceof TimeNaoEncontradoException);
		}

		incluirTimes(teste);
		incluirJogadores(teste);

		ArrayList<Long> lista = new ArrayList<Long>();
		lista.add(7L);
		lista.add(8L);
		lista.add(9L);

		Assert.assertEquals(lista, teste.buscarJogadoresDoTime(3L));
	}

	@Test
	public void testeBuscarMelhorJogadorDoTime() {
		DesafioMeuTimeApplication teste = new DesafioMeuTimeApplication();

		try {
			teste.buscarMelhorJogadorDoTime(1L);
			Assert.assertTrue("Deveria dar Exceção", false);
		} catch (Exception e) {
			Assert.assertTrue("Deveria ser TimeNaoEncontradoException", e instanceof TimeNaoEncontradoException);
		}

		incluirTimes(teste);
		incluirJogadores(teste);

		Assert.assertEquals("Deveria ser o 4", Long.valueOf(4), teste.buscarMelhorJogadorDoTime(2L));
	}

	@Test
	public void testeBuscarJogadorMaisVelho() {
		DesafioMeuTimeApplication teste = new DesafioMeuTimeApplication();

		try {
			teste.buscarJogadorMaisVelho(1L);
			Assert.assertTrue("Deveria dar Exceção", false);
		} catch (Exception e) {
			Assert.assertTrue("Deveria ser TimeNaoEncontradoException", e instanceof TimeNaoEncontradoException);
		}

		incluirTimes(teste);
		incluirJogadores(teste);

		Assert.assertEquals("Deveria ser Leonardo1", Long.valueOf(6), teste.buscarJogadorMaisVelho(2L));
	}

	@Test
	public void testeBuscarTimes() {
		DesafioMeuTimeApplication teste = new DesafioMeuTimeApplication();

		Assert.assertEquals("Deveria ser Lista Vazia", new ArrayList<Long>(), teste.buscarTimes());

		incluirTimes(teste);

		ArrayList<Long> listatimes = new ArrayList<Long>();
		listatimes.add(1L);
		listatimes.add(2L);
		listatimes.add(3L);

		Assert.assertEquals("Deveriam ser iguais", listatimes, teste.buscarTimes());
	}

	@Test
	public void testeBuscarJogadorMaiorSalario() {
		DesafioMeuTimeApplication teste = new DesafioMeuTimeApplication();

		try {
			teste.buscarJogadorMaiorSalario(2L);
			Assert.assertTrue("Deveria dar Exceção", false);
		} catch (Exception e) {
			Assert.assertTrue("Deveria ser TimeNaoEncontradoException", e instanceof TimeNaoEncontradoException);
		}

		incluirTimes(teste);
		incluirJogadores(teste);

		Assert.assertEquals("Deveria ser Leonardo3", Long.valueOf(6), teste.buscarJogadorMaiorSalario(2L));
	}

	@Test
	public void testeBuscarSalarioDoJogador() {
		DesafioMeuTimeApplication teste = new DesafioMeuTimeApplication();

		try {
			teste.buscarSalarioDoJogador(6L);
			Assert.assertTrue("Deveria dar Exceção", false);
		} catch (Exception e) {
			Assert.assertTrue("Deveria ser 0", e instanceof JogadorNaoEncontradoException);
		}

		incluirTimes(teste);
		incluirJogadores(teste);

		Assert.assertEquals("Deveria ser 0", BigDecimal.valueOf(0), teste.buscarSalarioDoJogador(5L));
	}

	@Test
	public void testeBuscarTopJogadores() {
		DesafioMeuTimeApplication teste = new DesafioMeuTimeApplication();

		Assert.assertEquals("Deveria ser lista vazia", new ArrayList<Long>(), teste.buscarTopJogadores(3));

		incluirTimes(teste);
		incluirJogadores(teste);

		ArrayList<Long> listadostop = new ArrayList<Long>();
		listadostop.add(4L);
		listadostop.add(5L);
		listadostop.add(6L);

		Assert.assertEquals("Lista Incorreta", listadostop, teste.buscarTopJogadores(3));
	}

	@Test
	public void testeBucarCorCamisetaTimeDeFora() {
		DesafioMeuTimeApplication teste = new DesafioMeuTimeApplication();

		try {
			teste.buscarCorCamisaTimeDeFora(1L, 2L);
			Assert.assertTrue("Deveria dar Exceção", false);
		} catch (Exception e) {
			Assert.assertTrue("Deveria ser TimeNaoEncontradoException", e instanceof TimeNaoEncontradoException);
		}

		incluirTimes(teste);
		
		try {
			teste.buscarCorCamisaTimeDeFora(1L, 5L);
			Assert.assertTrue("Deveria dar Exceção", false);
		} catch (Exception e) {
			Assert.assertTrue("Deveria ser TimeNaoEncontradoException", e instanceof TimeNaoEncontradoException);
		}

		Assert.assertEquals("Deveria ser prata", "Prata", teste.buscarCorCamisaTimeDeFora(1L, 2L));
		Assert.assertEquals("Deveria ser Vermelho", "Vermelho", teste.buscarCorCamisaTimeDeFora(1L, 3L));
	}
}
