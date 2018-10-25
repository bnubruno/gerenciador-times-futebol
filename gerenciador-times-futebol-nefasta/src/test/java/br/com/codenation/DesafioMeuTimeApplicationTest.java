package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import br.com.codenation.desafio.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;
import junit.framework.Assert;

public class DesafioMeuTimeApplicationTest {

	@Test
	public void incluirTimes() {
		DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();
		try {
			desafio.incluirTime(5l, "Baile de Monique", LocalDate.of(1900, 2, 27), "Vermeho", "Branco");
			desafio.incluirTime(15l, "Inter de Limão", LocalDate.of(1908, 3, 9), "Marom", "Verde");
			desafio.incluirTime(25l, "Atlético Maneiro", LocalDate.of(1908, 3, 25), "Preto", "Branco");
		} catch (Exception e) {
			//Fails a test with the given message.
			Assert.fail("An exception occurred: " + e);
		}
	}

	@Test
	public void incluirTimeIdDuplicado() {
		DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();
		desafio.incluirTime(5l, "Baile de Monique", LocalDate.of(1900, 2, 27), "Vermeho", "Branco");

		try {
			desafio.incluirTime(5l, "Baile de Monique", LocalDate.of(1900, 2, 27), "Vermeho", "Branco");
			Assert.assertTrue("Exception expected!", false);
		} catch (Exception e) {
//			if (e instanceof IdentificadorUtilizadoException) {
//				Assert.assertTrue(false);
//			}
			Assert.assertEquals(e.getClass(), IdentificadorUtilizadoException.class);
		}
	}

	@Test
	public void incluirJogadores() {
		DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();
		desafio.incluirTime(5l, "Baile de Monique", LocalDate.of(1900, 2, 27), "Vermeho", "Branco");
		desafio.incluirTime(15l, "Inter de Limão", LocalDate.of(1908, 3, 9), "Marom", "Verde");
		desafio.incluirTime(25l, "Atlético Maneiro", LocalDate.of(1908, 3, 25), "Preto", "Branco");
		try {
			desafio.incluirJogador(50l, 5l, "Kamuela", LocalDate.of(1998, 8, 4), 100, new BigDecimal(7500));
			desafio.incluirJogador(60l, 15l, "Halehale", LocalDate.of(1987, 12, 29), 90, new BigDecimal(5500));
			desafio.incluirJogador(70l, 25l, "Epaminondas", LocalDate.of(1990, 5, 19), 50, new BigDecimal(1500));
			desafio.incluirJogador(80l, 5l, "Justiniano", LocalDate.of(1991, 5, 7), 15, new BigDecimal(3500));
		} catch (Exception e) {
			Assert.fail("An exception occurred: " + e); //getMessage;
		}
	}

	@Test
	public void incluirJogadorIdDuplicado() {
		DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();
		desafio.incluirTime(15l, "Inter de Limão", LocalDate.of(1908, 3, 9), "Marom", "Verde");
		desafio.incluirJogador(50l, 15l, "Kamuela", LocalDate.of(1998, 8, 4), 100, new BigDecimal(7500));

		try {
			desafio.incluirJogador(50l, 15l, "Kamuela", LocalDate.of(1998, 8, 4), 100, new BigDecimal(7500));
			Assert.assertTrue("Exception expected!", false);
		} catch (Exception e) {
			Assert.assertEquals(e.getClass(), IdentificadorUtilizadoException.class);
		}
	}

	@Test
	public void incluirJogadorTimeInexistente() {
		DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();

		try {
			desafio.incluirJogador(50l, 15l, "Kamuela", LocalDate.of(1998, 8, 4), 100, new BigDecimal(7500));
			Assert.assertTrue("Exception expected!", false);
		} catch (Exception e) {
			Assert.assertEquals(e.getClass(), TimeNaoEncontradoException.class);
		}
	}


