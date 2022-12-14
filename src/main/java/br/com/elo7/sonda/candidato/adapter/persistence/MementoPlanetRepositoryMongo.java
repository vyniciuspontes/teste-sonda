package br.com.elo7.sonda.candidato.adapter.persistence;

import br.com.elo7.sonda.candidato.domain.PlanetId;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Essa implementação de repository utiliza o padrão de projeto Memento, que cria um snapshop do Aggregate Root
 * a fim de mapear para uma entidade do banco. Há uma outra opção  {@link PlanetRepositoryMongo} que salva diretamente
 * o Aggregate Root porém fere o princípio de isolamento do domínio pois requer um construtor vazio para utilizar o Spring Data
 */
interface MementoPlanetRepositoryMongo extends MongoRepository<PlanetDocument, PlanetId> {}
