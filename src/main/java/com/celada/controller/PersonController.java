package com.celada.controller;

import com.celada.api.PersonApi;
import com.celada.api.model.Person;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
public class PersonController implements PersonApi {

    @Override
    public ResponseEntity<UUID> create(Person person) {
        System.out.println("Person controller: Create");
        UUID id = UUID.randomUUID();
        return ResponseEntity.ok(id);
    }

    @Override
    public ResponseEntity<Person> read(UUID id) {
        System.out.println("Person controller: Read");
        Person person = new Person("Javier", "Celada");
        return ResponseEntity.ok(person);
    }

    @Override
    public ResponseEntity<Void> update(UUID id, Person person) {
        System.out.println("Person controller: Update");
        return ResponseEntity.ok().build();
    }

}
