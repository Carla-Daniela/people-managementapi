package com.gerenciamento.gerenciamentopessoasrest.service;

import com.gerenciamento.gerenciamentopessoasrest.Model.Person;
import com.gerenciamento.gerenciamentopessoasrest.dto.request.PersonDTO;
import com.gerenciamento.gerenciamentopessoasrest.dto.response.MessageResponseDTO;
import com.gerenciamento.gerenciamentopessoasrest.exception.PersonNotFoundException;
import com.gerenciamento.gerenciamentopessoasrest.mapper.PersonMapper;
import com.gerenciamento.gerenciamentopessoasrest.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class PersonService {
    private PersonRepository personRepository;
    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    @Autowired
    public  PersonService(PersonRepository personRepository){
        this.personRepository=personRepository;
    }
    public MessageResponseDTO CreatePerson( PersonDTO personDTO){
        Person personToSave=personMapper.toModel(personDTO);
        Person savedPerson= personRepository.save(personToSave);
        return MessageResponseDTO
                .builder()
                .message("create person"+ savedPerson.getId())
                .build();
    }
    public List<PersonDTO> listAll(){
        List<Person>allPeople = personRepository.findAll();
        return allPeople.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }
    public PersonDTO findById(Long id)throws PersonNotFoundException {
        Person person= verifyIfExists(id);
        return personMapper.toDTO(person);
    }
    public void delete(Long id) throws PersonNotFoundException {
        verifyIfExists(id);
        personRepository.deleteById(id);
    }
    public MessageResponseDTO updateById(Long id, PersonDTO personDTO) throws PersonNotFoundException {
        verifyIfExists(id);

        Person personToUpdate = personMapper.toModel(personDTO);

        Person updatedPerson = personRepository.save(personToUpdate);
        return createMessageResponse(updatedPerson.getId(), "Updated person with ID ");
    }
    private Person verifyIfExists(Long id) throws PersonNotFoundException {
        return personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }
    private MessageResponseDTO createMessageResponse(Long id, String message) {
        return MessageResponseDTO
                .builder()
                .message(message + id)
                .build();
    }
}
