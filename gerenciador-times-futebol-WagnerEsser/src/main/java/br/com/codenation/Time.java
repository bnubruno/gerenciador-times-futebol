package br.com.codenation;

import java.time.LocalDate;

public class Time {
    Long id;
    String nome, corUniformePrincipal, corUniformeSecundario;
    LocalDate dataCriacao;
    Long capitao;

    Time(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
        this.id = id;
        this.nome = nome;
        this.dataCriacao = dataCriacao;
        this.corUniformePrincipal = corUniformePrincipal;
        this.corUniformeSecundario = corUniformeSecundario;
        this.capitao = null;
    }

    @Override
    public String toString() {
        return "Time{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", corUniformePrincipal='" + corUniformePrincipal + '\'' +
                ", corUniformeSecundario='" + corUniformeSecundario + '\'' +
                ", dataCriacao=" + dataCriacao +
                '}' + "\n";
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCorUniformePrincipal() {
        return corUniformePrincipal;
    }

    public String getCorUniformeSecundario() {
        return corUniformeSecundario;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public Long getCapitao() {
        return capitao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public void setCapitao(Long capitao) {
        this.capitao = capitao;
    }

    public void setCorUniformeSecundario(String corUniformeSecundario) {
        this.corUniformeSecundario = corUniformeSecundario;
    }

    public void setCorUniformePrincipal(String corUniformePrincipal) {
        this.corUniformePrincipal = corUniformePrincipal;
    }

}
