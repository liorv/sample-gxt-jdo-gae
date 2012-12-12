package jdo;

import java.util.HashSet;
import java.util.Set;

import javax.jdo.Extent;
import javax.jdo.annotations.PersistenceAware;

@PersistenceAware
public class TestJDO
{
  public static void run() {
    init();

    addToGroup("Girls", "Nadine");
    attachCategory("Girls", "Looks");
    rate("Nadine", "Looks", 10);
    rate("Nadine", "Looks", 9);

    addToGroup("Girls", "Anna"); // after category attached
    attachCategory("Girls", "Looks"); // attach again
    rate("Anna", "Looks", 8);
    rate("Lily", "Looks", 6); // before she got added

    addToGroup("Girls", "Lily");
    rate("Lily", "Looks", 5);

    addToGroup("Boys", "Dave");
    attachCategory("Boys", "Looks");
    rate("Dave", "Looks", 8);

    /*
     * JDOSession session = JDOSession.open(true); Grouping g =
     * session.find(Grouping.class, "Girls");
     * session.getPM().deletePersistent(g); session.close();
     */
  }

  private static void rate(String rated, String category, int score) {
    JDOSession session = JDOSession.open(true);
    Category c = session.find(Category.class, category);
    Rated r = session.find(Rated.class, rated);
    c.rate(r, score);
    session.close();
  }

  private static void addToGroup(String group, String rated) {
    JDOSession session = JDOSession.open();    
    Rated r = session.find(Rated.class, rated);
    Grouping g = session.find(Grouping.class, group);    
    g.addMember(r);
    session.close();
  }

  private static void attachCategory(String group, String category) {

    JDOSession session = JDOSession.open();
    Category c = session.find(Category.class, category);
    Grouping g = session.find(Grouping.class, group);
    g.attachCategory(c);
    session.close();
  }

  public static String[] peopleNames = {
      "Mich",
      "Anna",
      "Dave",
      "Nadine",
      "Lily" };

  public static String[] groupNames = { "Girls", "Boys" };

  public static String[] categoryNames = { "Math", "Looks" };

  public static int[] categoryMax = { 100, 50 };

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
      categories.add(new Category(categoryNames[i], categoryMax[i]));
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

      JDOUtils.clear(Rated.class);
      JDOUtils.clear(Grouping.class);
      JDOUtils.clear(Category.class);
      JDOUtils.clear(Reward.class);

      printState("Clear - DONE");

      Set<Rated> people = createPeople();
      Set<Grouping> groups = createGroups();
      Set<Category> categories = createCategories();
      Set<Reward> rewards = createRewards();

      JDOUtils.persist(people);
      JDOUtils.persist(groups);
      JDOUtils.persist(categories);
      JDOUtils.persist(rewards);

      printState("Load - DONE");
    }
    catch (JDOException e) {
      e.printStackTrace();
    }
  }

  private static void printState(String label) throws JDOException {
    System.out.println("------------------------- " + label);
    JDOSession session = JDOSession.open();
    printExtent(session, Rated.class);
    printExtent(session, Grouping.class);
    printExtent(session, Category.class);
    printExtent(session, Reward.class);
    session.close();
    System.out.println();
  }

  private static <T extends DataObject> void printExtent(JDOSession session,
      Class<T> clz)
  {
    Extent<T> ex = session.getPM().getExtent(clz);
    for (T o : ex) {
      System.out.println("+ " + clz.getSimpleName() + ": [" + o.name
          + "], id=[" + session.getPM().getObjectId(o) + "]");
    }
  }

}
