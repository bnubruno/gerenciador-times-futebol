package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import br.com.codenation.desafio.annotation.Desafio;
import br.com.codenation.desafio.app.MeuTimeInterface;

public class DesafioMeuTimeApplication implements MeuTimeInterface {

	public List<Time> times = new LinkedList<>();
	public List<Jogador> jogadores = new LinkedList<>();

	@Desafio("incluirTime")
	public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal,
			String corUniformeSecundario) {

		// Testa se id é não é repetido
		for (Time checktime : times) {
			if (id == checktime.get_id()) {
				throw new br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException();
			}
		}

		// Inclui time
		times.add(new Time(id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario));
	}

	@Desafio("incluirJogador")
	public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade,
			BigDecimal salario) {

		// Testa existência do time
		int i = 0;

		for (Time checktime : times) {
			if (idTime == checktime.get_id()) {
				i++;
			}
		}

		if (i == 0) {
			throw new br.com.codenation.desafio.exceptions.TimeNaoEncontradoException();
		}

		// Testa se id não é repetido
		for (Jogador checkjogador : jogadores) {
			if (id == checkjogador.get_id()) {
				throw new br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException();
			}
		}

		// Inclui jogador
		jogadores.add(new Jogador(id, idTime, nome, dataNascimento, nivelHabilidade, salario));
	}

	@Desafio("definirCapitao")
	public void definirCapitao(Long idJogador) {

		int i = 0;
		long idtime = 0;

		// Encontra o time do jogador
		for (Jogador checkjogador : jogadores) {
			if (idJogador == checkjogador.get_id()) {
				i++;
				idtime = checkjogador.get_idTime();
			}
		}

		// Rebaixa todos do time
		for (Jogador checkjogador : jogadores) {
			if (idtime == checkjogador.get_idTime()) {
				checkjogador.set_capitao(false);
			}
		}

		// Promove a capitão o jogador indicado
		for (Jogador checkjogador : jogadores) {
			if (idJogador == checkjogador.get_id()) {
				checkjogador.set_capitao(true);
			}
		}

		// Testa existência do jogador
		if (i == 0) {
			throw new br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException();
		}
	}

	@Desafio("buscarCapitaoDoTime")
	public Long buscarCapitaoDoTime(Long idTime) {
		int i = 0;
		int j = 0;
		Long capitao = null;

		for (Jogador checkjogador : jogadores) {
			if (idTime == checkjogador.get_idTime()) {
				i++;
				if (checkjogador.get_capitao()) {
					j++;
					capitao = checkjogador.get_id();
				}
			}
		}

		if (i == 0) {
			throw new br.com.codenation.desafio.exceptions.TimeNaoEncontradoException();
		}

		if (j == 0) {
			throw new br.com.codenation.desafio.exceptions.CapitaoNaoInformadoException();
		}

		return capitao;
	}

	@Desafio("buscarNomeJogador")
	public String buscarNomeJogador(Long idJogador) {
		int i = 0;
		String nome = null;

		for (Jogador checkjogador : jogadores) {
			if (checkjogador.get_id() == idJogador) {
				i++;
				nome = checkjogador.get_nome();
			}
		}

		if (i == 0) {
			throw new br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException();
		}

		return nome;
	}

	@Desafio("buscarNomeTime")
	public String buscarNomeTime(Long idTime) {
		int i = 0;
		String nome = null;

		for (Time checktime : times) {
			if (checktime.get_id() == idTime) {
				i++;
				nome = checktime.get_nome();
			}
		}
		if (i == 0) {
			throw new br.com.codenation.desafio.exceptions.TimeNaoEncontradoException();
		}

		return nome;
	}

	@Desafio("buscarJogadoresDoTime")
	public List<Long> buscarJogadoresDoTime(Long idTime) {
		List<Long> jogadoresdotime = new ArrayList<>();
		int i = 0;

		// Testa a existência do time
		for (Time checktime : times) {
			if (checktime.get_id() == idTime) {
				i++;
			}
		}
		if (i == 0) {
			throw new br.com.codenation.desafio.exceptions.TimeNaoEncontradoException();
		}

		// Reúne os jogadores do time
		for (Jogador checkjogador : jogadores) {
			if (checkjogador.get_idTime() == idTime) {
				jogadoresdotime.add(checkjogador.get_id());
			}
		}

		if (jogadoresdotime.size() == 0) {
			throw new br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException();
		}

		// Ordena os ids e retorna a lista
		Collections.sort(jogadoresdotime);
		return jogadoresdotime;
	}

	@Desafio("buscarMelhorJogadorDoTime")
	public Long buscarMelhorJogadorDoTime(Long idTime) {
		List<Jogador> id_habilidade = new ArrayList<>();
		int i = 0;

		// Testa a existência do time
		for (Time checktime : times) {
			if (checktime.get_id() == idTime) {
				i++;
			}
		}
		if (i == 0) {
			throw new br.com.codenation.desafio.exceptions.TimeNaoEncontradoException();
		}

		// Reúne os jogadores do time em uma lista
		for (Jogador checkjogador : jogadores) {
			if (checkjogador.get_idTime() == idTime) {
				id_habilidade.add(checkjogador);
			}
		}

		// Cria duas listas separadas e ordenadas com os ids e as habilidades
		List<Integer> habilidades = new LinkedList<>();
		List<Long> ids = new LinkedList<>();
		for (Jogador checkjogador : id_habilidade) {
			ids.add(checkjogador.get_id());
			habilidades.add(checkjogador.get_nivelHabilidade());
		}

		// Encontra o maior valor de habilidade que ocorre no time
		int maiorhabilidade = Collections.max(habilidades);

		// Encontra as ocorrências do maior valor dentro do time e monta uma lista com
		// seus índices
		List<Integer> indx = new LinkedList<>();
		for (i = 0; i < habilidades.size(); i++) {
			if (habilidades.get(i) == maiorhabilidade) {
				indx.add(i);
			}
		}

		// Os índices são usados para consultar a lista de ids e montar uma lista com os
		// ids selecionados
		List<Long> idselecionados = new LinkedList<>();
		for (i = 0; i < indx.size(); i++) {
			idselecionados.add(ids.get(indx.get(i)));
		}

		// Retorna o menor valor de id dentre os jogadores de habilidade máxima
		return Collections.min(idselecionados);
	}

	@Desafio("buscarJogadorMaisVelho")
	public Long buscarJogadorMaisVelho(Long idTime) {
		List<Jogador> id_idade = new ArrayList<>();

		// Testa a existência do time
		int i = 0;
		for (Time checktime : times) {
			if (checktime.get_id() == idTime) {
				i++;
			}
		}
		if (i == 0) {
			throw new br.com.codenation.desafio.exceptions.TimeNaoEncontradoException();
		}

		// Reúne os jogadores do time em uma lista
		for (Jogador checkjogador : jogadores) {
			if (checkjogador.get_idTime() == idTime) {
				id_idade.add(checkjogador);
			}
		}

		// Cria duas listas separadas e ordenadas com os ids e as idades
		List<Long> idades = new LinkedList<>();
		List<Long> ids = new LinkedList<>();
		for (Jogador checkjogador : id_idade) {
			ids.add(checkjogador.get_id());
			idades.add((long) (ChronoUnit.DAYS.between(checkjogador.get_dataNascimento(), LocalDate.now())));
		}

		// Encontra o maior valor de idade que ocorre no time
		long maioridade = Collections.max(idades);

		// Encontra as ocorrências do maior valor dentro do time e monta uma lista com
		// seus índices
		List<Integer> indx = new LinkedList<>();
		for (i = 0; i < idades.size(); i++) {
			if (idades.get(i) == maioridade) {
				indx.add(i);
			}
		}

		// Os índices são usados para consultar a lista de ids e montar uma lista com os
		// ids selecionados
		List<Long> idselecionados = new LinkedList<>();
		for (i = 0; i < indx.size(); i++) {
			idselecionados.add(ids.get(indx.get(i)));
		}

		// Retorna o menor valor de id dentre os jogadores de idade máxima
		return Collections.min(idselecionados);

	}

	@Desafio("buscarTimes")
	public List<Long> buscarTimes() {
		List<Long> todosostimes = new ArrayList<>();

		// Monta a lista de ids dos times
		for (Time checktime : times) {
			todosostimes.add(checktime.get_id());
		}

		// Ordena os ids e retorna a lista
		Collections.sort(todosostimes);
		return todosostimes;
	}

	@Desafio("buscarJogadorMaiorSalario")
	public Long buscarJogadorMaiorSalario(Long idTime) {
		List<Jogador> id_salario = new ArrayList<>();
		long idtime = idTime;

		// Testa a existência do time
		int i = 0;
		for (Time checktime : times) {
			if (checktime.get_id() == idtime) {
				i++;
			}
		}
		if (i == 0) {
			throw new br.com.codenation.desafio.exceptions.TimeNaoEncontradoException();
		}

		// Reúne os jogadores do time em uma lista
		for (Jogador checkjogador : jogadores) {
			if (checkjogador.get_idTime() == idtime) {
				id_salario.add(checkjogador);
			}
		}

		// Cria duas listas separadas e ordenadas com os ids e os salários
		List<BigDecimal> salarios = new LinkedList<>();
		List<Long> ids = new LinkedList<>();
		for (Jogador checkjogador : id_salario) {
			ids.add(checkjogador.get_id());
			salarios.add(checkjogador.get_salario());
		}

		// Encontra o maior valor de salário que ocorre no time
		BigDecimal maiorsalario = Collections.max(salarios);

		// Encontra as ocorrências do maior valor dentro do time e monta uma lista com
		// seus índices
		List<Integer> indx = new LinkedList<>();
		for (i = 0; i < salarios.size(); i++) {
			if (salarios.get(i) == maiorsalario) {
				indx.add(i);
			}
		}

		// Os índices são usados para consultar a lista de ids e montar uma lista com os
		// ids selecionados
		List<Long> idselecionados = new LinkedList<>();
		for (i = 0; i < indx.size(); i++) {
			idselecionados.add(ids.get(indx.get(i)));
		}

		// Retorna o menor valor de id dentre os jogadores de salário máximo
		return Collections.min(idselecionados);
	}

	@Desafio("buscarSalarioDoJogador")
	public BigDecimal buscarSalarioDoJogador(Long idJogador) {
		BigDecimal salario = null;
		int i = 0;

		// Testa a existência do jogador
		for (Jogador checkjogador : jogadores) {
			if (checkjogador.get_id() == idJogador) {
				i++;
				salario = checkjogador.get_salario();
			}
		}
		if (i == 0) {
			throw new br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException();
		}
		return salario;
	}

	@Desafio("buscarTopJogadores")
	public List<Long> buscarTopJogadores(Integer top) {
		List<Long> topzera = new LinkedList<>();
		int i = 0;
		int j = 0;

		// Cria duas listas separadas e ordenadas com os ids e as habilidades
		List<Integer> habilidades = new LinkedList<>();
		List<Long> ids = new LinkedList<>();
		for (Jogador checkjogador : jogadores) {
			ids.add(checkjogador.get_id());
			habilidades.add(checkjogador.get_nivelHabilidade());
		}

		// Testa se não há jogadores
		if (ids.size() == 0) {
			return topzera;

		}

		while (j < top) {

			// Encontra a maior habilidade
			Integer maiorhabilidade = Collections.max(habilidades);

			// Encontra as ocorrências do maior valor e monta uma lista com seus índices
			List<Integer> indx = new LinkedList<>();
			for (i = 0; i < habilidades.size(); i++) {
				if (habilidades.get(i) == maiorhabilidade) {
					indx.add(i);
				}
			}

			// Os índices são usados para consultar a lista de ids e montar uma lista com os
			// ids selecionados
			List<Long> idselecionados = new LinkedList<>();
			for (i = 0; i < indx.size(); i++) {
				idselecionados.add(ids.get(indx.get(i)));
			}

			// Zera o maior valor para que a próxima iteração tome o próximo
			habilidades.set(Collections.min(indx), 0);

			// Adiciona o menor id na lista topzera
			topzera.add(Collections.min(idselecionados));
			j++;
		}

		return topzera;

	}

	@Desafio("buscarCorCamisaTimeDeFora")
	public String buscarCorCamisaTimeDeFora(Long timeDaCasa, Long timeDeFora) {
		long timedacasa = timeDaCasa;
		long timedefora = timeDeFora;

		// Testa a existência do time da casa
		int i = 0;
		for (Time checktime : times) {
			if (checktime.get_id() == timedacasa) {
				i++;
			}
		}
		if (i == 0) {
			throw new br.com.codenation.desafio.exceptions.TimeNaoEncontradoException();
		}

		// Testa a existência do time de fora
		i = 0;
		for (Time checktime : times) {
			if (checktime.get_id() == timedefora) {
				i++;
			}
		}
		if (i == 0) {
			throw new br.com.codenation.desafio.exceptions.TimeNaoEncontradoException();
		}

		String corcasa = null;
		String corfora = null;
		String corfora2 = null;
		for (Time checktimes : times) {
			if (checktimes.get_id() == timedacasa) {
				corcasa = checktimes.get_corUniformePrincipal();
			}
			if (checktimes.get_id() == timedefora) {
				corfora = checktimes.get_corUniformePrincipal();
				corfora2 = checktimes.get_corUniformeSecundario();
			}
		}
		if (corcasa != corfora) {
			return corfora;
		}
		return corfora2;
	}

}