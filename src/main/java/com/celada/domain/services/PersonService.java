package com.celada.domain.services;

import com.celada.domain.model.Person;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PersonService {
    public UUID create(Person person) {
        System.out.println("Create service");
        UUID id = UUID.randomUUID();
        person.setId(id);
        return id;
    }

    public Person read(UUID uuid) {
        System.out.println("Read Service");
        return Person.builder()
                .id(uuid)
                .name("Javier")
                .surname("Celada")
                .build();
    }

    public void update(UUID uuid, Person person) {
        System.out.println("Update Service");
    }
}
