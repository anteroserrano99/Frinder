package com.graph.finder.service;

import com.graph.finder.model.Sex;
import com.graph.finder.model.Person;
import com.graph.finder.repository.PersonRepository;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
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


  public Person createPerson(Person person){

    final Set<String> preferences = person.getPreferences()
        .stream()
        .map(Sex::getNameLiteral)
        .collect(Collectors.toSet());

    return personRepository.insertPerson(person.getBorn(), person.getName(), person.getSex().getNameLiteral(), preferences);
  }

  public boolean addFriend(String name, String friendName){

    return personRepository.addFriend(name, friendName);
  }

  public List<Person> getFriendByDepth(String name, int minDepth, int maxDepth){

    return personRepository.getFriendsByDepth(name, minDepth, maxDepth);
  }
}
