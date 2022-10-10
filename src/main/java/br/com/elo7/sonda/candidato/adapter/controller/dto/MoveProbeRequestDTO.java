package br.com.elo7.sonda.candidato.adapter.controller.dto;


import br.com.elo7.sonda.candidato.adapter.controller.validator.ListOfEnum;
import br.com.elo7.sonda.candidato.domain.Command;

import java.util.List;

public record MoveProbeRequestDTO(
  @ListOfEnum(enumClass = Command.class)
  List<String> commands
){}
