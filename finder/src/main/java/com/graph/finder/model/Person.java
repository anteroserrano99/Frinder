package com.graph.finder.model;

import java.util.Objects;
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

  public static PersonBuilder builder() {
    return new PersonBuilder();
  }

  public static class PersonBuilder{

    private String name;
    private Integer born;
    private Sex sex;

    public PersonBuilder sex(Sex sex){
      this.sex = sex;
      return this;
    }

    public PersonBuilder name(String name){
      this.name = name;
      return this;
    }

    public PersonBuilder born(int born){
      this.born = born;
      return this;
    }


    public Person build(){
      return new Person(born, name, sex);
    }

  }



  @Override
  public int hashCode() {
    return Objects.hash(name, born, sex);
  }
}

