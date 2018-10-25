package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.codenation.desafio.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;

public class DesafioMeuTimeApplicationTest {

	@Test
	public void incluirNovoTime() {
		DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();
		
		//adiciona um time
		try {
			desafio.incluirTime(0l, "Time0", LocalDate.of(2018, 10, 01), "Vermelho", "Azul");
		} catch (Exception e) {
			Assert.fail("Unexpected exception " + e.toString());
		}
	}
	
	@Test
	public void incluirTimeIdRepetido() {
		DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();
		
		//adiciona um time e depois o mesmo time
		try {
			desafio.incluirTime(0l, "Time0", LocalDate.of(2018, 10, 01), "Vermelho", "Azul");
			desafio.incluirTime(0l, "Time0", LocalDate.of(2018, 10, 01), "Vermelho", "Azul");
			Assert.fail("Expected exception, got none");
		} catch (Exception e) {
			Assert.assertTrue("Unexpected exception " + e.toString(), e instanceof IdentificadorUtilizadoException);
		}
	}
	
	@Test
	public void incluirNovoJogador() {
		DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();
		
		//adiciona jogador0 ao time0
		try {
			desafio.incluirTime(0l, "Time0", LocalDate.of(2018, 10, 01), "Vermelho", "Azul");
			desafio.incluirJogador(0l, 0l, "jogador0", LocalDate.of(1990, 06, 05), 5, BigDecimal.valueOf(20000));
		} catch (Exception e) {
			Assert.fail("Unexpected exception " + e.toString());
		}
	}
	
	@Test
	public void incluirJogadorIdRepetido() {
		DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();
		
		//adiciona jogador0 duas vezes ao time0
		try {
			desafio.incluirTime(0l, "Time0", LocalDate.of(2018, 10, 01), "Vermelho", "Azul");
			desafio.incluirJogador(0l, 0l, "jogador0", LocalDate.of(1990, 06, 05), 5, BigDecimal.valueOf(20000));
			desafio.incluirJogador(0l, 0l, "jogador0", LocalDate.of(1990, 06, 05), 5, BigDecimal.valueOf(20000));
			Assert.fail("Expected exception, got none");
		} catch (Exception e) {
			Assert.assertTrue("Unexpected exception " + e.toString(), e instanceof IdentificadorUtilizadoException);
		}
	}
	
	@Test
	public void incluirJogadorTimeInexistente() {
		DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();
		
		//adiciona jogador0 ao time1
		try {
			desafio.incluirTime(0l, "Time0", LocalDate.of(2018, 10, 01), "Vermelho", "Azul");
			desafio.incluirJogador(0l, 1l, "jogador0", LocalDate.of(1990, 06, 05), 5, BigDecimal.valueOf(20000));
			Assert.fail("Expected exception, got none");
		} catch (Exception e) {
			Assert.assertTrue("Unexpected exception " + e.toString(), e instanceof TimeNaoEncontradoException);
		}
	}
	
	@Test
	public void definirCapitao() {
		DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();
		
		//define jogador0 como capitao
		try {
			desafio.incluirTime(0l, "Time0", LocalDate.of(2018, 10, 01), "Vermelho", "Azul");
			desafio.incluirJogador(0l, 0l, "jogador0", LocalDate.of(1990, 06, 05), 5, BigDecimal.valueOf(20000));
			desafio.definirCapitao(0l);
		} catch (Exception e) {
			Assert.fail("Unexpected exception " + e.toString());
		}
	}
	
	@Test
	public void definirCapitaoJogadorInexistente() {
		DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();
		
		//define jogador inexistente como capitao
		try {
			desafio.incluirTime(0l, "Time0", LocalDate.of(2018, 10, 01), "Vermelho", "Azul");
			desafio.incluirJogador(0l, 0l, "jogador0", LocalDate.of(1990, 06, 05), 5, BigDecimal.valueOf(20000));
			desafio.definirCapitao(1l);
			Assert.fail("Expected exception, got none");
		} catch (Exception e) {
			Assert.assertTrue("Unexpected exception " + e.toString(), e instanceof JogadorNaoEncontradoException);
		}
	}
	
	@Test
	public void buscarCapitao() {
		DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();
		
		//adiciona jogador0 e jogador1, define ambos como capitao em sequencia, e busca o capitao ao final
		try {
			desafio.incluirTime(0l, "Time0", LocalDate.of(2018, 10, 01), "Vermelho", "Azul");
			desafio.incluirJogador(0l, 0l, "jogador0", LocalDate.of(1990, 06, 05), 5, BigDecimal.valueOf(20000));
			desafio.incluirJogador(1l, 0l, "jogador1", LocalDate.of(1990, 06, 05), 5, BigDecimal.valueOf(20000));
			desafio.definirCapitao(0l);
			desafio.definirCapitao(1l);
			Assert.assertTrue("Returned wrong player id", desafio.buscarCapitaoDoTime(0l).equals(1l));
		} catch (Exception e) {
			Assert.fail("Unexpected exception " + e.toString());
		}
	}
	
	@Test
	public void buscarCapitaoTimeInexistente() {
		DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();
		
		//adiciona time0 e jogador0 como seu capitao, busca capitao do time1
		try {
			desafio.incluirTime(0l, "Time0", LocalDate.of(2018, 10, 01), "Vermelho", "Azul");
			desafio.incluirJogador(0l, 0l, "jogador0", LocalDate.of(1990, 06, 05), 5, BigDecimal.valueOf(20000));
			desafio.definirCapitao(0l);
			desafio.buscarCapitaoDoTime(1l);
			Assert.fail("Expected exception, got none");
		} catch (Exception e) {
			Assert.assertTrue("Unexpected exception " + e.toString(), e instanceof TimeNaoEncontradoException);
		}
	}
	