	@Test
	public void definirCapitao() {
		DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();

		try {
			desafio.incluirTime(15l, "Inter de Limão", 	LocalDate.of(1908, 3, 9), 	"Marom", 	"Verde");
			desafio.incluirTime(25l, "Atlético Maneiro", LocalDate.of(1908, 3, 25), "Preto", 	"Branco");

			desafio.incluirJogador(50l, 15l, "Kamuela", LocalDate.of(1998, 8, 4), 100, new BigDecimal(7500));
			desafio.incluirJogador(60l, 25l, "Halehale", LocalDate.of(1987, 12, 29), 90, new BigDecimal(5500));
			desafio.incluirJogador(70l, 15l, "Epaminondas", LocalDate.of(1990, 5, 19), 50, new BigDecimal(1500));

			desafio.definirCapitao(50l);
			desafio.definirCapitao(60l);
			desafio.definirCapitao(70l);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("An exception occurred: " + e); //getMessage;
		}
	}


	@Test
	public void definirCapitaoJogadorInexistente() {
		DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();

		try {
			desafio.incluirTime(15l, "Inter de Limão", 	LocalDate.of(1908, 3, 9), 	"Marom", 	"Verde");
			desafio.incluirTime(25l, "Atlético Maneiro", LocalDate.of(1908, 3, 25), "Preto", 	"Branco");

			desafio.incluirJogador(50l, 15l, "Kamuela", LocalDate.of(1998, 8, 4), 100, new BigDecimal(7500));
			desafio.incluirJogador(60l, 25l, "Halehale", LocalDate.of(1987, 12, 29), 90, new BigDecimal(5500));

			desafio.definirCapitao(70l);

			Assert.assertTrue("Exception expected!", false);
		} catch (Exception e) {
			Assert.assertEquals(e.getClass(), JogadorNaoEncontradoException.class);
		}
	}

	@Test
	public void buscarCapitaoDoTime() {
		DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();

		try {
			desafio.incluirTime(15l, "Inter de Limão", 	LocalDate.of(1908, 3, 9), 	"Marom", 	"Verde");
			desafio.incluirTime(25l, "Atlético Maneiro", LocalDate.of(1908, 3, 25), "Preto", 	"Branco");

			desafio.incluirJogador(50l, 15l, "Kamuela", LocalDate.of(1998, 8, 4), 100, new BigDecimal(7500));
			desafio.incluirJogador(60l, 25l, "Halehale", LocalDate.of(1987, 12, 29), 90, new BigDecimal(5500));
			desafio.incluirJogador(70l, 15l, "Epaminondas", LocalDate.of(1990, 5, 19), 50, new BigDecimal(1500));

			desafio.definirCapitao(50l);
			desafio.definirCapitao(60l);
			desafio.definirCapitao(70l);

			Long capitaoInterDeLimao = desafio.buscarCapitaoDoTime(15l);
			Assert.assertEquals(new Long(70), capitaoInterDeLimao);

			Long capitaoAtleticoManeiro = desafio.buscarCapitaoDoTime(25l);
			Assert.assertEquals(new Long(60), capitaoAtleticoManeiro);

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("An exception occurred: " + e); //getMessage;
		}
	}

	@Test
	public void buscarCapitaoDoTimeInexistente() {
		DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();

		try {
			desafio.incluirTime(15l, "Inter de Limão", 	LocalDate.of(1908, 3, 9), 	"Marom", 	"Verde");

			desafio.incluirJogador(50l, 15l, "Kamuela", LocalDate.of(1998, 8, 4), 100, new BigDecimal(7500));
			desafio.incluirJogador(60l, 15l, "Halehale", LocalDate.of(1987, 12, 29), 90, new BigDecimal(5500));

			desafio.definirCapitao(60l);
			
			desafio.buscarCapitaoDoTime(15l);
			desafio.buscarCapitaoDoTime(25l);

			Assert.assertTrue("Exception expected!", false);
		} catch (Exception e) {
			Assert.assertEquals(e.getClass(), TimeNaoEncontradoException.class);
		}
	}

	@Test
	public void buscarCapitaoNaoInformadoDoTime() {
		DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();

		try {
			desafio.incluirTime(15l, "Inter de Limão", 	LocalDate.of(1908, 3, 9), 	"Marom", 	"Verde");
			desafio.incluirTime(25l, "Atlético Maneiro", LocalDate.of(1908, 3, 25), "Preto", 	"Branco");

			desafio.incluirJogador(50l, 15l, "Kamuela", LocalDate.of(1998, 8, 4), 100, new BigDecimal(7500));
			desafio.incluirJogador(60l, 15l, "Halehale", LocalDate.of(1987, 12, 29), 90, new BigDecimal(5500));

			desafio.buscarCapitaoDoTime(15l);
			desafio.buscarCapitaoDoTime(25l); //Exception

			Assert.assertTrue("Exception expected!", false);
		} catch (Exception e) {
			Assert.assertEquals(e.getClass(), CapitaoNaoInformadoException.class);
		}
	}

