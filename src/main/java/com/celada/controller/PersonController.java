package com.celada.controller;

import com.celada.api.PersonApi;
import com.celada.controller.mapper.PersonMapper;
import com.celada.domain.model.Person;
import com.celada.domain.services.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
public class PersonController implements PersonApi {

    private final PersonService personService;

    public PersonController(
            PersonService personService
    ) {
        this.personService = personService;
    }

    @Override
    public ResponseEntity<UUID> create(com.celada.api.model.Person request) {
        System.out.println("Person controller: Create");
        Person person = PersonMapper.INSTANCE.toDomain(request);
        UUID id = personService.create(person);
        return ResponseEntity.ok(id);
    }

    @Override
    public ResponseEntity<com.celada.api.model.Person> read(UUID id) {
        System.out.println("Person controller: Read");
        Person person = personService.read(id);
        com.celada.api.model.Person response = PersonMapper.INSTANCE.toApi(person);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> update(UUID id, com.celada.api.model.Person request) {
        System.out.println("Person controller: Update");
        Person person = PersonMapper.INSTANCE.toDomain(request);
        personService.update(id, person);
        return ResponseEntity.ok().build();
    }

}
