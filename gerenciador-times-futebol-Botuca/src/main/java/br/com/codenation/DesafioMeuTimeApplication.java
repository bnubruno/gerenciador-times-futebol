//C:\Users\Botuca\codenation\codenation test -c java-1
//C:\Users\Botuca\codenation\codenation submit -c java-1

package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import br.com.codenation.desafio.annotation.Desafio;
import br.com.codenation.desafio.app.MeuTimeInterface;
import br.com.codenation.desafio.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;
import br.com.codenation.model.Jogador;
import br.com.codenation.model.Time;

public class DesafioMeuTimeApplication implements MeuTimeInterface {

	private HashMap<Long, Time> times = new HashMap<Long, Time>();
	private HashMap<Long, Jogador> jogadores = new HashMap<Long, Jogador>();
	private HashMap<Long, List<Jogador>> jogadoresDoTime = new HashMap<Long, List<Jogador>>();

	@Desafio("incluirTime")
	public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal,
			String corUniformeSecundario) {

		if (times.containsKey(id) == true) {
			throw new IdentificadorUtilizadoException();
		}

		Time time = new Time();

		time.setId(id);
		time.setNome(nome);
		time.setDataCriacao(dataCriacao);
		time.setCorUniformePrincipal(corUniformePrincipal);
		time.setCorUniformeSecundario(corUniformeSecundario);
		time.setIdCapitao(null);
		times.put(id, time);
		jogadoresDoTime.put(id, new ArrayList<Jogador>());

		return;
	}

	@Desafio("incluirJogador")
	public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade,
			BigDecimal salario) {

		if (jogadores.containsKey(id) == true) {
			throw new IdentificadorUtilizadoException();
		}

		if (times.containsKey(idTime) == false) {
			throw new TimeNaoEncontradoException();
		}

		Jogador jogador = new Jogador();

		jogador.setId(id);
		jogador.setIdTime(idTime);
		jogador.setNome(nome);
		jogador.setDataNascimento(dataNascimento);
		jogador.setNivelHabilidade(nivelHabilidade);
		jogador.setSalario(salario);
		jogadores.put(id, jogador);

		jogadoresDoTime.get(idTime).add(jogador);
		return;
	}

	@Desafio("definirCapitao")
	public void definirCapitao(Long idJogador) {

		if (jogadores.containsKey(idJogador) == false) {
			throw new JogadorNaoEncontradoException();
		}

		times.get(jogadores.get(idJogador).getIdTime()).setIdCapitao(idJogador);

	}

	@Desafio("buscarCapitaoDoTime")
	public Long buscarCapitaoDoTime(Long idTime) {

		if (times.containsKey(idTime) == false) {
			throw new TimeNaoEncontradoException();
		}
		if (times.get(idTime).getIdCapitao() == null) {
			throw new CapitaoNaoInformadoException();
		}

		return times.get(idTime).getIdCapitao();
	}

	@Desafio("buscarNomeJogador")
	public String buscarNomeJogador(Long idJogador) {
		if (jogadores.containsKey(idJogador) == false) {
			throw new JogadorNaoEncontradoException();
		}

		return jogadores.get(idJogador).getNome();

	}

	@Desafio("buscarNomeTime")
	public String buscarNomeTime(Long idTime) {
		if (times.containsKey(idTime) == false) {
			throw new TimeNaoEncontradoException();
		}

		return times.get(idTime).getNome();

	}

	@Desafio("buscarJogadoresDoTime")
	public List<Long> buscarJogadoresDoTime(Long idTime) {
		if (times.containsKey(idTime) == false) {
			throw new TimeNaoEncontradoException();
		}

		List<Long> ids = new ArrayList<Long>();
		for (Jogador j : jogadoresDoTime.get(idTime)) {
			ids.add(j.getId());
		}
		// List<Long> collect =
		// jogadoresDoTime.get(idTime).stream().map(Jogador::getId).collect(Collectors.toList());
		Collections.sort(ids);
		return ids;
	}

	@Desafio("buscarMelhorJogadorDoTime")
	public Long buscarMelhorJogadorDoTime(Long idTime) {
		if (times.containsKey(idTime) == false) {
			throw new TimeNaoEncontradoException();
		}
		Long idMelhor = null;
		Integer melhor = -1;
		for (Jogador j : jogadoresDoTime.get(idTime)) {
			if (j.getNivelHabilidade() > melhor) {
				melhor = j.getNivelHabilidade();
				idMelhor = j.getId();
			}

		}
		return idMelhor;
	}

	@Desafio("buscarJogadorMaisVelho")
	public Long buscarJogadorMaisVelho(Long idTime) {
		if (times.containsKey(idTime) == false) {
			throw new TimeNaoEncontradoException();
		}

		Long idVelho = jogadoresDoTime.get(idTime).get(0).getId();
		LocalDate velho = jogadoresDoTime.get(idTime).get(0).getDataNascimento();
		for (Jogador j : jogadoresDoTime.get(idTime)) {
			if (j.getDataNascimento().isBefore(velho) || (j.getDataNascimento().equals(velho) && j.getId() < idVelho)) {
				velho = j.getDataNascimento();
				idVelho = j.getId();
			}

		}
		return idVelho;
	}

	@Desafio("buscarTimes")
	public List<Long> buscarTimes() {

		List<Long> ids = new ArrayList<Long>();
		if (times.isEmpty() == true) {
			return ids;
		}
		ids.addAll(times.keySet());
		Collections.sort(ids);
		return ids;
	}

	@Desafio("buscarJogadorMaiorSalario")
	public Long buscarJogadorMaiorSalario(Long idTime) {

		if (times.containsKey(idTime) == false) {
			throw new TimeNaoEncontradoException();
		}

		Long idMaiorSalario = jogadoresDoTime.get(idTime).get(0).getId();
		BigDecimal salario = jogadoresDoTime.get(idTime).get(0).getSalario();
		for (Jogador j : jogadoresDoTime.get(idTime)) {
			if (j.getSalario().compareTo(salario) == 1
					|| (j.getSalario().equals(salario) && j.getId() < idMaiorSalario)) {
				salario = j.getSalario();
				idMaiorSalario = j.getId();
			}
		}
		return idMaiorSalario;

	}

	@Desafio("buscarSalarioDoJogador")
	public BigDecimal buscarSalarioDoJogador(Long idJogador) {

		if (jogadores.containsKey(idJogador) == false) {
			throw new JogadorNaoEncontradoException();
		}

		return jogadores.get(idJogador).getSalario();

	}

	@Desafio("buscarTopJogadores")
	public List<Long> buscarTopJogadores(Integer top) {
		List<Jogador> topJog = new ArrayList<Jogador>();
		jogadores.forEach((k, j) -> topJog.add(j));
		Collections.sort(topJog, Jogador::compareTo);

		return topJog.subList(0, top).stream().map(Jogador::getId).collect(Collectors.toList());

	}

	@Desafio("buscarCorCamisaTimeDeFora")
	public String buscarCorCamisaTimeDeFora(Long timeDaCasa, Long timeDeFora) {
			
		if (times.containsKey(timeDaCasa) == false) {
			throw new TimeNaoEncontradoException();
		}
		
		if (times.containsKey(timeDeFora) == false) {
			throw new TimeNaoEncontradoException();
		}
		
		if((times.get(timeDaCasa).getCorUniformePrincipal() == times.get(timeDeFora).getCorUniformePrincipal())) {
			return times.get(timeDeFora).getCorUniformeSecundario();
		}
	
		return times.get(timeDeFora).getCorUniformePrincipal();
		
	}

}
