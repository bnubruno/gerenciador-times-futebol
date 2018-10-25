package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import br.com.codenation.Time;
import br.com.codenation.Jogador;

import br.com.codenation.desafio.annotation.Desafio;
import br.com.codenation.desafio.app.MeuTimeInterface;
import sun.rmi.runtime.Log;

public class DesafioMeuTimeApplication implements MeuTimeInterface {

    ArrayList<Time> times = new ArrayList<>();
    ArrayList<Jogador> jogadores = new ArrayList<>();

    @Desafio("incluirTime")
    public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
        Time time = new Time(id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario);
        if (times.isEmpty())
            times.add(time);
        else {
            for (Time t : times) {
                if (t.getId().equals(id)) {
                    throw new br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException();
                }

            }
            times.add(time);

        }

    }

    @Desafio("incluirJogador")
    public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade,
                               BigDecimal salario) {
        Jogador jogador = new Jogador(id, idTime, nome, dataNascimento, nivelHabilidade, salario);


        for (Jogador j : jogadores) {
            if (j.getId().equals(id))
                throw new br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException();
        }
        Time time = null;

        for (Time t : times) {
            if (t.getId().equals(idTime)) {
                time = t;
            }
        }

        if (time != null){
            time.addJogadores(jogador);
        }else{
            throw new br.com.codenation.desafio.exceptions.TimeNaoEncontradoException();
        }

        jogadores.add(jogador);

    }

    // Sempre dava 'break' e sempre dava JogadorNaoEncontradoException
    @Desafio("definirCapitao")
    public void definirCapitao(Long idJogador) {
        for (Time t : times) {
            List<Jogador> jogs = t.getJogadores();
            for (Jogador j : jogs) {
                if (j.getId().equals(idJogador)) {
                    t.setIdCapitao(idJogador);
                    return;
                }
            }
        }
        throw new br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException();
    }

    @Desafio("buscarCapitaoDoTime")
    public Long buscarCapitaoDoTime(Long idTime) {
        for (Time t : times) {
            if (t.getId().equals(idTime)) {
                if (t.getIdCapitao() == null) {
                    throw new br.com.codenation.desafio.exceptions.CapitaoNaoInformadoException();
                } else {
                    return t.getIdCapitao();
                }
            }
        }
        throw new br.com.codenation.desafio.exceptions.TimeNaoEncontradoException();
    }


    @Desafio("buscarNomeJogador")
    public String buscarNomeJogador(Long idJogador) {
        for (Jogador j : jogadores) {
            if (j.getId().equals(idJogador))
                return j.getNome();
        }
        throw new br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException();
    }

    @Desafio("buscarNomeTime")
    public String buscarNomeTime(Long idTime) {
        for (Time t : times) {
            if (t.getId().equals(idTime))
                return t.getNome();
        }
        throw new br.com.codenation.desafio.exceptions.TimeNaoEncontradoException();
    }

    @Desafio("buscarJogadoresDoTime")
    public List<Long> buscarJogadoresDoTime(Long idTime) {
        List<Jogador> jogs = null;
        for (Time t : times) {
            if (t.getId().equals(idTime)) {
                jogs = t.getJogadores();

                List<Long> listId = new ArrayList<>();
                for (Jogador j : jogs) {
                    listId.add(j.getId());
                }

                Collections.sort(listId);

                return listId;
            }
        }

        throw new br.com.codenation.desafio.exceptions.TimeNaoEncontradoException();
    }

    @Desafio("buscarMelhorJogadorDoTime")
    public Long buscarMelhorJogadorDoTime(Long idTime) {
        Time time = null;
        for (Time t : times) {
            if (t.getId().equals(idTime)) {
                time = t;
            }
        }

        if (time == null) {
            throw new br.com.codenation.desafio.exceptions.TimeNaoEncontradoException();
        }

        Long melhorId = 0l;
        int melhorHabilidade = 0;
        if (time.getJogadores() == null){
            throw new br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException();
        }
        for (Jogador j : time.getJogadores()) {
            if (j.getNivelHabilidade() > melhorHabilidade) {
                melhorHabilidade = j.getNivelHabilidade();
                melhorId = j.getId();
            }
        }
        return melhorId;
    }

    @Desafio("buscarJogadorMaisVelho")
    public Long buscarJogadorMaisVelho(Long idTime) {
        Time time = null;
        for (Time t : times) {
            if (t.getId().equals(idTime)) {
                time = t;
            }
        }

        if (time == null) {
            throw new br.com.codenation.desafio.exceptions.TimeNaoEncontradoException();
        }


        Jogador melhor = null;
        for (Jogador j : time.getJogadores()) {
            if (melhor == null)
                melhor = j;
            if (j.getDataNascimento().isEqual(melhor.getDataNascimento())) {
                if(j.getId().compareTo(melhor.getId()) == -1){
                    melhor = j;
                }
            }
            else if (j.getDataNascimento().isBefore(melhor.getDataNascimento())){
                melhor = j;
            }
        }
        return melhor.getId();
    }

    @Desafio("buscarTimes")
    public List<Long> buscarTimes() {
        List<Long> idTimes = new ArrayList<>();
        for (Time t : times) {
            idTimes.add(t.getId());
        }
        Collections.sort(idTimes);
        return idTimes;
    }

    @Desafio("buscarJogadorMaiorSalario")
    public Long buscarJogadorMaiorSalario(Long idTime) {
        Time time = null;
        for (Time t : times) {
            if (t.getId().equals(idTime)) {
                time = t;
            }
        }

        if (time == null) {
            throw new br.com.codenation.desafio.exceptions.TimeNaoEncontradoException();
        }
        Jogador jogador = null;
        Long melhorId = 0l;
        BigDecimal maiorSalario = null;
        for (Jogador j : time.getJogadores()) {
            if (maiorSalario == null)
                maiorSalario = j.getSalario();
            if (j.getSalario().longValue() > maiorSalario.longValue()) {
                maiorSalario = j.getSalario();
                melhorId = j.getId();
            }
        }


        return melhorId;
    }

    @Desafio("buscarSalarioDoJogador")
    public BigDecimal buscarSalarioDoJogador(Long idJogador) {
        for (Jogador j : jogadores) {
            if (j.getId().equals(idJogador)) {
                return j.getSalario();
            }
        }
        throw new br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException();
    }

    @Desafio("buscarTopJogadores")
    public List<Long> buscarTopJogadores(Integer top) {

        Collections.sort(jogadores, new Comparator<Jogador>() {
            public int compare(Jogador j1, Jogador j2){
                int result = j1.getNivelHabilidade().compareTo(j2.getNivelHabilidade());
                if (result == 0) {
                    result = j2.getId().compareTo(j1.getId());
                }
                return result;
            }
        });

        List<Jogador> topJogadores = jogadores.subList(Math.max(jogadores.size() - top.intValue(), 0), jogadores.size());

        List<Long> topIds = new ArrayList<>();
        for (Jogador jogador : topJogadores) {
            topIds.add(jogador.getId());
        }

        if (jogadores.isEmpty()){
            topIds = null;
        }
        Collections.reverse(topIds);
        return topIds;
    }


    @Desafio("buscarCorCamisaTimeDeFora")
    public String buscarCorCamisaTimeDeFora(Long timeDaCasa, Long timeDeFora) {
        Time timeCasa = null;
        Time timeAdversario = null;
        for (Time t : times) {
            if (t.getId().equals(timeDaCasa))
                timeCasa = t;
            if (t.getId().equals(timeDeFora))
                timeAdversario = t;
        }

        if (timeCasa == null || timeAdversario == null){
            throw new br.com.codenation.desafio.exceptions.TimeNaoEncontradoException();
        }

        if (timeCasa.getCorUniformePrincipal().equals(timeAdversario.getCorUniformePrincipal()))
            return timeAdversario.getCorUniformeSecundario();
        else {
            return timeAdversario.getCorUniformePrincipal();
        }
    }
}
