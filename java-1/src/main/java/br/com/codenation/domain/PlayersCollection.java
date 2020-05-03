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
    return players.stream().map(Player::getId).sorted().collect(Collectors.toList());
  }

  public Optional<Player> best() {
    return players.stream().max(Comparator.comparing(Player::getAbility));
  }

  public Optional<Player> oldest() {
    Comparator<Player> comparePlayers = Comparator.comparing(Player::getBirthday).thenComparing(Player::getId);
    return players.stream().sorted(comparePlayers).findFirst();
  }

  public Optional<Player> moreExpensive() {
    Comparator<Player> comparePlayers = Comparator.comparing(Player::getSalary).reversed().thenComparing(Player::getId);
    return players.stream().sorted(comparePlayers).findFirst();
  }

  public PlayersCollection top(Integer count) {
    Comparator<Player> comparePlayers = Comparator.comparing(Player::getAbility).reversed().thenComparing(Player::getId);
    Stream<Player> theTop = players.stream().sorted(comparePlayers).limit(count);
    return new PlayersCollection(theTop);
  }

}
