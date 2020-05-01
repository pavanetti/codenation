package br.com.codenation.domain;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import br.com.codenation.domain.Team;

public class TeamsCollection {
  private List<Team> teams;

  public TeamsCollection(Stream<Team> stream) {
    this.teams = stream.collect(Collectors.toList());
  }

  public List<Long> getIds() {
    return this.teams.stream().map(team -> team.getId()).collect(Collectors.toList());
  }
}
