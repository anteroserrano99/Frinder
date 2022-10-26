package com.graph.finder.repository;

import com.graph.finder.model.Person;
import java.util.List;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

public interface PersonRepository extends Neo4jRepository<Person, String> {

  @Query("Match (n:Person) RETURN n")
  List<Person> getAllPerson();

  @Query("Merge (n:Person {name: $name, born: $born, sex: $sex}) RETURN n")
  Person insertPerson(int born, String name, String sex);


  @Query("MATCH (n:Person {name: $name})-[:IS_FRIEND]-(p:Person) RETURN p")
  List<Person> getPersonFriends(String name);

  @Query("MATCH (n:Person {name: $name})"
      + "MATCH (p:Person {name: $friendName})"
      + "MERGE (p)-[r:IS_FRIEND]-(n)"
      + " RETURN count(r)>0")
  boolean addFriend(String name, String friendName);

}
