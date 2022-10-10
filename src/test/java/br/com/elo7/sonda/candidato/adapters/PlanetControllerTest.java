package br.com.elo7.sonda.candidato.adapters;

import br.com.elo7.sonda.candidato.adapter.controller.PlanetController;
import br.com.elo7.sonda.candidato.adapter.controller.dto.LandProbesRequestDTO;
import br.com.elo7.sonda.candidato.adapter.controller.dto.PlanetResponseDTO;
import br.com.elo7.sonda.candidato.adapter.controller.dto.PostPlanetRequestDTO;
import br.com.elo7.sonda.candidato.adapter.controller.dto.ProbeRequestDTO;
import br.com.elo7.sonda.candidato.adapter.controller.dto.ProbeResponseDTO;
import br.com.elo7.sonda.candidato.application.ControlProbesUseCase;
import br.com.elo7.sonda.candidato.application.ManagePlanetsUseCase;
import br.com.elo7.sonda.candidato.application.commands.LandProbes;
import br.com.elo7.sonda.candidato.application.commands.MoveProbe;
import br.com.elo7.sonda.candidato.domain.Command;
import br.com.elo7.sonda.candidato.domain.Planet;
import br.com.elo7.sonda.candidato.domain.PlanetGenerator;
import br.com.elo7.sonda.candidato.domain.Probe;
import br.com.elo7.sonda.candidato.domain.ProbeGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class PlanetControllerTest {

  @Test
  public void should_get_planet_and_return_status_200(){

    ManagePlanetsUseCase managePlanetsUseCase = mock(ManagePlanetsUseCase.class);
    ControlProbesUseCase controlProbesUseCase = mock(ControlProbesUseCase.class);

    final Planet generatedPlanet = PlanetGenerator.gen();
    when(managePlanetsUseCase.get(any())).thenReturn(generatedPlanet);

    PlanetController planetController = new PlanetController(managePlanetsUseCase, controlProbesUseCase);

    final ResponseEntity<PlanetResponseDTO> response = planetController.get(generatedPlanet.getId().value());

    verify(managePlanetsUseCase, times(1)).get(generatedPlanet.getId());

    Assertions.assertEquals(generatedPlanet.getId().value(), Objects.requireNonNull(response.getBody()).id());
    Assertions.assertEquals(generatedPlanet.getName().value(), Objects.requireNonNull(response.getBody()).name());
    Assertions.assertEquals(generatedPlanet.getWidth().value(), Objects.requireNonNull(response.getBody()).width());
    Assertions.assertEquals(generatedPlanet.getHeight().value(), Objects.requireNonNull(response.getBody()).height());

    Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
  }

  @Test
  public void should_get_all_planets_and_return_status_200(){

    ManagePlanetsUseCase managePlanetsUseCase = mock(ManagePlanetsUseCase.class);
    ControlProbesUseCase controlProbesUseCase = mock(ControlProbesUseCase.class);

    final Planet generatedPlanet1 = PlanetGenerator.gen();
    final Planet generatedPlanet2 = PlanetGenerator.gen();
    final Planet generatedPlanet3 = PlanetGenerator.gen();
    final List<Planet> planets = List.of(generatedPlanet1, generatedPlanet2, generatedPlanet3);
    when(managePlanetsUseCase.getAll()).thenReturn(planets);

    PlanetController planetController = new PlanetController(managePlanetsUseCase, controlProbesUseCase);

    final ResponseEntity<List<PlanetResponseDTO>> response = planetController.getAll();

    verify(managePlanetsUseCase, times(1)).getAll();

    Assertions.assertEquals(planets.size(), Objects.requireNonNull(response.getBody()).size());

    response.getBody().forEach(planetDTO -> {
      final Planet currentPlanet = planets.stream().filter(planet -> planet.getId().value().equals(planetDTO.id()))
        .findFirst().orElseThrow();

      Assertions.assertEquals(currentPlanet.getId().value(), Objects.requireNonNull(planetDTO).id());
      Assertions.assertEquals(currentPlanet.getName().value(), Objects.requireNonNull(planetDTO).name());
      Assertions.assertEquals(currentPlanet.getWidth().value(), Objects.requireNonNull(planetDTO).width());
      Assertions.assertEquals(currentPlanet.getHeight().value(), Objects.requireNonNull(planetDTO).height());
    });

    Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
  }

  @Test
  public void should_create_planet_and_return_status_201(){

    ManagePlanetsUseCase managePlanetsUseCase = mock(ManagePlanetsUseCase.class);
    ControlProbesUseCase controlProbesUseCase = mock(ControlProbesUseCase.class);

    final Planet generatedPlanet = PlanetGenerator.gen();
    when(managePlanetsUseCase.create(any())).thenReturn(generatedPlanet);

    PlanetController planetController = new PlanetController(managePlanetsUseCase, controlProbesUseCase);

    PostPlanetRequestDTO requestDTO = new PostPlanetRequestDTO(generatedPlanet.getName().value(),
      generatedPlanet.getWidth().value(), generatedPlanet.getHeight().value());

    final ResponseEntity<PlanetResponseDTO> response = planetController.create(requestDTO);

    verify(managePlanetsUseCase, times(1)).create(argThat(createPlanet -> {
      Assertions.assertEquals(requestDTO.name(), createPlanet.planetName().value());
      Assertions.assertEquals(requestDTO.width(), createPlanet.width().value());
      Assertions.assertEquals(requestDTO.height(), createPlanet.height().value());
      return true;
    }));

    Assertions.assertEquals(requestDTO.name(), Objects.requireNonNull(response.getBody()).name());
    Assertions.assertEquals(requestDTO.width(), Objects.requireNonNull(response.getBody()).width());
    Assertions.assertEquals(requestDTO.height(), Objects.requireNonNull(response.getBody()).height());

    Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
  }

  @Test
  public void should_land_probes_and_return_status_200(){

    ManagePlanetsUseCase managePlanetsUseCase = mock(ManagePlanetsUseCase.class);
    ControlProbesUseCase controlProbesUseCase = mock(ControlProbesUseCase.class);

    final Planet generatedPlanet = PlanetGenerator.gen();
    Probe probe1 = ProbeGenerator.gen(generatedPlanet.getId().value(), 0, 1);
    Probe probe2 = ProbeGenerator.gen(generatedPlanet.getId().value(), 2, 3);
    Probe probe3 = ProbeGenerator.gen(generatedPlanet.getId().value(), 1, 4);
    List<Probe> probes = List.of(probe1, probe2, probe3);
    generatedPlanet.land(probe1);
    generatedPlanet.land(probe2);
    generatedPlanet.land(probe3);

    when(controlProbesUseCase.execute(any(LandProbes.class))).thenReturn(generatedPlanet);

    PlanetController planetController = new PlanetController(managePlanetsUseCase, controlProbesUseCase);

    ProbeRequestDTO probeDTO1 = new ProbeRequestDTO(probe1.getName().value(), probe1.getPosition().x(), probe1.getPosition().y(), probe1.getDirection().name());
    ProbeRequestDTO probeDTO2 = new ProbeRequestDTO(probe2.getName().value(), probe2.getPosition().x(), probe2.getPosition().y(), probe2.getDirection().name());
    ProbeRequestDTO probeDTO3 = new ProbeRequestDTO(probe3.getName().value(), probe3.getPosition().x(), probe3.getPosition().y(), probe3.getDirection().name());
    final LandProbesRequestDTO request = new LandProbesRequestDTO(List.of(probeDTO1, probeDTO2, probeDTO3));

    final ResponseEntity<PlanetResponseDTO> response = planetController.landProbes(request, generatedPlanet.getId().value());

    Objects.requireNonNull(response.getBody()).probes().forEach(probeDTO -> {
      final Probe currentProbe = probes.stream().filter(probe -> probe.getId().value().equals(probeDTO.id()))
        .findFirst().orElseThrow();

      Assertions.assertEquals(currentProbe.getId().value(), Objects.requireNonNull(probeDTO).id());
      Assertions.assertEquals(currentProbe.getName().value(), Objects.requireNonNull(probeDTO).name());
      Assertions.assertEquals(currentProbe.getPosition().x(), Objects.requireNonNull(probeDTO).x());
      Assertions.assertEquals(currentProbe.getPosition().y(), Objects.requireNonNull(probeDTO).y());
      Assertions.assertEquals(currentProbe.getDirection().name(), Objects.requireNonNull(probeDTO).direction());
    });

    verify(controlProbesUseCase, times(1)).execute(any(LandProbes.class));

    Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
  }

  @Test
  public void should_move_probe_and_return_status_200(){

    ManagePlanetsUseCase managePlanetsUseCase = mock(ManagePlanetsUseCase.class);
    ControlProbesUseCase controlProbesUseCase = mock(ControlProbesUseCase.class);

    final Planet generatedPlanet = PlanetGenerator.gen();
    final Probe generatedProbe = ProbeGenerator.gen(generatedPlanet.getId().value(), 0, 2);
    generatedPlanet.land(generatedProbe);

    when(controlProbesUseCase.execute(any(MoveProbe.class))).thenReturn(generatedPlanet);

    PlanetController planetController = new PlanetController(managePlanetsUseCase, controlProbesUseCase);

    final ResponseEntity<PlanetResponseDTO> response = planetController.moveProbe(List.of(Command.M), generatedPlanet.getId().value(), generatedProbe.getId().value());

    verify(controlProbesUseCase, times(1)).execute(any(MoveProbe.class));

    ProbeResponseDTO probeResponseDTO = Objects.requireNonNull(response.getBody()).probes().stream().findFirst().orElseThrow();

    Assertions.assertEquals(generatedProbe.getId().value(), Objects.requireNonNull(probeResponseDTO).id());
    Assertions.assertEquals(generatedProbe.getName().value(), Objects.requireNonNull(probeResponseDTO).name());
    Assertions.assertEquals(generatedProbe.getPosition().x(), Objects.requireNonNull(probeResponseDTO).x());
    Assertions.assertEquals(generatedProbe.getPosition().y(), Objects.requireNonNull(probeResponseDTO).y());
    Assertions.assertEquals(generatedProbe.getDirection().name(), Objects.requireNonNull(probeResponseDTO).direction());

    Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
  }

}