	@Test
	public void buscarNomeJogador() {
		DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();

		try {
			desafio.incluirTime(15l, "Inter de Limão", 	LocalDate.of(1908, 3, 9), 	"Marom", 	"Verde");

			desafio.incluirJogador(50l, 15l, "Kamuela", LocalDate.of(1998, 8, 4), 100, new BigDecimal(7500));
			desafio.incluirJogador(60l, 15l, "Halehale", LocalDate.of(1987, 12, 29), 90, new BigDecimal(5500));
			desafio.incluirJogador(70l, 15l, "Epaminondas", LocalDate.of(1990, 5, 19), 50, new BigDecimal(1500));

			String nomeJogadorKamuela = desafio.buscarNomeJogador(new Long(50));
			Assert.assertEquals("Kamuela", nomeJogadorKamuela);

			String nomeJogadorHalehale = desafio.buscarNomeJogador(new Long(60));
			Assert.assertEquals("Halehale", nomeJogadorHalehale);

			String nomeJogadorEpaminondas = desafio.buscarNomeJogador(new Long(70));
			Assert.assertEquals("Epaminondas", nomeJogadorEpaminondas);

		} catch (Exception e) {
			Assert.fail("An exception occurred: " + e); //getMessage;
		}
	}

	@Test
	public void buscarNomeJogadorInexistente() {
		DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();
		
		try {
			desafio.incluirTime(15l, "Inter de Limão", LocalDate.of(1908, 3, 9), "Marom", "Verde");
			
			desafio.incluirJogador(50l, 15l, "Kamuela", LocalDate.of(1998, 8, 4), 100, new BigDecimal(7500));
			desafio.incluirJogador(60l, 15l, "Halehale", LocalDate.of(1987, 12, 29), 90, new BigDecimal(5500));
			desafio.incluirJogador(70l, 15l, "Epaminondas", LocalDate.of(1990, 5, 19), 50, new BigDecimal(1500));

			desafio.buscarNomeJogador(new Long(80));
			Assert.assertTrue("Exception expected!", false);
		} catch (Exception e) {
			Assert.assertEquals(e.getClass(), JogadorNaoEncontradoException.class);
		}
	}

	@Test
	public void buscarNomeTime() {
		DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();

		try {
			desafio.incluirTime(5l, "Baile de Monique", LocalDate.of(1900, 2, 27), 	"Vermeho", 	"Branco");
			desafio.incluirTime(15l, "Inter de Limão", 	LocalDate.of(1908, 3, 9), 	"Marom", 	"Verde");
			desafio.incluirTime(25l, "Atlético Maneiro", LocalDate.of(1908, 3, 25), "Preto", 	"Branco");

			String timeBaileDeMonique = desafio.buscarNomeTime(new Long(5));
			Assert.assertEquals("Baile de Monique", timeBaileDeMonique);

			String timeInterDeLimao = desafio.buscarNomeTime(new Long(15));
			Assert.assertEquals("Inter de Limão", timeInterDeLimao);

			String atleticoManeiro = desafio.buscarNomeTime(new Long(25));
			Assert.assertEquals("Atlético Maneiro", atleticoManeiro);
		} catch (Exception e) {
			Assert.fail("An exception occurred: " + e); //getMessage;
		}
	}

	@Test
	public void buscarNomeTimeInexistente() {
		DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();

		try {
			desafio.incluirTime(5l, "Baile de Monique", LocalDate.of(1900, 2, 27), 	"Vermeho", 	"Branco");
			desafio.incluirTime(15l, "Inter de Limão", 	LocalDate.of(1908, 3, 9), 	"Marom", 	"Verde");
			desafio.incluirTime(25l, "Atlético Maneiro", LocalDate.of(1908, 3, 25), "Preto", 	"Branco");

			desafio.buscarNomeTime(35l);
			Assert.assertTrue("Exception expected!", false);
		} catch (Exception e) {
			Assert.assertEquals(e.getClass(), TimeNaoEncontradoException.class);
		}
	}

