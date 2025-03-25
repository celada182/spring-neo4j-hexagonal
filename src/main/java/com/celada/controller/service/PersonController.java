package com.celada.controller.service;

import com.celada.api.PersonApi;
import com.celada.controller.mapper.PersonMapper;
import com.celada.domain.model.Person;
import com.celada.domain.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class PersonController implements PersonApi {

    private final PersonService personService;

    public PersonController(
            PersonService personService
    ) {
        this.personService = personService;
    }

    @Override
    public ResponseEntity<String> create(com.celada.api.model.Person request) {
        System.out.println("Person controller: Create");
        Person person = PersonMapper.INSTANCE.toDomain(request);
        String dni = personService.create(person);
        return ResponseEntity.ok(dni);
    }

    @Override
    public ResponseEntity<com.celada.api.model.Person> read(String id) {
        System.out.println("Person controller: Read");
        Person person = personService.read(id);
        com.celada.api.model.Person response = PersonMapper.INSTANCE.toApi(person);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> update(String dni, com.celada.api.model.Person request) {
        System.out.println("Person controller: Update");
        Person person = PersonMapper.INSTANCE.toDomain(request);
        personService.update(dni, person);
        return ResponseEntity.ok().build();
    }

}
