package com.gerenciamento.gerenciamentopessoasrest.controller;


import com.gerenciamento.gerenciamentopessoasrest.Model.Person;
import com.gerenciamento.gerenciamentopessoasrest.dto.request.PersonDTO;
import com.gerenciamento.gerenciamentopessoasrest.dto.response.MessageResponseDTO;
import com.gerenciamento.gerenciamentopessoasrest.exception.PersonNotFoundException;
import com.gerenciamento.gerenciamentopessoasrest.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/people")

public class PersonController {

    private PersonService personService;

    @Autowired
    public  PersonController(PersonService personService){
        this.personService=personService;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO CreatePerson(@RequestBody @Valid PersonDTO personDTO){
       return personService.CreatePerson(personDTO);
    }
    @GetMapping
    public List<PersonDTO> listAll() {
        return personService.listAll();
    }

    @GetMapping("/{id}")
    public PersonDTO findById(@PathVariable Long id) throws PersonNotFoundException {
        return personService.findById(id);
    }
    @PutMapping("/{id}")
    public MessageResponseDTO updateById(@PathVariable Long id, @RequestBody @Valid PersonDTO personDTO) throws PersonNotFoundException {
        return personService.updateById(id, personDTO);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws PersonNotFoundException {
        personService.delete(id);
    }
    }