	@Test
	public void buscarCapitaoSeCapitaoNaoInformado() {
		DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();
		
		//adiciona time0 e jogador0 como seu capitao, adiciona time1 sem jogadores, busca capitao do time1
		try {
			desafio.incluirTime(0l, "Time0", LocalDate.of(2018, 10, 01), "Vermelho", "Azul");
			desafio.incluirTime(1l, "Time1", LocalDate.of(2018, 10, 01), "Vermelho", "Azul");
			desafio.incluirJogador(0l, 0l, "jogador0", LocalDate.of(1990, 06, 05), 5, BigDecimal.valueOf(20000));
			desafio.definirCapitao(0l);
			desafio.buscarCapitaoDoTime(1l);
			Assert.fail("Expected exception, got none");
		} catch (Exception e) {
			Assert.assertTrue("Unexpected exception " + e.toString(), e instanceof CapitaoNaoInformadoException);
		}
	}
	
	@Test
	public void buscarNomeJogador() {
		DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();
		
		//adiciona jogadores e busca nome
		try {
			desafio.incluirTime(0l, "Time0", LocalDate.of(2018, 10, 01), "Vermelho", "Azul");
			desafio.incluirTime(1l, "Time1", LocalDate.of(2018, 10, 01), "Vermelho", "Azul");
			desafio.incluirJogador(0l, 0l, "jogador0", LocalDate.of(1990, 06, 05), 5, BigDecimal.valueOf(20000));
			desafio.incluirJogador(1l, 0l, "jogador1", LocalDate.of(1990, 06, 05), 5, BigDecimal.valueOf(20000));
			desafio.incluirJogador(2l, 1l, "jogador2", LocalDate.of(1990, 06, 05), 5, BigDecimal.valueOf(20000));
			desafio.incluirJogador(3l, 1l, "jogador3", LocalDate.of(1990, 06, 05), 5, BigDecimal.valueOf(20000));
			Assert.assertTrue("Returned wrong player name", desafio.buscarNomeJogador(2l).equals("jogador2"));
		} catch (Exception e) {
			Assert.fail("Unexpected exception " + e.toString());
		}
	}
	
	@Test
	public void buscarNomeJogadorInexistente() {
		DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();
		
		//adiciona jogadores e busca nome de nao adicionado
		try {
			desafio.incluirTime(0l, "Time0", LocalDate.of(2018, 10, 01), "Vermelho", "Azul");
			desafio.incluirTime(1l, "Time1", LocalDate.of(2018, 10, 01), "Vermelho", "Azul");
			desafio.incluirJogador(0l, 0l, "jogador0", LocalDate.of(1990, 06, 05), 5, BigDecimal.valueOf(20000));
			desafio.incluirJogador(1l, 0l, "jogador1", LocalDate.of(1990, 06, 05), 5, BigDecimal.valueOf(20000));
			desafio.incluirJogador(2l, 1l, "jogador2", LocalDate.of(1990, 06, 05), 5, BigDecimal.valueOf(20000));
			desafio.incluirJogador(3l, 1l, "jogador3", LocalDate.of(1990, 06, 05), 5, BigDecimal.valueOf(20000));
			desafio.buscarNomeJogador(4l);
			Assert.fail("Expected exception, got none");
		} catch (Exception e) {
			Assert.assertTrue("Unexpected exception " + e.toString(), e instanceof JogadorNaoEncontradoException);
		}
	}
	
	@Test
	public void buscarNomeTime() {
		DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();
		
		//adiciona times e busca nome
		try {
			desafio.incluirTime(0l, "Time0", LocalDate.of(2018, 10, 01), "Vermelho", "Azul");
			desafio.incluirTime(1l, "Time1", LocalDate.of(2018, 10, 01), "Vermelho", "Azul");
			desafio.incluirTime(2l, "Time2", LocalDate.of(2018, 10, 01), "Vermelho", "Azul");
			Assert.assertTrue("Returned wrong team name", desafio.buscarNomeTime(1l).equals("Time1"));
		} catch (Exception e) {
			Assert.fail("Unexpected exception " + e.toString());
		}
	}
	
	@Test
	public void buscarNomeTimeInexistente() {
		DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();
		
		//adiciona times e busca nome de time nao adicionado
		try {
			desafio.incluirTime(0l, "Time0", LocalDate.of(2018, 10, 01), "Vermelho", "Azul");
			desafio.incluirTime(1l, "Time1", LocalDate.of(2018, 10, 01), "Vermelho", "Azul");
			desafio.incluirTime(2l, "Time2", LocalDate.of(2018, 10, 01), "Vermelho", "Azul");
			desafio.buscarNomeTime(3l);
			Assert.fail("Expected exception, got none");
		} catch (Exception e) {
			Assert.assertTrue("Unexpected exception " + e.toString(), e instanceof TimeNaoEncontradoException);
		}
	}
	
