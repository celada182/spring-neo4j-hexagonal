package com.celada.domain.service;

import com.celada.domain.model.Person;
import com.celada.domain.repository.PersonRepository;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public String create(Person person) {
        System.out.println("Create service");
        personRepository.save(person);
        return person.getDni();
    }

    public Person read(String dni) {
        System.out.println("Read Service");
        return personRepository.read(dni);
    }

    public void update(String dni, Person person) {
        System.out.println("Update Service");
        personRepository.update(dni, person);
    }
}
