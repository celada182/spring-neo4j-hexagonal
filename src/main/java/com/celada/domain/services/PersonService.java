package com.celada.domain.services;

import com.celada.domain.model.Person;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PersonService {
    public String create(Person person) {
        System.out.println("Create service");
        return person.getDni();
    }

    public Person read(String dni) {
        System.out.println("Read Service");
        return Person.builder()
                .dni(dni)
                .name("Javier")
                .surname("Celada")
                .build();
    }

    public void update(String dni, Person person) {
        System.out.println("Update Service");
    }
}
