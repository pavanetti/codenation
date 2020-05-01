package br.com.codenation.domain;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PlayersCollection {
  private List<Player> players;

  public PlayersCollection(Stream<Player> stream) {
    this.players = stream.collect(Collectors.toList());
  }

  public List<Long> getIds() {
    return players.stream().map(player -> player.getId()).collect(Collectors.toList());
  }

  public Optional<Player> best() {
    return players.stream().max(Comparator.comparing(Player::getAbility));
  }

}
