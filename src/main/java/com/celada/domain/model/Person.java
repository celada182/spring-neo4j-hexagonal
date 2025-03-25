package com.celada.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class Person {
    private String dni;
    private String name;
    private String surname;
    private Person father;
    private Person mother;
    private Person wife;
    private Person husband;
    private List<Person> children;
    private List<Person> brothers;
    private List<Person> sisters;
}
