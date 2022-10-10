package br.com.elo7.sonda.candidato.adapter.controller.dto;

import br.com.elo7.sonda.candidato.adapter.controller.validator.ValueOfEnum;
import br.com.elo7.sonda.candidato.application.commands.LandProbes;
import br.com.elo7.sonda.candidato.domain.Direction;
import br.com.elo7.sonda.candidato.domain.Position;
import br.com.elo7.sonda.candidato.domain.ProbeName;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public record ProbeRequestDTO(
  @NotNull
  @NotEmpty
  String name,
  @NotNull
  @Min(1)
  int x,
  @NotNull
  @Min(1)
  int y,
  @NotNull
  @Length(min = 1, max = 1)
  @ValueOfEnum(enumClass = Direction.class)
  String direction
) {

  public LandProbes.LandingProbe toLandingProbe(){
    return new LandProbes.LandingProbe(new ProbeName(this.name()), new Position(x, y), Direction.valueOf(direction));
  }

}
