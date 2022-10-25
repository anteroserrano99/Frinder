package com.graph.finder.repository;

import com.graph.finder.model.Person;
import java.util.List;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

public interface PersonRepository extends Neo4jRepository<Person, String> {

  @Query("Match (n:Person) RETURN n")
  List<Person> getAllPerson();

  @Query("Merge (n:Person {name: $name, born: $born}) RETURN n")
  Person insertPerson(int born, String name);



}
