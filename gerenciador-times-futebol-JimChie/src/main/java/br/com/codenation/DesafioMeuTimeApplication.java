package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.com.codenation.desafio.annotation.Desafio;
import br.com.codenation.desafio.app.MeuTimeInterface;
import br.com.codenation.desafio.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;

public class DesafioMeuTimeApplication implements MeuTimeInterface {

	ArrayList<Time> times = new ArrayList<Time>();
	ArrayList<Long> idtime = new ArrayList<Long>();
	
	ArrayList<Atributo> jogadores = new ArrayList<Atributo>();
	ArrayList<Long> idjogador = new ArrayList<Long>();
			
	@Desafio("incluirTime")
	public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
		
		if (idtime.contains(id)) {
			throw new IdentificadorUtilizadoException();
		}
		else {
			Time time = new Time();
			time.id = id;
			time.nome = nome;
			time.datacriacao = dataCriacao;
			time.coruniformeprincipal = corUniformePrincipal;
			time.coruniformesecundario = corUniformeSecundario;
			time.capitao = null;
			times.add(time);
			idtime.add(id);
		}

	}

	@Desafio("incluirJogador")
	public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {

		if (idjogador.contains(id)) {
			throw new IdentificadorUtilizadoException();
		}
		else {
			if (idtime.contains(idTime)) {
				Atributo jogador = new Atributo();
				jogador.id = id;
				jogador.idtime = idTime;
				jogador.nome = nome;
				jogador.datanascimento = dataNascimento;
				jogador.nivelhabilidade = nivelHabilidade;
				jogador.salario = salario;
				jogadores.add(jogador);
				idjogador.add(id);
			}

			else {
				throw new TimeNaoEncontradoException();
			}
		}

	}

	@Desafio("definirCapitao")
	public void definirCapitao(Long idJogador) {

		if (idjogador.contains(idJogador)) {
			for (Atributo a : jogadores) {
				if (a.id == idJogador) {
					for (Time b : times) {
						if (b.id == a.idtime) {
							b.capitao = idJogador;
						}
					}
				}
			}
		}

		else {
			throw new JogadorNaoEncontradoException();
		}

	}
	
	@Desafio("buscarCapitaoDoTime")
	public Long buscarCapitaoDoTime(Long idTime) {
		
		Long capitao = null;
		
		if (idtime.contains(idTime)) {
			for (Time a : times) {
				if (a.id == idTime) {
					if (a.capitao == null) {
						throw new CapitaoNaoInformadoException();
					}
					else {
						capitao =  a.capitao;
					}
				}
			}
		}
		else {
			throw new TimeNaoEncontradoException();
		}
		return capitao;
	}

	@Desafio("buscarNomeJogador")
	public String buscarNomeJogador(Long idJogador) {
		
		String nomejogador = null;
		
		if (idjogador.contains(idJogador)) {
			for (Atributo a : jogadores) {
				if (a.id == idJogador) {
					nomejogador = a.nome;
				}
			}
		}

		else {
			throw new JogadorNaoEncontradoException();
		}
		return nomejogador;
	}

	@Desafio("buscarNomeTime")
	public String buscarNomeTime(Long idTime) {
		
		String nometime = null;
		
		if (idtime.contains(idTime)) {
			for (Time a : times) {
				if (a.id == idTime) {
					nometime = a.nome;
				}
			}
		}
		else {
			throw new TimeNaoEncontradoException();
		}
		return nometime;
	}

	@Desafio("buscarJogadoresDoTime")
	public List<Long> buscarJogadoresDoTime(Long idTime) {

		if (idtime.contains(idTime)) {
			List<Long> i = new ArrayList<Long>();
			for (Atributo a : jogadores) {
				if (a.idtime == idTime) {
					i.add(a.id);
				}
			}
			i.sort(Comparator.naturalOrder());
			return i;
		}
		else {
			throw new TimeNaoEncontradoException();
		}
	}

	@Desafio("buscarMelhorJogadorDoTime")
	public Long buscarMelhorJogadorDoTime(Long idTime) {

		if (idtime.contains(idTime)) {
			List<Atributo> i = new ArrayList<Atributo>();
			Atributo j = null;
			for (Atributo a : jogadores) {
				if (a.idtime == idTime) {
					j = new Atributo();
					j.id = a.id;
					j.nivelhabilidade = a.nivelhabilidade;
					i.add(j);
				}
			}
			Atributo k = new Atributo();
			k = i.get(1);
			int res = 0;
			for (Atributo a : i) {
				res = a.nivelhabilidade.compareTo(k.nivelhabilidade);
				if ( res == 1) {
					k.nivelhabilidade = a.nivelhabilidade;
					k.id = a.id;
				}
				else if (res == 0) {
					if (a.id < k.id) {
						k.id = a.id;
					}
				}
				
			}
			return k.id;
		}
		else {
			throw new TimeNaoEncontradoException();
		}
	}

	@Desafio("buscarJogadorMaisVelho")
	public Long buscarJogadorMaisVelho(Long idTime) {

		if (idtime.contains(idTime)) {
			List<Atributo> i = new ArrayList<Atributo>();
			Atributo j = null;
			for (Atributo a : jogadores) {
				if (a.idtime == idTime) {
					j = new Atributo();
					j.id = a.id;
					j.datanascimento = a.datanascimento;
					i.add(j);
				}
			}
			int res = 0;
			Atributo k = new Atributo();
			k = i.get(1);
			for (Atributo a : i) {
				res = a.datanascimento.compareTo(k.datanascimento);
				if ( res < 0) {
					k.datanascimento = a.datanascimento;
					k.id = a.id;
				}
				else if (res == 0) {
					if (a.id < k.id) {
						k.id = a.id;
					}
				}
				
			}
			return k.id;
		}
		else {
			throw new TimeNaoEncontradoException();
		}
	}

	@Desafio("buscarTimes")
	public List<Long> buscarTimes() {

		if (idtime.isEmpty()) {
			List<Long> i = new ArrayList<Long>();
			return i;
		}
		else {
			idtime.sort(Comparator.naturalOrder());
			return idtime;
		}
	}

	@Desafio("buscarJogadorMaiorSalario")
	public Long buscarJogadorMaiorSalario(Long idTime) {
		
		if (idtime.contains(idTime)) {
			List<Atributo> i = new ArrayList<Atributo>();
			Atributo j = null;
			for (Atributo a : jogadores) {
				if (a.idtime == idTime) {
					j = new Atributo();
					j.id = a.id;
					j.salario = a.salario;
					i.add(j);
				}
			}
			Atributo k = new Atributo();
			k = i.get(1);
			int res = 0;
			for (Atributo a : i) {
				res = a.salario.compareTo(k.salario);
				if ( res == 1) {
					k.salario = a.salario;
					k.id = a.id;
				}
				else if (res == 0) {
					if (a.id < k.id) {
						k.id = a.id;
					}
				}
				
			}
			return k.id;
		}
		else {
			throw new TimeNaoEncontradoException();
		}
	}

	@Desafio("buscarSalarioDoJogador")
	public BigDecimal buscarSalarioDoJogador(Long idJogador) {
		
		BigDecimal salario = BigDecimal.valueOf(0);
		if (idjogador.contains(idJogador)) {
			for (Atributo a : jogadores) {
				if (a.id == idJogador) {
					salario = a.salario;
				}
			}
		}
		else {
			throw new JogadorNaoEncontradoException();
		}
		return salario;
	}

	@Desafio("buscarTopJogadores")
	public List<Long> buscarTopJogadores(Integer top) {
		
		List<Long> top1 = new ArrayList<Long>();
		List<Long> top2 = new ArrayList<Long>();
		if (idjogador.isEmpty()) {
			return top1;
		}
		else {
			Collections.sort(jogadores);
			for (Atributo a : jogadores) {
				top1.add(a.id);
			}
			for (int b = 0; b < top ; b++) {
				top2.add(top1.get(b));
			}
		return top2;
		}
	}

	@Desafio("buscarCorCamisaTimeDeFora")
	public String buscarCorCamisaTimeDeFora(Long timeDaCasa, Long timeDeFora) {
		
		String coruniforme = new String();		
		for (Time a : times) {
			if (a.id == timeDaCasa) {
				for (Time b : times) {
					if (b.id == timeDeFora) {
						if (a.coruniformeprincipal != b.coruniformeprincipal) {
							coruniforme = b.coruniformeprincipal;
						}
						else {
							coruniforme = b.coruniformesecundario;
						}
					}
				}
			}
		}
		return coruniforme;
	}

}
