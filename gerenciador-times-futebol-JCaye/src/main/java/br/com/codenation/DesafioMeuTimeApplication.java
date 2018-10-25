package br.com.codenation;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import br.com.codenation.desafio.annotation.Desafio;
import br.com.codenation.desafio.app.MeuTimeInterface;

class Index {
	Time primeiroTime = null;
	Jogador primeiroJogador = null;
}

class Time {
	private Long id = null;
	private LocalDate dataCriacao = null;
	private String nome, corUniformePrincipal = null, corUniformeSecundario = null;
	private Time timeSeguinte = null;
	private Jogador capitao = null;
	
	void criarTime(Long idNovo, String nomeNovo, LocalDate dataNova,
			String corPrincipalNova, String corSecundariaNova) {
		id = new Long(idNovo.longValue());
		nome = new String(nomeNovo.toString());
		dataCriacao = dataNova;
		corUniformePrincipal = new String(corPrincipalNova.toString());
		corUniformeSecundario = new String(corSecundariaNova.toString());
	}
	void definirCapitao(Jogador capitaoNovo) {
		capitao = capitaoNovo;
	}
	void definirSeguinte(Time seguinte) {
		timeSeguinte = seguinte;
	}
	Long idTime() {
		return new Long(id.longValue());
	}
	LocalDate dataTime() {
		return dataCriacao;
	}
	String nomeTime() {
		return new String(nome.toString());
	}
	String corPrincipal() {
		return new String(corUniformePrincipal.toString());
	}
	String corSecundaria() {
		return new String(corUniformeSecundario.toString());
	}
	Time seguinteTime() {
		Time temp = new Time();
		temp = timeSeguinte;
		return temp;
	}
	Jogador capitaoDoTime() {
		Jogador temp = new Jogador();
		temp = capitao;
		return temp;
	}
}

class Jogador {
	private Long id = null;
	private Time time = null;
	private String nome = null;
	private LocalDate dataNascimento = null;
	private Integer nivelHabilidade = null;
	private BigDecimal salario = null;
	private boolean capitao = false;
	private Jogador jogadorSeguinte = null;
	
	void criarJogador(Long idNovo, Time timeNovo, String nomeNovo,
			LocalDate dataNova, Integer nivelNovo, BigDecimal salarioNovo) {
		id = new Long(idNovo.longValue());
		time = timeNovo;
		nome = new String(nomeNovo.toString());
		dataNascimento = dataNova;
		nivelHabilidade = new Integer(nivelNovo.intValue());
		salario = salarioNovo;
	}
	void definirCapitao(boolean valor) {
		capitao = valor;
	}
	void definirSeguinte(Jogador seguinte) {
		jogadorSeguinte = seguinte;
	}
	Long idJogador() {
		return new Long(id.longValue());
	}
	Time timeDoJogador() {
		Time temp = new Time();
		temp = time;
		return temp;
	}
	String nomeJogador() {
		return new String(nome.toString());
	}
	LocalDate dataJogador() {
		return dataNascimento;
	}
	Integer habilidade() {
		return new Integer(nivelHabilidade.intValue());
	}
	BigDecimal salarioDoJogador() {
		return salario;
	}
	boolean capitao() {
		return capitao;
	}
	Jogador seguinteJogador() {
		Jogador temp = new Jogador();
		temp = jogadorSeguinte;
		return temp;
	}
}

public class DesafioMeuTimeApplication implements MeuTimeInterface {

	private Index mainindex = new Index();
	
	private Index getIndex() {
		Index index = new Index();
		index.primeiroTime = this.mainindex.primeiroTime;
		index.primeiroJogador = this.mainindex.primeiroJogador;
		return index;
	}
	
	private void setTime(Time novoTime) {
		this.mainindex.primeiroTime = novoTime;
	}
	
	private void setJogador (Jogador novoJogador) {
		this.mainindex.primeiroJogador = novoJogador;
	}
	
	private Time encontrarTime(Long idTime) {
		Time temp = getIndex().primeiroTime;
		while ((temp != null) && (!idTime.equals(temp.idTime()))) {
			temp = temp.seguinteTime();
		}
		return temp;
	}
	
	private Jogador encontrarJogador(Long idJogador) {
		Jogador temp = getIndex().primeiroJogador;
		while ((temp != null) && (!idJogador.equals(temp.idJogador()))) {
			temp = temp.seguinteJogador();
		}
		return temp;
	}
	
	@Desafio("incluirTime")
	public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
		//checar se id ja foi usado
		if (encontrarTime(id) != null) {throw new br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException();}
		
		//criar novo time
		Time temp = new Time();
		temp.criarTime(id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario);
		
		//atribuir o atual primeiro time a temp.timeSeguinte
		temp.definirSeguinte(getIndex().primeiroTime);
		
