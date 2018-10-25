package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;

import br.com.codenation.desafio.annotation.Desafio;
import br.com.codenation.desafio.app.MeuTimeInterface;

class Jogador{
	private String nome;
	private LocalDate dataNascimento;
	private Integer nivelHabilidade;
	private BigDecimal salario;
	private int ehCapitao = 0;

	public Jogador(String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario){
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.nivelHabilidade = nivelHabilidade;
		this.salario = salario;
	}

	public int isCap(){
		return ehCapitao;
	}

	public void makeCap(int caplvl){
		this.ehCapitao = caplvl;
	}

	public String getNome(){
		return this.nome;
	}
	public LocalDate getDataNascimento(){
		return this.dataNascimento;
	}
	public Integer getNivelHabilidade(){
		return this.nivelHabilidade;
	}
	public BigDecimal getSalario(){
		return this.salario;
	}

}

class Time{
	private String nome;
	private LocalDate dataCriacao;
	private String corUniformePrincipal;
	private String corUniformeSecundario;
	HashMap<Long, Jogador> jogadores = new HashMap<Long, Jogador>();

	public Time(String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario){
		this.nome = nome;
		this.dataCriacao = dataCriacao;
		this.corUniformePrincipal = corUniformePrincipal;
		this.corUniformeSecundario = corUniformeSecundario;
	}

	public Long getCap(){
		Long chave = new Long(-1);
		for(Map.Entry<Long, Jogador> entry : jogadores.entrySet()) {
	    Long key = entry.getKey();
	    Jogador value = entry.getValue();

			if(value.isCap()==1)
				return key;
 		}
		return chave;
	}

	public int jogadorExiste(Long id){
		if (jogadores.containsKey(id)) {
			return 1;
		} else {
			return 0;
		}
	}

	public void makeCap(Long id){
		for(Map.Entry<Long, Jogador> entry : jogadores.entrySet()) {
	    Long key = entry.getKey();
	    Jogador value = entry.getValue();

			if(key==id){
				Jogador tmp = jogadores.get(id);
				tmp.makeCap(1);
				jogadores.put(id, tmp);
			} else {
				if(value.isCap()==1){
					Jogador tmp = jogadores.get(id);
					tmp.makeCap(0);
					jogadores.put(id, tmp);
				}
			}

 		}
	}


	public int addJogador(Long id, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario){
		if (jogadores.containsKey(id)) {
			return 1;
		} else {
			jogadores.put(id, new Jogador(nome, dataNascimento, nivelHabilidade, salario));
			return 0;
		}
	}

	public Long getMelhorJogador(){
		Integer maiorHabilidade = 0;
		Long id = new Long(0);
		for(Map.Entry<Long, Jogador> entry : jogadores.entrySet()) {
	    Long key = entry.getKey();
	    Jogador value = entry.getValue();
			if(value.getNivelHabilidade() > maiorHabilidade){
				maiorHabilidade = value.getNivelHabilidade();
				id = key;
			}
 		}
		return id;
	}

	public Long getJogadorMaisVelho(){
		LocalDate maisAntigo = LocalDate.now();
		Long id = new Long(0);
		for(Map.Entry<Long, Jogador> entry : jogadores.entrySet()) {
	    Long key = entry.getKey();
	    Jogador value = entry.getValue();
			if(value.getDataNascimento().isBefore(maisAntigo)){
				maisAntigo = value.getDataNascimento();
				id = key;
			}
 		}
		return id;
	}

	public Long getMaisRico(){
		BigDecimal maisRico = new BigDecimal(0);
		Long id = new Long(0);
		for(Map.Entry<Long, Jogador> entry : jogadores.entrySet()) {
			Long key = entry.getKey();
			Jogador value = entry.getValue();
			if(value.getSalario().compareTo(maisRico)==1){
				maisRico = value.getSalario();
				id = key;
			}
		}
		return id;
	}


	public List<Long> getJogadores(){
		List<Long> todosOsJogadores = new ArrayList<Long>();
		todosOsJogadores.addAll(jogadores.keySet());
		return todosOsJogadores;
	}

