package com.graph.finder.repository;

import com.graph.finder.model.Person;
import com.graph.finder.model.Sex;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.data.neo4j.core.Neo4jClient;


public class PathRangeRepositoryImpl implements PathRangeRepository {

  private final Neo4jClient neo4jClient;

  public PathRangeRepositoryImpl(Neo4jClient neo4jClient) {
    this.neo4jClient = neo4jClient;
  }


  @Override
  public List<Person> getFriendsByDepth(Person person, int minDepth, int maxDepth) {



    //TODO TEST FOR NO PREFERENCES SET RESULTS
    String query =
        "MATCH (n:Person {name: $name})"
            + "MATCH (n)-[r:IS_FRIEND *" + minDepth + ".." + maxDepth + "]-(p:Person)"
            + "WHERE any(x IN p.preferences WHERE x IN $preferences)"
            + "RETURN p";


    Map<String, Object> parameters = new HashMap<>();
    parameters.put("name", person.getName());
    parameters.put("preferences", person.getPreferencesAsString());


    return neo4jClient.getQueryRunner()
        .run(query, parameters)
        .list((record -> Person.builder()
            .born(record.fields().get(0).value().get("born").asInt())
            .sex(Sex.valueOf(record.fields().get(0).value().get("sex").asString()))
            .name(record.fields().get(0).value().get("name").asString())
            .build()));
  }
}