	@Test
	public void buscarJogadoresTime() {
		DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();
		
		//adiciona jogadores e busca lista de jogadores de um dos times
		try {
			desafio.incluirTime(0l, "Time0", LocalDate.of(2018, 10, 01), "Vermelho", "Azul");
			desafio.incluirTime(1l, "Time1", LocalDate.of(2018, 10, 01), "Vermelho", "Azul");
			desafio.incluirTime(2l, "Time2", LocalDate.of(2018, 10, 01), "Vermelho", "Azul");
			
			desafio.incluirJogador(0l, 0l, "jogador0", LocalDate.of(1990, 06, 05), 5, BigDecimal.valueOf(20000));
			desafio.incluirJogador(1l, 0l, "jogador1", LocalDate.of(1990, 06, 05), 5, BigDecimal.valueOf(20000));
			desafio.incluirJogador(2l, 0l, "jogador2", LocalDate.of(1990, 06, 05), 5, BigDecimal.valueOf(20000));
			desafio.incluirJogador(3l, 0l, "jogador3", LocalDate.of(1990, 06, 05), 5, BigDecimal.valueOf(20000));
			
			desafio.incluirJogador(5l, 1l, "jogador5", LocalDate.of(1990, 06, 05), 5, BigDecimal.valueOf(20000));
			desafio.incluirJogador(7l, 1l, "jogador7", LocalDate.of(1990, 06, 05), 5, BigDecimal.valueOf(20000));
			desafio.incluirJogador(6l, 1l, "jogador6", LocalDate.of(1990, 06, 05), 5, BigDecimal.valueOf(20000));
			desafio.incluirJogador(4l, 1l, "jogador4", LocalDate.of(1990, 06, 05), 5, BigDecimal.valueOf(20000));
			
			desafio.incluirJogador(8l, 2l, "jogador8", LocalDate.of(1990, 06, 05), 5, BigDecimal.valueOf(20000));
			desafio.incluirJogador(9l, 2l, "jogador9", LocalDate.of(1990, 06, 05), 5, BigDecimal.valueOf(20000));
			desafio.incluirJogador(10l, 2l, "jogador10", LocalDate.of(1990, 06, 05), 5, BigDecimal.valueOf(20000));
			desafio.incluirJogador(11l, 2l, "jogador11", LocalDate.of(1990, 06, 05), 5, BigDecimal.valueOf(20000));
			
			List<Long> ids = desafio.buscarJogadoresDoTime(1l);
			Assert.assertTrue("Returned wrong number of players", ids.size() == 4);
			Assert.assertTrue("Returned wrong ids/wrong ordering", ids.get(0).equals(4l) &&
					ids.get(1).equals(5l) && ids.get(2).equals(6l) && ids.get(3).equals(7l));
		} catch (Exception e) {
			Assert.fail("Unexpected exception " + e.toString());
		}
	}
	
	@Test
	public void buscarJogadoresTimeInexistente() {
		DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();
		
		//adiciona jogadores e busca lista de jogadores de um time nao adicionado
		try {
			desafio.incluirTime(0l, "Time0", LocalDate.of(2018, 10, 01), "Vermelho", "Azul");
			desafio.incluirTime(1l, "Time1", LocalDate.of(2018, 10, 01), "Vermelho", "Azul");
			desafio.incluirTime(2l, "Time2", LocalDate.of(2018, 10, 01), "Vermelho", "Azul");
			
			desafio.buscarJogadoresDoTime(3l);
			Assert.fail("Expected exception, got none");
		} catch (Exception e) {
			Assert.assertTrue("Unexpected exception " + e.toString(), e instanceof TimeNaoEncontradoException);
		}
	}
	
	@Test
	public void buscarJogadoresTimeVazio() {
		DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();
		
		//adiciona times e jogadores e busca jogadores de time vazio
		try {
			desafio.incluirTime(0l, "Time0", LocalDate.of(2018, 10, 01), "Vermelho", "Azul");
			desafio.incluirTime(1l, "Time1", LocalDate.of(2018, 10, 01), "Vermelho", "Azul");
			desafio.incluirTime(2l, "Time2", LocalDate.of(2018, 10, 01), "Vermelho", "Azul");
			
			desafio.incluirJogador(0l, 0l, "jogador0", LocalDate.of(1990, 06, 05), 5, BigDecimal.valueOf(20000));
			desafio.incluirJogador(1l, 0l, "jogador1", LocalDate.of(1990, 06, 05), 5, BigDecimal.valueOf(20000));
			desafio.incluirJogador(2l, 0l, "jogador2", LocalDate.of(1990, 06, 05), 5, BigDecimal.valueOf(20000));
			desafio.incluirJogador(3l, 0l, "jogador3", LocalDate.of(1990, 06, 05), 5, BigDecimal.valueOf(20000));
			
			desafio.incluirJogador(5l, 1l, "jogador5", LocalDate.of(1990, 06, 05), 5, BigDecimal.valueOf(20000));
			desafio.incluirJogador(7l, 1l, "jogador7", LocalDate.of(1990, 06, 05), 5, BigDecimal.valueOf(20000));
			desafio.incluirJogador(6l, 1l, "jogador6", LocalDate.of(1990, 06, 05), 5, BigDecimal.valueOf(20000));
			desafio.incluirJogador(4l, 1l, "jogador4", LocalDate.of(1990, 06, 05), 5, BigDecimal.valueOf(20000));
			
			List<Long> ids = desafio.buscarJogadoresDoTime(2l);
			Assert.assertTrue("Returned non-empty list", ids.isEmpty());
		} catch (Exception e) {
			Assert.fail("Unexpected exception " + e.toString());
		}
	}
	
