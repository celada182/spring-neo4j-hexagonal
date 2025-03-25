package com.celada.controller.mapper;

import com.celada.domain.model.Person;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonMapper {
    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    Person toDomain(com.celada.api.model.Person person);

    com.celada.api.model.Person toApi(Person person);
}
