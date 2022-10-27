package com.graph.finder.repository;

import com.graph.finder.model.Person;
import java.util.List;

public interface PathRangeRepository {


  List<Person> getFriendsByDepth(String name, int minDepth, int maxDepth);

}
