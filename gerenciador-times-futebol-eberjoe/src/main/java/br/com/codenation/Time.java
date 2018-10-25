package br.com.codenation;

import java.time.LocalDate;

public class Time {
	private Long _id;
	
	private String _nome;
	
	private LocalDate _dataCriacao;
	
	private String _corUniformePrincipal;
	
	private String _corUniformeSecundario;
	
	Time (Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
		this._id=id;
		this._nome=nome;
		this._dataCriacao=dataCriacao;
		this._corUniformePrincipal=corUniformePrincipal;
		this._corUniformeSecundario=corUniformeSecundario;
	}
	
	public Long get_id() {
		return _id;
	}

	public void set_id(Long _id) {
		this._id = _id;
	}

	public String get_nome() {
		return _nome;
	}

	public void set_nome(String _nome) {
		this._nome = _nome;
	}

	public LocalDate get_dataCriacao() {
		return _dataCriacao;
	}

	public void set_dataCriacao(LocalDate _dataCriacao) {
		this._dataCriacao = _dataCriacao;
	}

	public String get_corUniformePrincipal() {
		return _corUniformePrincipal;
	}

	public void set_corUniformePrincipal(String _corUniformePrincipal) {
		this._corUniformePrincipal = _corUniformePrincipal;
	}

	public String get_corUniformeSecundario() {
		return _corUniformeSecundario;
	}

	public void set_corUniformeSecundario(String _corUniformeSecundario) {
		this._corUniformeSecundario = _corUniformeSecundario;
	}

}