	@Test
	public void buscarJogadoresDoTime() {
		DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();

		try {
			desafio.incluirTime(5l, "Baile de Monique", LocalDate.of(1900, 2, 27), 	"Vermeho", 	"Branco");
			desafio.incluirTime(15l, "Inter de Limão", 	LocalDate.of(1908, 3, 9), 	"Marom", 	"Verde");
			desafio.incluirTime(25l, "Atlético Maneiro", LocalDate.of(1908, 3, 25), "Preto", 	"Branco");

			desafio.incluirJogador(70l, 5l, "Epaminondas", LocalDate.of(1990, 5, 19), 50, new BigDecimal(1500));
			desafio.incluirJogador(50l, 5l, "Kamuela", LocalDate.of(1998, 8, 4), 100, new BigDecimal(7500));
			desafio.incluirJogador(60l, 5l, "Halehale", LocalDate.of(1987, 12, 29), 90, new BigDecimal(5500));
			desafio.incluirJogador(80l, 15l, "Justiniano", LocalDate.of(1991, 5, 7), 15, new BigDecimal(3500));

			List<Long> jogadoresBaileDeMonique = desafio.buscarJogadoresDoTime(5l);
			List<Long> jogadoresEsperadosBaileDeMonique = new ArrayList<>();
			jogadoresEsperadosBaileDeMonique.add(50l);
			jogadoresEsperadosBaileDeMonique.add(60l);
			jogadoresEsperadosBaileDeMonique.add(70l);
			Assert.assertEquals(jogadoresEsperadosBaileDeMonique, jogadoresBaileDeMonique);

			List<Long> jogadoresInterDeLimao = desafio.buscarJogadoresDoTime(15l);
			List<Long> jogadoresEsperadosInterDeLimao = new ArrayList<>();
			jogadoresEsperadosInterDeLimao.add(80l);
			Assert.assertEquals(jogadoresEsperadosInterDeLimao, jogadoresInterDeLimao);

			List<Long> jogadoresAtleticoManeiro = desafio.buscarJogadoresDoTime(25l);
			List<Long> jogadoresEsperadosAtleticoManeiro = new ArrayList<>();
			Assert.assertEquals(jogadoresEsperadosAtleticoManeiro, jogadoresAtleticoManeiro);

		} catch (Exception e) {
			Assert.assertEquals(e.getClass(), TimeNaoEncontradoException.class);
		}
	}

	@Test
	public void buscarJogadoresDoTimeInexistente() {
		DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();

		try {
			desafio.incluirTime(5l, "Baile de Monique", LocalDate.of(1900, 2, 27), 	"Vermeho", 	"Branco");
			desafio.incluirTime(15l, "Inter de Limão", 	LocalDate.of(1908, 3, 9), 	"Marom", 	"Verde");

			desafio.incluirJogador(50l, 5l, "Kamuela", LocalDate.of(1998, 8, 4), 100, new BigDecimal(7500));
			desafio.incluirJogador(60l, 5l, "Halehale", LocalDate.of(1987, 12, 29), 90, new BigDecimal(5500));
			desafio.incluirJogador(70l, 15l, "Epaminondas", LocalDate.of(1990, 5, 19), 50, new BigDecimal(1500));
			desafio.incluirJogador(80l, 15l, "Justiniano", LocalDate.of(1991, 5, 7), 15, new BigDecimal(3500));

			List<Long> jogadores = desafio.buscarJogadoresDoTime(25l);
			Assert.assertTrue("Exception expected!", false);
		} catch (Exception e) {
			Assert.assertEquals(e.getClass(), TimeNaoEncontradoException.class);
		}
	}


