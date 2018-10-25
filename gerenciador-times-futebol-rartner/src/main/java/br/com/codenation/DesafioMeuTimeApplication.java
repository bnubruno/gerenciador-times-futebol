package br.com.codenation;

import br.com.codenation.desafio.annotation.Desafio;
import br.com.codenation.desafio.app.MeuTimeInterface;
import br.com.codenation.desafio.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;
import br.com.codenation.model.Jogador;
import br.com.codenation.model.Time;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

public class DesafioMeuTimeApplication implements MeuTimeInterface {

	private HashMap<Long, Time> times;
	private HashMap<Long, Jogador> jogadores;
	private HashMap<Long, List<Long>> time_jogadores;

	public DesafioMeuTimeApplication() {
		times = new HashMap<>();
		jogadores = new HashMap<>();
		time_jogadores = new HashMap<>();
	}

	@Desafio("incluirTime")
	public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
		if (this.times.containsKey(id)) {
			throw new IdentificadorUtilizadoException();
		}

		Time novoTime = new Time(id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario);
		this.times.put(id, novoTime);
		this.time_jogadores.put(id, new ArrayList<>());
	}

	@Desafio("incluirJogador")
	public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {
		if (this.jogadores.containsKey(id)) {
			throw new IdentificadorUtilizadoException();
		}

		Time time = this.times.get(idTime);
		if (time == null) {
			throw new TimeNaoEncontradoException();
		}

		Jogador novoJogador = new Jogador(id, idTime, nome, dataNascimento, nivelHabilidade, salario);

		this.jogadores.put(id, novoJogador);
		this.time_jogadores.get(idTime).add(id);

		if (time.getIdMelhorJogador() != null) {
			Jogador melhor = this.jogadores.get(time.getIdMelhorJogador());
			if (melhor.getNivelHabilidade() < nivelHabilidade) {
				time.setIdMelhorJogador(id);
			} else if (melhor.getNivelHabilidade() == nivelHabilidade) {
				if (id < melhor.getId()) {
					time.setIdMelhorJogador(id);
				}
			}
		} else {
			time.setIdMelhorJogador(id);
		}

		if (time.getIdMaisVelho() != null) {
			Jogador maisVelho = this.jogadores.get(time.getIdMaisVelho());

			if (maisVelho.getDataNascimento().isAfter(dataNascimento)) {
				time.setIdMaisVelho(id);
			} else if (maisVelho.getDataNascimento().isEqual(dataNascimento)) {
				if (maisVelho.getId() > id) {
					time.setIdMaisVelho(id);
				}
			}
		} else {
			time.setIdMaisVelho(id);
		}

		if (time.getIdMaiorSalario() != null) {
			Jogador maiorSalario = this.jogadores.get(time.getIdMaiorSalario());
			if (maiorSalario.getSalario().compareTo(salario) == -1) {
				time.setIdMaiorSalario(id);
			} else if (maiorSalario.getSalario().compareTo(salario) == 0) {
				if (id < maiorSalario.getId()) {
					time.setIdMaiorSalario(id);
				}
			}
		} else {
			time.setIdMaiorSalario(id);
		}
	}

	@Desafio("definirCapitao")
	public void definirCapitao(Long idJogador) {
        Jogador jogador = this.jogadores.get(idJogador);

		if (jogador == null) {
			throw new JogadorNaoEncontradoException();
		}

		this.times.get(jogador.getIdTime()).setCapitao(idJogador);
	}

	@Desafio("buscarCapitaoDoTime")
	public Long buscarCapitaoDoTime(Long idTime) {
	    Time time = this.times.get(idTime);

		if (time == null) {
			throw new TimeNaoEncontradoException();
		}

		if (time.getCapitao() == null) {
			throw new CapitaoNaoInformadoException();
		}

		return time.getCapitao();
	}

	@Desafio("buscarNomeJogador")
	public String buscarNomeJogador(Long idJogador) {
		if (!this.jogadores.containsKey(idJogador)) {
			throw new JogadorNaoEncontradoException();
		}

		Jogador jogadorEncontrado = this.jogadores.get(idJogador);

		return jogadorEncontrado.getNome();
	}

	@Desafio("buscarNomeTime")
	public String buscarNomeTime(Long idTime) {
		if (!this.times.containsKey(idTime)) {
			throw new TimeNaoEncontradoException();
		}

		Time timeEncontrado = this.times.get(idTime);

		return timeEncontrado.getNome();
	}

	@Desafio("buscarJogadoresDoTime")
	public List<Long> buscarJogadoresDoTime(Long idTime) {
		if (!this.times.containsKey(idTime)) {
			throw new TimeNaoEncontradoException();
		}

		List<Long> idJogadores = this.time_jogadores.get(idTime);

		Collections.sort(idJogadores);

		return idJogadores;
	}

	@Desafio("buscarMelhorJogadorDoTime")
	public Long buscarMelhorJogadorDoTime(Long idTime) {
	    Time time = this.times.get(idTime);

		if (time == null) {
			throw new TimeNaoEncontradoException();
		}

		if (time.getIdMelhorJogador() == null) {
			throw new JogadorNaoEncontradoException();
		}

		return time.getIdMelhorJogador();
	}

	@Desafio("buscarJogadorMaisVelho")
	public Long buscarJogadorMaisVelho(Long idTime) {
        Time time = this.times.get(idTime);

        if (time == null) {
            throw new TimeNaoEncontradoException();
        }

        if (time.getIdMaisVelho() == null) {
            throw new JogadorNaoEncontradoException();
        }

        return time.getIdMaisVelho();
	}

	@Desafio("buscarTimes")
	public List<Long> buscarTimes() {
		List<Long> times = new ArrayList<>(this.times.keySet());
		Collections.sort(times);

		return times;
	}

	@Desafio("buscarJogadorMaiorSalario")
	public Long buscarJogadorMaiorSalario(Long idTime) {
        Time time = this.times.get(idTime);

        if (time == null) {
            throw new TimeNaoEncontradoException();
        }

        if (time.getIdMaiorSalario() == null) {
            throw new JogadorNaoEncontradoException();
        }

        return time.getIdMaiorSalario();
	}

	@Desafio("buscarSalarioDoJogador")
	public BigDecimal buscarSalarioDoJogador(Long idJogador) {
		if (!this.jogadores.containsKey(idJogador)) {
			throw new JogadorNaoEncontradoException();
		}

		return this.jogadores.get(idJogador).getSalario();
	}

	@Desafio("buscarTopJogadores")
	public List<Long> buscarTopJogadores(Integer top) {
		if (this.jogadores.size() == 0) {
			return new ArrayList<>();
		}

		top = top > this.jogadores.size() ? this.jogadores.size() : top;

		List<Long> topJogadores = new ArrayList<>();
		List<Jogador> players = new ArrayList<>(this.jogadores.values());

		Collections.sort(players, (o1, o2) -> {
			int nivelHabilidade = o2.getNivelHabilidade().compareTo(o1.getNivelHabilidade());

			if (nivelHabilidade != 0) {
				return nivelHabilidade;
			}

			return o1.getId().compareTo(o2.getId());
		});
		players.subList(0, top).forEach(jogador -> topJogadores.add(jogador.getId()));

		return topJogadores;
	}

	@Desafio("buscarCorCamisaTimeDeFora")
	public String buscarCorCamisaTimeDeFora(Long timeDaCasa, Long timeDeFora) {
		if (!this.times.containsKey(timeDaCasa) || !this.times.containsKey(timeDeFora)) {
			throw new TimeNaoEncontradoException();
		}

		Time casa = this.times.get(timeDaCasa);
		Time fora = this.times.get(timeDeFora);

		if (casa.getCorUniformePrincipal().equals(fora.getCorUniformePrincipal())) {
			return fora.getCorUniformeSecundario();
		}

		return fora.getCorUniformePrincipal();
	}

}