	@Test
	public void buscarMelhorJogador() {
		DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();
		
		//adiciona times e jogadores e busca o melhor de um time
		try {
			desafio.incluirTime(0l, "Time0", LocalDate.of(2018, 10, 01), "Vermelho", "Azul");
			desafio.incluirTime(1l, "Time1", LocalDate.of(2018, 10, 01), "Vermelho", "Azul");
			desafio.incluirTime(2l, "Time2", LocalDate.of(2018, 10, 01), "Vermelho", "Azul");
			
			desafio.incluirJogador(0l, 0l, "jogador0", LocalDate.of(1990, 06, 05), 5, BigDecimal.valueOf(20000));
			desafio.incluirJogador(1l, 0l, "jogador1", LocalDate.of(1990, 06, 05), 2, BigDecimal.valueOf(20000));
			desafio.incluirJogador(8l, 0l, "jogador8", LocalDate.of(1990, 06, 05), 8, BigDecimal.valueOf(20000));
			desafio.incluirJogador(2l, 0l, "jogador2", LocalDate.of(1990, 06, 05), 8, BigDecimal.valueOf(20000));
			desafio.incluirJogador(3l, 0l, "jogador3", LocalDate.of(1990, 06, 05), 8, BigDecimal.valueOf(20000));
			
			desafio.incluirJogador(5l, 1l, "jogador5", LocalDate.of(1990, 06, 05), 5, BigDecimal.valueOf(20000));
			desafio.incluirJogador(7l, 1l, "jogador7", LocalDate.of(1990, 06, 05), 5, BigDecimal.valueOf(20000));
			desafio.incluirJogador(6l, 1l, "jogador6", LocalDate.of(1990, 06, 05), 5, BigDecimal.valueOf(20000));
			desafio.incluirJogador(4l, 1l, "jogador4", LocalDate.of(1990, 06, 05), 5, BigDecimal.valueOf(20000));
			
			Assert.assertTrue("Returned wrong player as best", desafio.buscarMelhorJogadorDoTime(0l).equals(2l));
		} catch (Exception e) {
			Assert.fail("Unexpected exception " + e.toString());
		}
	}
	
	@Test
	public void buscarMelhorJogadorTimeVazio() {
		DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();
		
		//adiciona times e jogadores e busca o melhor de um time
		try {
			desafio.incluirTime(0l, "Time0", LocalDate.of(2018, 10, 01), "Vermelho", "Azul");
			desafio.incluirTime(1l, "Time1", LocalDate.of(2018, 10, 01), "Vermelho", "Azul");
			desafio.incluirTime(2l, "Time2", LocalDate.of(2018, 10, 01), "Vermelho", "Azul");
			
			desafio.incluirJogador(0l, 0l, "jogador0", LocalDate.of(1990, 06, 05), 5, BigDecimal.valueOf(20000));
			desafio.incluirJogador(1l, 0l, "jogador1", LocalDate.of(1990, 06, 05), 2, BigDecimal.valueOf(20000));
			desafio.incluirJogador(8l, 0l, "jogador8", LocalDate.of(1990, 06, 05), 8, BigDecimal.valueOf(20000));
			desafio.incluirJogador(2l, 0l, "jogador2", LocalDate.of(1990, 06, 05), 8, BigDecimal.valueOf(20000));
			desafio.incluirJogador(3l, 0l, "jogador3", LocalDate.of(1990, 06, 05), 8, BigDecimal.valueOf(20000));
			
			desafio.incluirJogador(5l, 1l, "jogador5", LocalDate.of(1990, 06, 05), 5, BigDecimal.valueOf(20000));
			desafio.incluirJogador(7l, 1l, "jogador7", LocalDate.of(1990, 06, 05), 5, BigDecimal.valueOf(20000));
			desafio.incluirJogador(6l, 1l, "jogador6", LocalDate.of(1990, 06, 05), 5, BigDecimal.valueOf(20000));
			desafio.incluirJogador(4l, 1l, "jogador4", LocalDate.of(1990, 06, 05), 5, BigDecimal.valueOf(20000));
			
			Assert.assertTrue("Returned non-null pointer", desafio.buscarMelhorJogadorDoTime(2l) == null);
		} catch (Exception e) {
			Assert.fail("Unexpected exception " + e.toString());
		}
	}
	
	@Test
	public void buscarMelhorJogadorTimeInexistente() {
		DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();
		
		//adiciona times e jogadores e busca o melhor de um time nao adicionado
		try {
			desafio.incluirTime(0l, "Time0", LocalDate.of(2018, 10, 01), "Vermelho", "Azul");
			desafio.incluirTime(1l, "Time1", LocalDate.of(2018, 10, 01), "Vermelho", "Azul");
			desafio.incluirTime(2l, "Time2", LocalDate.of(2018, 10, 01), "Vermelho", "Azul");
			
			desafio.incluirJogador(0l, 0l, "jogador0", LocalDate.of(1990, 06, 05), 5, BigDecimal.valueOf(20000));
			desafio.incluirJogador(1l, 0l, "jogador1", LocalDate.of(1990, 06, 05), 2, BigDecimal.valueOf(20000));
			desafio.incluirJogador(2l, 0l, "jogador2", LocalDate.of(1990, 06, 05), 8, BigDecimal.valueOf(20000));
			desafio.incluirJogador(3l, 0l, "jogador3", LocalDate.of(1990, 06, 05), 8, BigDecimal.valueOf(20000));
			
			desafio.incluirJogador(5l, 1l, "jogador5", LocalDate.of(1990, 06, 05), 5, BigDecimal.valueOf(20000));
			desafio.incluirJogador(7l, 1l, "jogador7", LocalDate.of(1990, 06, 05), 5, BigDecimal.valueOf(20000));
			desafio.incluirJogador(6l, 1l, "jogador6", LocalDate.of(1990, 06, 05), 5, BigDecimal.valueOf(20000));
			desafio.incluirJogador(4l, 1l, "jogador4", LocalDate.of(1990, 06, 05), 5, BigDecimal.valueOf(20000));
			
			desafio.buscarMelhorJogadorDoTime(3l);
			Assert.fail("Expected exception, got none");
		} catch (Exception e) {
			Assert.assertTrue("Unexpected exception " + e.toString(), e instanceof TimeNaoEncontradoException);
		}
	}
	
