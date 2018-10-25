package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;

import br.com.codenation.desafio.annotation.Desafio;
import br.com.codenation.desafio.exceptions.*;

public class DesafioMeuTimeApplicationTest {

	@Test
	public void metodo() {
		DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();

		// =========== TESTES DA CLASSE DesafioMeuTimeApplication =========== //
		
		// @Desafio("incluirTime")
		desafio.incluirTime(1l, "Real Madrid", LocalDate.of(2016, 1, 1), "Branco", "Azul");

		try {
			desafio.incluirTime(1l, "Real Madrid", LocalDate.of(2016, 1, 1), "Branco", "Azul");
			Assert.assertTrue("Deveria dar exceção", false);
		} catch (Exception e) {
			if (e.getCause() instanceof IdentificadorUtilizadoException) {
				Assert.assertTrue(false);
			}
		}

		// @Desafio("incluirJogador")
		desafio.incluirJogador(10l, 1l, "Cristiano Ronaldo", LocalDate.of(1970, 10, 15), 99, new BigDecimal ("1.500"));

		try {
			desafio.incluirJogador(10l, 2l, "Cristiano Ronaldo", LocalDate.of(1970, 10, 15), 99, new BigDecimal ("1.500"));
			Assert.assertTrue("Deveria dar exceção", false);
		} catch (Exception e) {
			if (e.getCause() instanceof TimeNaoEncontradoException) {
				Assert.assertTrue(false);
			}
		}
		
		try {
			desafio.incluirJogador(10l, 1l, "Cristiano Ronaldo", LocalDate.of(1970, 10, 15), 99, new BigDecimal ("1.500"));
			Assert.assertTrue("Deveria dar exceção", false);
		} catch (Exception e) {
			if (e.getCause() instanceof IdentificadorUtilizadoException) {
				Assert.assertTrue(false);
			}
		}
		
		
		// @Desafio("definirCapitao")
		desafio.definirCapitao(10l);
		try {
			desafio.definirCapitao(15l);
			Assert.assertTrue("Deveria dar exceção", false);
		} catch (Exception e) {
			if (e.getCause() instanceof JogadorNaoEncontradoException) {
				Assert.assertTrue(false);
			}
		}
		
		// cadastro de novo jogador sem time
        desafio.incluirJogadorSemTime(11l, null, "Bale", LocalDate.of(1980, 10, 15), 97, new BigDecimal ("1.450"));
        // testando a definição de um capitão para um jogador sem time
        try{
        	desafio.definirCapitao(11l);
        	Assert.assertTrue("Deveria dar exceção", false);
        } catch (Exception e) {
			if (e.getCause() instanceof TimeNaoEncontradoException) {
				Assert.assertTrue(false);
			}
		}
        // removendo jogador sem time
        desafio.removerJodador(11l);
		
		// inclusão de novo jogador para testar um time com capitão já setado
		desafio.incluirJogador(12l, 1l, "Marcelo", LocalDate.of(1910, 10, 15), 95, new BigDecimal ("1.400"));
		desafio.definirCapitao(12l);
		
		// @Desafio("buscarCapitaoDoTime")
		try {
			desafio.buscarCapitaoDoTime(2l);
			Assert.assertTrue("Deveria dar exceção", false);
		} catch (Exception e) {
			if (e.getCause() instanceof TimeNaoEncontradoException) {
				Assert.assertTrue(false);
			}
		}
		desafio.buscarCapitaoDoTime(1l);
		
		// cadastro de novo time sem capitão setado
		desafio.incluirTime(2l, "Barcelona", LocalDate.of(2016, 1, 1), "Azul e Vermelho", "Amarelo");
		// buscar capitão de time sem capitão setado
		try {
			desafio.buscarCapitaoDoTime(2l);
			Assert.assertTrue("Deveria dar exceção", false);
		} catch (Exception e) {
			if (e.getCause() instanceof CapitaoNaoInformadoException) {
				Assert.assertTrue(false);
			}
		}
		
		// @Desafio("buscarNomeJogador")
		try {
			desafio.buscarNomeJogador(15l);
			Assert.assertTrue("Deveria dar exceção", false);
		} catch (Exception e) {
			if (e.getCause() instanceof JogadorNaoEncontradoException) {
				Assert.assertTrue(false);
			}
		}
		desafio.buscarNomeJogador(10l);
		
		// @Desafio("buscarNomeTime")
		try {
			desafio.buscarNomeTime(5l);
			Assert.assertTrue("Deveria dar exceção", false);
		} catch (Exception e) {
			if (e.getCause() instanceof TimeNaoEncontradoException) {
				Assert.assertTrue(false);
			}
		}
		desafio.buscarNomeTime(1l);
		
		// @Desafio("buscarJogadoresDoTime")
		try {
			desafio.buscarJogadoresDoTime(5l);
			Assert.assertTrue("Deveria dar exceção", false);
		} catch (Exception e) {
			if (e.getCause() instanceof TimeNaoEncontradoException) {
				Assert.assertTrue(false);
			}
		}
		desafio.buscarJogadoresDoTime(1l);
		
		// @Desafio("buscarMelhorJogadorDoTime")
		try {
			desafio.buscarMelhorJogadorDoTime(5l);
			Assert.assertTrue("Deveria dar exceção", false);
		} catch (Exception e) {
			if (e.getCause() instanceof TimeNaoEncontradoException) {
				Assert.assertTrue(false);
			}
		}
		desafio.buscarMelhorJogadorDoTime(1l);
		
		// @Desafio("buscarJogadorMaisVelho")
		try {
			desafio.buscarJogadorMaisVelho(5l);
			Assert.assertTrue("Deveria dar exceção", false);
		} catch (Exception e) {
			if (e.getCause() instanceof TimeNaoEncontradoException) {
				Assert.assertTrue(false);
			}
		}
		desafio.buscarJogadorMaisVelho(1l);
		
		// @Desafio("buscarTimes")
		desafio.buscarTimes();
		
		// @Desafio("buscarJogadorMaiorSalario")
		try {
			desafio.buscarJogadorMaiorSalario(5l);
			Assert.assertTrue("Deveria dar exceção", false);
		} catch (Exception e) {
			if (e.getCause() instanceof TimeNaoEncontradoException) {
				Assert.assertTrue(false);
			}
		}
		desafio.buscarJogadorMaiorSalario(1l);
		
		// @Desafio("buscarSalarioDoJogador")
		desafio.buscarSalarioDoJogador(10l);
		try {
			desafio.buscarSalarioDoJogador(15l);
			Assert.assertTrue("Deveria dar exceção", false);
		} catch (Exception e) {
			if (e.getCause() instanceof JogadorNaoEncontradoException) {
				Assert.assertTrue(false);
			}
		}
		
		// @Desafio("buscarTopJogadores")
		desafio.buscarTopJogadores(2);
		
		// @Desafio("buscarCorCamisaTimeDeFora")
		desafio.buscarCorCamisaTimeDeFora(1l, 2l);
		try {
			desafio.buscarCorCamisaTimeDeFora(5l, 6l);
			Assert.assertTrue("Deveria dar exceção", false);
		} catch (Exception e) {
			if (e.getCause() instanceof TimeNaoEncontradoException) {
				Assert.assertTrue(false);
			}
		}
		// cadastro de novo time com cor da camisa principal igual ao do time "1l"
		desafio.incluirTime(3l, "Tottenham", LocalDate.of(2014, 1, 1), "Branco", "Azul");
		desafio.buscarCorCamisaTimeDeFora(1l, 3l);

		
		// =========== TESTES DA CLASSE Jogador =========== //
	
		// cadastro de novo jogador para atualização dos dados
		desafio.incluirJogador(13l, 1L, "Ramos", LocalDate.of(1985, 10, 15), 96, new BigDecimal ("1.480"));

		// @Desafio("buscarJogadorObject")
		desafio.buscarJogadorObject(13l);
		
		// @Desafio("updateNomeJogador")
		desafio.updateNomeJogador(13l, "Sergio Ramos");
		
		// @Desafio("updateDataNascimento")
		desafio.updateDataNascimento(13l, LocalDate.of(1986, 10, 15));
		
		// @Desafio("updateNivelHabilidade")
		desafio.updateNivelHabilidade(13l, 94);

		// @Desafio("updateIdJogador")
		desafio.updateIdJogador(13l, 14l);

		// @Desafio("updateTime")
		desafio.updateTime(14l, 2l);
		
		// @Desafio("updateSalario")
		desafio.updateSalario(14l, new BigDecimal ("1.490"));
		
		
		// =========== TESTES DA CLASSE Time =========== //
		
		// cadastro de novo time para atualização dos dados
		desafio.incluirTime(6l, "Chelseaa", LocalDate.of(2015, 1, 1), "Azul", "Branco");
		
		// @Desafio("buscarTimeObject")
		desafio.buscarTimeObject(1l);
		
		// @Desafio("updateIdTime")
		desafio.updateIdTime(6l, 7l);
		
		// @Desafio("updateNomeTime")
		desafio.updateNomeTime(7l, "Chelsea");
		
		// @Desafio("getDataCriacao")
		desafio.getDataCriacao(7l);
		
		// @Desafio("getCapitao")
		desafio.getCapitao(1l);

		// @Desafio("updateDataCriacao")
		desafio.updateDataCriacao(7l, LocalDate.of(2014, 1, 1));
		
		// @Desafio("updateCapitao")
		desafio.updateCapitao(1l, 13l);
		
		// @Desafio("updateCorUniformeSecundario")
		desafio.updateCorUniformeSecundario(7l, "Rosa");
		
		// @Desafio("updateCorUniformePrincipal")
		desafio.updateCorUniformePrincipal(7l, "Roxo");				
	}
}
