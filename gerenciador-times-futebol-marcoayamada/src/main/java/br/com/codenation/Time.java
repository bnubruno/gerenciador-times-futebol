package br.com.codenation;

import java.time.LocalDate;

public class Time {

	//public Long id;
	public String nome;
	public LocalDate dataCriacao;
	public String corUniformePrincipal;
	public String corUniformeSecundario;
	public Long idCapitao;
	
   public Time(/*Long id, */String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
      //this.id = id;
      this.nome = nome;
      this.dataCriacao = dataCriacao;
      this.corUniformePrincipal = corUniformePrincipal;
      this.corUniformeSecundario = corUniformeSecundario;
   }
   

	
}