		//atribuir temp a mainindex.primeiroTime
		setTime(temp);
	}

	@Desafio("incluirJogador")
	public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {
		//checar se time existe
		Time time = encontrarTime(idTime);
		if (time == null) {throw new br.com.codenation.desafio.exceptions.TimeNaoEncontradoException();}
		
		//checar se identificador ja foi utilizado
		if (encontrarJogador(id) != null) {throw new br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException();}
		
		//criar novo jogador
		Jogador temp = new Jogador();
		temp.criarJogador(id, time, nome, dataNascimento, nivelHabilidade, salario);
		
		//atribuir o atual primeiro jogador a temp.jogadorSeguinte
		temp.definirSeguinte(getIndex().primeiroJogador);
		
		//atribuir o novo jogador a mainindex.primeiroJogador
		setJogador(temp);
	}

	@Desafio("definirCapitao")
	public void definirCapitao(Long idJogador) {
		//checar se jogador existe
		Jogador temp = encontrarJogador(idJogador);
		if (temp == null) {throw new br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException();}
		
		//se o time ja tinha capitao, alterar o capitao anterior
		Time time = temp.timeDoJogador();
		Jogador capitao = time.capitaoDoTime();
		if (capitao != null) {capitao.definirCapitao(false);}
		
		//atribuir o novo capitao
		temp.definirCapitao(true);
		time.definirCapitao(temp);
	}

	@Desafio("buscarCapitaoDoTime")
	public Long buscarCapitaoDoTime(Long idTime) {
		//checar se time existe
		Time temp = encontrarTime(idTime);
		if (temp == null) {throw new br.com.codenation.desafio.exceptions.TimeNaoEncontradoException();}
		
		//se o time nao tem capitao, levantar excecao
		Jogador capitao = temp.capitaoDoTime();
		if (capitao == null) {throw new br.com.codenation.desafio.exceptions.CapitaoNaoInformadoException();}
		
		//retornar o id do capitao
		return new Long(capitao.idJogador().longValue());
	}

	@Desafio("buscarNomeJogador")
	public String buscarNomeJogador(Long idJogador) {
		//checar se jogador existe
		Jogador temp = encontrarJogador(idJogador);
		if (temp == null) {throw new br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException();}
		
		//retornar seu nome
		return new String(temp.nomeJogador().toString());
	}

	@Desafio("buscarNomeTime")
	public String buscarNomeTime(Long idTime) {
		//checar se time existe
		Time temp = encontrarTime(idTime);
		if (temp == null) {throw new br.com.codenation.desafio.exceptions.TimeNaoEncontradoException();}
		
		//retornar seu nome
		return new String(temp.nomeTime().toString());
	}
	
	@Desafio("buscarJogadoresDoTime")
	public List<Long> buscarJogadoresDoTime(Long idTime) {
		//checar se time existe
		if (encontrarTime(idTime) == null) {throw new br.com.codenation.desafio.exceptions.TimeNaoEncontradoException();}
		
		//listar jogadores pertencentes ao time
		List<Long> jogadores = new ArrayList<Long>();
		Jogador temp = getIndex().primeiroJogador;
		while (temp != null) {
			if (temp.timeDoJogador().idTime().equals(idTime)) {
				jogadores.add(new Long(temp.idJogador().longValue()));
			}
			temp = temp.seguinteJogador();
		}
		//ordenar lista por ordem ascendente de id, caso ela nao seja vazia
		if (jogadores.size() > 1) {
			Collections.sort(jogadores);
		}
		
		//retornar lista
		return jogadores;
	}
	
	@Desafio("buscarMelhorJogadorDoTime")
	public Long buscarMelhorJogadorDoTime(Long idTime) {
		//checar se time existe
		if (encontrarTime(idTime) == null) {throw new br.com.codenation.desafio.exceptions.TimeNaoEncontradoException();}
		
		//encontrar melhor jogador
		Jogador melhor = null;
		Jogador temp = getIndex().primeiroJogador;
		while (temp != null) {
			if (temp.timeDoJogador().idTime().equals(idTime) &&
					((melhor == null) || (0 < temp.habilidade().compareTo(melhor.habilidade()) ||
							(temp.habilidade().equals(melhor.habilidade()) &&
									(0 > temp.idJogador().compareTo(melhor.idJogador())))))) {
				melhor = temp;
			}
			temp = temp.seguinteJogador();
		}
		//caso não haja jogadores no time, retornar null
		if (melhor == null) {return null;}
		
		//retornar o id do melhor jogador
		return new Long(melhor.idJogador().longValue());
	}
	
	@Desafio("buscarJogadorMaisVelho")
	public Long buscarJogadorMaisVelho(Long idTime) {
		//checar se time existe
		if (encontrarTime(idTime) == null) {throw new br.com.codenation.desafio.exceptions.TimeNaoEncontradoException();}
		
		//encontrar o mais velho
		Jogador velho = null;
		Jogador temp = getIndex().primeiroJogador;
		while (temp != null) {
			if (temp.timeDoJogador().idTime().equals(idTime) && //caso seja o time certo E
					//nao haja ainda um jogador mais velho OU temp seja mais velho OU
					((velho == null) || (temp.dataJogador().isBefore(velho.dataJogador()))||
							//temp e velho tem a mesma idade mas tem tem id menor
							(temp.dataJogador().isEqual(velho.dataJogador()) &&
									(0 > temp.idJogador().compareTo(velho.idJogador()))))) {
				velho = temp;
			}
			temp = temp.seguinteJogador();
		}

		//caso nao haja jogadores no time, retornar null
		if (velho == null) {return null;}
		
		//retornar id do jogador mais velho
		return new Long(velho.idJogador().longValue());
	}
	
	@Desafio("buscarTimes")
	public List<Long> buscarTimes() {
		//listar id de todos os times presentes na memoria
		List<Long> times = new ArrayList<Long>();
		Time temp = getIndex().primeiroTime;
		while (temp != null) {
			times.add(new Long(temp.idTime().longValue()));
			temp = temp.seguinteTime();
		}
		
		//ordenar lista em ordem ascendente de id, caso ela seja nao vazia
		if (times.size() > 1) {
			Collections.sort(times);
		}
		
		//retornar lista dos times
		return times;
	}
	
	@Desafio("buscarJogadorMaiorSalario")
	public Long buscarJogadorMaiorSalario(Long idTime) {
		//checar se time existe
		if (encontrarTime(idTime) == null) {throw new br.com.codenation.desafio.exceptions.TimeNaoEncontradoException();}
		
		//encontrar o mais bem pago
		Jogador caro = null;
		Jogador temp = getIndex().primeiroJogador;
		while (temp != null) {
			if (temp.timeDoJogador().idTime().equals(idTime) && //caso seja o time correto E
					((caro == null) || //nao haja ainda um jogador caro OU
							//temp seja mais bem pago OU
							(0 < temp.salarioDoJogador().compareTo(caro.salarioDoJogador())) ||
							//ambos tem o mesmo salario mas temp tem id menor
							(0 == temp.salarioDoJogador().compareTo(caro.salarioDoJogador()) &&
									(0 > temp.idJogador().compareTo(caro.idJogador()))))) {
				caro = temp;
			}
			temp = temp.seguinteJogador();
		}
		//caso nao haja jogadores no time, retornar null
		if (caro == null) {return null;}
		
		//retornar id do jogador mais caro
		return new Long(caro.idJogador().longValue());
	}
	
	@Desafio("buscarSalarioDoJogador")
	public BigDecimal buscarSalarioDoJogador(Long idJogador) {
		//checar se jogador existe
		Jogador temp = encontrarJogador(idJogador);
		if (temp == null) {throw new br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException();}
		
		//retornar o salario
		return temp.salarioDoJogador();
	}
	
	@Desafio("buscarTopJogadores")
	public List<Long> buscarTopJogadores(Integer top) {
		List<Jogador> topPlayers = new ArrayList<Jogador>();
		Jogador temp = getIndex().primeiroJogador;
		//iterar sobre todos os jogadores presentes na memoria
		while (temp != null) {
			//inserir jogador na posicao adequada,
			//se ele for melhor que qualquer dos já incluídos na lista
			for (int i = 0, j = topPlayers.size(); i < j; i++) {
				if ((0 < temp.habilidade().compareTo(topPlayers.get(i).habilidade())) ||
						(temp.habilidade().equals(topPlayers.get(i).habilidade()) &&
								(0 > temp.idJogador().compareTo(topPlayers.get(i).idJogador())))) {
					topPlayers.add(i, temp);
					
					//if this addition exceeds the length of the list, remove last element
					if (topPlayers.size() > top.intValue()) {
						topPlayers.remove(topPlayers.size() - 1);
					}
					break;
				}
			}
			//caso temp não seja melhor que nehum jogador já presente
			//e a lista ainda possa crescer, forcar a inclusao no final da lista
			if ((topPlayers.size() < top.intValue()) && !topPlayers.contains(temp)) {
				topPlayers.add(temp);
			}
			temp = temp.seguinteJogador();
		}
		//extrair da lista ordenada de jogadores uma lista com apenas seus ids
		List<Long> topId = new ArrayList<Long>();
		for (Jogador temp2:topPlayers) {
			topId.add(new Long(temp2.idJogador().longValue()));
		}
		return topId;
	}
	
	@Desafio("buscarCorCamisaTimeDeFora")
	public String buscarCorCamisaTimeDeFora(Long timeDaCasa, Long timeDeFora) {
		//se ambos os times forem iguais, levantar excecao
		if (timeDaCasa.longValue() == timeDeFora.longValue()) {
			throw new UnsupportedOperationException();
		}
		
		//buscar times
		Time casa = encontrarTime(timeDaCasa), fora = encontrarTime(timeDeFora);
		
		//se qualquer dos times nao tiver sido encontrado, levantar excecao
		if ((casa == null) || (fora == null)) {throw new br.com.codenation.desafio.exceptions.TimeNaoEncontradoException();}
		
		//retornar cores corretas
		if(casa.corPrincipal().equalsIgnoreCase(fora.corPrincipal())) {
			return new String(fora.corSecundaria().toString());
		}
		else {
			return new String(fora.corPrincipal().toString());
		}
	}

}