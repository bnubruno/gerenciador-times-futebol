package br.com.codenation;

import java.time.LocalDate;
import java.util.Optional;

public class Time {
	private Long idTime;
	private String nome;
	private LocalDate dataCriacao;
	private String corUniformePrincipal;
	private String corUniformeSecundario;
	private Long jogadorCapitao;

	Time(Long idTime, String nome, LocalDate dataCriacao, String corUniformePrincipal,
			String corUniformeSecundario, Long jogadorCapitao) {
		super();
		this.idTime = idTime;
		this.nome = nome;
		this.dataCriacao = dataCriacao;
		this.corUniformePrincipal = corUniformePrincipal;
		this.corUniformeSecundario = corUniformeSecundario;
		this.jogadorCapitao = jogadorCapitao;
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

	LocalDate getDataCriacao() {
		return dataCriacao;
	}

	void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	String getCorUniformePrincipal() {
		return corUniformePrincipal;
	}

	void setCorUniformePrincipal(String corUniformePrincipal) {
		this.corUniformePrincipal = corUniformePrincipal;
	}

	String getCorUniformeSecundario() {
		return corUniformeSecundario;
	}

	void setCorUniformeSecundario(String corUniformeSecundario) {
		this.corUniformeSecundario = corUniformeSecundario;
	}

	Optional<Long> getJogadorCapitao() {
		return Optional.ofNullable(jogadorCapitao);
	}

	void setJogadorCapitao(Long jogadorCapitao) {
		this.jogadorCapitao = jogadorCapitao;
	}
	
	

}