	@Test
	public void buscarMaisVelho() {
		DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();
		
		//adiciona times e jogadores e busca o melhor de um time
		try {
			desafio.incluirTime(0l, "Time0", LocalDate.of(2018, 10, 01), "Vermelho", "Azul");
			desafio.incluirTime(1l, "Time1", LocalDate.of(2018, 10, 01), "Vermelho", "Azul");
			desafio.incluirTime(2l, "Time2", LocalDate.of(2018, 10, 01), "Vermelho", "Azul");
			
			desafio.incluirJogador(1l, 0l, "jogador1", LocalDate.of(1991, 06, 05), 2, BigDecimal.valueOf(20000));
			desafio.incluirJogador(2l, 0l, "jogador2", LocalDate.of(1990, 06, 05), 8, BigDecimal.valueOf(20000));
			desafio.incluirJogador(0l, 0l, "jogador0", LocalDate.of(1990, 06, 05), 5, BigDecimal.valueOf(20000));
			desafio.incluirJogador(3l, 0l, "jogador3", LocalDate.of(1990, 06, 05), 8, BigDecimal.valueOf(20000));
			desafio.incluirJogador(8l, 0l, "jogador8", LocalDate.of(1992, 06, 05), 8, BigDecimal.valueOf(20000));
			
			desafio.incluirJogador(5l, 1l, "jogador5", LocalDate.of(1990, 06, 05), 5, BigDecimal.valueOf(20000));
			desafio.incluirJogador(7l, 1l, "jogador7", LocalDate.of(1990, 06, 05), 5, BigDecimal.valueOf(20000));
			desafio.incluirJogador(6l, 1l, "jogador6", LocalDate.of(1990, 06, 05), 5, BigDecimal.valueOf(20000));
			desafio.incluirJogador(4l, 1l, "jogador4", LocalDate.of(1990, 06, 05), 5, BigDecimal.valueOf(20000));
			
			Assert.assertTrue("Returned wrong player as oldest", desafio.buscarJogadorMaisVelho(0l).equals(0l));
		} catch (Exception e) {
			Assert.fail("Unexpected exception " + e.toString());
		}
	}
	
	@Test
	public void buscarMaisVelhoTimeVazio() {
		DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();
		
		//adiciona times e jogadores e busca o melhor de um time
		try {
			desafio.incluirTime(0l, "Time0", LocalDate.of(2018, 10, 01), "Vermelho", "Azul");
			desafio.incluirTime(1l, "Time1", LocalDate.of(2018, 10, 01), "Vermelho", "Azul");
			desafio.incluirTime(2l, "Time2", LocalDate.of(2018, 10, 01), "Vermelho", "Azul");
			
			desafio.incluirJogador(1l, 0l, "jogador1", LocalDate.of(1990, 06, 05), 2, BigDecimal.valueOf(20000));
			desafio.incluirJogador(0l, 0l, "jogador0", LocalDate.of(1990, 06, 05), 5, BigDecimal.valueOf(20000));
			desafio.incluirJogador(2l, 0l, "jogador2", LocalDate.of(1991, 06, 05), 8, BigDecimal.valueOf(20000));
			desafio.incluirJogador(3l, 0l, "jogador3", LocalDate.of(1992, 06, 05), 8, BigDecimal.valueOf(20000));
			
			desafio.incluirJogador(5l, 1l, "jogador5", LocalDate.of(1990, 06, 05), 5, BigDecimal.valueOf(20000));
			desafio.incluirJogador(7l, 1l, "jogador7", LocalDate.of(1990, 06, 05), 5, BigDecimal.valueOf(20000));
			desafio.incluirJogador(6l, 1l, "jogador6", LocalDate.of(1990, 06, 05), 5, BigDecimal.valueOf(20000));
			desafio.incluirJogador(4l, 1l, "jogador4", LocalDate.of(1990, 06, 05), 5, BigDecimal.valueOf(20000));
			
			Assert.assertTrue("Returned non-null pointer", desafio.buscarJogadorMaisVelho(2l) == null);
		} catch (Exception e) {
			Assert.fail("Unexpected exception " + e.toString());
		}
	}
	
	@Test
	public void buscarMaisVelhoTimeInexistente() {
		DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();
		
		//adiciona times e jogadores e busca o mais velho de um time nao adicionado
		try {
			desafio.incluirTime(0l, "Time0", LocalDate.of(2018, 10, 01), "Vermelho", "Azul");
			desafio.incluirTime(1l, "Time1", LocalDate.of(2018, 10, 01), "Vermelho", "Azul");
			desafio.incluirTime(2l, "Time2", LocalDate.of(2018, 10, 01), "Vermelho", "Azul");
			
			desafio.incluirJogador(0l, 0l, "jogador0", LocalDate.of(1990, 06, 05), 5, BigDecimal.valueOf(20000));
			desafio.incluirJogador(1l, 0l, "jogador1", LocalDate.of(1990, 06, 05), 2, BigDecimal.valueOf(20000));
			desafio.incluirJogador(2l, 0l, "jogador2", LocalDate.of(1990, 06, 05), 8, BigDecimal.valueOf(20000));
			desafio.incluirJogador(3l, 0l, "jogador3", LocalDate.of(1990, 06, 05), 8, BigDecimal.valueOf(20000));
			
			desafio.incluirJogador(5l, 1l, "jogador5", LocalDate.of(1990, 06, 05), 5, BigDecimal.valueOf(20000));
			desafio.incluirJogador(7l, 1l, "jogador7", LocalDate.of(1990, 06, 05), 5, BigDecimal.valueOf(20000));
			desafio.incluirJogador(6l, 1l, "jogador6", LocalDate.of(1990, 06, 05), 5, BigDecimal.valueOf(20000));
			desafio.incluirJogador(4l, 1l, "jogador4", LocalDate.of(1990, 06, 05), 5, BigDecimal.valueOf(20000));
			
			desafio.buscarJogadorMaisVelho(3l);
			Assert.fail("Expected exception, got none");
		} catch (Exception e) {
			Assert.assertTrue("Unexpected exception " + e.toString(), e instanceof TimeNaoEncontradoException);
		}
	}
	
