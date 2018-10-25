package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;


import br.com.codenation.desafio.annotation.Desafio;
import br.com.codenation.desafio.app.MeuTimeInterface;

import static java.util.stream.Collectors.*;

public class DesafioMeuTimeApplication implements MeuTimeInterface {


    private Map<Long, Time> times = new TreeMap<>();
    private Map<Long, Jogador> jogadores = new TreeMap<>();


	@Desafio("incluirTime")
	public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {

        if(times.containsKey(id)) throw new br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException();

        Time novoT = new Time(id, nome, dataCriacao,corUniformePrincipal,corUniformeSecundario,
                null, null, null,null);

        times.put(id, novoT);

	}

	@Desafio("incluirJogador")
	public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {

	    if(jogadores.containsKey(id)) throw new br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException();
        if(!times.containsKey(idTime)) throw new br.com.codenation.desafio.exceptions.TimeNaoEncontradoException();

        if(nivelHabilidade > 100) nivelHabilidade = 100;
        if(nivelHabilidade < 0) nivelHabilidade = 0;

        Jogador novoJ = new Jogador(id, idTime, nome, dataNascimento, nivelHabilidade, salario);

        Time t = times.get(idTime);

        Long jogadorMaisVelho = t.getJogadorMaisVelho();
        Long jogadorMaisHabilidoso = t.getJogadorMaisHabilidoso();
        Long jogadorMaisSalario = t.getJogadorMaisSalario();

        if(jogadorMaisVelho != null){
            if(novoJ.getDataNascimento().isBefore(jogadores.get(jogadorMaisVelho).getDataNascimento())){
                t.setJogadorMaisVelho(id);
            }
            if(novoJ.getDataNascimento().isEqual(jogadores.get(jogadorMaisVelho).getDataNascimento())){
                if(id < jogadorMaisVelho){
                    t.setJogadorMaisVelho(id);
                }
            }

        }else{
            t.setJogadorMaisVelho(id);
        }

        if(jogadorMaisHabilidoso != null){
            if(novoJ.getNivelHabilidade() > jogadores.get(jogadorMaisHabilidoso).getNivelHabilidade()){
                times.get(idTime).setJogadorMaisHabilidoso(id);
            }
            if(novoJ.getNivelHabilidade().equals(jogadores.get(jogadorMaisHabilidoso).getNivelHabilidade())){
                if(id < jogadorMaisHabilidoso){
                    t.setJogadorMaisHabilidoso(id);
                }
            }
        }else{
            t.setJogadorMaisHabilidoso(id);
        }

        if(jogadorMaisSalario != null){
            if(novoJ.getSalario().compareTo(jogadores.get(jogadorMaisSalario).getSalario()) > 0){
                t.setJogadorMaisSalario(id);
            }
            if(novoJ.getSalario().compareTo(jogadores.get(jogadorMaisHabilidoso).getSalario()) == 0){
                if(id < jogadorMaisSalario){
                    t.setJogadorMaisSalario(id);
                }
            }
        }else{
            t.setJogadorMaisSalario(id);
        }

        jogadores.put(id,novoJ);

	}

	@Desafio("definirCapitao")
	public void definirCapitao(Long idJogador) {
		if(!jogadores.containsKey(idJogador)) throw new br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException();

        Time t = times.get(jogadores.get(idJogador).getIdTime());

        t.setIdCapitao(idJogador);

	}

	@Desafio("buscarCapitaoDoTime")
	public Long buscarCapitaoDoTime(Long idTime) {
		if(!times.containsKey(idTime)) throw new br.com.codenation.desafio.exceptions.TimeNaoEncontradoException();
        if(times.get(idTime).getIdCapitao() == null) throw new br.com.codenation.desafio.exceptions.CapitaoNaoInformadoException();

        return times.get(idTime).getIdCapitao();

	}

	@Desafio("buscarNomeJogador")
	public String buscarNomeJogador(Long idJogador) {
		if(!jogadores.containsKey(idJogador)) throw new br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException();
        return jogadores.get(idJogador).getNome();
	}

	@Desafio("buscarNomeTime")
	public String buscarNomeTime(Long idTime) {
		if(!times.containsKey(idTime)) throw new br.com.codenation.desafio.exceptions.TimeNaoEncontradoException();
        return times.get(idTime).getNome();
	}

	@Desafio("buscarJogadoresDoTime")
	public List<Long> buscarJogadoresDoTime(Long idTime) {
		if(!times.containsKey(idTime)) throw new br.com.codenation.desafio.exceptions.TimeNaoEncontradoException();

        return jogadores.values().stream().filter(j -> (j.getIdTime().equals(idTime))).map(Jogador::getId).collect(toList());
	}

	@Desafio("buscarMelhorJogadorDoTime")
	public Long buscarMelhorJogadorDoTime(Long idTime) {

	    if(!times.containsKey(idTime)) throw new br.com.codenation.desafio.exceptions.TimeNaoEncontradoException();

	    return times.get(idTime).getJogadorMaisHabilidoso();

	}

	@Desafio("buscarJogadorMaisVelho")
	public Long buscarJogadorMaisVelho(Long idTime) {

        if(!times.containsKey(idTime)) throw new br.com.codenation.desafio.exceptions.TimeNaoEncontradoException();

        return times.get(idTime).getJogadorMaisVelho();

	}

	@Desafio("buscarTimes")
	public List<Long> buscarTimes() {
        return new ArrayList<>(times.keySet());
	}

	@Desafio("buscarJogadorMaiorSalario")
	public Long buscarJogadorMaiorSalario(Long idTime) {

        if(!times.containsKey(idTime)) throw new br.com.codenation.desafio.exceptions.TimeNaoEncontradoException();

        return times.get(idTime).getJogadorMaisSalario();
	}

	@Desafio("buscarSalarioDoJogador")
	public BigDecimal buscarSalarioDoJogador(Long idJogador) {

	    if(!jogadores.containsKey(idJogador)) throw new br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException();

        return jogadores.get(idJogador).getSalario();
	}

	@Desafio("buscarTopJogadores")
	public List<Long> buscarTopJogadores(Integer top) {

        return jogadores.values().stream()
                .sorted(Comparator.comparing(Jogador::getNivelHabilidade).reversed())
                .map(Jogador::getId).limit(top)
                .collect(toList());
	}

	@Desafio("buscarCorCamisaTimeDeFora")
	public String buscarCorCamisaTimeDeFora(Long timeDaCasa, Long timeDeFora) {

        if(!times.containsKey(timeDaCasa)) throw new br.com.codenation.desafio.exceptions.TimeNaoEncontradoException();
        if(!times.containsKey(timeDeFora)) throw new br.com.codenation.desafio.exceptions.TimeNaoEncontradoException();
	    Time casa = times.get(timeDaCasa);
        Time fora = times.get(timeDeFora);

        if(casa.getCorUniformePrincipal().equals(fora.getCorUniformePrincipal())){
            return fora.getCorUniformeSecundario();
        }
        return fora.getCorUniformePrincipal();

	}

}
