package br.com.elo7.sonda.candidato.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class PlanetTest {

  @Test
  public void should_land_probes_sucessfully() {
    Planet planet = new Planet(new PlanetId(123), new PlanetDimension(5), new PlanetDimension(5));
    Probe probe1 = new Probe(new ProbeId(1), new Position(0, 0), Direction.N, planet.getId());
    Probe probe2 = new Probe(new ProbeId(2), new Position(1, 0), Direction.N, planet.getId());

    planet.land(probe1);
    planet.land(probe2);

    Assertions.assertTrue(planet.getProbe(probe1.getId()).isPresent());
    Assertions.assertEquals(new Position(0, 0), planet.getProbe(probe1.getId()).get().getPosition());

    Assertions.assertTrue(planet.getProbe(probe2.getId()).isPresent());
    Assertions.assertEquals(new Position(1, 0), planet.getProbe(probe2.getId()).get().getPosition());
  }

  @Test
  public void should_throw_exception_when_probes_land_overlaps() {
    Planet planet = new Planet(new PlanetId(123), new PlanetDimension(5), new PlanetDimension(5));
    Probe probe1 = new Probe(new ProbeId(1), new Position(1, 0), Direction.N, planet.getId());
    Probe probe2 = new Probe(new ProbeId(2), new Position(1, 0), Direction.N, planet.getId());

    planet.land(probe1);

    Assertions.assertThrows(IllegalStateException.class, () -> planet.land(probe2));
  }

  @Test
  public void should_throw_exception_when_probe_lands_out_of_bounds() {
    Planet planet = new Planet(new PlanetId(123), new PlanetDimension(5), new PlanetDimension(5));

    Probe probe1 = new Probe(new ProbeId(1), new Position(5, 0), Direction.N, planet.getId());
    Assertions.assertThrows(IllegalStateException.class, () -> planet.land(probe1));

    Probe probe2 = new Probe(new ProbeId(2), new Position(-1, 0), Direction.N, planet.getId());
    Assertions.assertThrows(IllegalStateException.class, () -> planet.land(probe2));

    Probe probe3 = new Probe(new ProbeId(3), new Position(0, 5), Direction.N, planet.getId());
    Assertions.assertThrows(IllegalStateException.class, () -> planet.land(probe3));

    Probe probe4 = new Probe(new ProbeId(4), new Position(0, -1), Direction.N, planet.getId());
    Assertions.assertThrows(IllegalStateException.class, () -> planet.land(probe4));
  }

  @Test
  public void should_throw_exception_when_probe_move_overlaps_another_planet_probe() {
    Planet planet = new Planet(new PlanetId(123), new PlanetDimension(5), new PlanetDimension(5));
    Probe probe1 = new Probe(new ProbeId(1), new Position(0, 0), Direction.N, planet.getId());
    Probe probe2 = new Probe(new ProbeId(2), new Position(1, 0), Direction.N, planet.getId());

    planet.land(probe1);
    planet.land(probe2);

    Assertions.assertThrows(IllegalStateException.class, () -> planet.move(probe1, List.of(Command.R, Command.M)));
  }

  @Test
  public void should_move_probe_successfully() {
    Planet planet = new Planet(new PlanetId(123), new PlanetDimension(5), new PlanetDimension(5));
    Probe probe1 = new Probe(new ProbeId(1), new Position(3, 3), Direction.N, planet.getId());
    Probe probe2 = new Probe(new ProbeId(2), new Position(2, 2), Direction.N, planet.getId());
    Probe probe3 = new Probe(new ProbeId(3), new Position(4, 2), Direction.N, planet.getId());

    planet.land(probe1);
    planet.land(probe2);
    planet.land(probe3);

    planet.move(probe3, List.of(Command.L, Command.M, Command.L, Command.M, Command.R, Command.M, Command.M,
      Command.R, Command.M, Command.M, Command.R, Command.M, Command.L, Command.M, Command.R, Command.M,
      Command.M, Command.R, Command.M));

    Assertions.assertEquals(probe3.getPosition(), new Position(4, 3));
  }

  @Test
  public void should_throw_exception_when_probe_move_out_of_bounds() {
    Planet planet = new Planet(new PlanetId(123), new PlanetDimension(5), new PlanetDimension(5));
    Probe probe1 = new Probe(new ProbeId(1), new Position(0, 0), Direction.N, planet.getId());

    planet.land(probe1);

    Assertions.assertThrows(IllegalStateException.class, () -> planet.move(probe1, List.of(Command.M, Command.M, Command.M,
      Command.M, Command.M)));
  }
}
