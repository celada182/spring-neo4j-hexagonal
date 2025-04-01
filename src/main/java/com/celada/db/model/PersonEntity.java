package com.celada.db.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

@Node("Person")
@Builder
@Getter
@Setter
public class PersonEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String dni;
    private String name;
    private String surname;
    @Relationship("HAS_FATHER")
    private PersonEntity father;
    @Relationship("HAS_MOTHER")
    private PersonEntity mother;
    @Relationship("HAS_WIFE")
    private PersonEntity wife;
    @Relationship("HAS_HUSBAND")
    private PersonEntity husband;
    @Relationship("HAS_CHILDREN")
    private List<PersonEntity> children;
    @Relationship("HAS_BROTHERS")
    private List<PersonEntity> brothers;
    @Relationship("HAS_SISTER")
    private List<PersonEntity> sisters;
}
