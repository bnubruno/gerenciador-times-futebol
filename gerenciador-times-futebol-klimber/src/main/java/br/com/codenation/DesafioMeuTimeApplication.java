package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import br.com.codenation.desafio.annotation.Desafio;
import br.com.codenation.desafio.app.MeuTimeInterface;
import br.com.codenation.desafio.exceptions.*;

public class DesafioMeuTimeApplication implements MeuTimeInterface {

	HashMap<Long, Time> listaTime = new HashMap<Long, Time>();
	HashMap<Long, Jogador> listaJogador = new HashMap<Long, Jogador>();

	@Desafio("incluirTime")
	public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal,
			String corUniformeSecundario) {
		if (listaTime.containsKey(id)) {
			throw new IdentificadorUtilizadoException();
		} else {
			listaTime.put(id, new Time(id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario, null));
		}
	}

	@Desafio("incluirJogador")
	public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade,
			BigDecimal salario) {
		if (listaJogador.containsKey(id)) {
			throw new IdentificadorUtilizadoException();
		}
		if (listaTime.containsKey(idTime) == false) {
			throw new TimeNaoEncontradoException();
		}
		listaJogador.put(id, new Jogador(id, idTime, nome, dataNascimento, nivelHabilidade, salario));
	}

	@Desafio("definirCapitao")
	// Define o capitão do time buscando a id do time e colocando
	// a id do jogador naquele time
	public void definirCapitao(Long idJogador) {
		if (listaJogador.containsKey(idJogador)) {
			listaTime.get(listaJogador.get(idJogador).getIdTime()).setJogadorCapitao(idJogador);
		} else {
			throw new JogadorNaoEncontradoException();
		}
	}

	@Desafio("buscarCapitaoDoTime")
	public Long buscarCapitaoDoTime(Long idTime) {
		if (listaTime.containsKey(idTime) == false) {
			throw new TimeNaoEncontradoException();
		}
		if (listaTime.get(idTime).getJogadorCapitao().isPresent() == false) {
			throw new CapitaoNaoInformadoException();
		}
		return listaTime.get(idTime).getJogadorCapitao().get().longValue();
	}

	@Desafio("buscarNomeJogador")
	public String buscarNomeJogador(Long idJogador) {
		if (listaJogador.containsKey(idJogador)) {
			return listaJogador.get(idJogador).getNome();
		} else {
			throw new JogadorNaoEncontradoException();
		}

	}

	@Desafio("buscarNomeTime")
	public String buscarNomeTime(Long idTime) {
		if (listaTime.containsKey(idTime)) {
			return listaTime.get(idTime).getNome();
		} else {
			throw new TimeNaoEncontradoException();
		}
	}

	@Desafio("buscarJogadoresDoTime")
	// Buscar os jogadores que pertencem a um time,
	// mapeia os IDs, sorteia, transforma em Lista e retorna
	public List<Long> buscarJogadoresDoTime(Long idTime) {
		if (listaTime.containsKey(idTime)) {
			return listaJogador.values().stream().filter(jogador -> jogador.getIdTime().equals(idTime))
					.map(jogador -> jogador.getId()).sorted().collect(Collectors.toList());
		} else {
			throw new TimeNaoEncontradoException();
		}
	}

	@Desafio("buscarMelhorJogadorDoTime")
	public Long buscarMelhorJogadorDoTime(Long idTime) {
		if (listaTime.containsKey(idTime)) {
			return listaJogador.values().stream().filter(jogador -> jogador.getIdTime().equals(idTime))
					.sorted(Comparator.comparing(Jogador::getNivelHabilidade).reversed()
							.thenComparing(Comparator.comparing(Jogador::getId)))
					.findFirst().get().getId();
		} else {
			throw new TimeNaoEncontradoException();
		}
	}

	@Desafio("buscarJogadorMaisVelho")
	public Long buscarJogadorMaisVelho(Long idTime) {
		if (listaTime.containsKey(idTime)) {
			LocalDate maisvelho = listaJogador.values().stream().filter(jogador -> jogador.getIdTime().equals(idTime))
					.sorted(Comparator.comparing(Jogador::getDataNascimento)).findFirst().get().getDataNascimento();
			return listaJogador.values().stream().filter(jogador -> jogador.getIdTime().equals(idTime))
					.filter(jogador -> jogador.getDataNascimento().equals(maisvelho))
					.sorted(Comparator.comparing(Jogador::getId)).findFirst().get().getId();
		} else {
			throw new TimeNaoEncontradoException();
		}
	}

	@Desafio("buscarTimes")
	public List<Long> buscarTimes() {
		return listaTime.values().stream().map(time -> time.getIdTime()).sorted().collect(Collectors.toList());
	}

	@Desafio("buscarJogadorMaiorSalario")
	public Long buscarJogadorMaiorSalario(Long idTime) {
		if (listaTime.containsKey(idTime)) {
			return listaJogador.values().stream().filter(jogador -> jogador.getIdTime().equals(idTime))
					.sorted(Comparator.comparing(Jogador::getSalario).reversed()
							.thenComparing(Comparator.comparing(Jogador::getId)))
					.findFirst().get().getId();
		} else {
			throw new TimeNaoEncontradoException();
		}
	}

	@Desafio("buscarSalarioDoJogador")
	public BigDecimal buscarSalarioDoJogador(Long idJogador) {
		if (listaJogador.containsKey(idJogador)) {
			return listaJogador.get(idJogador).getSalario();
		} else {
			throw new JogadorNaoEncontradoException();
		}
	}

	@Desafio("buscarTopJogadores")
	public List<Long> buscarTopJogadores(Integer top) {
		if (listaJogador.isEmpty()) {
			return new ArrayList<>();
		} else {
			return listaJogador.values().stream()
					.sorted(Comparator.comparing(Jogador::getNivelHabilidade).reversed()
							.thenComparing(Comparator.comparing(Jogador::getId)))
					.map(jogador -> jogador.getId()).collect(Collectors.toList()).subList(0, top);
		}

	}

	@Desafio("buscarCorCamisaTimeDeFora")
	public String buscarCorCamisaTimeDeFora(Long timeDaCasa, Long timeDeFora) {

		if (listaTime.containsKey(timeDaCasa) && listaTime.containsKey(timeDeFora)) {
			if (listaTime.get(timeDaCasa).getCorUniformePrincipal()
					.equals(listaTime.get(timeDeFora).getCorUniformePrincipal())) {
				return listaTime.get(timeDeFora).getCorUniformeSecundario();
			} else {
				return listaTime.get(timeDeFora).getCorUniformePrincipal();
			}
		} else {
			throw new TimeNaoEncontradoException();
		}
	}

}
