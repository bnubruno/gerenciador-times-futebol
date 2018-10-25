package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.HashMap;

import br.com.codenation.desafio.annotation.Desafio;
import br.com.codenation.desafio.app.MeuTimeInterface;

public class DesafioMeuTimeApplication implements MeuTimeInterface {

	private HashMap<Long, Time> times = new HashMap<Long, Time>();
	private HashMap<Long, Jogador> jogadores = new HashMap<Long, Jogador>();

	@Desafio("incluirTime")
	public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
		Time put = this.times.putIfAbsent(id, new Time(
			nome,
			dataCriacao,
			corUniformePrincipal,
			corUniformeSecundario
		));
		if (put != null){
			throw new br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException();
		}
	}

	@Desafio("incluirJogador")
	public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {
		if (!this.times.containsKey(idTime)){
			throw new br.com.codenation.desafio.exceptions.TimeNaoEncontradoException();
		}
		Jogador put = this.jogadores.putIfAbsent(id, new Jogador(
			idTime,
			nome,
			dataNascimento,
			nivelHabilidade,
			salario
		));
		if (put != null){
			throw new br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException();
		}
	}

	@Desafio("definirCapitao")
	public void definirCapitao(Long idJogador) {
		Jogador jogador = this.jogadores.get(idJogador);
		if (jogador == null){
			throw new br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException();
		}
		Time time = this.times.get(jogador.getIdTime());
		time.setCapitao(idJogador);
	}

	@Desafio("buscarCapitaoDoTime")
	public Long buscarCapitaoDoTime(Long idTime) {
		Time time = times.get(idTime);
		if (time == null){
			throw new br.com.codenation.desafio.exceptions.TimeNaoEncontradoException();
		}
		Long idCapitao = time.getIdCapitao();
		if (idCapitao == null){
			throw new br.com.codenation.desafio.exceptions.CapitaoNaoInformadoException();
		}
		return idCapitao;
	}

	@Desafio("buscarNomeJogador")
	public String buscarNomeJogador(Long idJogador) {
		Jogador jogador = this.jogadores.get(idJogador);
		if (jogador == null){
			throw new br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException();
		}
		return jogador.getNome();
	}

	@Desafio("buscarNomeTime")
	public String buscarNomeTime(Long idTime) {
		Time time = this.times.get(idTime);
		if (time == null){
			throw new br.com.codenation.desafio.exceptions.TimeNaoEncontradoException();
		}
		return time.getNome();
	}

	@Desafio("buscarJogadoresDoTime")
	public List<Long> buscarJogadoresDoTime(Long idTime) {
		if(!this.times.containsKey(idTime)){
			throw new br.com.codenation.desafio.exceptions.TimeNaoEncontradoException();
		}

		List<Long> idsJogadoresTime = new ArrayList<Long>();

		for (Map.Entry<Long, Jogador> entry: this.jogadores.entrySet()){
			Long idJogador = entry.getKey();
			Jogador jogador = entry.getValue();
			if (jogador.getIdTime().equals(idTime)){
				idsJogadoresTime.add(idJogador);
			}
		}
		Collections.sort(idsJogadoresTime);

		return idsJogadoresTime;
	}

	@Desafio("buscarMelhorJogadorDoTime")
	public Long buscarMelhorJogadorDoTime(Long idTime) {
		if(!this.times.containsKey(idTime)){
			throw new br.com.codenation.desafio.exceptions.TimeNaoEncontradoException();
		}

		List<Long> idsJogadoresTime = this.buscarJogadoresDoTime(idTime);
		if (idsJogadoresTime.isEmpty()){
			return null;
		}

		Jogador melhor = this.jogadores.get(idsJogadoresTime.get(0));
		Long idMelhor = idsJogadoresTime.get(0);

		for(Long idJogador: idsJogadoresTime){
			Jogador jogador = this.jogadores.get(idJogador);
			int comparacao = jogador.getNivelHabilidade().compareTo(melhor.getNivelHabilidade());
			if ((comparacao > 0) || (comparacao == 0 && idJogador.compareTo(idMelhor) < 0)){
				melhor = jogador;
				idMelhor = idJogador;
			}
		}

		return idMelhor;
	}

	@Desafio("buscarJogadorMaisVelho")
	public Long buscarJogadorMaisVelho(Long idTime) {
		if(!this.times.containsKey(idTime)){
			throw new br.com.codenation.desafio.exceptions.TimeNaoEncontradoException();
		}

		List<Long> idsJogadoresTime = this.buscarJogadoresDoTime(idTime);
		if (idsJogadoresTime.isEmpty()){
			return null;
		}
		Jogador maisVelho = this.jogadores.get(idsJogadoresTime.get(0));
		Long idMaisVelho = idsJogadoresTime.get(0);

		for(Long idJogador: idsJogadoresTime){
			Jogador jogador = this.jogadores.get(idJogador);
			int comparacao = jogador.getDataNascimento().compareTo(maisVelho.getDataNascimento());
			if ((comparacao < 0) || (comparacao == 0 && idJogador.compareTo(idMaisVelho) < 0)){
				maisVelho = jogador;
				idMaisVelho = idJogador;
			}
		}

		return idMaisVelho;
	}

	@Desafio("buscarTimes")
	public List<Long> buscarTimes() {
		List<Long> idsTimes = new ArrayList<Long>();
		for (Map.Entry<Long, Time> entry: this.times.entrySet()){
			Long idTime = entry.getKey();
			idsTimes.add(idTime);
		}
		Collections.sort(idsTimes);
		return idsTimes;
	}

	@Desafio("buscarJogadorMaiorSalario")
	public Long buscarJogadorMaiorSalario(Long idTime) {
		if(!this.times.containsKey(idTime)){
			throw new br.com.codenation.desafio.exceptions.TimeNaoEncontradoException();
		}

		List<Long> idsJogadoresTime = this.buscarJogadoresDoTime(idTime);
		if (idsJogadoresTime.isEmpty()){
			return null;
		}

		Jogador maiorSalario = this.jogadores.get(idsJogadoresTime.get(0));
		Long idMaiorSalario = idsJogadoresTime.get(0);

		for(Long idJogador: idsJogadoresTime){
			Jogador jogador = this.jogadores.get(idJogador);
			int comparacao = jogador.getSalario().compareTo(maiorSalario.getSalario());
			if ((comparacao > 0) || (comparacao == 0 && idJogador.compareTo(idMaiorSalario) < 0)){
				maiorSalario = jogador;
				idMaiorSalario = idJogador;
			}
		}

		return idMaiorSalario;
	}

	@Desafio("buscarSalarioDoJogador")
	public BigDecimal buscarSalarioDoJogador(Long idJogador) {
		Jogador jogador = this.jogadores.get(idJogador);
		if (jogador == null){
			throw new br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException();
		}
		return jogador.getSalario();
	}

	@Desafio("buscarTopJogadores")
	public List<Long> buscarTopJogadores(Integer top) {

		List<Map.Entry<Long, Jogador>> list = new LinkedList<Map.Entry<Long, Jogador>>(this.jogadores.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<Long, Jogador>>(){
			public int compare(Map.Entry<Long, Jogador> o1, Map.Entry<Long, Jogador> o2){
				Jogador a = o1.getValue();
				Jogador b = o2.getValue();
				int result = a.getNivelHabilidade().compareTo(b.getNivelHabilidade());
				if (result == 0){
					result = o1.getKey().compareTo(o2.getKey());
				} else {
					result = -result;
				}
				return result;
			}
		});

		ArrayList<Long> idsJogadores = new ArrayList<Long>();

		if(top == 0){
			return idsJogadores;
		}

		for (Map.Entry<Long, Jogador> entry: list){
			idsJogadores.add(entry.getKey());
			top = top - 1;
			if(top <= 0){
				break;
			}
		}

		return idsJogadores;
	}

	@Desafio("buscarCorCamisaTimeDeFora")
	public String buscarCorCamisaTimeDeFora(Long timeDaCasa, Long timeDeFora) {
		Time objTimeDaCasa = this.times.get(timeDaCasa);
		Time objTimeDeFora = this.times.get(timeDeFora);

		if (objTimeDaCasa == null || objTimeDeFora == null){
			throw new br.com.codenation.desafio.exceptions.TimeNaoEncontradoException();
		}
		if(objTimeDaCasa.getCorUniformePrincipal().equals(objTimeDeFora.getCorUniformePrincipal())){
			return objTimeDeFora.getCorUniformeSecundario();
		} else {
			return objTimeDeFora.getCorUniformePrincipal();
		}
	}

}
