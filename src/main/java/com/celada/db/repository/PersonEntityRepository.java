package com.celada.db.repository;

import com.celada.db.model.PersonEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface PersonEntityRepository extends Neo4jRepository<PersonEntity, Long> {
}
