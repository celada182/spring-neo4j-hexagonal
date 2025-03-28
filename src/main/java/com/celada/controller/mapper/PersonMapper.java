package com.celada.controller.mapper;

import com.celada.domain.model.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonMapper {
    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    Person toDomain(com.celada.controller.model.Person person);

    com.celada.controller.model.Person toApi(Person person);
}
