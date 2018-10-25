package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.com.codenation.desafio.annotation.Desafio;
import br.com.codenation.desafio.app.MeuTimeInterface;
import br.com.codenation.desafio.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;
import br.com.codenation.desafio.model.Jogador;
import br.com.codenation.desafio.model.Time;

public class DesafioMeuTimeApplication implements MeuTimeInterface {

	private List<Time> times;
	public List<Jogador> jogadores;

	public DesafioMeuTimeApplication() {
		times = new ArrayList<>();
		jogadores = new ArrayList<>();
	}

	@Desafio("incluirTime")
	public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {

		if(verificaIdTimeExiste(id)) {
			throw new IdentificadorUtilizadoException();

		} else {
			Time time = new Time(id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario, null);
			this.times.add(time);
		}

	}

	@Desafio("incluirJogador")
	public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {

		if(verificaIdJogadorExiste(id)) {
			throw new IdentificadorUtilizadoException();

		} else if (!verificaIdTimeExiste(idTime)) {
			throw new TimeNaoEncontradoException();

		} else {
			Jogador jogador = new Jogador(id, idTime, nome, dataNascimento, nivelHabilidade, salario);
			this.jogadores.add(jogador);
		}

	}

	@Desafio("definirCapitao")
	public void definirCapitao(Long idJogador) {

		if(!verificaIdJogadorExiste(idJogador)) {
			throw new JogadorNaoEncontradoException();

		} else {
			Jogador jogador = buscaJogadorPorId(idJogador);
			Time timeDoJogador = buscaTimePorId(jogador.getIdTime());

//			if(timeDoJogador == null) {
//				throw new TimeNaoEncontradoException();
//			}

			timeDoJogador.setIdCapitao(jogador.getId());
		}
	}

	@Desafio("buscarCapitaoDoTime")
	public Long buscarCapitaoDoTime(Long idTime) {

		Time time = buscaTimePorId(idTime);

		if(!verificaIdTimeExiste(idTime)) {
			throw new TimeNaoEncontradoException();

		} else if(time.getIdCapitao() == null) {
			throw new CapitaoNaoInformadoException();

		} else {
			return time.getIdCapitao();
		}
	}

	@Desafio("buscarNomeJogador")
	public String buscarNomeJogador(Long idJogador) {

		if(!verificaIdJogadorExiste(idJogador)) {
			throw new JogadorNaoEncontradoException();
		} else {
			Jogador jogador = buscaJogadorPorId(idJogador);
			return jogador.getNome();
		}
	}

	@Desafio("buscarNomeTime")
	public String buscarNomeTime(Long idTime) {

		if(!verificaIdTimeExiste(idTime)) {
			throw new TimeNaoEncontradoException();
		} else {
			Time time = buscaTimePorId(idTime);
			return time.getNome();
		}
	}

	@Desafio("buscarJogadoresDoTime")
	public List<Long> buscarJogadoresDoTime(Long idTime) {

		if(!verificaIdTimeExiste(idTime)) {
			throw new TimeNaoEncontradoException();
		} else {
			List<Jogador> jogadores = buscaJogadoresDoTime(idTime);
			jogadores = ordenaPorIdJogador(jogadores);

			List<Long> identificadores = new ArrayList<>();
			for(Jogador jogadorFor : jogadores) {
				identificadores.add(jogadorFor.getId());
			}

			return identificadores;
		}
	}

	@Desafio("buscarMelhorJogadorDoTime")
	public Long buscarMelhorJogadorDoTime(Long idTime) {

		if(!verificaIdTimeExiste(idTime)) {
			throw new TimeNaoEncontradoException();
		} else {
			List<Jogador> jogadores = buscaJogadoresDoTime(idTime);
			jogadores = ordenaPorNivelHabilidadeIdentificador(jogadores);

			return jogadores.get(0).getId();

		}
	}

	@Desafio("buscarJogadorMaisVelho")
	public Long buscarJogadorMaisVelho(Long idTime) {

		if(!verificaIdTimeExiste(idTime)) {
			throw new TimeNaoEncontradoException();
		} else {
			List<Jogador> jogadores = buscaJogadoresDoTime(idTime);
			jogadores = ordenaPorIdade(jogadores);

			return jogadores.get(0).getId();
		}
	}

	@Desafio("buscarTimes")
	public List<Long> buscarTimes() {

		List<Long> identificadores = new ArrayList<>();
		List<Time> timesOrdenadorPorId = ordenaPorIdTime(this.times);

		for(Time timeFor : timesOrdenadorPorId) {
			identificadores.add(timeFor.getId());
		}

		return identificadores;
	}

	@Desafio("buscarJogadorMaiorSalario")
	public Long buscarJogadorMaiorSalario(Long idTime) {

		if(!verificaIdTimeExiste(idTime)) {
			throw new TimeNaoEncontradoException();
		} else {
			List<Jogador> jogadores = buscaJogadoresDoTime(idTime);
			jogadores = ordenaPorSalario(jogadores);

			return jogadores.get(0).getId();
		}
	}

	@Desafio("buscarSalarioDoJogador")
	public BigDecimal buscarSalarioDoJogador(Long idJogador) {

		if(!verificaIdJogadorExiste(idJogador)) {
			throw new JogadorNaoEncontradoException();
		} else {
			Jogador jogador = buscaJogadorPorId(idJogador);
			return jogador.getSalario();
		}
	}

