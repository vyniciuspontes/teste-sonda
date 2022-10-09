package br.com.elo7.sonda.candidato.adapter.controller;

import br.com.elo7.sonda.candidato.adapter.controller.dto.LandProbesRequestDTO;
import br.com.elo7.sonda.candidato.adapter.controller.dto.PlanetResponseDTO;
import br.com.elo7.sonda.candidato.adapter.controller.dto.PostPlanetRequestDTO;
import br.com.elo7.sonda.candidato.application.ControlProbesUseCase;
import br.com.elo7.sonda.candidato.application.ManagePlanetsUseCase;
import br.com.elo7.sonda.candidato.application.commands.MoveProbe;
import br.com.elo7.sonda.candidato.domain.Command;
import br.com.elo7.sonda.candidato.domain.Planet;
import br.com.elo7.sonda.candidato.domain.PlanetId;
import br.com.elo7.sonda.candidato.domain.ProbeId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/planets")
public class PlanetController {
  private final ManagePlanetsUseCase managePlanetsUseCase;
  private final ControlProbesUseCase controlProbesUseCase;

  public PlanetController(ManagePlanetsUseCase managePlanetsUseCase, ControlProbesUseCase controlProbesUseCase) {
    this.managePlanetsUseCase = managePlanetsUseCase;
    this.controlProbesUseCase = controlProbesUseCase;
  }

  @PostMapping
  public ResponseEntity<PlanetResponseDTO> create(@RequestBody @Valid PostPlanetRequestDTO postPlanetRequestDTO) {
    final Planet result = managePlanetsUseCase.create(postPlanetRequestDTO.toCommand());
    final PlanetResponseDTO response = PlanetResponseDTO.from(result);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @GetMapping("/{planetId}")
  public ResponseEntity<PlanetResponseDTO> get(@PathVariable String planetId) {

    final Planet result = managePlanetsUseCase.get(new PlanetId(planetId));
    final PlanetResponseDTO response = PlanetResponseDTO.from(result);
    return ResponseEntity.ok().body(response);
  }

  @GetMapping
  public ResponseEntity<List<PlanetResponseDTO>> getAll() {

    final Set<Planet> result = managePlanetsUseCase.getAll();
    final List<PlanetResponseDTO> response = result.stream().map(PlanetResponseDTO::from).toList();
    return ResponseEntity.ok().body(response);
  }

  @PostMapping("/{planetId}/probes")
  public ResponseEntity<PlanetResponseDTO> landProbes(@RequestBody @Valid LandProbesRequestDTO landProbesRequestDTO, @PathVariable String planetId) {
    final Planet result = controlProbesUseCase.execute(landProbesRequestDTO.toLandProbes(planetId));
    return ResponseEntity.status(HttpStatus.CREATED).body(PlanetResponseDTO.from(result));
  }

  @PatchMapping("/{planetId}/probes/{probeId}/move")
  public ResponseEntity<PlanetResponseDTO> moveProbe(@RequestBody List<@Valid Command> commands, @PathVariable String planetId, @PathVariable String probeId) {

    MoveProbe moveProbe = new MoveProbe(new PlanetId(planetId), new ProbeId(probeId), commands);
    final Planet result = controlProbesUseCase.execute(moveProbe);
    return ResponseEntity.ok().body(PlanetResponseDTO.from(result));
  }
}
