package br.com.codenation.domain;

import java.time.LocalDate;

import br.com.codenation.domain.Player;

public class Team {
  private Long id;
  private String name;
  private LocalDate createdAt;
  private String mainColor;
  private String secundaryColor;
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
}
