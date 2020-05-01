package br.com.codenation.domain;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class TeamRepository {
  Map<Long, Team> teams = new HashMap<>();
  Map<Long, Player> players = new HashMap<>();
  Map<Long, Long> playersByTeam = new HashMap<>();

  public void addTeam(Team team) {
    teams.put(team.getId(), team);
  }

  public void addPlayer(Player player) {
    players.put(player.getId(), player);
    playersByTeam.put(player.getId(), player.getTeamId());
  }
}
