package com.celada.db.mapper;

import com.celada.domain.model.Person;
import com.celada.db.model.PersonEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonEntityMapper {
    PersonEntityMapper INSTANCE = Mappers.getMapper(PersonEntityMapper.class);

    Person toDomain(PersonEntity entity);

    @Mapping(target = "id", ignore = true)
    PersonEntity toEntity(Person person);
}