	@Test
	public void buscarTimes() {
		DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();
		
		//adiciona times e jogadores e busca lista dos times
		try {
			desafio.incluirTime(3l, "Time3", LocalDate.of(2018, 10, 01), "Vermelho", "Azul");
			desafio.incluirTime(1l, "Time1", LocalDate.of(2018, 10, 01), "Vermelho", "Azul");
			desafio.incluirTime(0l, "Time0", LocalDate.of(2018, 10, 01), "Vermelho", "Azul");
			desafio.incluirTime(2l, "Time2", LocalDate.of(2018, 10, 01), "Vermelho", "Azul");
			desafio.incluirTime(4l, "Time4", LocalDate.of(2018, 10, 01), "Vermelho", "Azul");
			
			List<Long> times = desafio.buscarTimes();
			Assert.assertTrue("Returned wrong number of teams", times.size() == 5);
			Assert.assertTrue("Returned wrong ids/ordering", times.get(0).equals(0l) &&
					times.get(1).equals(1l) && times.get(2).equals(2l) &&
					times.get(3).equals(3l) && times.get(4).equals(4l));
		} catch (Exception e) {
			Assert.fail("Unexpected exception " + e.toString());
		}
	}
	
	@Test
	public void buscarTimesListaVazia() {
		DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();
		
		//busca lista de times sem times adicionados
		try {
			Assert.assertTrue("Returned non-empty list", desafio.buscarTimes().isEmpty());
		} catch (Exception e) {
			Assert.fail("Returned unexpected exception " + e.toString());
		}
	}
	
	@Test
	public void buscarMaisBemPago() {
		DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();
		
		//adiciona times e jogadores e busca o mais bem pago de um time
		try {
			desafio.incluirTime(0l, "Time0", LocalDate.of(2018, 10, 01), "Vermelho", "Azul");
			desafio.incluirTime(1l, "Time1", LocalDate.of(2018, 10, 01), "Vermelho", "Azul");
			desafio.incluirTime(2l, "Time2", LocalDate.of(2018, 10, 01), "Vermelho", "Azul");
			
			desafio.incluirJogador(1l, 0l, "jogador1", LocalDate.of(1990, 06, 05), 2, BigDecimal.valueOf(20000));
			desafio.incluirJogador(8l, 0l, "jogador8", LocalDate.of(1990, 06, 05), 5, BigDecimal.valueOf(30000));
			desafio.incluirJogador(0l, 0l, "jogador0", LocalDate.of(1990, 06, 05), 5, BigDecimal.valueOf(30000));
			desafio.incluirJogador(2l, 0l, "jogador2", LocalDate.of(1991, 06, 05), 8, BigDecimal.valueOf(25000));
			desafio.incluirJogador(3l, 0l, "jogador3", LocalDate.of(1992, 06, 05), 8, BigDecimal.valueOf(30000));
			
			desafio.incluirJogador(5l, 1l, "jogador5", LocalDate.of(1990, 06, 05), 5, BigDecimal.valueOf(20000));
			desafio.incluirJogador(7l, 1l, "jogador7", LocalDate.of(1990, 06, 05), 5, BigDecimal.valueOf(20000));
			desafio.incluirJogador(6l, 1l, "jogador6", LocalDate.of(1990, 06, 05), 5, BigDecimal.valueOf(20000));
			desafio.incluirJogador(4l, 1l, "jogador4", LocalDate.of(1990, 06, 05), 5, BigDecimal.valueOf(20000));
			
			Assert.assertTrue("Returned wrong player as best paid", desafio.buscarJogadorMaiorSalario(0l).equals(0l));
		} catch (Exception e) {
			Assert.fail("Unexpected exception " + e.toString());
		}
	}
	
	@Test
	public void buscarMaisBemPagoTimeVazio() {
		DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();
		
		//adiciona times e jogadores e busca o mais bem pago de um time
		try {
			desafio.incluirTime(0l, "Time0", LocalDate.of(2018, 10, 01), "Vermelho", "Azul");
			desafio.incluirTime(1l, "Time1", LocalDate.of(2018, 10, 01), "Vermelho", "Azul");
			desafio.incluirTime(2l, "Time2", LocalDate.of(2018, 10, 01), "Vermelho", "Azul");
			
			desafio.incluirJogador(1l, 0l, "jogador1", LocalDate.of(1990, 06, 05), 2, BigDecimal.valueOf(20000));
			desafio.incluirJogador(8l, 0l, "jogador8", LocalDate.of(1990, 06, 05), 5, BigDecimal.valueOf(30000));
			desafio.incluirJogador(0l, 0l, "jogador0", LocalDate.of(1990, 06, 05), 5, BigDecimal.valueOf(30000));
			desafio.incluirJogador(2l, 0l, "jogador2", LocalDate.of(1991, 06, 05), 8, BigDecimal.valueOf(25000));
			desafio.incluirJogador(3l, 0l, "jogador3", LocalDate.of(1992, 06, 05), 8, BigDecimal.valueOf(30000));
			
			desafio.incluirJogador(5l, 1l, "jogador5", LocalDate.of(1990, 06, 05), 5, BigDecimal.valueOf(20000));
			desafio.incluirJogador(7l, 1l, "jogador7", LocalDate.of(1990, 06, 05), 5, BigDecimal.valueOf(20000));
			desafio.incluirJogador(6l, 1l, "jogador6", LocalDate.of(1990, 06, 05), 5, BigDecimal.valueOf(20000));
			desafio.incluirJogador(4l, 1l, "jogador4", LocalDate.of(1990, 06, 05), 5, BigDecimal.valueOf(20000));
			
			Assert.assertTrue("Returned non-null pointer", desafio.buscarJogadorMaiorSalario(2l) == null);
		} catch (Exception e) {
			Assert.fail("Unexpected exception " + e.toString());
		}
	}
	
