package br.com.codenation.domain;

import java.time.LocalDate;

import br.com.codenation.domain.Player;

public class Team {
  private final Long id;
  private final String name;
  private final LocalDate createdAt;
  private final String mainColor;
  private final String secundaryColor;
  private Player captain;

  public Team(Long id, String name, LocalDate createdAt, String mainColor, String secundaryColor) {
    this.id = id;
    this.name = name;
    this.createdAt = createdAt;
    this.mainColor = mainColor;
    this.secundaryColor = secundaryColor;
  }

  public Long getId() {
    return this.id;
  }

  public void setCaptain(Player captain) {
    this.captain = captain;
  }

  public Player getCaptain() {
    return this.captain;
  }

  public String getName() {
    return this.name;
  }

  public String getMainColor() {
    return this.mainColor;
  }

  public String getSecundaryColor() {
    return this.secundaryColor;
  }

  public LocalDate getCreatedAt() { return this.createdAt; }
}
