package sample.server;

import java.util.LinkedList;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import jdo.Category;
import jdo.Grouping;
import jdo.JDOSession;
import jdo.Rated;
import jdo.StatRelation;
import jdo.TestJDO;

import sample.client.RatingService;
import sample.server.dtomapper.CategoryDTOMapper;
import sample.server.dtomapper.GroupDTOMapper;
import sample.server.dtomapper.RatedDTOMapper;
import sample.server.dtomapper.StatsDTOMapper;
import sample.shared.Action;
import sample.shared.Response;
import sample.shared.result.CategoryDTO;
import sample.shared.result.GroupDTO;
import sample.shared.result.RatedDTO;
import sample.shared.result.StatsDTO;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class RatingServiceImpl extends RemoteServiceServlet implements RatingService
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
  public List<StatsDTO> loadData() {
    TestJDO.run();
    JDOSession session = JDOSession.open();
    List<StatsDTO> retval = new LinkedList<StatsDTO>();
    (new StatsDTOMapper()).toDTO(session.getPM().getExtent(StatRelation.class), retval);
    session.close();
    return retval;
  }

  @Override
  public List<StatsDTO> getCategoryRatedStats(String category) {
    JDOSession session = JDOSession.open();
    Category cat = session.find(Category.class, category);

    List<StatsDTO> retval = new LinkedList<StatsDTO>();
    (new StatsDTOMapper()).toDTO(cat.getRatedStats(), retval);
    
    session.close();
    return retval;
  }

  @Override
  public List<StatsDTO> getCategoryGroupStats(String category) {
    JDOSession session = JDOSession.open();
    Category cat = session.find(Category.class, category);

    List<StatsDTO> retval = new LinkedList<StatsDTO>();
    (new StatsDTOMapper()).toDTO(cat.getGroupStats(), retval);

    session.close();
    return retval;
  }

  @Override
  public List<StatsDTO> getRatedStats(String rated) {
    JDOSession session = JDOSession.open();
    Rated r = session.find(Rated.class, rated);
    
    List<StatsDTO> retval = new LinkedList<StatsDTO>();
    (new StatsDTOMapper()).toDTO(r.getStats(), retval);

    session.close();
    return retval;
  }

  @Override
  public List<StatsDTO> getGroupStats(String group) {
    JDOSession session = JDOSession.open();
    Grouping g = session.find(Grouping.class, group);
    
    List<StatsDTO> retval = new LinkedList<StatsDTO>();
    (new StatsDTOMapper()).toDTO(g.getStats(), retval);
   
    session.close();
    return retval;
  }

  @Override
  public List<CategoryDTO> getAllCategories() {
    JDOSession session = JDOSession.open();

    LinkedList<CategoryDTO> retval = new LinkedList<CategoryDTO>();
    (new CategoryDTOMapper()).toDTO(session.getPM().getExtent(Category.class),
        retval);
    session.close();
    return retval;
  }

  @Override
  public List<RatedDTO> getAllRated() {
    JDOSession session = JDOSession.open();

    LinkedList<RatedDTO> retval = new LinkedList<RatedDTO>();
    (new RatedDTOMapper())
        .toDTO(session.getPM().getExtent(Rated.class), retval);
    session.close();
    return retval;
  }

  @Override
  public GroupDTO getGroup(String groupName) {
    JDOSession session = JDOSession.open();
    Grouping g = session.find(Grouping.class, groupName);
    session.close();
    return (new GroupDTOMapper()).toDTO(g);
  }

  @Override
  public void rate(String category, String rated, float score) {
    JDOSession session = JDOSession.open();
    Category c = session.find(Category.class, category);
    Rated r = session.find(Rated.class, rated);
    c.rate(r, score);
    session.close();
  }
  
// ---------------------------------
  
  @Override
  public <R extends Response> R execute(Action<R> action) {
    System.out.println("server:execute() ---> "+action.getClass().getSimpleName());
    // TODO Auto-generated method stub    
    return null;
  }
}
