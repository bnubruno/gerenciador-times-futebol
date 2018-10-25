package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Jogador {
	private Long _id;
	
	private Long _idTime;
	
	private String _nome;
	
	private LocalDate _dataNascimento;
	
	private Integer _nivelHabilidade;
	
	private BigDecimal _salario;
	
	private boolean _capitao;
	
	Jogador (Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {
		this._id=id;
		this._idTime=idTime;
		this._nome=nome;
		this._dataNascimento=dataNascimento;
		this._nivelHabilidade=nivelHabilidade;
		this._salario=salario;
	}

	public long get_id() {
		return _id;
	}

	public void set_id(Long _id) {
		this._id = _id;
	}

	public Long get_idTime() {
		return _idTime;
	}

	public void set_idTime(Long _idTime) {
		this._idTime = _idTime;
	}

	public String get_nome() {
		return _nome;
	}

	public void set_nome(String _nome) {
		this._nome = _nome;
	}

	public LocalDate get_dataNascimento() {
		return _dataNascimento;
	}

	public void set_dataNascimento(LocalDate _dataNascimento) {
		this._dataNascimento = _dataNascimento;
	}

	public Integer get_nivelHabilidade() {
		return _nivelHabilidade;
	}

	public void set_nivelHabilidade(Integer _nivelHabilidade) {
		this._nivelHabilidade = _nivelHabilidade;
	}

	public BigDecimal get_salario() {
		return _salario;
	}

	public void set_salario(BigDecimal _salario) {
		this._salario = _salario;
	}

	public Boolean get_capitao() {
		return _capitao;
	}

	public void set_capitao(Boolean _capitao) {
		this._capitao = _capitao;
	}
}
