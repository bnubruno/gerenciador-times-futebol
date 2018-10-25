package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Jogador implements Comparable<Jogador> {
    Long id, idTime;
    String nome;
    LocalDate dataNascimento;
    Integer nivelHabilidade;
    BigDecimal salario;
    Boolean capitao;

    Jogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {
        this.id = id;
        this.idTime = idTime;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.nivelHabilidade = nivelHabilidade;
        this.salario = salario;
        this.capitao = false;
    }

    @Override
    public String toString() {
        return "Jogador{" +
                "id=" + id +
                ", nivelHabilidade=" + nivelHabilidade +
                '}' + "\n";
    }

    @Override
    public int compareTo(Jogador outroJogador) {
        if (this.nivelHabilidade > outroJogador.getNivelHabilidade()) {
            return -1;
        }
        if (this.nivelHabilidade < outroJogador.getNivelHabilidade()) {
            return 1;
        }
        if (this.nivelHabilidade.equals(outroJogador.getNivelHabilidade())) {
            if (this.id < outroJogador.getId()) {
                return -1;
            } else {
                return 1;
            }
        }
        return 0;
    }


    public Long getId() {
        return id;
    }

    public Long getIdTime() {
        return idTime;
    }

    public String getNome() {
        return nome;
    }

    public Integer getNivelHabilidade() {
        return nivelHabilidade;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public Boolean getCapitao() {
        return capitao;
    }

    public void setCapitao(Boolean capitao) {
        this.capitao = capitao;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setNivelHabilidade(Integer nivelHabilidade) {
        this.nivelHabilidade = nivelHabilidade;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setIdTime(Long idTime) {
        this.idTime = idTime;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }
}
