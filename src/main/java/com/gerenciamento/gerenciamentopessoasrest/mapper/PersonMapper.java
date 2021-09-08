package com.gerenciamento.gerenciamentopessoasrest.mapper;

import com.gerenciamento.gerenciamentopessoasrest.Model.Person;
import com.gerenciamento.gerenciamentopessoasrest.dto.request.PersonDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper
public interface PersonMapper {
    PersonMapper INSTANCE = getMapper(PersonMapper.class);

    @Mapping(target = "birthDate", source = "birthDate", dateFormat = "dd-MM-yyyy")
    Person toModel(PersonDTO personDTO);

    PersonDTO toDTO(Person person);
}
