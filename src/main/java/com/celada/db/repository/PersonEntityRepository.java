package com.celada.db.repository;

import com.celada.db.model.PersonEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.Optional;

public interface PersonEntityRepository extends Neo4jRepository<PersonEntity, Long> {
    Optional<PersonEntity> findByDni(String dni);
}
