package br.com.elo7.sonda.candidato.adapter;

import br.com.elo7.sonda.candidato.adapter.dto.InputDTO;
import br.com.elo7.sonda.candidato.adapter.dto.ProbeDTO;
import br.com.elo7.sonda.candidato.application.ProbesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/planet-with-probes")
public class PlanetAndProbeController {
  @Autowired
  private ProbesService probeService;

  @PostMapping
  public ResponseEntity<List<ProbeDTO>> register(@RequestBody InputDTO inputDto) {
    probeService.landProbes(Collections.emptyList());
    return ResponseEntity.ok().build();
  }
}
