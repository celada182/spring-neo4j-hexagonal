package com.celada.db.mapper;

import com.celada.db.model.PersonEntity;
import com.celada.domain.model.Person;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-28T10:33:45+0100",
    comments = "version: 1.6.3, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
public class PersonEntityMapperImpl implements PersonEntityMapper {

    @Override
    public Person toDomain(PersonEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Person.PersonBuilder person = Person.builder();

        person.dni( entity.getDni() );
        person.name( entity.getName() );
        person.surname( entity.getSurname() );
        person.father( toDomain( entity.getFather() ) );
        person.mother( toDomain( entity.getMother() ) );
        person.wife( toDomain( entity.getWife() ) );
        person.husband( toDomain( entity.getHusband() ) );
        person.children( personEntityListToPersonList( entity.getChildren() ) );
        person.brothers( personEntityListToPersonList( entity.getBrothers() ) );
        person.sisters( personEntityListToPersonList( entity.getSisters() ) );

        return person.build();
    }

    @Override
    public PersonEntity toEntity(Person person) {
        if ( person == null ) {
            return null;
        }

        PersonEntity.PersonEntityBuilder personEntity = PersonEntity.builder();

        personEntity.dni( person.getDni() );
        personEntity.name( person.getName() );
        personEntity.surname( person.getSurname() );
        personEntity.father( toEntity( person.getFather() ) );
        personEntity.mother( toEntity( person.getMother() ) );
        personEntity.wife( toEntity( person.getWife() ) );
        personEntity.husband( toEntity( person.getHusband() ) );
        personEntity.children( personListToPersonEntityList( person.getChildren() ) );
        personEntity.brothers( personListToPersonEntityList( person.getBrothers() ) );
        personEntity.sisters( personListToPersonEntityList( person.getSisters() ) );

        return personEntity.build();
    }

    protected List<Person> personEntityListToPersonList(List<PersonEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<Person> list1 = new ArrayList<Person>( list.size() );
        for ( PersonEntity personEntity : list ) {
            list1.add( toDomain( personEntity ) );
        }

        return list1;
    }

    protected List<PersonEntity> personListToPersonEntityList(List<Person> list) {
        if ( list == null ) {
            return null;
        }

        List<PersonEntity> list1 = new ArrayList<PersonEntity>( list.size() );
        for ( Person person : list ) {
            list1.add( toEntity( person ) );
        }

        return list1;
    }
}
