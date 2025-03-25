package com.celada.domain.repository;

import com.celada.domain.model.Person;

public interface PersonRepository {
    String save(Person person);

    Person read(String dni);

    void update(String dni, Person person);
}
