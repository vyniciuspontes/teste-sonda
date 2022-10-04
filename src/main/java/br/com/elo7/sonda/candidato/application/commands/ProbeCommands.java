package br.com.elo7.sonda.candidato.application.commands;

import br.com.elo7.sonda.candidato.domain.Command;
import br.com.elo7.sonda.candidato.domain.Probe;

import java.util.List;

public record ProbeCommands(Probe probe, List<Command> commands) {
}
