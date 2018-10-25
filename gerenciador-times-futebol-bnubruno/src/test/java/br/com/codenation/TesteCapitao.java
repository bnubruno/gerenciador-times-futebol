package br.com.codenation;

import java.util.Optional;

import org.junit.Test;

import br.com.codenation.model.Jogador;
import br.com.codenation.model.Time;

public class TesteCapitao {

	@Test
	public void teste() {
		Jogador jogador = new Jogador();
		jogador.setNome("Bruno");

		Time time = new Time();
		time.setNome("Flamengo");
		time.setJogador(jogador);
		jogador.setTime(time);

		Optional<String> timeAbc = time.getJogador().flatMap(j -> j.getTime()).map(t -> t.getNome());

		timeAbc.ifPresent(s -> System.out.println(s));
		fazCoisaComTime(timeAbc);
	}

	public void fazCoisaComTime(Optional<String> time) {
		time.ifPresent(t -> {
		});
	}

}
