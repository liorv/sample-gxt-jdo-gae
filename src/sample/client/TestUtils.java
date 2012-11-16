package sample.client;

import java.util.HashSet;
import java.util.Set;

import javax.jdo.annotations.PersistenceAware;

import jdo.DataObject;
import jdo.GAEUtils;
import jdo.JDOException;
import jdo.JDOSession;

@PersistenceAware
public class TestUtils
{
  public static void Test1() {

  }

  public static String[] peopleNames = {
      "Mich",
      "Anna",
      "Dave",
      "Nadine",
      "Lily" };

  public static String[] groupNames = { "Girls", "Boys" };

  public static String[] categoryNames = { "Math", "Looks" };

  public static boolean[] categoryIsPercent = { true, false };

  public static String[] rewardNames = { "Gold", "Silver" };

  public static Set<Rated> createPeople() {
    HashSet<Rated> people = new HashSet<Rated>();
    for (String name : peopleNames) {
      people.add(new Rated(name));
    }
    return people;
  }

  public static Set<Grouping> createGroups() {
    HashSet<Grouping> groups = new HashSet<Grouping>();
    for (String name : groupNames) {
      groups.add(new Grouping(name));
    }
    return groups;
  }

  public static Set<Category> createCategories() {
    HashSet<Category> categories = new HashSet<Category>();
    for (int i = 0; i < categoryNames.length; i++) {
      categories.add(new Category(categoryNames[i], categoryIsPercent[i]));
    }
    return categories;
  }

  public static Set<Reward> createRewards() {
    HashSet<Reward> rewards = new HashSet<Reward>();
    for (String name : rewardNames) {
      rewards.add(new Reward(name));
    }
    return rewards;
  }

  public static void init() {

    try {
      printState("START");
      
      JDOSession session = JDOSession.open(true);
      session.clear(Rated.class);
      session.clear(Grouping.class);
      session.clear(Category.class);
      session.clear(Reward.class);
      session.close();
      
      printState("Clear - DONE");

      session = JDOSession.open(true);
      Set<Rated> people = createPeople();
      Set<Grouping> groups = createGroups();
      Set<Category> categories = createCategories();
      Set<Reward> rewards = createRewards();
      session.getPM().makePersistentAll(people);
      session.getPM().makePersistentAll(groups);
      session.getPM().makePersistentAll(categories);
      session.getPM().makePersistentAll(rewards);

      session.close();
      printState("Load - DONE");
    }
    catch (JDOException e) {
      e.printStackTrace();
    }
  }

  private static void printState(String label) throws JDOException {
    System.out.println("------------------------- "+label);
    System.out.println(GAEUtils.find(Rated.class, DataObject.AppKey));
    System.out.println(GAEUtils.find(Grouping.class, DataObject.AppKey));
    System.out.println(GAEUtils.find(Category.class, DataObject.AppKey));
    System.out.println(GAEUtils.find(Reward.class, DataObject.AppKey));
  }

}
