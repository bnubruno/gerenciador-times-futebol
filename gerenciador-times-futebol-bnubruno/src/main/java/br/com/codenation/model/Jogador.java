package br.com.codenation.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

public class Jogador {

	private Long id;
	private String nome;
	private Long idTime;
	private LocalDate dataNascimento;
	private Integer nivelHabilidade;
	private BigDecimal salarioJogador;

	private Time time;

	public Jogador() {
		super();
	}

	public Long getId() {
		return this.id;
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

	public Long getIdTime() {
		return idTime;
	}

	public void setIdTime(Long idTime) {
		this.idTime = idTime;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Integer getNivelHabilidade() {
		return nivelHabilidade;
	}

	public void setNivelHabilidade(Integer nivelHabilidade) {
		this.nivelHabilidade = nivelHabilidade;
	}

	public BigDecimal getSalarioJogador() {
		return salarioJogador;
	}

	public void setSalarioJogador(BigDecimal salarioJogador) {
		this.salarioJogador = salarioJogador;
	}

	public Optional<Time> getTime() {
		return Optional.ofNullable(time);
	}

	public void setTime(Time time) {
		this.time = time;
	}

}