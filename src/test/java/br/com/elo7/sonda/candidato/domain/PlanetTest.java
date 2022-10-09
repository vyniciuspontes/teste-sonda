package br.com.elo7.sonda.candidato.domain;

import br.com.elo7.sonda.candidato.domain.exception.InvalidVOException;
import br.com.elo7.sonda.candidato.domain.exception.ProbeOutOfBoundsException;
import br.com.elo7.sonda.candidato.domain.exception.ProbeOverlapingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class PlanetTest {

  @Test
  public void should_land_probes_sucessfully() {
    Planet planet = PlanetGenerator.gen();
    Probe probe1 = ProbeGenerator.gen(planet.getId().value(), 0, 0);
    Probe probe2 = ProbeGenerator.gen(planet.getId().value(), 1, 0);

    planet.land(probe1);
    planet.land(probe2);

    Assertions.assertTrue(planet.getProbe(probe1.getId()).isPresent());
    Assertions.assertEquals(new Position(0, 0), planet.getProbe(probe1.getId()).get().getPosition());

    Assertions.assertTrue(planet.getProbe(probe2.getId()).isPresent());
    Assertions.assertEquals(new Position(1, 0), planet.getProbe(probe2.getId()).get().getPosition());
  }

  @Test
  public void should_throw_exception_when_probes_land_overlaps() {
    Planet planet = PlanetGenerator.gen();

    Probe probe1 = ProbeGenerator.gen(planet.getId().value(), 1, 0);
    Probe probe2 = ProbeGenerator.gen(planet.getId().value(), 1, 0);

    planet.land(probe1);

    Assertions.assertThrows(ProbeOverlapingException.class, () -> planet.land(probe2));
  }

  @Test
  public void should_throw_exception_when_probe_lands_out_of_bounds() {
    Planet planet = PlanetGenerator.gen();

    Probe probe1 = ProbeGenerator.gen(planet.getId().value(), 5, 0, Direction.N);
    Assertions.assertThrows(ProbeOutOfBoundsException.class, () -> planet.land(probe1));

    Probe probe2 = ProbeGenerator.gen(planet.getId().value(), 8, 0, Direction.N);
    Assertions.assertThrows(ProbeOutOfBoundsException.class, () -> planet.land(probe2));

    Probe probe3 = ProbeGenerator.gen(planet.getId().value(), 0, 5, Direction.N);
    Assertions.assertThrows(ProbeOutOfBoundsException.class, () -> planet.land(probe3));

    Probe probe4 = ProbeGenerator.gen(planet.getId().value(), 0, 8, Direction.N);
    Assertions.assertThrows(ProbeOutOfBoundsException.class, () -> planet.land(probe4));
  }

  @Test
  public void should_throw_exception_when_probe_move_overlaps_another_planet_probe() {
    Planet planet = PlanetGenerator.gen();

    Probe probe1 = ProbeGenerator.gen(planet.getId().value(), 0, 0);
    Probe probe2 = ProbeGenerator.gen(planet.getId().value(), 1, 0);

    planet.land(probe1);
    planet.land(probe2);

    Assertions.assertThrows(ProbeOverlapingException.class, () -> planet.move(probe1.getId(), List.of(Command.R, Command.M)));
  }

  @Test
  public void should_move_probe_successfully() {
    Planet planet = PlanetGenerator.gen();

    Probe probe1 = ProbeGenerator.gen(planet.getId().value(), 3, 3);
    Probe probe2 = ProbeGenerator.gen(planet.getId().value(), 2, 2);
    Probe probe3 = ProbeGenerator.gen(planet.getId().value(), 4, 2);

    planet.land(probe1);
    planet.land(probe2);
    planet.land(probe3);

    planet.move(probe3.getId(), List.of(Command.L, Command.M, Command.L, Command.M, Command.R, Command.M, Command.M,
      Command.R, Command.M, Command.M, Command.R, Command.M, Command.L, Command.M, Command.R, Command.M,
      Command.M, Command.R, Command.M));

    Assertions.assertEquals(probe3.getPosition(), new Position(4, 3));
  }

  @Test
  public void should_throw_exception_when_probe_move_out_of_bounds() {
    Planet planet = PlanetGenerator.gen();
    Probe probe1 = ProbeGenerator.gen(planet.getId().value(), 0, 0);

    planet.land(probe1);

    Assertions.assertThrows(ProbeOutOfBoundsException.class, () -> planet.move(probe1.getId(), List.of(Command.M, Command.M, Command.M,
      Command.M, Command.M)));
  }

  @Test
  public void should_throw_exception_when_vo_is_invalid(){

    Assertions.assertThrows(InvalidVOException.class, () -> new PlanetId(null));
    Assertions.assertThrows(InvalidVOException.class, () -> new PlanetName(null));
    Assertions.assertThrows(InvalidVOException.class, () -> new PlanetName(""));
    Assertions.assertThrows(InvalidVOException.class, () -> new PlanetName("  "));
    Assertions.assertThrows(InvalidVOException.class, () -> new PlanetDimension(-1));
  }
}
