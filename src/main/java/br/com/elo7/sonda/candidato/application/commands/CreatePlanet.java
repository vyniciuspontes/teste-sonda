package br.com.elo7.sonda.candidato.application.commands;

import br.com.elo7.sonda.candidato.domain.PlanetDimension;
import br.com.elo7.sonda.candidato.domain.PlanetName;

public record CreatePlanet(PlanetName planetName, PlanetDimension width, PlanetDimension height) {
}
