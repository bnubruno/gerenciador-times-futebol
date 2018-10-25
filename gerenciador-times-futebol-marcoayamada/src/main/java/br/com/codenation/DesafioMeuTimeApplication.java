package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import br.com.codenation.desafio.annotation.Desafio;
import br.com.codenation.desafio.app.MeuTimeInterface;
import br.com.codenation.desafio.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;

public class DesafioMeuTimeApplication implements MeuTimeInterface {

	private Map<Long, Time> times;
	private Map<Long, Jogador> jogadores;
	
	public DesafioMeuTimeApplication(){
		times = new HashMap<Long, Time>(); 
		jogadores = new HashMap<Long, Jogador>();
	}
	
	//Map<Long, Time> times = new HashMap<Long, Time>();
	//Map<Long, Jogador> jogadores = new HashMap<Long, Jogador>();
	
	public Map<Long, Time> getTimes() {
	       return times;
	   }
	
	public Map<Long, Jogador> getJogadores() {
	       return jogadores;
	   }

	@Desafio("incluirTime")
	public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
		if(times.containsKey(id)){
			//System.out.println("Error");
			throw new IdentificadorUtilizadoException();
		}else{
			//System.out.println("Add");
			times.put(id, new Time(nome, dataCriacao, corUniformePrincipal, corUniformeSecundario));
		}

	}

	@Desafio("incluirJogador")
	public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {
		if(!times.containsKey(idTime)){
			//System.out.println("Error_Time");
			throw new TimeNaoEncontradoException();
		}
		
		if(jogadores.containsKey(id)){
			//System.out.println("Error_Jogador");
			throw new IdentificadorUtilizadoException();
		}else{
			//System.out.println("Add");
			jogadores.put(id, new Jogador(idTime,nome, dataNascimento, nivelHabilidade, salario));
		}
		
		//jogadores.forEach((k,v) -> System.out.println("key: "+k+" value:"+v.idTime));
	}

	@Desafio("definirCapitao")
	public void definirCapitao(Long idJogador) {
		//System.out.println("Antes:");
		//times.forEach((k,v) -> System.out.println("key: "+k+" value:"+v.idCapitao));
		
		if(!jogadores.containsKey(idJogador)){
			throw new JogadorNaoEncontradoException();
		}else{
			times.forEach((k,v) -> v.idCapitao=null); //setando todo mundo com jogador normal
			
			Long t = jogadores.get(idJogador).idTime; //retorna o idTime do Jogador
			times.get(t).idCapitao = idJogador;
		}

		//System.out.println("Depois:");
		//times.forEach((k,v) -> System.out.println("key: "+k+" value:"+v.idCapitao));
	}

	@Desafio("buscarCapitaoDoTime")
	public Long buscarCapitaoDoTime(Long idTime) {
		if(!times.containsKey(idTime)){
			//System.out.println("Error_Time");
			throw new TimeNaoEncontradoException();
		}else{
			if(times.get(idTime).idCapitao != null){
				return times.get(idTime).idCapitao;
			}else{
				throw new CapitaoNaoInformadoException();
			}
		}
	}

	@Desafio("buscarNomeJogador")
	public String buscarNomeJogador(Long idJogador) {
		if(!jogadores.containsKey(idJogador)){
			throw new JogadorNaoEncontradoException();
		}else{
			return jogadores.get(idJogador).nome;
		}
	}

	@Desafio("buscarNomeTime")
	public String buscarNomeTime(Long idTime) {
		if(!times.containsKey(idTime)){
			throw new TimeNaoEncontradoException();
		}else{
			return times.get(idTime).nome;
		}
	}

	@Desafio("buscarJogadoresDoTime")
	public List<Long> buscarJogadoresDoTime(Long idTime) {
		//System.out.println(idTime);
		List<Long> idsJogadores = new ArrayList<Long>();
		if(!times.containsKey(idTime)){
			throw new TimeNaoEncontradoException();
		}else{
			jogadores.forEach(
					(k,v) -> {
						if(v.idTime.equals(idTime)){
							//System.out.println("key: "+k+" value:"+v.idTime);
							idsJogadores.add(k);
						}
					});
			Collections.sort(idsJogadores);
		}
		//System.out.println(idsJogadores);
		return idsJogadores;
	}

	@Desafio("buscarMelhorJogadorDoTime")
	public Long buscarMelhorJogadorDoTime(Long idTime) {
		if(!times.containsKey(idTime)){
			throw new TimeNaoEncontradoException();
		}else{
			
			//1) Retornando um map de ids e habilidades
			Map<Long, Integer> idsHab = new HashMap<Long, Integer>();
			jogadores.forEach(
					(k,v) -> {
						if(v.idTime.equals(idTime)){
							//System.out.println("key: "+k+" value:"+v.idTime);
							idsHab.put(k, v.nivelHabilidade);
						}
					});
			
			List<Long> tops= idsHab.entrySet()
	                .stream()
	                .sorted(Map.Entry.<Long, Integer>comparingByValue()
	                        .reversed()
	                .thenComparing(Map.Entry.comparingByKey()))
	                .map(e -> e.getKey())
	                .collect(Collectors.toList());
			
			//https://stackoverflow.com/questions/33313058/sort-hashmap-by-value-and-then-alphabetically
			
			return tops.get(0);
		}
	}

	@Desafio("buscarJogadorMaisVelho")
	public Long buscarJogadorMaisVelho(Long idTime) {
		if(!times.containsKey(idTime)){
			throw new TimeNaoEncontradoException();
		}else{
			
			Map<Long, LocalDate> idsIdade = new HashMap<Long, LocalDate>();
			jogadores.forEach(
					(k,v) -> {
						if(v.idTime.equals(idTime)){
							//System.out.println("key: "+k+" value:"+v.idTime);
							idsIdade.put(k, v.dataNascimento);
						}
					});
			
			List<Long> tops= idsIdade.entrySet()
	                .stream()
	                .sorted(Map.Entry.<Long, LocalDate>comparingByValue()
	                .thenComparing(Map.Entry.comparingByKey()))
	                .map(e -> e.getKey())
	                .collect(Collectors.toList());
			
			//System.out.println(max_age.getKey());
			//System.out.println(tops.get(0));
			return tops.get(0);
		}
	}

	@Desafio("buscarTimes")
	public List<Long> buscarTimes() {
		List<Long> ts = new ArrayList<Long>(times.keySet());
		Collections.sort(ts);
		
		return ts;
	}
	
	@Desafio("buscarJogadorMaiorSalario")
	public Long buscarJogadorMaiorSalario(Long idTime) {
		if(!times.containsKey(idTime)){
			throw new TimeNaoEncontradoException();
		}else{
			Map<Long, BigDecimal> idsSalario = new HashMap<Long, BigDecimal>();
			jogadores.forEach(
					(k,v) -> {
						if(v.idTime.equals(idTime)){
							//System.out.println("key: "+k+" value:"+v.idTime);
							idsSalario.put(k, v.salario);
						}
					});
			
			List<Long> tops= idsSalario.entrySet()
	                .stream()
	                .sorted(Map.Entry.<Long, BigDecimal>comparingByValue()
	                		.reversed()
	                .thenComparing(Map.Entry.comparingByKey()))
	                .map(e -> e.getKey())
	                .collect(Collectors.toList());
			
			//System.out.println(max_age.getKey());
			//System.out.println(tops.get(0));
			return tops.get(0);
		}
	}

	@Desafio("buscarSalarioDoJogador")
	public BigDecimal buscarSalarioDoJogador(Long idJogador) {
		if(!jogadores.containsKey(idJogador)){
			throw new JogadorNaoEncontradoException();
		}else{
			return jogadores.get(idJogador).salario;
		}
	}

	@Desafio("buscarTopJogadores")
	public List<Long> buscarTopJogadores(Integer top) {
		//1) Retornando um map de ids e habilidades
		Map<Long, Integer> idsHab = new HashMap<Long, Integer>();
		jogadores.forEach((k,v)->{idsHab.put(k, v.nivelHabilidade);});
		
		List<Long> tops= idsHab.entrySet()
                .stream()
                //.sorted(Map.Entry.<Long, Integer>comparingByValue())
                .limit(top)
                .sorted(Map.Entry.<Long, Integer>comparingByValue()
                        .reversed()
                .thenComparing(Map.Entry.comparingByKey()))
                .map(e -> e.getKey())
                .collect(Collectors.toList());
		
		//https://stackoverflow.com/questions/33313058/sort-hashmap-by-value-and-then-alphabetically
		
		return tops;
	}

	@Desafio("buscarCorCamisaTimeDeFora")
	public String buscarCorCamisaTimeDeFora(Long timeDaCasa, Long timeDeFora) {
		if(times.get(timeDaCasa).corUniformePrincipal.equals(times.get(timeDeFora).corUniformePrincipal)){
			return times.get(timeDeFora).corUniformeSecundario;
		}else{
			return times.get(timeDeFora).corUniformePrincipal;
		}
	}
	
   	/*public static void main(String args[]) {
	   //1)
   	   DesafioMeuTimeApplication dmta = new DesafioMeuTimeApplication();
	   long t1 = 234L;
	   dmta.incluirTime(t1, "time_1", LocalDate.now(), "princ_1", "sec_1");
	   long t2 = 123L;
	   dmta.incluirTime(t2, "time_2", LocalDate.now(), "princ_2", "sec_2");
	   
	   //2)
	   long j1=789L;
	   dmta.incluirJogador(j1, t1, "jog_1", LocalDate.now(), 50, new BigDecimal(900.0));
	   	   
	   long j2=678;
	   dmta.incluirJogador(j2, t2, "jog_2", LocalDate.of(1980, 6, 30), 90, new BigDecimal(900.0));
	   
	   long j3=578L;
	   dmta.incluirJogador(j3, t2, "jog_3", LocalDate.of(1980, 6, 30), 90, new BigDecimal(900.0));
	   
	   //dmta.incluirJogador(j1, t1, "jog_1", LocalDate.now(), 50, new BigDecimal(500.0));
	   
	   //3)
	   //dmta.definirCapitao(j1);
	   dmta.buscarJogadorMaiorSalario(t2);
		  
	  }*/

}
