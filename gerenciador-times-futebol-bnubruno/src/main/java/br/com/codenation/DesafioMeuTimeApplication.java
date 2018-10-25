package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.codenation.desafio.annotation.Desafio;
import br.com.codenation.desafio.app.MeuTimeInterface;
import br.com.codenation.desafio.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;

public class DesafioMeuTimeApplication implements MeuTimeInterface {

	private List<Jogador> jogadores = new ArrayList<>();
	private List<Time> times = new ArrayList<>();

	@Desafio("incluirTime")
	public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal,
			String corUniformeSecundario) {

		if (buscarTimePorId(id).isPresent()) {
			throw new IdentificadorUtilizadoException();
		}
		this.times.add(new Time(id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario));
	}

	@Desafio("incluirJogador")
	public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade,
			BigDecimal salario) {
		buscarTimePorId(idTime).orElseThrow(() -> new TimeNaoEncontradoException());
		if (buscarJogadorPorId(id).isPresent()) {
			throw new IdentificadorUtilizadoException();
		}
		jogadores.add(new Jogador(id, idTime, nome, dataNascimento, nivelHabilidade, salario));
	}

	@Desafio("definirCapitao")
	public void definirCapitao(Long idJogador) {
		Jogador jogador = buscarJogadorPorId(idJogador).orElseThrow(() -> new JogadorNaoEncontradoException());
		Time time = buscarTimePorId(jogador.getIdTime()).get();
		time.setIdJogadorCapitao(jogador.getId());
	}

	@Desafio("buscarCapitaoDoTime")
	public Long buscarCapitaoDoTime(Long idTime) {
		Time time = buscarTimePorId(idTime).orElseThrow(() -> new TimeNaoEncontradoException());
		return time.getIdJogadorCapitao().orElseThrow(() -> new CapitaoNaoInformadoException());
	}

	@Desafio("buscarNomeJogador")
	public String buscarNomeJogador(Long idJogador) {
		return buscarJogadorPorId(idJogador).map(j -> j.getNome())
				.orElseThrow(() -> new JogadorNaoEncontradoException());
	}

	@Desafio("buscarNomeTime")
	public String buscarNomeTime(Long idTime) {
		return buscarTimePorId(idTime).map(t -> t.getNome()).orElseThrow(() -> new TimeNaoEncontradoException());
	}

	@Desafio("buscarJogadoresDoTime")
	public List<Long> buscarJogadoresDoTime(Long idTime) {
		buscarTimePorId(idTime).orElseThrow(() -> new TimeNaoEncontradoException());
		return jogadores.stream().filter(j -> j.getIdTime().equals(idTime)).map(j -> j.getId())
				.collect(Collectors.toList());
	}

	@Desafio("buscarMelhorJogadorDoTime")
	public Long buscarMelhorJogadorDoTime(Long idTime) {
		Comparator<Jogador> comparator = Comparator.comparing(j -> j.getHabilidade());
		comparator = comparator.reversed();
		comparator = comparator.thenComparing(j -> j.getId());

		buscarTimePorId(idTime).orElseThrow(() -> new TimeNaoEncontradoException());
		return jogadores.stream().filter(j -> j.getIdTime().equals(idTime)).sorted(comparator).limit(1)
				.map(j -> j.getId()).findFirst().orElseGet(null);
	}

	@Desafio("buscarJogadorMaisVelho")
	public Long buscarJogadorMaisVelho(Long idTime) {
		Comparator<Jogador> comparator = Comparator.comparing(j -> j.getDataNascimento());
		comparator = comparator.thenComparing(j -> j.getId());

		buscarTimePorId(idTime).orElseThrow(() -> new TimeNaoEncontradoException());
		return jogadores.stream().filter(j -> j.getIdTime().equals(idTime)).sorted(comparator).limit(1)
				.map(j -> j.getId()).findFirst().orElseGet(null);
	}

	@Desafio("buscarTimes")
	public List<Long> buscarTimes() {
		return times.stream().map(t -> t.getId()).collect(Collectors.toList());
	}

	@Desafio("buscarJogadorMaiorSalario")
	public Long buscarJogadorMaiorSalario(Long idTime) {
		Comparator<Jogador> comparator = Comparator.comparing(j -> j.getSalario());
		comparator = comparator.reversed();
		comparator = comparator.thenComparing(j -> j.getId());

		buscarTimePorId(idTime).orElseThrow(() -> new TimeNaoEncontradoException());
		return jogadores.stream().filter(j -> j.getIdTime().equals(idTime)).sorted(comparator).map(j -> j.getId())
				.findFirst().orElse(null);
	}

	@Desafio("buscarSalarioDoJogador")
	public BigDecimal buscarSalarioDoJogador(Long idJogador) {
		return buscarJogadorPorId(idJogador).map(j -> j.getSalario())
				.orElseThrow(() -> new JogadorNaoEncontradoException());
	}

	@Desafio("buscarTopJogadores")
	public List<Long> buscarTopJogadores(Integer top) {
		Comparator<Jogador> comparator = Comparator.comparing(j -> j.getHabilidade());
		comparator = comparator.reversed();
		comparator = comparator.thenComparing(j -> j.getId());

		return jogadores.stream().sorted(comparator).limit(top).map(j -> j.getId()).collect(Collectors.toList());
	}

	@Desafio("buscarCorCamisaTimeDeFora")
	public String buscarCorCamisaTimeDeFora(Long timeDaCasa, Long timeDeFora) {
		Time casa = buscarTimePorId(timeDaCasa).orElseThrow(() -> new TimeNaoEncontradoException());
		Time fora = buscarTimePorId(timeDeFora).orElseThrow(() -> new TimeNaoEncontradoException());

		if (casa.getCorCamisaPrincipal().equals(fora.getCorCamisaPrincipal())) {
			return fora.getCorCamisaSecundaria();
		}
		return fora.getCorCamisaPrincipal();
	}

	private Optional<Jogador> buscarJogadorPorId(Long idJogador) {
		return jogadores.stream().filter(j -> j.getId().equals(idJogador)).findFirst();
	}

	private Optional<Time> buscarTimePorId(Long idTime) {
		return times.stream().filter(t -> t.getId().equals(idTime)).findFirst();
	}

	public class Jogador {

		private Long id;
		private Long idTime;
		private String nome;
		private LocalDate dataNascimento;
		private Integer habilidade;
		private BigDecimal salario;

		public Jogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer habilidade,
				BigDecimal salario) {
			this.id = id;
			this.idTime = idTime;
			this.nome = nome;
			this.dataNascimento = dataNascimento;
			this.habilidade = habilidade;
			this.salario = salario;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public LocalDate getDataNascimento() {
			return dataNascimento;
		}

		public void setDataNascimento(LocalDate dataNascimento) {
			this.dataNascimento = dataNascimento;
		}

		public Integer getHabilidade() {
			return habilidade;
		}

		public void setHabilidade(Integer habilidade) {
			this.habilidade = habilidade;
		}

		public Long getIdTime() {
			return idTime;
		}

		public BigDecimal getSalario() {
			return salario;
		}

		public void setSalario(BigDecimal salario) {
			this.salario = salario;
		}
	}

	public class Time {

		private Long id;
		private String nome;
		private LocalDate dataCriacao;
		private String corCamisaPrincipal;
		private String corCamisaSecundaria;
		private Long idJogadorCapitao;

		public Time(Long id, String nome, LocalDate dataCriacao, String corCamisaPrincipal,
				String corCamisaSecundaria) {
			this.id = id;
			this.nome = nome;
			this.dataCriacao = dataCriacao;
			this.corCamisaPrincipal = corCamisaPrincipal;
			this.corCamisaSecundaria = corCamisaSecundaria;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public LocalDate getDataCriacao() {
			return dataCriacao;
		}

		public void setDataCriacao(LocalDate dataCriacao) {
			this.dataCriacao = dataCriacao;
		}

		public String getCorCamisaPrincipal() {
			return corCamisaPrincipal;
		}

		public void setCorCamisaPrincipal(String corCamisaPrincipal) {
			this.corCamisaPrincipal = corCamisaPrincipal;
		}

		public String getCorCamisaSecundaria() {
			return corCamisaSecundaria;
		}

		public void setCorCamisaSecundaria(String corCamisaSecundaria) {
			this.corCamisaSecundaria = corCamisaSecundaria;
		}

		public Optional<Long> getIdJogadorCapitao() {
			return Optional.ofNullable(idJogadorCapitao);
		}

		public void setIdJogadorCapitao(Long idJogadorCapitao) {
			this.idJogadorCapitao = idJogadorCapitao;
		}
	}

}
