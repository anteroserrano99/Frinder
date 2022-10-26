package com.graph.finder.model;

import com.graph.finder.controller.Sex;
import lombok.Data;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node("Person")
@Data
public class Person {

  @Id
  private final String name;
  private final Integer born;
  private final Sex sex;

  public Person(Integer born, String name, Sex sex) {
    this.born = born;
    this.name = name;
    this.sex = sex;
  }
}