	@Test
	public void buscarMelhorJogadorDoTime() {
		DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();

		try {
			desafio.incluirTime(5l, "Baile de Monique", LocalDate.of(1900, 2, 27), 	"Vermeho", 	"Branco");
			desafio.incluirTime(15l, "Inter de Limão", 	LocalDate.of(1908, 3, 9), 	"Marom", 	"Verde");

			desafio.incluirJogador(50l, 5l, "Kamuela", LocalDate.of(1998, 8, 4), 100, new BigDecimal(7500));
			desafio.incluirJogador(60l, 5l, "Halehale", LocalDate.of(1987, 12, 29), 90, new BigDecimal(5500));
			desafio.incluirJogador(70l, 15l, "Epaminondas", LocalDate.of(1990, 5, 19), 50, new BigDecimal(1500));
			desafio.incluirJogador(80l, 15l, "Justiniano", LocalDate.of(1991, 5, 7), 15, new BigDecimal(3500));

			Long melhorJogadorBaileDeMonique = desafio.buscarMelhorJogadorDoTime(5l);
			Assert.assertEquals(new Long(50), melhorJogadorBaileDeMonique);

			Long melhorJogadorInterDeLimao = desafio.buscarMelhorJogadorDoTime(15l);
			Assert.assertEquals(new Long(70), melhorJogadorInterDeLimao);

		} catch (Exception e) {
			Assert.fail("An exception occurred: " + e);
		}
	}


	@Test
	public void buscarMelhorJogadorDoTimeInexistente() {
		DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();

		try {
			desafio.incluirTime(5l, "Baile de Monique", LocalDate.of(1900, 2, 27), 	"Vermeho", 	"Branco");
			desafio.incluirTime(15l, "Inter de Limão", 	LocalDate.of(1908, 3, 9), 	"Marom", 	"Verde");

			desafio.incluirJogador(50l, 5l, "Kamuela", LocalDate.of(1998, 8, 4), 100, new BigDecimal(7500));
			desafio.incluirJogador(60l, 15l, "Halehale", LocalDate.of(1987, 12, 29), 90, new BigDecimal(5500));

			Long melhorJogadorAtleticoManeiro = desafio.buscarMelhorJogadorDoTime(25l);
			Assert.assertTrue("Exception expected!", false);
		} catch (Exception e) {
			Assert.assertEquals(e.getClass(), TimeNaoEncontradoException.class);
		}
	}


	@Test
	public void buscarJogadorMaisVelho() {
		DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();

		try {
			desafio.incluirTime(5l, "Baile de Monique", LocalDate.of(1900, 2, 27), 	"Vermeho", 	"Branco");

			desafio.incluirJogador(50l, 5l, "Kamuela", LocalDate.of(1998, 8, 4), 100, new BigDecimal(7500));
			desafio.incluirJogador(60l, 5l, "Halehale", LocalDate.of(1987, 12, 29), 90, new BigDecimal(5500));
			desafio.incluirJogador(70l, 5l, "Epaminondas", LocalDate.of(1990, 5, 19), 50, new BigDecimal(1500));

			Long jogadorMaisVelho = desafio.buscarJogadorMaisVelho(5l);
			Assert.assertEquals(new Long(60), jogadorMaisVelho);
		} catch (Exception e) {
			Assert.fail("An exception occurred: " + e);
		}
	}

	@Test
	public void buscarJogadorMaisVelhoTimeInexistente() {
		DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();

		try {
			desafio.incluirTime(5l, "Baile de Monique", LocalDate.of(1900, 2, 27), 	"Vermeho", 	"Branco");

			desafio.incluirJogador(50l, 5l, "Kamuela", LocalDate.of(1998, 8, 4), 100, new BigDecimal(7500));
			desafio.incluirJogador(60l, 5l, "Halehale", LocalDate.of(1987, 12, 29), 90, new BigDecimal(5500));
			desafio.incluirJogador(70l, 5l, "Epaminondas", LocalDate.of(1990, 5, 19), 50, new BigDecimal(1500));

			Long jogadorMaisVelho = desafio.buscarJogadorMaisVelho(15l);
			Assert.assertTrue("Exception expected!", false);
		} catch (Exception e) {
			Assert.assertEquals(e.getClass(), TimeNaoEncontradoException.class);
		}
	}
	
