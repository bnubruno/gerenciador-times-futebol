package br.com.test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import br.com.codenation.DesafioMeuTimeApplication;
import br.com.codenation.Jogador;
import br.com.codenation.Time;
import br.com.codenation.desafio.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;

public class DesafioMeuTimeApplicationTest {
	
	private DesafioMeuTimeApplication desafio;
	private Map<Long, Time> times;
	private Map<Long, Jogador> jogadores;
	
	public DesafioMeuTimeApplicationTest() {
		desafio = new DesafioMeuTimeApplication();

		// initializing a team
		desafio.incluirTime(0L, "Tabajara FC", LocalDate.now(), "principal1", "reserva1");
		// initializing a new player
		desafio.incluirJogador(50L, 0L, "Ronarid", LocalDate.now(), 50, new BigDecimal(900.0));
		// defining captain
		desafio.definirCapitao(50L);
		
		//returning teams and players
		times = desafio.getTimes();
		jogadores = desafio.getJogadores();
		
	}
	
	@Test
	public void notNullTimes() {
		Assert.assertNotNull(times);
	}
	
	@Test
	public void notNullJogadores() {
		Assert.assertNotNull(jogadores);
	}
	
	@Test()
	public void verificaTimeDuplicado() {
		try {
			desafio.incluirTime(0L, "Tabajara FC2", LocalDate.now(), "principal2", "reserva2");
		}catch (Exception e) {
			Assert.assertSame(e.getClass(), IdentificadorUtilizadoException.class);
		}
	}
	
	@Test()
	public void verificaJogadorDuplicado() {
		try {
			desafio.incluirJogador(50L, 0L, "Ronarid", LocalDate.now(), 50, new BigDecimal(900.0));
		}catch (Exception e) {
			Assert.assertSame(e.getClass(), IdentificadorUtilizadoException.class);
		}
	}
	
	@Test()
	public void definirCapitaoNaoExiste() {
		try {
			desafio.incluirJogador(50L, 999L, "Romedio", LocalDate.now(), 50, new BigDecimal(900.0));
		}catch (Exception e) {
			Assert.assertSame(e.getClass(), TimeNaoEncontradoException.class);
		}
		
	}	
	
	@Test()
	public void definirCapitaoNaoexiste() {
		try {
			desafio.definirCapitao(99L);
		}
		catch (Exception e) {
			Assert.assertSame(e.getClass(), JogadorNaoEncontradoException.class);
		}
	}
	
	
	@Test()
	public void definirCapitaoNovoJogador() {
		desafio.incluirJogador(51L, 0L, "Robert Charles", LocalDate.now(), 50, new BigDecimal(1000.0));
		desafio.definirCapitao(51L);
		
		Assert.assertSame(times.get(0L).idCapitao, 51L);
	}
	
	@Test()
	public void buscarCapitaoTimeNaoEncontrado() {
		try {
			desafio.buscarCapitaoDoTime(99L);
		}
		catch(Exception e)
		{
			Assert.assertSame(e.getClass(), TimeNaoEncontradoException.class);
		}
	}
	
	@Test()
	public void buscarCapitaoNaoInformado() {
		try {
			desafio.incluirTime(1L, "Time2", LocalDate.now(), "principal1", "reserva1");
			desafio.buscarCapitaoDoTime(1L);
		}
		catch(Exception e)
		{
			Assert.assertSame(e.getClass(), CapitaoNaoInformadoException.class);
		}
	}

	@Test()
	public void buscarCapitaoPass() {
		Assert.assertSame(50L, desafio.buscarCapitaoDoTime(0L));
	}
	
	@Test()
	public void buscarNomeJogador() {
		Assert.assertSame("Ronarid", desafio.buscarNomeJogador(50L));
		
		try{
			desafio.buscarNomeJogador(99L);
		}catch (Exception e) {
			Assert.assertSame(e.getClass(), JogadorNaoEncontradoException.class);
		}
	}
	
	@Test()
	public void buscarNomeTime() {
		Assert.assertSame("Tabajara FC", desafio.buscarNomeTime(0L));
		
		try{
			desafio.buscarNomeTime(99L);
		}catch (Exception e) {
			Assert.assertSame(e.getClass(), TimeNaoEncontradoException.class);
		}
	}

	@Test()
	public void buscarJogadoresTime() {
		try{
			desafio.buscarJogadoresDoTime(99L);
		}catch (Exception e) {
			Assert.assertSame(e.getClass(), TimeNaoEncontradoException.class);
		}
	}

	@Test()
	public void buscarMelhorJogadorTime() {
		try{
			desafio.buscarMelhorJogadorDoTime(99L);
		}catch (Exception e) {
			Assert.assertSame(e.getClass(), TimeNaoEncontradoException.class);
		}
	}
	
	@Test()
	public void buscarJogadorMaisVelho() {
		try{
			desafio.buscarJogadorMaisVelho(99L);
		}catch (Exception e) {
			Assert.assertSame(e.getClass(), TimeNaoEncontradoException.class);
		}
	}
	
	@Test
	public void buscarTimes() {
		List<Long> ts;
		ts = desafio.buscarTimes();
		
		Assert.assertSame(ts.size(), times.size());
	}
	
	@Test()
	public void buscarJogadorMaiorSalario() {
		try{
			desafio.buscarJogadorMaiorSalario(99L);
		}catch (Exception e) {
			Assert.assertSame(e.getClass(), TimeNaoEncontradoException.class);
		}
	}
	
	@Test()
	public void buscarSalarioJogador() {
		try{
			desafio.buscarSalarioDoJogador(99L);
		}catch (Exception e) {
			Assert.assertSame(e.getClass(), JogadorNaoEncontradoException.class);
		}
	}
	
	@Test()
	public void buscaCorCamisa() {
		// cor diferente
		desafio.incluirTime(1L, "Academicos do Tucuruvi", LocalDate.now(), "Laranja", "Preto");
		
		// cor igual
		desafio.incluirTime(2L, "Bruxo FC", LocalDate.now(), "principal1", "reserva1");
		
		Assert.assertSame(desafio.buscarCorCamisaTimeDeFora(0L, 1L), "Laranja");
		Assert.assertSame(desafio.buscarCorCamisaTimeDeFora(0L, 2L), "reserva1");
		
	}
	
}
