package com.graph.finder.controller;

import com.graph.finder.model.Person;
import com.graph.finder.repository.PersonRepository;
import com.graph.finder.service.PersonService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/persons")
public class PersonController {

  @Autowired
  PersonService personService;

  public PersonController(PersonService personService) {
    this.personService = personService;
  }

  @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Person> getAllPersons(){

    return personService.getAllPersons();
  }


  @GetMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
  public Person create(@RequestParam("born") int born, @RequestParam("name") String name ){

    return personService.createPerson(born, name);

  }




}