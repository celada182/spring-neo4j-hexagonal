package com.celada.db.service;

import com.celada.db.mapper.PersonEntityMapper;
import com.celada.db.model.PersonEntity;
import com.celada.db.repository.PersonEntityRepository;
import com.celada.domain.model.Person;
import com.celada.domain.repository.PersonRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class PersonNeo4jRepository implements PersonRepository {

    private final PersonEntityRepository repository;

    public PersonNeo4jRepository(PersonEntityRepository repository) {
        this.repository = repository;
    }

    @Override
    public String save(Person person) {
        System.out.println("Save Neo4j");
        PersonEntity entity = PersonEntityMapper.INSTANCE.toEntity(person);
        repository.save(entity);
        return entity.getDni();
    }

    @Override
    public Person read(String dni) {
        System.out.println("Read Neo4j");
        PersonEntity entity = repository.findByDni(dni).orElseThrow();
        return PersonEntityMapper.INSTANCE.toDomain(entity);
    }

    @Override
    public void update(String dni, Person person) {
        System.out.println("Update Neo4j");
        PersonEntity entity = repository.findByDni(dni).orElseThrow();
        // Existing wife
        Optional<PersonEntity> wife = repository.findByDni(person.getWife().getDni());
        wife.ifPresent(entity::setWife);
        // Creating children
        List<PersonEntity> children = person.getChildren().stream()
                .map(PersonEntityMapper.INSTANCE::toEntity)
                .collect(Collectors.toList());
        entity.setChildren(children);
        repository.save(entity);
    }
}
