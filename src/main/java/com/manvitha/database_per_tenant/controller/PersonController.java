package com.manvitha.database_per_tenant.controller;

import com.manvitha.database_per_tenant.entity.Person;
import com.manvitha.database_per_tenant.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/person")
public class PersonController {

    @Autowired
    PersonService personService;

    @PostMapping(value = "/addPerson")
    public ResponseEntity addPerson(@Validated @RequestBody Person person) {
        personService.addPerson(person);
        return new ResponseEntity("success", HttpStatus.CREATED);
    }
}
