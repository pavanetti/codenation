package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import br.com.codenation.desafio.annotation.Desafio;
import br.com.codenation.desafio.app.MeuTimeInterface;
import br.com.codenation.desafio.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;

import br.com.codenation.domain.Player;
import br.com.codenation.domain.PlayersCollection;
import br.com.codenation.domain.Team;
import br.com.codenation.domain.TeamRepository;

public class DesafioMeuTimeApplication implements MeuTimeInterface {
	private final TeamRepository repository = new TeamRepository();

	@Desafio("incluirTime")
	public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal,
			String corUniformeSecundario) {
		if (this.repository.findTeam(id) != null)
			throw new IdentificadorUtilizadoException();

		this.repository.addTeam(new Team(id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario));
	}

	@Desafio("incluirJogador")
	public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade,
			BigDecimal salario) {
		if (this.repository.findPlayer(id) != null)
			throw new IdentificadorUtilizadoException();
		if (this.repository.findTeam(idTime) == null)
			throw new TimeNaoEncontradoException();

		this.repository.addPlayer(new Player(id, idTime, nome, dataNascimento, nivelHabilidade, salario));
	}

	@Desafio("definirCapitao")
	public void definirCapitao(Long idJogador) {
		Player player = this.repository.findPlayer(idJogador);
		if (player == null)
			throw new JogadorNaoEncontradoException();

		Team team = this.repository.findTeam(player.getTeamId());
		team.setCaptain(player);
		this.repository.updateTeam(team);
	}

	@Desafio("buscarCapitaoDoTime")
	public Long buscarCapitaoDoTime(Long idTime) {
		Team team = this.repository.findTeam(idTime);
		if (team == null)
			throw new TimeNaoEncontradoException();

		Player captain = team.getCaptain();
		if (captain == null)
			throw new CapitaoNaoInformadoException();

		return captain.getId();
	}

	@Desafio("buscarNomeJogador")
	public String buscarNomeJogador(Long idJogador) {
		Player player = this.repository.findPlayer(idJogador);
		if (player == null)
			throw new JogadorNaoEncontradoException();

		return player.getName();
	}

	@Desafio("buscarNomeTime")
	public String buscarNomeTime(Long idTime) {
		Team team = this.repository.findTeam(idTime);
		if (team == null)
			throw new TimeNaoEncontradoException();

		return team.getName();
	}

	@Desafio("buscarJogadoresDoTime")
	public List<Long> buscarJogadoresDoTime(Long idTime) {
		if (this.repository.findTeam(idTime) == null)
			throw new TimeNaoEncontradoException();

		return this.repository.findPlayersByTeam(idTime).getIds();
	}

	@Desafio("buscarMelhorJogadorDoTime")
	public Long buscarMelhorJogadorDoTime(Long idTime) {
		if (this.repository.findTeam(idTime) == null)
			throw new TimeNaoEncontradoException();

		PlayersCollection players = this.repository.findPlayersByTeam(idTime);
		Optional<Player> theBest = players.best();
		return theBest.map(Player::getId).orElse(0L);
	}

	@Desafio("buscarJogadorMaisVelho")
	public Long buscarJogadorMaisVelho(Long idTime) {
		if (this.repository.findTeam(idTime) == null)
			throw new TimeNaoEncontradoException();

		PlayersCollection players = this.repository.findPlayersByTeam(idTime);
		Optional<Player> theOldest = players.oldest();
		return theOldest.map(Player::getId).orElse(0L);
	}

	@Desafio("buscarTimes")
	public List<Long> buscarTimes() {
		return this.repository.retrieveAllTeams().getIds();
	}

	@Desafio("buscarJogadorMaiorSalario")
	public Long buscarJogadorMaiorSalario(Long idTime) {
		if (this.repository.findTeam(idTime) == null)
			throw new TimeNaoEncontradoException();

		PlayersCollection players = this.repository.findPlayersByTeam(idTime);
		Optional<Player> theMoreExpensive = players.moreExpensive();
		return theMoreExpensive.map(Player::getId).orElse(0L);
	}

	@Desafio("buscarSalarioDoJogador")
	public BigDecimal buscarSalarioDoJogador(Long idJogador) {
		Player player = this.repository.findPlayer(idJogador);
		if (player == null)
			throw new JogadorNaoEncontradoException();

		return player.getSalary();
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
