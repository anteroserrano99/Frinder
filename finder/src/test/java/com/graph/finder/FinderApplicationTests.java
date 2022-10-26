package com.graph.finder;


import com.graph.finder.controller.Sex;
import com.graph.finder.model.Person;
import com.graph.finder.repository.PersonRepository;
import com.graph.finder.test.repository.PersonTestRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import utilities.RandomUtilities;

@SpringBootTest
class FinderApplicationTests {

  @Autowired
  PersonRepository personRepository;

	@Autowired
	PersonTestRepository personTestRepository;


	@BeforeAll
  public static void setUp(@Autowired PersonTestRepository personTestRepository){
		personTestRepository.deleteAllPersons();
	}



  @Test
  void contextLoads() throws Exception {

    final List<Person> allPerson = RandomUtilities.generatePersons("L1", 50);

		persistPersonList(allPerson);

		int relationships = getCustomRelationshipNumber(allPerson.size(), 0.05);

		persistRandomRelationShips(allPerson, relationships);

  }


  public void persistRandomRelationShips(List<Person> personList, int relationShips)
			throws Exception {

		for (int i = 0; i < relationShips; i++) {

			final Person p1 = RandomUtilities.getRandomElementFromList(personList);

			final Person p2 = RandomUtilities.getRandomElementFromList(personList);

			personRepository.addFriend(p1.getName(), p2.getName());
		}


	}



  public void persistPersonList(List<Person> personList){

		for (Person person: personList){
			personRepository.insertPerson(person.getBorn(), person.getName(), person.getSex().name());
		}

	}

//DIRECTED GRAPH   (n * n-1) = r
//UNDIRECTED GRAPH (n * n-1)/2 = r
  public int getMaxRelationshipNumber(int size){
		return (size * size-1) / 2;
	}

	public int getHalfRelationshipNumber(int size){
		return (int) ((size * size-1) / 2 * 0.5);
	}

	public int getQuarterRelationshipNumber(int size){
		return (int) ((size * size-1) / 2 * 0.25);
	}

	public int getCustomRelationshipNumber(int size, double multiplier){
		return (int) ((size * size-1) / 2 * multiplier);
	}


}
