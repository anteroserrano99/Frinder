package com.graph.finder.model;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node("Person")
@Data
public class Person {
  @Id
  private final String name;
  private final Integer born;

  public Person(Integer born, String name) {
    this.born = born;
    this.name = name;
  }
}