	public HashMap<Long, Jogador> exportJogadores(){
		return jogadores;
	}

	public List<Long> getTopJogadores(){
		HashMap<Integer, Long> melhoresJogadores = new HashMap<Integer, Long>();
		for(Map.Entry<Long, Jogador> entry : jogadores.entrySet()) {
	    Long key = entry.getKey();
	    Jogador value = entry.getValue();

			melhoresJogadores.put(value.getNivelHabilidade(), key);

 		}

		List<Long> topJogadores = new ArrayList<Long>();
		for(Map.Entry<Integer, Long> entry : melhoresJogadores.entrySet()) {
	    Integer key = entry.getKey();
	    Long value = entry.getValue();

			topJogadores.add(value);
 		}
		return topJogadores;
	}

	public String getNomeJogador(Long id){
		Jogador tmp = jogadores.get(id);
		return tmp.getNome();
	}

	public String getNome(){
		return this.nome;
	}
	public LocalDate getDataCriacao(){
		return this.dataCriacao;
	}
	public String getCorUniformePrincipal(){
		return this.corUniformePrincipal;
	}
	public String getCorUniformeSecundario(){
		return this.corUniformeSecundario;
	}

}

public class DesafioMeuTimeApplication implements MeuTimeInterface {
  HashMap<Long, Time> times = new HashMap<Long, Time>();

