package br.com.elo7.sonda.candidato.application;

import br.com.elo7.sonda.candidato.application.commands.CreatePlanet;
import br.com.elo7.sonda.candidato.domain.Planet;
import br.com.elo7.sonda.candidato.domain.PlanetDimension;
import br.com.elo7.sonda.candidato.domain.PlanetGenerator;
import br.com.elo7.sonda.candidato.domain.PlanetId;
import br.com.elo7.sonda.candidato.domain.PlanetName;
import br.com.elo7.sonda.candidato.domain.PlanetRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class ManagePlanetsUseCaseTest {

  @Test
  public void should_create_planet_successfully() {

    PlanetRepository planetRepository = mock(PlanetRepository.class);
    CreatePlanet command = new CreatePlanet(new PlanetName("Plant Name"), new PlanetDimension(5), new PlanetDimension(5));

    final Planet newPlanet = new Planet(new PlanetId(UUID.randomUUID().toString()), command.planetName(),
      command.width(), command.height());

    when(planetRepository.save(any())).thenReturn(newPlanet);

    ManagePlanetsUseCase managePlanetsUseCase = new PlanetService(planetRepository);

    final Planet result = managePlanetsUseCase.create(command);

    Assertions.assertEquals(newPlanet.getName(), result.getName());
    Assertions.assertEquals(newPlanet.getWidth(), result.getWidth());
    Assertions.assertEquals(newPlanet.getHeight(), result.getHeight());

    verify(planetRepository, times(1)).save(any(Planet.class));

  }

  @Test
  public void should_get_planet_successfully() {

    PlanetRepository planetRepository = mock(PlanetRepository.class);

    final PlanetId id = new PlanetId(UUID.randomUUID().toString());
    final Planet foundPlanet = PlanetGenerator.gen(id);

    when(planetRepository.findById(id)).thenReturn(Optional.of(foundPlanet));

    ManagePlanetsUseCase managePlanetsUseCase = new PlanetService(planetRepository);

    final Planet result = managePlanetsUseCase.get(id);

    Assertions.assertEquals(foundPlanet.getName(), result.getName());
    Assertions.assertEquals(foundPlanet.getWidth(), result.getWidth());
    Assertions.assertEquals(foundPlanet.getHeight(), result.getHeight());

    verify(planetRepository, times(1)).findById(id);

  }

  @Test
  public void should_throw_exception_when_planet_not_found() {

    PlanetRepository planetRepository = mock(PlanetRepository.class);

    final PlanetId id = new PlanetId(UUID.randomUUID().toString());

    when(planetRepository.findById(id)).thenReturn(Optional.empty());

    ManagePlanetsUseCase managePlanetsUseCase = new PlanetService(planetRepository);

    Assertions.assertThrows(IllegalStateException.class, () -> managePlanetsUseCase.get(id));
  }

  @Test
  public void should_get_all_planets_successfully() {

    PlanetRepository planetRepository = mock(PlanetRepository.class);

    final Planet foundPlanet1 = PlanetGenerator.gen();
    final Planet foundPlanet2 = PlanetGenerator.gen();

    when(planetRepository.findAll()).thenReturn(Set.of(foundPlanet1, foundPlanet2));

    ManagePlanetsUseCase managePlanetsUseCase = new PlanetService(planetRepository);

    final Set<Planet> result = managePlanetsUseCase.getAll();

    Assertions.assertEquals(2, result.size());

    verify(planetRepository, times(1)).findAll();
  }
}
