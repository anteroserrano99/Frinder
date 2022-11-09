package com.graph.finder.repository;

import com.graph.finder.model.Person;
import java.util.List;

public interface PathRangeRepository {


  List<Person> getFriendsByDepth(Person person, int minDepth, int maxDepth);

}