	@Test
	public void buscarJogadorMaisVelhoComDesempate() {
		DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();

		try {
			desafio.incluirTime(5l, "Baile de Monique", LocalDate.of(1900, 2, 27), 	"Vermeho", 	"Branco");

			desafio.incluirJogador(50l, 5l, "Kamuela", LocalDate.of(1998, 8, 4), 100, new BigDecimal(7500));
			desafio.incluirJogador(60l, 5l, "Halehale", LocalDate.of(1987, 12, 29), 90, new BigDecimal(5500));
			desafio.incluirJogador(70l, 5l, "Epaminondas", LocalDate.of(1998, 8, 4), 50, new BigDecimal(1500));

			Long jogadorMaisVelho = desafio.buscarJogadorMaisVelho(5l);
			Assert.assertEquals(new Long(60), jogadorMaisVelho);
		} catch (Exception e) {
			Assert.fail("An exception occurred: " + e);
		}
	}

	@Test
	public void buscarTimes() {
		DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();

		try {
			//Validar se jé existe times antes da inserééo
			List<Long> times = desafio.buscarTimes();
			List<Long> timesEsperados = new ArrayList<>();
			Assert.assertEquals(timesEsperados, times);

			desafio.incluirTime(25l, "Atlético Maneiro", LocalDate.of(1908, 3, 25), "Preto", "Branco");
			desafio.incluirTime(5l, "Baile de Monique", LocalDate.of(1900, 2, 27), "Vermeho", "Branco");
			desafio.incluirTime(15l, "Inter de Limão", LocalDate.of(1908, 3, 9), "Marom", "Verde");

			times = desafio.buscarTimes();
			timesEsperados.add(5l);
			timesEsperados.add(15l);
			timesEsperados.add(25l);

			Assert.assertEquals(timesEsperados, times);
		} catch (Exception e) {
			Assert.fail("An exception occurred: " + e);
		}
	}

	@Test
	public void buscarJogadorMaiorSalario() {
		DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();

		try {
			desafio.incluirTime(5l, "Baile de Monique", LocalDate.of(1900, 2, 27), 	"Vermeho", 	"Branco");

			desafio.incluirJogador(50l, 5l, "Kamuela", LocalDate.of(1998, 8, 4), 100, new BigDecimal(7500));
			desafio.incluirJogador(60l, 5l, "Halehale", LocalDate.of(1987, 12, 29), 90, new BigDecimal(5500));
			desafio.incluirJogador(70l, 5l, "Epaminondas", LocalDate.of(1990, 5, 19), 50, new BigDecimal(1500));

			Long jogadorMaiorSalario = desafio.buscarJogadorMaiorSalario(5l);
			Assert.assertEquals(new Long(50), jogadorMaiorSalario);
		} catch (Exception e) {
			Assert.fail("An exception occurred: " + e);
		}
	}

	@Test
	public void buscarJogadorMaiorSalarioTimeInexistente() {
		DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();

		try {
			desafio.incluirTime(5l, "Baile de Monique", LocalDate.of(1900, 2, 27), 	"Vermeho", 	"Branco");

			desafio.incluirJogador(50l, 5l, "Kamuela", LocalDate.of(1998, 8, 4), 100, new BigDecimal(7500));
			desafio.incluirJogador(60l, 5l, "Halehale", LocalDate.of(1987, 12, 29), 90, new BigDecimal(5500));
			desafio.incluirJogador(70l, 5l, "Epaminondas", LocalDate.of(1990, 5, 19), 50, new BigDecimal(1500));

			Long jogadorMaiorSalario = desafio.buscarJogadorMaiorSalario(15l);
			Assert.assertTrue("Exception expected!", false);
		} catch (Exception e) {
			Assert.assertEquals(e.getClass(), TimeNaoEncontradoException.class);
		}
	}
	
	@Test
	public void buscarJogadorMaiorSalarioComDesempate() {
		DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();

		try {
			desafio.incluirTime(5l, "Baile de Monique", LocalDate.of(1900, 2, 27), 	"Vermeho", 	"Branco");

			desafio.incluirJogador(50l, 5l, "Kamuela", LocalDate.of(1998, 8, 4), 100, new BigDecimal(7500));
			desafio.incluirJogador(60l, 5l, "Halehale", LocalDate.of(1987, 12, 29), 90, new BigDecimal(5500));
			desafio.incluirJogador(70l, 5l, "Epaminondas", LocalDate.of(1990, 5, 19), 50, new BigDecimal(5500));

			Long jogadorMaiorSalario = desafio.buscarJogadorMaiorSalario(5l);
			Assert.assertEquals(new Long(50), jogadorMaiorSalario);
		} catch (Exception e) {
			Assert.fail("An exception occurred: " + e);
		}
	}

