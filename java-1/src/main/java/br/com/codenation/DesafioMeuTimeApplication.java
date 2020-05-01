package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import br.com.codenation.desafio.annotation.Desafio;
import br.com.codenation.desafio.app.MeuTimeInterface;

import br.com.codenation.domain.Player;
import br.com.codenation.domain.PlayersCollection;
import br.com.codenation.domain.Team;
import br.com.codenation.domain.TeamRepository;

public class DesafioMeuTimeApplication implements MeuTimeInterface {
	private TeamRepository repository = new TeamRepository();

	@Desafio("incluirTime")
	public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal,
			String corUniformeSecundario) {
		this.repository.addTeam(new Team(id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario));
	}

	@Desafio("incluirJogador")
	public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade,
			BigDecimal salario) {
		this.repository.addPlayer(new Player(id, idTime, nome, dataNascimento, nivelHabilidade, salario));
	}

	@Desafio("definirCapitao")
	public void definirCapitao(Long idJogador) {
		Player player = this.repository.findPlayer(idJogador);
		Team team = this.repository.findTeam(player.getTeamId());
		team.setCaptain(player);
		this.repository.updateTeam(team);
	}

	@Desafio("buscarCapitaoDoTime")
	public Long buscarCapitaoDoTime(Long idTime) {
		Team team = this.repository.findTeam(idTime);
		return team.getCaptain().getId();
	}

	@Desafio("buscarNomeJogador")
	public String buscarNomeJogador(Long idJogador) {
		Player player = this.repository.findPlayer(idJogador);
		return player.getName();
	}

	@Desafio("buscarNomeTime")
	public String buscarNomeTime(Long idTime) {
		Team team = this.repository.findTeam(idTime);
		return team.getName();
	}

	@Desafio("buscarJogadoresDoTime")
	public List<Long> buscarJogadoresDoTime(Long idTime) {
		return this.repository.findPlayersByTeam(idTime).getIds();
	}

	@Desafio("buscarMelhorJogadorDoTime")
	public Long buscarMelhorJogadorDoTime(Long idTime) {
		PlayersCollection players = this.repository.findPlayersByTeam(idTime);
		Optional<Player> theBest = players.best();
		return theBest.map(player -> player.getId()).orElse(0L);
	}

	@Desafio("buscarJogadorMaisVelho")
	public Long buscarJogadorMaisVelho(Long idTime) {
		PlayersCollection players = this.repository.findPlayersByTeam(idTime);
		Optional<Player> theOldest = players.oldest();
		return theOldest.map(player -> player.getId()).orElse(0L);
	}

	@Desafio("buscarTimes")
	public List<Long> buscarTimes() {
		return this.repository.retrieveAllTeams().getIds();
	}

	@Desafio("buscarJogadorMaiorSalario")
	public Long buscarJogadorMaiorSalario(Long idTime) {
		PlayersCollection players = this.repository.findPlayersByTeam(idTime);
		Optional<Player> theMoreExpensive = players.moreExpensive();
		return theMoreExpensive.map(player -> player.getId()).orElse(0L);
	}

	@Desafio("buscarSalarioDoJogador")
	public BigDecimal buscarSalarioDoJogador(Long idJogador) {
		return this.repository.findPlayer(idJogador).getSalary();
	}

	@Desafio("buscarTopJogadores")
	public List<Long> buscarTopJogadores(Integer top) {
		PlayersCollection players = this.repository.retrieveAllPlayers();
		PlayersCollection tops = players.top(top);
		return tops.getIds();
	}

	@Desafio("buscarCorCamisaTimeDeFora")
	public String buscarCorCamisaTimeDeFora(Long timeDaCasa, Long timeDeFora) {
		Team homeTeam = this.repository.findTeam(timeDaCasa);
		Team guestTeam = this.repository.findTeam(timeDeFora);
		if (guestTeam.getMainColor().equals(homeTeam.getMainColor()))
			return guestTeam.getSecundaryColor();
		return guestTeam.getMainColor();
	}

}