	@Desafio("incluirTime")
	public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
		if (times.containsKey(id)) {
			throw new br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException();
		} else {
			times.put(id, new Time(nome, dataCriacao, corUniformePrincipal, corUniformeSecundario));
		}
	}

	@Desafio("incluirJogador")
	public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {
		if (times.containsKey(id)) {
			throw new br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException();
		} else {
			Time tmp = times.get(idTime);
			int error = tmp.addJogador(id, nome, dataNascimento, nivelHabilidade, salario);
			if(error==1)
				throw new br.com.codenation.desafio.exceptions.TimeNaoEncontradoException();
			else
				times.put(id,tmp);
		}

	}

	@Desafio("definirCapitao")
	public void definirCapitao(Long idJogador) {
		int achouJogador = 0;

		for(Map.Entry<Long, Time> entry : times.entrySet()) {
			Long idTime = entry.getKey();
			Time value = entry.getValue();

 			if(value.jogadorExiste(idJogador)==1){
				achouJogador = 1;
				Time tmp = times.get(idTime);
				tmp.makeCap(idJogador);
				times.put(idTime, tmp);
				break;
			}
		}
	if(achouJogador==0)
		throw new br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException();
	}

	@Desafio("buscarCapitaoDoTime")
	public Long buscarCapitaoDoTime(Long idTime) {
		if (times.containsKey(idTime)) {
			Time tmp = times.get(idTime);
			Long chave = tmp.getCap();
			if(chave==-1)
				throw new br.com.codenation.desafio.exceptions.CapitaoNaoInformadoException();
			else
				return chave;
		} else {
			throw new br.com.codenation.desafio.exceptions.TimeNaoEncontradoException();
		}
	}

	@Desafio("buscarNomeJogador")
	public String buscarNomeJogador(Long idJogador) {
		int achouJogador = 0;
		String nomeJogador = "";

		for(Map.Entry<Long, Time> entry : times.entrySet()) {
			Long idTime = entry.getKey();
			Time value = entry.getValue();

 			if(value.jogadorExiste(idJogador)==1){
				achouJogador = 1;
				Time tmp = times.get(idTime);
				nomeJogador = tmp.getNomeJogador(idJogador);
				break;
			}
		}
	if(achouJogador==0)
		throw new br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException();
	return nomeJogador;
	}

	@Desafio("buscarNomeTime")
	public String buscarNomeTime(Long idTime) {
		String nomeDoTime = "";
		if (times.containsKey(idTime)) {
			Time tmp = times.get(idTime);
			nomeDoTime = tmp.getNome();
		} else {
			throw new br.com.codenation.desafio.exceptions.TimeNaoEncontradoException();
		}
		return nomeDoTime;


	}

	@Desafio("buscarJogadoresDoTime")
	public List<Long> buscarJogadoresDoTime(Long idTime) {
		if (times.containsKey(idTime)) {
			return times.get(idTime).getJogadores();
		} else {
			throw new br.com.codenation.desafio.exceptions.TimeNaoEncontradoException();
		}
	}

	@Desafio("buscarMelhorJogadorDoTime")
	public Long buscarMelhorJogadorDoTime(Long idTime) {
		if (times.containsKey(idTime)) {
			Time tmp = times.get(idTime);
			return tmp.getMelhorJogador();
		} else {
			throw new br.com.codenation.desafio.exceptions.TimeNaoEncontradoException();
		}
	}

	@Desafio("buscarJogadorMaisVelho")
	public Long buscarJogadorMaisVelho(Long idTime) {
		if (times.containsKey(idTime)) {
			Time tmp = times.get(idTime);
			return tmp.getJogadorMaisVelho();
		} else {
			throw new br.com.codenation.desafio.exceptions.TimeNaoEncontradoException();
		}
	}

	@Desafio("buscarTimes")
	public List<Long> buscarTimes() {
		List<Long> todosOsTimes = new ArrayList<Long>();
		if (times.size()>0) {
			todosOsTimes.addAll(times.keySet());
		}
		return todosOsTimes;
	}

	@Desafio("buscarJogadorMaiorSalario")
	public Long buscarJogadorMaiorSalario(Long idTime) {
		if (times.containsKey(idTime)) {
			Time tmp = times.get(idTime);
			return tmp.getMaisRico();
		} else {
			throw new br.com.codenation.desafio.exceptions.TimeNaoEncontradoException();
		}
	}

	@Desafio("buscarSalarioDoJogador")
	public BigDecimal buscarSalarioDoJogador(Long idJogador) {
		throw new br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException();
	}

	@Desafio("buscarTopJogadores")
	public List<Long> buscarTopJogadores(Integer top) {
	  HashMap<Long, Jogador> todosOsJogadores = new HashMap<Long, Jogador>();

		for(Map.Entry<Long, Time> entry : times.entrySet()) {
			Long idTime = entry.getKey();
			Time value = entry.getValue();


			todosOsJogadores.putAll(value.exportJogadores());
		}

		HashMap<Integer, Long> melhoresJogadores = new HashMap<Integer, Long>();
		for(Map.Entry<Long, Jogador> entry : todosOsJogadores.entrySet()) {
	    Long key = entry.getKey();
	    Jogador value = entry.getValue();

			melhoresJogadores.put(value.getNivelHabilidade(), key);

 		}

		List<Long> topJogadores = new ArrayList<Long>();
		for(Map.Entry<Integer, Long> entry : melhoresJogadores.entrySet()) {
	    Integer key = entry.getKey();
	    Long value = entry.getValue();

			topJogadores.add(value);
 		}

		if(topJogadores.isEmpty()){
			return topJogadores;
		} else {
			//Collections.reverse(topJogadores);
			return topJogadores.subList(0,top);
		}

	}

	@Desafio("buscarCorCamisaTimeDeFora")
	// Precisa ter throw exception caso não ache os dois times
	public String buscarCorCamisaTimeDeFora(Long timeDeCasa, Long timeDeFora) {
		String corTimeDeCasaI = "";
		String corTimeDeCasaII = "";

		String corTimeDeForaI = "";
		String corTimeDeForaII = "";

		if (times.containsKey(timeDeCasa) && times.containsKey(timeDeFora)) {
			corTimeDeCasaI = times.get(timeDeCasa).getCorUniformePrincipal();
			corTimeDeCasaII = times.get(timeDeCasa).getCorUniformeSecundario();

			corTimeDeForaI = times.get(timeDeFora).getCorUniformePrincipal();
			corTimeDeForaII = times.get(timeDeFora).getCorUniformeSecundario();

			if(corTimeDeCasaI==corTimeDeForaI){
				return corTimeDeForaII;
			} else if(corTimeDeCasaI!=corTimeDeForaI){
				return corTimeDeForaI;
			}
		} else {
			throw new br.com.codenation.desafio.exceptions.TimeNaoEncontradoException();
		}
		return "";
	}

}
