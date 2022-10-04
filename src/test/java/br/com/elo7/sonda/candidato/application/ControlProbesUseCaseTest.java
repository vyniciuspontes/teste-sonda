package br.com.elo7.sonda.candidato.application;

import br.com.elo7.sonda.candidato.application.commands.LandProbes;
import br.com.elo7.sonda.candidato.application.commands.MoveProbe;
import br.com.elo7.sonda.candidato.domain.Command;
import br.com.elo7.sonda.candidato.domain.Direction;
import br.com.elo7.sonda.candidato.domain.Planet;
import br.com.elo7.sonda.candidato.domain.PlanetGenerator;
import br.com.elo7.sonda.candidato.domain.PlanetId;
import br.com.elo7.sonda.candidato.domain.PlanetRepository;
import br.com.elo7.sonda.candidato.domain.Position;
import br.com.elo7.sonda.candidato.domain.Probe;
import br.com.elo7.sonda.candidato.domain.ProbeGenerator;
import br.com.elo7.sonda.candidato.domain.ProbeName;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class ControlProbesUseCaseTest {

  @Test
  public void should_land_probes_successfully() {

    PlanetRepository planetRepository = mock(PlanetRepository.class);
    final Planet generatedPlanet = spy(PlanetGenerator.gen());
    final LandProbes.LandingProbe landingProbe1 = new LandProbes.LandingProbe(new ProbeName("Probe Name 1"), new Position(0, 0), Direction.N);
    final LandProbes.LandingProbe landingProbe2 = new LandProbes.LandingProbe(new ProbeName("Probe Name 2"), new Position(1, 2), Direction.N);

    when(planetRepository.findById(any())).thenReturn(Optional.of(generatedPlanet));
    when(planetRepository.save(any())).thenReturn(generatedPlanet);

    ControlProbesUseCase controlProbesUseCase = new PlanetService(planetRepository);

    LandProbes command = new LandProbes(generatedPlanet.getId(), List.of(landingProbe1, landingProbe2));
    controlProbesUseCase.execute(command);

    verify(planetRepository, times(1)).findById(generatedPlanet.getId());

    ArgumentCaptor<Probe> argument = ArgumentCaptor.forClass(Probe.class);

    verify(generatedPlanet, times(2)).land(argument.capture());


    final Probe result1 = argument.getAllValues()
      .get(0);
    final Probe result2 = argument.getAllValues()
      .get(1);

    Assertions.assertEquals(landingProbe1.probeName(), result1.getName());
    Assertions.assertEquals(landingProbe1.startDirection(), result1.getDirection());
    Assertions.assertEquals(landingProbe1.startPosition(), result1.getPosition());

    Assertions.assertEquals(landingProbe2.probeName(), result2.getName());
    Assertions.assertEquals(landingProbe2.startDirection(), result2.getDirection());
    Assertions.assertEquals(landingProbe2.startPosition(), result2.getPosition());

    verify(planetRepository, times(1)).save(argThat(planet -> {
      Assertions.assertEquals(2, planet.getProbes().size());
      return true;
    }));
  }

  @Test
  public void should_move_existing_probe_successfully() {

    PlanetRepository planetRepository = mock(PlanetRepository.class);
    final Planet generatedPlanet = spy(PlanetGenerator.gen());
    final Probe generatedProbe1 = ProbeGenerator.gen(generatedPlanet.getId().value(), 0, 0);
    final Probe generatedProbe2 = ProbeGenerator.gen(generatedPlanet.getId().value(), 2, 2);

    generatedPlanet.land(generatedProbe1);
    generatedPlanet.land(generatedProbe2);

    when(planetRepository.findById(any())).thenReturn(Optional.of(generatedPlanet));
    when(planetRepository.save(any())).thenReturn(generatedPlanet);

    ControlProbesUseCase controlProbesUseCase = new PlanetService(planetRepository);

    MoveProbe command = new MoveProbe(generatedPlanet.getId(), generatedProbe1.getId(), List.of(Command.R, Command.M, Command.L, Command.M));

    controlProbesUseCase.execute(command);

    verify(planetRepository, times(1)).findById(generatedPlanet.getId());
    verify(generatedPlanet, times(1)).move(generatedProbe1.getId(), command.commands());
    verify(planetRepository, times(1)).save(argThat(planets -> {
      Assertions.assertEquals(2, planets.getProbes().size());
      return true;
    }));
  }

  @Test
  public void should_throw_exception_when_land_not_found_planet() {

    PlanetRepository planetRepository = mock(PlanetRepository.class);

    final LandProbes.LandingProbe landingProbe =
      new LandProbes.LandingProbe(new ProbeName("Probe Name 1"), new Position(0, 0), Direction.N);


    when(planetRepository.findById(any())).thenReturn(Optional.empty());

    ControlProbesUseCase controlProbesUseCase = new PlanetService(planetRepository);

    final LandProbes command = new LandProbes(new PlanetId(UUID.randomUUID().toString()), List.of(landingProbe));

    Assertions.assertThrows(IllegalStateException.class, () -> controlProbesUseCase.execute(command));
  }

  @Test
  public void should_throw_exception_when_move_not_found_planet() {

    PlanetRepository planetRepository = mock(PlanetRepository.class);
    final Probe generatedProbe1 = ProbeGenerator.gen(UUID.randomUUID().toString(), 0, 0);

    when(planetRepository.findById(any())).thenReturn(Optional.empty());

    ControlProbesUseCase controlProbesUseCase = new PlanetService(planetRepository);

    MoveProbe command = new MoveProbe(new PlanetId(UUID.randomUUID().toString()), generatedProbe1.getId(), List.of(Command.R, Command.M, Command.L, Command.M));

    Assertions.assertThrows(IllegalStateException.class, () -> controlProbesUseCase.execute(command));
  }
}
