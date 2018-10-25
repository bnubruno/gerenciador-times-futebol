package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Jogador {
	private Long idTime;
	private String nome;
	private LocalDate dataNascimento;
	private Integer nivelHabilidade;
	private BigDecimal salario;

	public Jogador(Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario){
		this.idTime = idTime;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.nivelHabilidade = nivelHabilidade;
		this.salario = salario;
	}

	public Long getIdTime(){
		return this.idTime;
	}

	public String getNome(){
		return this.nome;
	}

	public LocalDate getDataNascimento(){
		return this.dataNascimento;
	}

	public Integer getNivelHabilidade(){
		return this.nivelHabilidade;
	}

	public BigDecimal getSalario(){
		return this.salario;
	}
}