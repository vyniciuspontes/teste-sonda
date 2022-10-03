package br.com.elo7.sonda.candidato.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProbeTest {

  @Test
  public void should_change_probe_direction_from_N_To_W_when_receive_the_command_L() {
    Probe probe = new Probe(new ProbeId(321), new Position(0, 0), Direction.N, new PlanetId(123));
    probe.applyCommandToProbe(Command.L);
    assertEquals(Direction.W, probe.getDirection());
  }

  @Test
  public void should_change_probe_direction_from_W_To_S_when_receive_the_command_L() {
    Probe probe = new Probe(new ProbeId(321), new Position(0, 0), Direction.W, new PlanetId(123));
    probe.applyCommandToProbe(Command.L);
    assertEquals(Direction.S, probe.getDirection());
  }

  @Test
  public void should_change_probe_direction_from_S_To_E_when_receive_the_command_L() {
    Probe probe = new Probe(new ProbeId(321), new Position(0, 0), Direction.S, new PlanetId(123));
    probe.applyCommandToProbe(Command.L);
    assertEquals(Direction.E, probe.getDirection());
  }

  @Test
  public void should_change_probe_direction_from_E_To_N_when_receive_the_command_L() {
    Probe probe = new Probe(new ProbeId(321), new Position(0, 0), Direction.E, new PlanetId(123));
    probe.applyCommandToProbe(Command.L);
    assertEquals(Direction.N, probe.getDirection());
  }

  @Test
  public void should_change_probe_direction_from_N_To_E_when_receive_the_command_R() {

    Probe probe = new Probe(new ProbeId(321), new Position(0, 0), Direction.N, new PlanetId(123));
    probe.applyCommandToProbe(Command.R);
    assertEquals(Direction.E, probe.getDirection());
  }

  @Test
  public void should_change_probe_direction_from_E_To_S_when_receive_the_command_R() {

    Probe probe = new Probe(new ProbeId(321), new Position(0, 0), Direction.E, new PlanetId(123));
    probe.applyCommandToProbe(Command.R);
    assertEquals(Direction.S, probe.getDirection());
  }

  @Test
  public void should_change_probe_direction_from_S_To_W_when_receive_the_command_R() {

    Probe probe = new Probe(new ProbeId(321), new Position(0, 0), Direction.S, new PlanetId(123));
    probe.applyCommandToProbe(Command.R);
    assertEquals(Direction.W, probe.getDirection());
  }

  @Test
  public void should_change_probe_direction_from_W_To_N_when_receive_the_command_R() {

    Probe probe = new Probe(new ProbeId(321), new Position(0, 0), Direction.W, new PlanetId(123));
    probe.applyCommandToProbe(Command.R);
    assertEquals(Direction.N, probe.getDirection());
  }

  @Test
  public void should_change_probe_position_from_1_1_N_To_1_2_N_when_receive_the_command_M() {

    Probe probe = new Probe(new ProbeId(321), new Position(1, 1), Direction.N, new PlanetId(123));
    probe.applyCommandToProbe(Command.M);

    assertEquals(1, probe.getPosition().getX());
    assertEquals(2, probe.getPosition().getY());
    assertEquals(Direction.N, probe.getDirection());
  }

  @Test
  public void should_change_probe_position_from_1_1_S_To_1_0_S_when_receive_the_command_M() {
    Probe probe = new Probe(new ProbeId(321), new Position(1, 1), Direction.S, new PlanetId(123));
    probe.applyCommandToProbe(Command.M);

    assertEquals(1, probe.getPosition().getX());
    assertEquals(0, probe.getPosition().getY());
    assertEquals(Direction.S, probe.getDirection());
  }

  @Test
  public void should_change_probe_position_from_1_1_W_To_0_1_W_when_receive_the_command_M() {

    Probe probe = new Probe(new ProbeId(321), new Position(1, 1), Direction.W, new PlanetId(123));
    probe.applyCommandToProbe(Command.M);

    assertEquals(0, probe.getPosition().getX());
    assertEquals(1, probe.getPosition().getY());
    assertEquals(Direction.W, probe.getDirection());
  }

  @Test
  public void should_change_probe_position_from_1_1_E_To_2_1_E_when_receive_the_command_M() {

    Probe probe = new Probe(new ProbeId(321), new Position(1, 1), Direction.E, new PlanetId(123));
    probe.applyCommandToProbe(Command.M);

    assertEquals(2, probe.getPosition().getX());
    assertEquals(1, probe.getPosition().getY());
    assertEquals(Direction.E, probe.getDirection());
  }
}
