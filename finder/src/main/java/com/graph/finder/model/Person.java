package com.graph.finder.model;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
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
  private final Set<Sex> preferences;

  public Person(Integer born, String name, Sex sex,
      Set<Sex> preferences) {
    this.born = born;
    this.name = name;
    this.sex = sex;
    this.preferences = preferences;
  }

  public Set<String> getPreferencesAsString(){

    if (preferences == null) return new HashSet<>();

    return this.getPreferences()
        .stream()
        .map(Sex::getNameLiteral)
        .collect(Collectors.toSet());
  }

  public static PersonBuilder builder() {
    return new PersonBuilder();
  }

  public static class PersonBuilder{

    private String name;
    private Integer born;
    private Sex sex;
    private Set<Sex> preferences;

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

    public PersonBuilder preferences(Set<Sex> preferences){
      this.preferences = preferences;
      return this;
    }


    public Person build(){
      return new Person(born, name, sex, preferences);
    }

  }



  @Override
  public int hashCode() {
    return Objects.hash(name, born, sex);
  }
}

