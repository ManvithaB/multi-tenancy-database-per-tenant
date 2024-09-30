package com.manvitha.database_per_tenant.service;

import com.manvitha.database_per_tenant.entity.Person;
import com.manvitha.database_per_tenant.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonRepository personRepository;

    @Override
    public Person addPerson(Person person) {
        return personRepository.save(person);
    }
}
