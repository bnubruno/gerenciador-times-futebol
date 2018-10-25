package br.com.codenation.model;

import java.time.LocalDate;

public class Time {

	private Long id;
	private String nome;
	private LocalDate dataCriacao;
	private String corUniformePrincipal;
	private String corUniformeSecundario;
	private Long idCapitao;
	
	/*public Time() {
		id = this.getId();
		nome = this.getNome();
		dataCriacao = this.getDataCriacao();
		corUniformePrincipal = this.getCorUniformePrincipal();
		corUniformeSecundario = this.getCorUniformeSecundario();
		idCapitao = null;
	}*/
	/*public int compareTo(Time outroTime) {
	        if (this.id < outroTime.id) {
	            return -1;
	        }
	        if (this.id > outroTime.id) {
	            return 1;
	        }
	        return 0;
	}*/

	public Long getIdCapitao() {
		return idCapitao;
	}

	public void setIdCapitao(Long idCapitao) {
		this.idCapitao = idCapitao;
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

	public String getCorUniformePrincipal() {
		return corUniformePrincipal;
	}

	public void setCorUniformePrincipal(String corUniformePrincipal) {
		this.corUniformePrincipal = corUniformePrincipal;
	}

	public String getCorUniformeSecundario() {
		return corUniformeSecundario;
	}

	public void setCorUniformeSecundario(String corUniformeSecundario) {
		this.corUniformeSecundario = corUniformeSecundario;
	}

}
