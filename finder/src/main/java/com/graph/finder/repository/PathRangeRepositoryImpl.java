package com.graph.finder.repository;

import com.graph.finder.model.Person;
import com.graph.finder.model.Sex;
import java.util.List;
import org.springframework.data.neo4j.core.Neo4jClient;


public class PathRangeRepositoryImpl implements PathRangeRepository {

  private final Neo4jClient neo4jClient;

  public PathRangeRepositoryImpl(Neo4jClient neo4jClient) {
    this.neo4jClient = neo4jClient;
  }


  @Override
  public List<Person> getFriendsByDepth(String name, int minDepth, int maxDepth) {

    String query =
        "MATCH (n:Person {name: '" + name + "'})"
            + "MATCH (n)-[r:IS_FRIEND *" + minDepth + ".." + maxDepth + "]-(p:Person)"
            + "RETURN p";

    return neo4jClient.getQueryRunner().run(query)
        .list((record -> Person.builder()
            .born(record.fields().get(0).value().get("born").asInt())
            .sex(Sex.valueOf(record.fields().get(0).value().get("sex").asString()))
            .name(record.fields().get(0).value().get("name").asString())
            .build()));
  }
}
