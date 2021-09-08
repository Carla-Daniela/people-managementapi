package com.gerenciamento.gerenciamentopessoasrest.repository;

import com.gerenciamento.gerenciamentopessoasrest.Model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
