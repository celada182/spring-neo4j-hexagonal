package com.celada.repository.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

@Node
@Builder
@Getter
@Setter
public class Person {
    @Id
    @GeneratedValue
    private Long id;

    private String dni;
    private String name;
    private String surname;
    @Relationship("HAS_FATHER")
    private Person father;
    @Relationship("HAS_MOTHER")
    private Person mother;
    @Relationship("HAS_WIFE")
    private Person wife;
    @Relationship("HAS_HUSBAND")
    private Person husband;
    @Relationship("HAS_CHILDREN")
    private List<Person> children;
    @Relationship("HAS_BROTHERS")
    private List<Person> brothers;
    @Relationship("HAS_SISTER")
    private List<Person> sisters;
}
