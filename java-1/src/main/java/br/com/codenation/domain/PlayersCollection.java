package br.com.codenation.domain;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PlayersCollection {
  private final List<Player> players;

  public PlayersCollection(Stream<Player> stream) {
    this.players = stream.collect(Collectors.toList());
  }

  public List<Long> getIds() {
    return players.stream().map(Player::getId).collect(Collectors.toList());
  }

  public Optional<Player> best() {
    return players.stream().max(Comparator.comparing(Player::getAbility));
  }

  public Optional<Player> oldest() {
    return players.stream().max(Comparator.comparing(Player::getBirthday));
  }

  public Optional<Player> moreExpensive() {
    return players.stream().max(Comparator.comparing(Player::getSalary));
  }

  public PlayersCollection top(Integer count) {
    Comparator<Player> comparePlayers = (p1, p2) -> p1.getAbility().equals(p2.getAbility())
        ? p1.getId().compareTo(p2.getId())
        : p1.getAbility().compareTo(p2.getAbility());
    Stream<Player> theTop = players.stream().sorted(comparePlayers).limit(count);
    return new PlayersCollection(theTop);
  }

}
