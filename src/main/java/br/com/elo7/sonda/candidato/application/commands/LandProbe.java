package br.com.elo7.sonda.candidato.application.commands;

import br.com.elo7.sonda.candidato.domain.PlanetId;

import java.util.List;

public record LandProbe(PlanetId planetId, List<ProbeCommands> probeCommands) {
}
