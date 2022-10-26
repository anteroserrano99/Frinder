package utilities;

import com.graph.finder.controller.Sex;
import com.graph.finder.model.Person;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomUtilities {


  static Random RANDOM = new Random(1L);


  public static <T extends Enum> T getRandomEnum(Class<T> enumeration) throws Exception{

    if (!enumeration.isEnum()) throw new Exception("The specified class isn't an enum");

    final T[] enumList = enumeration.getEnumConstants();

    return enumList[RANDOM.nextInt(enumList.length)];
  }

  public static <T> T getRandomElementFromList(List<T> list) throws Exception{

    if (list.isEmpty()) throw new Exception("The specified list is empty");

    return list.get(RANDOM.nextInt(list.size()));
  }


  public static List<Person> generatePersons(String namePrefix, int amount) throws Exception {

    List<Person> personList = new ArrayList<>();

    for (int i = 0; i < amount; i++) {

      personList.add(generateRandomPerson(namePrefix, amount));

    }

    return personList;
  }

  private static Person generateRandomPerson(String namePrefix, int amount) throws Exception {

    return Person.builder()
        .born(RANDOM.nextInt(2022))
        .name(namePrefix + "-" +RANDOM.nextInt(amount))
        .sex(getRandomEnum(Sex.class))
        .build();
  }

}
