package com.mahi.demo.service;


import com.mahi.demo.models.Person;
import com.mahi.demo.repository.PersonRepository;
import com.mahi.demo.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class PersonService {
    @Autowired
    PersonRepository personRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    public PersonService(PersonRepository personRepository){
        this.personRepository=personRepository;
    }
    public Person findByEmail(String email){
        return personRepository.findByEmail(email);
    }
    public Long countByEmail(String email){
        return personRepository.countByEmail(email);
    }
    public Person findByUsername(String username){
        return personRepository.findByUsername(username);
    }
    public void saveUser(Person person){
        person.setPersonRoles(Arrays.asList(roleRepository.findByRole("USER")));
        person.setEnabled(true);
        personRepository.save(person);
    }
    public void saveAdmin(Person person){
        person.setPersonRoles(Arrays.asList(roleRepository.findByRole("ADMIN")));
        person.setEnabled(true);
        personRepository.save(person);
    }


}
