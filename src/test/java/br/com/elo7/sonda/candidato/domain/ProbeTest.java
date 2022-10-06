package br.com.elo7.sonda.candidato.domain;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProbeTest {

  @Test
  public void should_change_probe_direction_from_N_To_W_when_receive_the_command_L() {
    Probe probe = ProbeGenerator.gen(UUID.randomUUID().toString(), 0, 0, Direction.N);
    probe.applyCommandToProbe(Command.L);
    assertEquals(Direction.W, probe.getDirection());
  }

  @Test
  public void should_change_probe_direction_from_W_To_S_when_receive_the_command_L() {
    Probe probe = ProbeGenerator.gen(UUID.randomUUID().toString(), 0, 0, Direction.W);
    probe.applyCommandToProbe(Command.L);
    assertEquals(Direction.S, probe.getDirection());
  }

  @Test
  public void should_change_probe_direction_from_S_To_E_when_receive_the_command_L() {
    Probe probe = ProbeGenerator.gen(UUID.randomUUID().toString(), 0, 0, Direction.S);
    probe.applyCommandToProbe(Command.L);
    assertEquals(Direction.E, probe.getDirection());
  }

  @Test
  public void should_change_probe_direction_from_E_To_N_when_receive_the_command_L() {
    Probe probe = ProbeGenerator.gen(UUID.randomUUID().toString(), 0, 0, Direction.E);
    probe.applyCommandToProbe(Command.L);
    assertEquals(Direction.N, probe.getDirection());
  }

  @Test
  public void should_change_probe_direction_from_N_To_E_when_receive_the_command_R() {

    Probe probe = ProbeGenerator.gen(UUID.randomUUID().toString(), 0, 0, Direction.N);
    probe.applyCommandToProbe(Command.R);
    assertEquals(Direction.E, probe.getDirection());
  }

  @Test
  public void should_change_probe_direction_from_E_To_S_when_receive_the_command_R() {

    Probe probe = ProbeGenerator.gen(UUID.randomUUID().toString(), 0, 0, Direction.E);
    probe.applyCommandToProbe(Command.R);
    assertEquals(Direction.S, probe.getDirection());
  }

  @Test
  public void should_change_probe_direction_from_S_To_W_when_receive_the_command_R() {

    Probe probe = ProbeGenerator.gen(UUID.randomUUID().toString(), 0, 0, Direction.S);
    probe.applyCommandToProbe(Command.R);
    assertEquals(Direction.W, probe.getDirection());
  }

  @Test
  public void should_change_probe_direction_from_W_To_N_when_receive_the_command_R() {

    Probe probe = ProbeGenerator.gen(UUID.randomUUID().toString(), 0, 0, Direction.W);
    probe.applyCommandToProbe(Command.R);
    assertEquals(Direction.N, probe.getDirection());
  }

  @Test
  public void should_change_probe_position_from_1_1_N_To_1_2_N_when_receive_the_command_M() {

    Probe probe = ProbeGenerator.gen(UUID.randomUUID().toString(), 1, 1, Direction.N);
    probe.applyCommandToProbe(Command.M);

    assertEquals(1, probe.getPosition().x());
    assertEquals(2, probe.getPosition().y());
    assertEquals(Direction.N, probe.getDirection());
  }

  @Test
  public void should_change_probe_position_from_1_1_S_To_1_0_S_when_receive_the_command_M() {

    Probe probe = ProbeGenerator.gen(UUID.randomUUID().toString(), 1, 1, Direction.S);
    probe.applyCommandToProbe(Command.M);

    assertEquals(1, probe.getPosition().x());
    assertEquals(0, probe.getPosition().y());
    assertEquals(Direction.S, probe.getDirection());
  }

  @Test
  public void should_change_probe_position_from_1_1_W_To_0_1_W_when_receive_the_command_M() {

    Probe probe = ProbeGenerator.gen(UUID.randomUUID().toString(), 1, 1, Direction.W);
    probe.applyCommandToProbe(Command.M);

    assertEquals(0, probe.getPosition().x());
    assertEquals(1, probe.getPosition().y());
    assertEquals(Direction.W, probe.getDirection());
  }

  @Test
  public void should_change_probe_position_from_1_1_E_To_2_1_E_when_receive_the_command_M() {

    Probe probe = ProbeGenerator.gen(UUID.randomUUID().toString(), 1, 1, Direction.E);
    probe.applyCommandToProbe(Command.M);

    assertEquals(2, probe.getPosition().x());
    assertEquals(1, probe.getPosition().y());
    assertEquals(Direction.E, probe.getDirection());
  }
}
