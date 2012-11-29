package sample.client;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.jdo.Query;
import javax.jdo.annotations.PersistenceAware;

import sample.client.Category;
import sample.client.Grouping;
import sample.client.Rated;
import sample.client.Reward;

import jdo.JDOException;
import jdo.JDOSession;
import jdo.JDOUtils;

@PersistenceAware
public class TestUtils
{
  public static void Test2() {
    init();

    addToGroup("Girls", "Nadine");
    attachCategory("Girls", "Looks");
    rate("Nadine", "Looks", 10);
    rate("Nadine", "Looks", 9);
    
    addToGroup("Girls", "Anna");
    attachCategory("Girls", "Looks");
    rate("Anna", "Looks", 85);
    
    JDOSession session = JDOSession.open(true);
    Grouping g = session.find(Grouping.class, "Girls");
    session.getPM().deletePersistent(g);
    session.close();
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
    c.addGroup(g);
    session.close();
  }

  public static <T> T find(Class<T> clz, String id) {
    JDOSession session = JDOSession.open();
    Query q = session.getPM().newQuery(Rated.class);
    q.setFilter("id == idParam");
    q.declareParameters("String idParam");

    @SuppressWarnings("unchecked")
    List<T> retval = (List<T>) q.execute(id);

    session.close();
    return retval.get(0);
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
    /*
    Extent<TEAM> teamx = session.getPM().getExtent(TEAM.class);
    for (TEAM team : teamx) {
      System.out.println("+ TEAM: ["+team.name+"], id=["+session.getPM().getObjectId(team)+"]");
    }
    */
    session.close();
    System.out.println();
  }

}
