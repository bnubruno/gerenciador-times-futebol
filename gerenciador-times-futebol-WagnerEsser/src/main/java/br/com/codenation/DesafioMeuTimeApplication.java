package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import br.com.codenation.desafio.annotation.Desafio;
import br.com.codenation.desafio.app.MeuTimeInterface;

public class DesafioMeuTimeApplication implements MeuTimeInterface {
    ArrayList<Time> times = new ArrayList<>();
    ArrayList<Jogador> jogadores = new ArrayList<>();

    public boolean existeTime(Long idTime) {
        boolean existe = false;
        
        for (Time time : this.times) {
            if (time.getId().equals(idTime)) {
                existe = true;
                break;
            }
        }
        return existe;
    }

    public boolean existeJogador(Long idJogador) {
        boolean existe = false;
        
        for (Jogador jogador : this.jogadores) {
            if (jogador.getId().equals(idJogador)) {
                existe = true;
                break;
            }
        }
        return existe;
    }
    
    @Desafio("incluirTime")
    public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
        boolean existe = existeTime(id);
        
        if (!existe) {
            Time time = new Time(id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario);
            this.times.add(time);
        } else {
            throw new br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException();
        }
    }

    @Desafio("incluirJogador")
    public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {
        boolean existe = existeTime(idTime);
        
        if (!existe) {
            throw new br.com.codenation.desafio.exceptions.TimeNaoEncontradoException();
        }
        existe = existeJogador(id);
        if (!existe) {
            Jogador jogador = new Jogador(id, idTime, nome, dataNascimento, nivelHabilidade, salario);
            this.jogadores.add(jogador);
        } else {
            throw new br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException();
        }
    }
    
	@Desafio("definirCapitao")
    public void definirCapitao(Long idJogador) {
        Long idTime = null;
        boolean existe = existeJogador(idJogador);
        
        if (!existe) {
			throw new br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException();
		} else {
			for (Jogador jogador : this.jogadores) {
				if (jogador.getId().equals(idJogador)) {
					if (jogador.getIdTime() != null){
						idTime = jogador.getIdTime();
						jogador.setCapitao(true);
					} else {
						throw new br.com.codenation.desafio.exceptions.TimeNaoEncontradoException();
					}
					break;
				}
			}
		}
        for (Jogador jogador : this.jogadores) {
            if (jogador.getIdTime().equals(idTime) && jogador.getCapitao() && !jogador.getId().equals(idJogador)) {
				// SE jogador for do time do novo capitão E jogador for capitão E jogador não for o novo capitão: tornar NÃO capitão
                jogador.setCapitao(false);
                break;
            }
        }
    }

    @Desafio("buscarCapitaoDoTime")
    public Long buscarCapitaoDoTime(Long idTime) {
        Long idJogador = null;
        boolean existe = existeTime(idTime);
        
        if (!existe) {
            throw new br.com.codenation.desafio.exceptions.TimeNaoEncontradoException();
        } else {
			for (Jogador jogador : this.jogadores) {
				if (jogador.getIdTime().equals(idTime) && jogador.getCapitao()) {
					// SE jogador for do time E jogador for capitão: retornar ID do jogador
					idJogador = jogador.getId();
					return idJogador;
				}
			}
			throw new br.com.codenation.desafio.exceptions.CapitaoNaoInformadoException();
		}
    }

    @Desafio("buscarNomeJogador")
    public String buscarNomeJogador(Long idJogador) {
        String nome = null;
        boolean existe = existeJogador(idJogador);
        
        if (!existe) {
            throw new br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException();
        } else {
			for (Jogador jogador : this.jogadores) {
				if (jogador.getId().equals(idJogador)) {
					nome = jogador.getNome();
					break;
				}
			}
			return nome;
		}
    }

    @Desafio("buscarNomeTime")
    public String buscarNomeTime(Long idTime) {
        String nome = null;
        boolean existe = existeTime(idTime);
        
        if (!existe) {
            throw new br.com.codenation.desafio.exceptions.TimeNaoEncontradoException();
        } else {
			for (Time time : this.times) {
				if (time.getId().equals(idTime)) {
					nome = time.getNome();
					break;
				}
			}
			return nome;
		}
    }

    @Desafio("buscarJogadoresDoTime")
    public List<Long> buscarJogadoresDoTime(Long idTime) {
        boolean existe = existeTime(idTime);
        
        if (!existe) {
            throw new br.com.codenation.desafio.exceptions.TimeNaoEncontradoException();
        } else {
			List<Long> lista = new ArrayList<Long>();
			for (Jogador jogador : this.jogadores) {
				if (jogador.getIdTime().equals(idTime)) {
					// SE jogador for do time: adicione na lista
					lista.add(jogador.getId());
				}
			}
			Collections.sort(lista);
			return (lista);
		}
    }

    @Desafio("buscarMelhorJogadorDoTime")
    public Long buscarMelhorJogadorDoTime(Long idTime) {
        boolean existe = existeTime(idTime);
        
        if (!existe) {
            throw new br.com.codenation.desafio.exceptions.TimeNaoEncontradoException();
        } else {
			Jogador melhor_jogador = null;
			
			for (Jogador jogador : this.jogadores) {
				if (jogador.getIdTime().equals(idTime)) {
					if (melhor_jogador == null){
						melhor_jogador = jogador;
					}else{
						if (jogador.getNivelHabilidade() > melhor_jogador.getNivelHabilidade() || (jogador.getNivelHabilidade().equals(melhor_jogador.getNivelHabilidade()) && jogador.getId() < melhor_jogador.getId())){
							melhor_jogador = jogador;
						}
					}
				}
			}
			return melhor_jogador.getId();
		}
    }

    @Desafio("buscarJogadorMaisVelho")
    public Long buscarJogadorMaisVelho(Long idTime) {
        boolean existe = existeTime(idTime);
		Jogador jogador_mais_valho = null;
        
        if (!existe) {
            throw new br.com.codenation.desafio.exceptions.TimeNaoEncontradoException();
        } else {
			for (Jogador jogador : this.jogadores) {
				if (jogador.getIdTime().equals(idTime)) {
					if (jogador_mais_valho == null) {
						jogador_mais_valho = jogador;
					}
					if ((jogador.getDataNascimento().isBefore(jogador_mais_valho.getDataNascimento())) || (jogador.getDataNascimento().isEqual(jogador_mais_valho.getDataNascimento()) && jogador.getId() < jogador_mais_valho.getId())) {
						jogador_mais_valho = jogador;
					}
				}
			}
			return jogador_mais_valho.getId();
		}
    }

    @Desafio("buscarTimes")
    public List<Long> buscarTimes() {
        List<Long> lista = new ArrayList<>();
        
        for (Time time : this.times) {
            lista.add(time.getId());
        }
        Collections.sort(lista);
        return lista;
    }

    @Desafio("buscarJogadorMaiorSalario")
    public Long buscarJogadorMaiorSalario(Long idTime) {
        boolean existe = existeTime(idTime);
        
        if (!existe) {
            throw new br.com.codenation.desafio.exceptions.TimeNaoEncontradoException();
        }else{
			Jogador jogador_maior_salario = null;
			
			for (Jogador jogador : this.jogadores) {
				if (jogador.getIdTime().equals(idTime)) {
					if (jogador_maior_salario == null) {
						jogador_maior_salario = jogador;
					}
					if ((jogador.getSalario().compareTo(jogador_maior_salario.getSalario()) > 0) || (jogador.getSalario().compareTo(jogador_maior_salario.getSalario()) == 0 && jogador.getId() < jogador_maior_salario.getId())) {
						jogador_maior_salario = jogador;
					}
				}
			}
			return jogador_maior_salario.getId();
		}
    }

    @Desafio("buscarSalarioDoJogador")
    public BigDecimal buscarSalarioDoJogador(Long idJogador) {
        BigDecimal salario = null;
        for (Jogador jogador : this.jogadores) {
            if (jogador.getId().equals(idJogador)) {
                salario = jogador.getSalario();
                break;
            }
        }
        if (salario == null) {
            throw new br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException();
        }

        return salario;
    }

    @Desafio("buscarTopJogadores")
    public List<Long> buscarTopJogadores(Integer top) {
        List<Long> lista = new ArrayList<>();
        
        Collections.sort(jogadores);
        for (Jogador jogador : jogadores.subList(0, top)) {
            lista.add(jogador.getId());
        }
        return lista;
    }


    @Desafio("buscarCorCamisaTimeDeFora")
    public String buscarCorCamisaTimeDeFora(Long timeDaCasa, Long timeDeFora) {
        Time time_da_casa = null;
        Time time_de_fora = null;
        
        for (Time time : this.times) {
            if (time.getId().equals(timeDaCasa)) {
                time_da_casa = time;
            }
            if (time.getId().equals(timeDeFora)) {
                time_de_fora = time;
            }
        }

        if (time_de_fora == null || time_da_casa == null) {
            throw new br.com.codenation.desafio.exceptions.TimeNaoEncontradoException();
        }
        if (time_de_fora.getCorUniformePrincipal().equals(time_da_casa.getCorUniformePrincipal())) {
            return time_de_fora.getCorUniformeSecundario();
        }
        return time_de_fora.getCorUniformePrincipal();
    }
    
    // ========== PARA TESTES ========== //
    
    @Desafio("incluirJogadorSemTime")
    public void incluirJogadorSemTime(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {
        Jogador jogador = new Jogador(id, idTime, nome, dataNascimento, nivelHabilidade, salario);
        this.jogadores.add(jogador);
    }
    @Desafio("removerJogador")
    public void removerJodador(Long idJogador) {
        for (Jogador jogador : this.jogadores) {
			if (jogador.getId().equals(idJogador)) {
				this.jogadores.remove(jogador);
				break;
			}
        }
    }
    @Desafio("buscarJogadorObject")
	public void buscarJogadorObject(Long idJogador) {
		 for (Jogador jogador : this.jogadores) {
			if (jogador.getId().equals(idJogador)) {
				System.out.println(jogador);
	            break;
			}
        }
    }
    @Desafio("updateNomeJogador")
    public void updateNomeJogador(Long idJogador, String nome) {
        for (Jogador jogador : this.jogadores) {
			if (jogador.getId().equals(idJogador)) {
				jogador.setNome(nome);;
				break;
			}
        }
    }
    @Desafio("updateDataNascimento")
    public void updateDataNascimento(Long idJogador, LocalDate data) {
        for (Jogador jogador : this.jogadores) {
			if (jogador.getId().equals(idJogador)) {
				jogador.setDataNascimento(data);
				break;
			}
        }
    }
    @Desafio("updateNivelHabilidade")
    public void updateNivelHabilidade(Long idJogador, Integer nivel) {
        for (Jogador jogador : this.jogadores) {
			if (jogador.getId().equals(idJogador)) {
				jogador.setNivelHabilidade(nivel);
				break;
			}
        }
    }
    @Desafio("updateIdJogador")
    public void updateIdJogador(Long idJogador, Long id) {
        for (Jogador jogador : this.jogadores) {
			if (jogador.getId().equals(idJogador)) {
				jogador.setId(id);
				break;
			}
        }
    }
    @Desafio("updateTime")
    public void updateTime(Long idJogador, Long idTime) {
        for (Jogador jogador : this.jogadores) {
			if (jogador.getId().equals(idJogador)) {
				jogador.setIdTime(idTime);
				break;
			}
        }
    }
    @Desafio("updateSalario")
    public void updateSalario(Long idJogador, BigDecimal salario) {
        for (Jogador jogador : this.jogadores) {
			if (jogador.getId().equals(idJogador)) {
				jogador.setSalario(salario);
				break;
			}
        }
    }
    @Desafio("buscarTimeObject")
	public void buscarTimeObject(Long idTime) {
		 for (Time time : this.times) {
			if (time.getId().equals(idTime)) {
				System.out.println(time);
	            break;
			}
        }
    }
    @Desafio("updateIdTime")
	public void updateIdTime(Long idTime, Long newIdTime) {
		 for (Time time : this.times) {
			if (time.getId().equals(idTime)) {
				time.setId(newIdTime);
	            break;
			}
        }
    }
    @Desafio("updateNomeTime")
	public void updateNomeTime(Long idTime, String nome) {
		 for (Time time : this.times) {
			if (time.getId().equals(idTime)) {
				time.setNome(nome);
	            break;
			}
        }
    }
    @Desafio("getDataCriacao")
	public void getDataCriacao(Long idTime) {
		 for (Time time : this.times) {
			if (time.getId().equals(idTime)) {
				System.out.println(time.getDataCriacao());
	            break;
			}
        }
    }
    @Desafio("getCapitao")
	public void getCapitao(Long idTime) {
		 for (Time time : this.times) {
			if (time.getId().equals(idTime)) {
				System.out.println(time.getCapitao());
	            break;
			}
        }
    }
    @Desafio("updateDataCriacao")
	public void updateDataCriacao(Long idTime, LocalDate data) {
		 for (Time time : this.times) {
			if (time.getId().equals(idTime)) {
				time.setDataCriacao(data);
	            break;
			}
        }
    }
    @Desafio("updateCapitao")
	public void updateCapitao(Long idTime, Long novoCapitao) {
		 for (Time time : this.times) {
			if (time.getId().equals(idTime)) {
				time.setCapitao(novoCapitao);
	            break;
			}
        }
    }
    @Desafio("updateCorUniformeSecundario")
	public void updateCorUniformeSecundario(Long idTime, String cor) {
		 for (Time time : this.times) {
			if (time.getId().equals(idTime)) {
				time.setCorUniformeSecundario(cor);
	            break;
			}
        }
    }
    @Desafio("updateCorUniformePrincipal")
	public void updateCorUniformePrincipal(Long idTime, String cor) {
		 for (Time time : this.times) {
			if (time.getId().equals(idTime)) {
				time.setCorUniformePrincipal(cor);
	            break;
			}
        }
    }
    // ================================= //

}
