package com.graph.finder.test.repository;

import com.graph.finder.model.Person;
import java.util.List;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

public interface PersonTestRepository extends Neo4jRepository<Person, String> {

  @Query("Match (n:Person) DETACH DELETE n")
  void deleteAllPersons();

}
