package br.com.codenation;

import java.time.LocalDate;
import java.math.BigDecimal;

public class Jogador {
	private Long id;
	private Long idTime;
	private String nome;
	private LocalDate dataNascimento;
	private Integer nivelHabilidade;
	private BigDecimal salario;

	Jogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade,
			BigDecimal salario) {
		super();
		this.id = id;
		this.idTime = idTime;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.nivelHabilidade = nivelHabilidade;
		this.salario = salario;
	}
	
	Long getId() {
		return id;
	}

	void setId(Long id) {
		this.id = id;
	}

	Long getIdTime() {
		return idTime;
	}

	void setIdTime(Long idTime) {
		this.idTime = idTime;
	}

	String getNome() {
		return nome;
	}

	void setNome(String nome) {
		this.nome = nome;
	}

	LocalDate getDataNascimento() {
		return dataNascimento;
	}

	void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	Integer getNivelHabilidade() {
		return nivelHabilidade;
	}

	void setNivelHabilidade(Integer nivelHabilidade) {
		this.nivelHabilidade = nivelHabilidade;
	}

	BigDecimal getSalario() {
		return salario;
	}

	void setSalario(BigDecimal salario) {
		this.salario = salario;
	}
}
