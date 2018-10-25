package br.com.codenation;

import java.time.LocalDate;

public class Time {

    private Long id;
    private String nome;
    private LocalDate dataCriacao;
    private String corUniformePrincipal;
    private String corUniformeSecundario;
    private Long jogadorMaisVelho;
    private Long jogadorMaisSalario;
    private Long jogadorMaisHabilidoso;
    private Long idCapitao;

    public Time(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal,
                String corUniformeSecundario, Long jogadorMaisVelho, Long jogadorMaisSalario, Long jogadorMaisHabilidoso,
                Long idCapitao) {
        this.id = id;
        this.nome = nome;
        this.dataCriacao = dataCriacao;
        this.corUniformePrincipal = corUniformePrincipal;
        this.corUniformeSecundario = corUniformeSecundario;
        this.jogadorMaisVelho = jogadorMaisVelho;
        this.jogadorMaisSalario = jogadorMaisSalario;
        this.jogadorMaisHabilidoso = jogadorMaisHabilidoso;
        this.idCapitao = idCapitao;
    }

    public Long getIdCapitao() {
        return idCapitao;
    }

    public void setIdCapitao(Long idCapitao) {
        this.idCapitao = idCapitao;
    }

    public Long getJogadorMaisVelho() {
        return jogadorMaisVelho;
    }

    public void setJogadorMaisVelho(Long jogadorMaisVelho) {
        this.jogadorMaisVelho = jogadorMaisVelho;
    }

    public Long getJogadorMaisSalario() {
        return jogadorMaisSalario;
    }

    public void setJogadorMaisSalario(Long jogadorMaisSalario) {
        this.jogadorMaisSalario = jogadorMaisSalario;
    }

    public Long getJogadorMaisHabilidoso() {
        return jogadorMaisHabilidoso;
    }

    public void setJogadorMaisHabilidoso(Long jogadorMaisHabilidoso) {
        this.jogadorMaisHabilidoso = jogadorMaisHabilidoso;
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