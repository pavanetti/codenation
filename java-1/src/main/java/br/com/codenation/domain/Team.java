package br.com.codenation.domain;

import java.time.LocalDate;

public class Team {
  private Long id;
  private String name;
  private LocalDate createdAt;
  private String mainColor;
  private String secundaryColor;

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
}
