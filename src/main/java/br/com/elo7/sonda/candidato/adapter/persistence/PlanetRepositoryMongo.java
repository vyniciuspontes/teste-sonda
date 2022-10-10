package br.com.elo7.sonda.candidato.adapter.persistence;

import br.com.elo7.sonda.candidato.domain.Planet;
import br.com.elo7.sonda.candidato.domain.PlanetId;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Essa implementação de repository salva a classe do Aggregate Root (Planet) diretamente como um documento. No entanto, para atender
 * os requisitos do Spring Data, há a necessidade de um construtor vazio na classe Planet.
 * Se formos 100% pragmáticos, isso fere o principio de isolamento do domínio.
 * Mas, facilita bastante pois não há a necessidade de mapeamento. Então, para agradar os pragmáticos, há uma outra opção de
 * repositório {@link MementoPlanetRepositoryMongo } utilizando o padrão Memento,
 * que cria um snapshop do Aggregate Root a fim de mapear para uma entidade do banco.
 */
interface PlanetRepositoryMongo extends MongoRepository<Planet, PlanetId> {}