	@Test
	public void buscarMaisBemPagoTimeInexistente() {
		DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();
		
		//adiciona times e jogadores e busca o mais bem pago de time nao adicionado
		try {
			desafio.incluirTime(0l, "Time0", LocalDate.of(2018, 10, 01), "Vermelho", "Azul");
			desafio.incluirTime(1l, "Time1", LocalDate.of(2018, 10, 01), "Vermelho", "Azul");
			desafio.incluirTime(2l, "Time2", LocalDate.of(2018, 10, 01), "Vermelho", "Azul");
			
			desafio.incluirJogador(1l, 0l, "jogador1", LocalDate.of(1990, 06, 05), 2, BigDecimal.valueOf(20000));
			desafio.incluirJogador(0l, 0l, "jogador0", LocalDate.of(1990, 06, 05), 5, BigDecimal.valueOf(30000));
			desafio.incluirJogador(2l, 0l, "jogador2", LocalDate.of(1991, 06, 05), 8, BigDecimal.valueOf(25000));
			desafio.incluirJogador(3l, 0l, "jogador3", LocalDate.of(1992, 06, 05), 8, BigDecimal.valueOf(30000));
			
			desafio.incluirJogador(5l, 1l, "jogador5", LocalDate.of(1990, 06, 05), 5, BigDecimal.valueOf(20000));
			desafio.incluirJogador(7l, 1l, "jogador7", LocalDate.of(1990, 06, 05), 5, BigDecimal.valueOf(20000));
			desafio.incluirJogador(6l, 1l, "jogador6", LocalDate.of(1990, 06, 05), 5, BigDecimal.valueOf(20000));
			desafio.incluirJogador(4l, 1l, "jogador4", LocalDate.of(1990, 06, 05), 5, BigDecimal.valueOf(20000));
			
			desafio.buscarJogadorMaiorSalario(3l);
			Assert.fail("Expected exception, got none");
		} catch (Exception e) {
			Assert.assertTrue("Unexpected exception " + e.toString(), e instanceof TimeNaoEncontradoException);
		}
	}
	
	@Test
	public void buscarValorSalario() {
		DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();
		
		//adiciona times e jogadores e busca o salario de um jogador
		try {
			desafio.incluirTime(0l, "Time0", LocalDate.of(2018, 10, 01), "Vermelho", "Azul");
			desafio.incluirTime(1l, "Time1", LocalDate.of(2018, 10, 01), "Vermelho", "Azul");
			desafio.incluirTime(2l, "Time2", LocalDate.of(2018, 10, 01), "Vermelho", "Azul");
			
			desafio.incluirJogador(1l, 0l, "jogador1", LocalDate.of(1990, 06, 05), 2, BigDecimal.valueOf(20000));
			desafio.incluirJogador(0l, 0l, "jogador0", LocalDate.of(1990, 06, 05), 5, BigDecimal.valueOf(30000));
			desafio.incluirJogador(2l, 0l, "jogador2", LocalDate.of(1991, 06, 05), 8, BigDecimal.valueOf(25000));
			desafio.incluirJogador(3l, 0l, "jogador3", LocalDate.of(1992, 06, 05), 8, BigDecimal.valueOf(30000));
			
			desafio.incluirJogador(5l, 1l, "jogador5", LocalDate.of(1990, 06, 05), 5, BigDecimal.valueOf(20000));
			desafio.incluirJogador(7l, 1l, "jogador7", LocalDate.of(1990, 06, 05), 5, BigDecimal.valueOf(20000));
			desafio.incluirJogador(6l, 1l, "jogador6", LocalDate.of(1990, 06, 05), 5, BigDecimal.valueOf(20000));
			desafio.incluirJogador(4l, 1l, "jogador4", LocalDate.of(1990, 06, 05), 5, BigDecimal.valueOf(20000));
			
			Assert.assertTrue("Returned wrong income value", desafio.buscarSalarioDoJogador(2l).equals(BigDecimal.valueOf(25000)));
		} catch (Exception e) {
			Assert.fail("Unexpected exception " + e.toString());
		}
	}
	
	@Test
	public void buscarValorSalarioJogadorInexistente() {
		DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();
		
		//adiciona times e jogadores e busca o salario de um jogador
		try {
			desafio.incluirTime(0l, "Time0", LocalDate.of(2018, 10, 01), "Vermelho", "Azul");
			desafio.incluirTime(1l, "Time1", LocalDate.of(2018, 10, 01), "Vermelho", "Azul");
			desafio.incluirTime(2l, "Time2", LocalDate.of(2018, 10, 01), "Vermelho", "Azul");
			
			desafio.incluirJogador(1l, 0l, "jogador1", LocalDate.of(1990, 06, 05), 2, BigDecimal.valueOf(20000));
			desafio.incluirJogador(0l, 0l, "jogador0", LocalDate.of(1990, 06, 05), 5, BigDecimal.valueOf(30000));
			desafio.incluirJogador(2l, 0l, "jogador2", LocalDate.of(1991, 06, 05), 8, BigDecimal.valueOf(25000));
			desafio.incluirJogador(3l, 0l, "jogador3", LocalDate.of(1992, 06, 05), 8, BigDecimal.valueOf(30000));
			
			desafio.incluirJogador(5l, 1l, "jogador5", LocalDate.of(1990, 06, 05), 5, BigDecimal.valueOf(20000));
			desafio.incluirJogador(7l, 1l, "jogador7", LocalDate.of(1990, 06, 05), 5, BigDecimal.valueOf(20000));
			desafio.incluirJogador(6l, 1l, "jogador6", LocalDate.of(1990, 06, 05), 5, BigDecimal.valueOf(20000));
			desafio.incluirJogador(4l, 1l, "jogador4", LocalDate.of(1990, 06, 05), 5, BigDecimal.valueOf(20000));
			
			desafio.buscarSalarioDoJogador(8l);
			Assert.fail("Expected exception, got none");
		} catch (Exception e) {
			Assert.assertTrue("Unexpected exception " + e.toString(), e instanceof JogadorNaoEncontradoException);
		}
	}
	
