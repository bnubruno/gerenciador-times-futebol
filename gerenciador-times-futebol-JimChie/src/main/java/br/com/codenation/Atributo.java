package br.com.codenation;

import java.time.LocalDate;
import java.math.BigDecimal;

public class Atributo implements Comparable<Atributo> {
	public Long id;
	public Long idtime;
	public String nome;
	public LocalDate datanascimento;
	public Integer nivelhabilidade;
	public BigDecimal salario;
	
	@Override
	public int compareTo(Atributo outroAtributo) {
		if (this.nivelhabilidade > outroAtributo.nivelhabilidade) {
			return -1;
		}
		if (this.nivelhabilidade < outroAtributo.nivelhabilidade) {
			return 1;
		}
		if (this.nivelhabilidade == outroAtributo.nivelhabilidade) {
			if (this.id > outroAtributo.id ) {
				return 1;	
			}
			if (this.id < outroAtributo.id) {
				return -1;
			}
			return 0;
		}
		return 0;
	}
}
