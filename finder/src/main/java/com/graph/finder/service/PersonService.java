package com.graph.finder.service;

import com.graph.finder.controller.Sex;
import com.graph.finder.model.Person;
import com.graph.finder.repository.PersonRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

  @Autowired
  PersonRepository personRepository;


  public PersonService(PersonRepository personRepository) {
    this.personRepository = personRepository;
  }

  public List<Person> getAllPersons(){

    return personRepository.getAllPerson();
  }

  public List<Person> getFriends(String name){

    return personRepository.getPersonFriends(name);
  }


  public Person createPerson(int born, String name, Sex sex){

    return personRepository.insertPerson(born, name, sex.name());
  }

  public boolean addFriend(String name, String friendName){

    return personRepository.addFriend(name, friendName);
  }
}