	@Test
	public void buscarListaMelhoresJogadores() {
		DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();
		
		//adiciona times e jogadores e busca os 4 melhores
		try {
			desafio.incluirTime(0l, "Time0", LocalDate.of(2018, 10, 01), "Vermelho", "Azul");
			desafio.incluirTime(1l, "Time1", LocalDate.of(2018, 10, 01), "Vermelho", "Azul");
			desafio.incluirTime(2l, "Time2", LocalDate.of(2018, 10, 01), "Vermelho", "Azul");
			
			desafio.incluirJogador(1l, 0l, "jogador1", LocalDate.of(1990, 06, 05), 2, BigDecimal.valueOf(20000));
			desafio.incluirJogador(0l, 0l, "jogador0", LocalDate.of(1990, 06, 05), 5, BigDecimal.valueOf(30000));
			desafio.incluirJogador(3l, 0l, "jogador3", LocalDate.of(1992, 06, 05), 8, BigDecimal.valueOf(30000));
			desafio.incluirJogador(2l, 0l, "jogador2", LocalDate.of(1991, 06, 05), 8, BigDecimal.valueOf(25000));
			
			desafio.incluirJogador(5l, 1l, "jogador5", LocalDate.of(1990, 06, 05), 6, BigDecimal.valueOf(20000));
			desafio.incluirJogador(7l, 1l, "jogador7", LocalDate.of(1990, 06, 05), 6, BigDecimal.valueOf(20000));
			desafio.incluirJogador(6l, 1l, "jogador6", LocalDate.of(1990, 06, 05), 3, BigDecimal.valueOf(20000));
			desafio.incluirJogador(4l, 1l, "jogador4", LocalDate.of(1990, 06, 05), 1, BigDecimal.valueOf(20000));
			
			List<Long> top = desafio.buscarTopJogadores(4);
			Assert.assertTrue("Returned wrong number of players", top.size() == 4);
			Assert.assertTrue("Returned wrong ids/ordering", top.get(0).equals(2l) &&
					top.get(1).equals(3l) && top.get(2).equals(5l) && top.get(3).equals(7l));
		} catch (Exception e) {
			Assert.fail("Unexpected exception " + e.toString());
		}
	}
	
	@Test
	public void buscarCorFora() {
		DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();
		
		//adiciona times e busca cor do time de fora
		try {
			desafio.incluirTime(0l, "Time0", LocalDate.of(2018, 10, 01), "Vermelho", "Azul");
			desafio.incluirTime(1l, "Time1", LocalDate.of(2018, 10, 01), "Vermelho", "Preto");
			desafio.incluirTime(2l, "Time2", LocalDate.of(2018, 10, 01), "Amarelo", "Preto");
			
			Assert.assertTrue("Returned wrong colours", desafio.buscarCorCamisaTimeDeFora(0l, 1l).equals("Preto") &&
					desafio.buscarCorCamisaTimeDeFora(1l, 2l).equals("Amarelo"));
		} catch (Exception e) {
			Assert.fail("Unexpected exception " + e.toString());
		}
	}
	
	@Test
	public void buscarCorForaMesmoTime() {
		DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();
		
		//adiciona times e busca cor do time de fora
		try {
			desafio.incluirTime(0l, "Time0", LocalDate.of(2018, 10, 01), "Vermelho", "Azul");
			desafio.incluirTime(1l, "Time1", LocalDate.of(2018, 10, 01), "Vermelho", "Preto");
			desafio.incluirTime(2l, "Time2", LocalDate.of(2018, 10, 01), "Amarelo", "Preto");
			
			desafio.buscarCorCamisaTimeDeFora(1l, 1l);
			Assert.fail("Expected exception, got none");
		} catch (Exception e) {
			Assert.assertTrue("Unexpected exception " + e.toString(), e instanceof UnsupportedOperationException);
		}
	}
	
	@Test
	public void buscarCorForaTimeCasaInexistente() {
		DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();
		
		//adiciona times e jogadores e busca cor de fora com time da casa inexistente
		try {
			desafio.incluirTime(0l, "Time0", LocalDate.of(2018, 10, 01), "Vermelho", "Azul");
			desafio.incluirTime(1l, "Time1", LocalDate.of(2018, 10, 01), "Vermelho", "Preto");
			desafio.incluirTime(2l, "Time2", LocalDate.of(2018, 10, 01), "Amarelo", "Preto");
			
			desafio.buscarCorCamisaTimeDeFora(3l, 2l);
			Assert.fail("Excpected exception, got none");
		} catch (Exception e) {
			Assert.assertTrue("Unexpected exception " + e.toString(), e instanceof TimeNaoEncontradoException);
		}
	}
	
	@Test
	public void buscarCorForaTimeForaInexistente() {
		DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();
		
		//adiciona times e jogadores e busca cor de fora com time de fora inexistente
		try {
			desafio.incluirTime(0l, "Time0", LocalDate.of(2018, 10, 01), "Vermelho", "Azul");
			desafio.incluirTime(1l, "Time1", LocalDate.of(2018, 10, 01), "Vermelho", "Preto");
			desafio.incluirTime(2l, "Time2", LocalDate.of(2018, 10, 01), "Amarelo", "Preto");
			
			desafio.buscarCorCamisaTimeDeFora(1l, 3l);
			Assert.fail("Excpected exception, got none");
		} catch (Exception e) {
			Assert.assertTrue("Unexpected exception " + e.toString(), e instanceof TimeNaoEncontradoException);
		}
	}
}