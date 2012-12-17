package sample.server;

import java.util.LinkedList;
import java.util.List;

import jdo.Category;
import jdo.Grouping;
import jdo.JDOSession;
import jdo.Rated;
import jdo.StatRelation;
import jdo.TestJDO;

import org.springframework.stereotype.Service;

import sample.client.RatingService;
import sample.client.dto.StatsDTO;

/**
 * The server side implementation of the RPC service.
 */
@Service("rating")
public class RatingServiceImpl implements RatingService
{

  /**
   * Escape an html string. Escaping data received from the client helps to
   * prevent cross-site script vulnerabilities.
   * 
   * @param html
   *          the html string to escape
   * @return the escaped string
   */
  @SuppressWarnings("unused")
  private String escapeHtml(String html) {
    if (html == null) { return null; }
    return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;")
        .replaceAll(">", "&gt;");
  }

  @Override
  public List<StatsDTO> getCategoryRatedStats(String category) {
    JDOSession session = JDOSession.open();
    Category cat = session.find(Category.class, category);

    LinkedList<StatsDTO> retval = toDTO(cat.getRatedStats());
    session.close();
    return retval;
  }

  @Override
  public List<StatsDTO> getCategoryGroupStats(String category) {
    JDOSession session = JDOSession.open();
    Category cat = session.find(Category.class, category);

    LinkedList<StatsDTO> retval = toDTO(cat.getGroupStats());
    session.close();
    return retval;
  }

  @Override
  public List<StatsDTO> getRatedStats(String rated) {
    JDOSession session = JDOSession.open();
    Rated r = session.find(Rated.class, rated);
    LinkedList<StatsDTO> retval = toDTO(r.getStats());
    session.close();
    return retval;
  }

  @Override
  public List<StatsDTO> getGroupStats(String group) {
    JDOSession session = JDOSession.open();
    Grouping g = session.find(Grouping.class, group);
    LinkedList<StatsDTO> retval = toDTO(g.getStats());
    session.close();
    return retval;
  }

  @Override
  public List<StatsDTO> loadData() {
    TestJDO.run();
    JDOSession session = JDOSession.open();
    List<StatsDTO> retval = toDTO(session.getPM().getExtent(StatRelation.class));
    session.close();
    return retval;
  }

  private LinkedList<StatsDTO> toDTO(Iterable<StatRelation> stats) {
    LinkedList<StatsDTO> retval = new LinkedList<StatsDTO>();
    for (StatRelation stat : stats) {
      StatsDTO dto =
          new StatsDTO(stat.getCategory().getName(), stat.getName(),
              stat.getMin(), stat.getMax(), stat.getMean(), stat.getCount());
      retval.add(dto);
    }
    return retval;
  }
}
