package br.com.elo7.sonda.candidato.application.commands;

import br.com.elo7.sonda.candidato.domain.Command;
import br.com.elo7.sonda.candidato.domain.PlanetId;
import br.com.elo7.sonda.candidato.domain.ProbeId;

import java.util.List;

public record MoveProbe(PlanetId planetId, ProbeId probeId, List<Command> commands) {

}
