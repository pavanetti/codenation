package br.com.codenation.domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TeamsCollection {
  private final List<Team> teams;

  public TeamsCollection(Stream<Team> stream) {
    this.teams = stream.collect(Collectors.toList());
  }

  public List<Long> getIds() {
    return this.teams.stream().map(Team::getId).sorted().collect(Collectors.toList());
  }
}
