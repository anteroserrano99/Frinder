package utilities;

import com.graph.finder.model.Sex;
import com.graph.finder.model.Person;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Set;

public class RandomUtilities {


  static Random RANDOM = new Random(1L);


  public static <T> T getRandomEnum(Class<T> enumeration) throws Exception{

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

  public static <T> Set<T> getEnumSet(Class<T> enumeration) throws Exception {

    final int length = enumeration.getEnumConstants().length;

    HashSet<T> result = new HashSet<>();

    for (int i = 0; i < length; i++) {
      result.add(getRandomEnum(enumeration));
    }

    return result;
  }

  private static Person generateRandomPerson(String namePrefix, int amount) throws Exception {

    final String name = namePrefix + "-" + RANDOM.nextInt(amount);
    final int born = RANDOM.nextInt(2022);

    return Person.builder()
        .born(born)
        .name(name + Objects.hash(name, born))
        .sex(getRandomEnum(Sex.class))
        .preferences(getEnumSet(Sex.class))
        .build();
  }

}