	@Test
	public void buscarSalarioDoJogador() {
		DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();

		try {
			desafio.incluirTime(5l, "Baile de Monique", LocalDate.of(1900, 2, 27), 	"Vermeho", 	"Branco");

			desafio.incluirJogador(50l, 5l, "Kamuela", LocalDate.of(1998, 8, 4), 100, new BigDecimal(7500));
			desafio.incluirJogador(60l, 5l, "Halehale", LocalDate.of(1987, 12, 29), 90, new BigDecimal(5500));

			BigDecimal salarioKamuela = desafio.buscarSalarioDoJogador(50l);
			Assert.assertEquals(new BigDecimal(7500), salarioKamuela);

			BigDecimal salarioHalehale= desafio.buscarSalarioDoJogador(60l);
			Assert.assertEquals(new BigDecimal(5500), salarioHalehale);

		} catch (Exception e) {
			Assert.fail("An exception occurred: " + e);
		}
	}

	@Test
	public void buscarSalarioDoJogadorInexistente() {
		DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();

		try {
			desafio.incluirTime(5l, "Baile de Monique", LocalDate.of(1900, 2, 27), 	"Vermeho", 	"Branco");

			desafio.incluirJogador(50l, 5l, "Kamuela", LocalDate.of(1998, 8, 4), 100, new BigDecimal(7500));
			desafio.incluirJogador(60l, 5l, "Halehale", LocalDate.of(1987, 12, 29), 90, new BigDecimal(5500));

			BigDecimal salarioKamuela = desafio.buscarSalarioDoJogador(70l);
			Assert.assertTrue("Exception expected!", false);
		} catch (Exception e) {
			Assert.assertEquals(e.getClass(), JogadorNaoEncontradoException.class);
		}
	}

	@Test
	public void buscarTopJogadoresComDesempate() {
		DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();

		try {
			desafio.incluirTime(25l, "Atlético Maneiro", LocalDate.of(1908, 3, 25), "Preto", "Branco");

			desafio.incluirJogador(80l, 25l, "Justiniano", LocalDate.of(1991, 5, 7), 15, new BigDecimal(3500));
			desafio.incluirJogador(50l, 25l, "Kamuela", LocalDate.of(1998, 8, 4), 100, new BigDecimal(7500));
			desafio.incluirJogador(70l, 25l, "Epaminondas", LocalDate.of(1990, 5, 19), 50, new BigDecimal(1500));
			desafio.incluirJogador(60l, 25l, "Halehale", LocalDate.of(1987, 12, 29), 100, new BigDecimal(5500));

			List<Long> jogadores = desafio.buscarTopJogadores(new Integer(10));

			List<Long> jogadoresEsperados = new ArrayList<>();
			jogadoresEsperados.add(50l);
			jogadoresEsperados.add(60l);
			jogadoresEsperados.add(70l);
			jogadoresEsperados.add(80l);

			Assert.assertEquals(jogadoresEsperados, jogadores);
		} catch (Exception e) {
			Assert.fail("An exception occurred: " + e);
		}
	}
	
	@Test
	public void buscarTopNullJogadores() {
		DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();

		try {
			desafio.incluirTime(25l, "Atlético Maneiro", LocalDate.of(1908, 3, 25), "Preto", "Branco");

			desafio.incluirJogador(80l, 25l, "Justiniano", LocalDate.of(1991, 5, 7), 15, new BigDecimal(3500));
			desafio.incluirJogador(50l, 25l, "Kamuela", LocalDate.of(1998, 8, 4), 100, new BigDecimal(7500));
			desafio.incluirJogador(70l, 25l, "Epaminondas", LocalDate.of(1990, 5, 19), 50, new BigDecimal(1500));
			desafio.incluirJogador(60l, 25l, "Halehale", LocalDate.of(1987, 12, 29), 100, new BigDecimal(5500));

			List<Long> jogadores = desafio.buscarTopJogadores(null);

			List<Long> jogadoresEsperados = new ArrayList<>();
			jogadoresEsperados.add(50l);
			jogadoresEsperados.add(60l);
			jogadoresEsperados.add(70l);
			jogadoresEsperados.add(80l);

			Assert.assertEquals(jogadoresEsperados, jogadores);
		} catch (Exception e) {
			Assert.fail("An exception occurred: " + e);
		}
	}
	
