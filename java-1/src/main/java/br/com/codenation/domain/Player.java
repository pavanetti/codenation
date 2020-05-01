package br.com.codenation.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Player {
  private Long id;
  private Long teamId;
  private String name;
  private LocalDate birthday;
  private Integer ability;
  private BigDecimal salary;

  public Player(Long id, Long teamId, String name, LocalDate birthday, Integer ability, BigDecimal salary) {
    this.id = id;
    this.teamId = teamId;
    this.name = name;
    this.birthday = birthday;
    this.ability = ability;
    this.salary = salary;
  }

  public Long getId() {
    return this.id;
  }

  public Long getTeamId() {
    return this.teamId;
  }

  public String getName() {
    return this.name;
  }
}
