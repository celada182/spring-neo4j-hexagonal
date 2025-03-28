package com.celada.controller.mapper;

import com.celada.controller.model.Person;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-28T10:34:42+0100",
    comments = "version: 1.6.3, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
public class PersonMapperImpl implements PersonMapper {

    @Override
    public com.celada.domain.model.Person toDomain(Person person) {
        if ( person == null ) {
            return null;
        }

        com.celada.domain.model.Person.PersonBuilder person1 = com.celada.domain.model.Person.builder();

        person1.dni( person.getDni() );
        person1.name( person.getName() );
        person1.surname( person.getSurname() );
        person1.father( toDomain( person.getFather() ) );
        person1.mother( toDomain( person.getMother() ) );
        person1.wife( toDomain( person.getWife() ) );
        person1.husband( toDomain( person.getHusband() ) );
        person1.children( personListToPersonList( person.getChildren() ) );
        person1.brothers( personListToPersonList( person.getBrothers() ) );
        person1.sisters( personListToPersonList( person.getSisters() ) );

        return person1.build();
    }

    @Override
    public Person toApi(com.celada.domain.model.Person person) {
        if ( person == null ) {
            return null;
        }

        Person person1 = new Person();

        person1.setDni( person.getDni() );
        person1.setName( person.getName() );
        person1.setSurname( person.getSurname() );
        person1.setFather( toApi( person.getFather() ) );
        person1.setMother( toApi( person.getMother() ) );
        person1.setBrothers( personListToPersonList1( person.getBrothers() ) );
        person1.setSisters( personListToPersonList1( person.getSisters() ) );
        person1.setHusband( toApi( person.getHusband() ) );
        person1.setWife( toApi( person.getWife() ) );
        person1.setChildren( personListToPersonList1( person.getChildren() ) );

        return person1;
    }

    protected List<com.celada.domain.model.Person> personListToPersonList(List<Person> list) {
        if ( list == null ) {
            return null;
        }

        List<com.celada.domain.model.Person> list1 = new ArrayList<com.celada.domain.model.Person>( list.size() );
        for ( Person person : list ) {
            list1.add( toDomain( person ) );
        }

        return list1;
    }

    protected List<Person> personListToPersonList1(List<com.celada.domain.model.Person> list) {
        if ( list == null ) {
            return null;
        }

        List<Person> list1 = new ArrayList<Person>( list.size() );
        for ( com.celada.domain.model.Person person : list ) {
            list1.add( toApi( person ) );
        }

        return list1;
    }
}
