package br.com.codenation.model;

import java.math.BigDecimal;
import java.time.LocalDate;


public class Jogador implements Comparable<Jogador> {

	private Long id;
	private Long idTime;
	private String nome;
	private LocalDate dataNascimento;
	private Integer nivelHabilidade;
	private BigDecimal salario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdTime() {
		return idTime;
	}

	public void setIdTime(Long idTime) {
		this.idTime = idTime;
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

	public Integer getNivelHabilidade() {
		return nivelHabilidade;
	}

	public void setNivelHabilidade(Integer nivelHabilidade) {
		this.nivelHabilidade = nivelHabilidade;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

	@Override
	public int compareTo(Jogador jog1) {
		if(this.nivelHabilidade < jog1.getNivelHabilidade()) {
			return 1;
		}
		
		if(this.nivelHabilidade > jog1.getNivelHabilidade()) {
			return -1;
		}
		
		return 0;
	}

}