	@Test
	public void buscarTop3JogadoresDe4() {
		DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();

		try {
			desafio.incluirTime(25l, "Atlético Maneiro", LocalDate.of(1908, 3, 25), "Preto", "Branco");
			
			desafio.incluirJogador(80l, 25l, "Justiniano", LocalDate.of(1991, 5, 7), 15, new BigDecimal(3500));
			desafio.incluirJogador(50l, 25l, "Kamuela", LocalDate.of(1998, 8, 4), 100, new BigDecimal(7500));
			desafio.incluirJogador(70l, 25l, "Epaminondas", LocalDate.of(1990, 5, 19), 50, new BigDecimal(1500));
			desafio.incluirJogador(60l, 25l, "Halehale", LocalDate.of(1987, 12, 29), 100, new BigDecimal(5500));

			// buscar somente os três primeiros top jogadores
			List<Long> jogadores = desafio.buscarTopJogadores(new Integer(3));

			List<Long> jogadoresEsperados = new ArrayList<>();
			jogadoresEsperados.add(50l);
			jogadoresEsperados.add(60l);
			jogadoresEsperados.add(70l);

			Assert.assertEquals(jogadoresEsperados, jogadores);
		} catch (Exception e) {
			Assert.fail("An exception occurred: " + e);
		}
	}


	@Test
	public void buscarCorCamisaTimeDeFora() {
		DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();

		try {
			desafio.incluirTime(5l, "Baile de Monique", LocalDate.of(1900, 2, 27), "Vermeho", "Branco");
			desafio.incluirTime(15l, "Inter de Limão", LocalDate.of(1908, 3, 9), "Vermeho", "Verde");
			desafio.incluirTime(25l, "Atlético Maneiro", LocalDate.of(1908, 3, 25), "Preto", "Branco");

			//Caso a cor principal do time da casa seja diferente da cor principal do time de fora,
			//retornar cor principal do time de fora.
			String atleticoManeiroPreto = desafio.buscarCorCamisaTimeDeFora(5l, 25l);
			Assert.assertEquals("Preto", atleticoManeiroPreto);

			//Caso a cor principal do time da casa seja igual a cor principal do time de fora,
			//retornar cor secundéria do time de fora
			String interDeLimaoVerde = desafio.buscarCorCamisaTimeDeFora(5l, 15l);
			Assert.assertEquals("Verde", interDeLimaoVerde);
		} catch (Exception e) {
			Assert.fail("An exception occurred: " + e);
		}
	}

	@Test
	public void buscarCorCamisaTimeDeForaTimeDaCasaInexistente() {
		DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();

		try {
			desafio.incluirTime(5l, "Baile de Monique", LocalDate.of(1900, 2, 27), "Vermeho", "Branco");
			desafio.incluirTime(15l, "Inter de Limão", LocalDate.of(1908, 3, 9), "Marom", "Verde");

			desafio.buscarCorCamisaTimeDeFora(25l, 5l);
			Assert.assertTrue("Exception expected!", false);
		} catch (Exception e) {
			Assert.assertEquals(e.getClass(), TimeNaoEncontradoException.class);
		}
	}

	@Test
	public void buscarCorCamisaTimeDeForaTimeDeForaInexistente() {
		DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();

		try {
			desafio.incluirTime(5l, "Baile de Monique", LocalDate.of(1900, 2, 27), "Vermeho", "Branco");
			desafio.incluirTime(15l, "Inter de Limão", LocalDate.of(1908, 3, 9), "Marom", "Verde");

			desafio.buscarCorCamisaTimeDeFora(5l, 25l);
			Assert.assertTrue("Exception expected!", false);
		} catch (Exception e) {
			Assert.assertEquals(e.getClass(), TimeNaoEncontradoException.class);
		}
	}
}
