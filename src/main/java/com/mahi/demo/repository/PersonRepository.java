package com.mahi.demo.repository;


import com.mahi.demo.models.Person;
import org.springframework.data.repository.CrudRepository;


public interface PersonRepository extends CrudRepository<Person, Long> {
    Person findByUsername(String username);
    Person findByEmail(String email);
    Person findAllByUsername(String username);
    Long countByEmail(String email);
    Long countByUsername(String username);
    Iterable<Person>findAllByEmail(String email);
    Iterable<Person>findAllById(Long Long);
    Iterable<Person>findPersonById(Long Long);
    Iterable<Person>findAllByFirstName(String partialstring);
}