	@Desafio("buscarTopJogadores")
	public List<Long> buscarTopJogadores(Integer top) {

		int i = 0;

		List<Long> identificadores = new ArrayList<>();

		List<Jogador> jogadores = ordenaPorNivelHabilidadeIdentificador(this.jogadores);

		if(top == null) {
			for(Jogador jogadorFor : jogadores) {
				identificadores.add(jogadorFor.getId());
			}

		} else {
			for(Jogador jogadorFor : jogadores) {
				i++;
				identificadores.add(jogadorFor.getId());
				if(i == top.intValue()) {
					return identificadores;
				}
			}
		}

		return identificadores;
	}

	@Desafio("buscarCorCamisaTimeDeFora")
	public String buscarCorCamisaTimeDeFora(Long idTimeDaCasa, Long idTimeDeFora) {

		Time timeDaCasa = buscaTimePorId(idTimeDaCasa);
		Time timeDeFora = buscaTimePorId(idTimeDeFora);

		if(!verificaIdTimeExiste(idTimeDaCasa)) {
			throw new TimeNaoEncontradoException();
		}

		if(!verificaIdTimeExiste(idTimeDeFora)) {
			throw new TimeNaoEncontradoException();
		}

		if(timeDaCasa.getCorUniformePrincipal().equals(timeDeFora.getCorUniformePrincipal())) {
			return timeDeFora.getCorUniformeSecundario();
		} else {
			return timeDeFora.getCorUniformePrincipal();
		}
	}


	private boolean verificaIdJogadorExiste(Long id) {
		boolean existe = false;

		for(Jogador jogador :  this.jogadores) {
			if(jogador.getId().equals(id)) {
				existe = true;
			}
		}

		return existe;
	}

	private Jogador buscaJogadorPorId(Long id) {
		for(Jogador jogadorFor : this.jogadores) {
			if(jogadorFor.getId().equals(id)) {
				return jogadorFor;
			}
		}

		return null;
	}

	private List<Jogador> buscaJogadoresDoTime(Long idTime) {
		List<Jogador> jogadoresDoTime = new ArrayList<>();

		for(Jogador jogadorFor : this.jogadores) {
			if(jogadorFor.getIdTime().equals(idTime)) {
				jogadoresDoTime.add(jogadorFor);
			}
		}

		return jogadoresDoTime;
	}

	private List<Jogador> ordenaPorIdJogador(List<Jogador> jogadoresOrdenados) {

		Collections.sort(jogadoresOrdenados, new Comparator<Jogador>() {
					public int compare(Jogador j1, Jogador j2) {
						return j1.getId().compareTo(j2.getId());
					}
				}
		);

		return jogadoresOrdenados;
	}

	private List<Jogador> ordenaPorIdade(List<Jogador> jogadoresOrdenados) {

		Collections.sort(jogadoresOrdenados, new Comparator<Jogador>() {
					public int compare(Jogador j1, Jogador j2) {
						int retorno = j1.getDataNascimento().compareTo(j2.getDataNascimento());
						if(retorno == 0) {
							return j1.getId().compareTo(j2.getId());
						} else {
							return j1.getDataNascimento().compareTo(j2.getDataNascimento());
						}
					}
				}
		);

		return jogadoresOrdenados;
	}

	private List<Jogador> ordenaPorSalario(List<Jogador> jogadoresOrdenados) {

		Collections.sort(jogadoresOrdenados, new Comparator<Jogador>() {
					public int compare(Jogador j1, Jogador j2) {
						int retorno = j2.getSalario().compareTo(j1.getSalario());
						if(retorno == 0) {
							return j1.getId().compareTo(j2.getId());
						} else {
							return j2.getSalario().compareTo(j1.getSalario());
						}
					}
				}
		);

		return jogadoresOrdenados;
	}

	private List<Jogador> ordenaPorNivelHabilidadeIdentificador(List<Jogador> jogadoresOrdenados) {

		Collections.sort(jogadoresOrdenados, new Comparator<Jogador>() {
					public int compare(Jogador j1, Jogador j2) {
						int retorno = j2.getNivelHabilidade().compareTo(j1.getNivelHabilidade());
						if(retorno == 0) {
							return j1.getId().compareTo(j2.getId());
						} else {
							return j2.getNivelHabilidade().compareTo(j1.getNivelHabilidade());
						}
					}
				}
		);

		return jogadoresOrdenados;
	}

	private boolean verificaIdTimeExiste(Long id) {
		boolean existe = false;

		for(Time time : this.times) {
			if(time.getId().equals(id)) {
				existe = true;
			}
		}

		return existe;
	}

	private Time buscaTimePorId(Long id) {
		for(Time timeFor : this.times) {
			if(timeFor.getId().equals(id)) {
				return timeFor;
			}
		}

		return null;
	}

	private List<Time> ordenaPorIdTime(List<Time> timesOrdenados) {

		Collections.sort(timesOrdenados, new Comparator<Time>() {
					public int compare(Time t1, Time t2) {
						return t1.getId().compareTo(t2.getId());
					}
				}
		);

		return timesOrdenados;
	}
}
