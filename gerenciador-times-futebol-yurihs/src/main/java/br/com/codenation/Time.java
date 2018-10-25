package br.com.codenation;

import java.time.LocalDate;

public class Time {
	private String nome;
	private LocalDate dataCriacao;
	private String corUniformePrincipal;
	private String corUniformeSecundario;
	private Long idCapitao;

	public Time(String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario){
		this.nome = nome;
		this.dataCriacao = dataCriacao;
		this.corUniformePrincipal = corUniformePrincipal;
		this.corUniformeSecundario = corUniformeSecundario;
	}

	public String getNome(){
		return this.nome;
	}

	public Long getIdCapitao(){
		return this.idCapitao;
	}

	public void setCapitao(Long idJogador){
		this.idCapitao = idJogador;
	}

	public String getCorUniformePrincipal(){
		return this.corUniformePrincipal;
	}

	public String getCorUniformeSecundario(){
		return this.corUniformeSecundario;
	}
}