package br.com.codenation.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Player {
  private final Long id;
  private final Long teamId;
  private final String name;
  private final LocalDate birthday;
  private final Integer ability;
  private final BigDecimal salary;

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

  public Integer getAbility() {
    return this.ability;
  }

  public LocalDate getBirthday() {
    return this.birthday;
  }

  public BigDecimal getSalary() {
    return this.salary;
  }
}
