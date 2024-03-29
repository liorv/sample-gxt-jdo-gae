package sample.server.handlers;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;
import jdo.Category;
import jdo.JDOSession;
import jdo.Rated;
import jdo.StatRelation;
import jdo.TestJDO;
import sample.server.dtomapper.CategoryDTOMapper;
import sample.server.dtomapper.RatedDTOMapper;
import sample.server.dtomapper.StatsDTOMapper;
import sample.shared.action.LoadDataAction;
import sample.shared.result.LoadDataResult;

public class LoadDataActionHandler implements ActionHandler<LoadDataAction, LoadDataResult>
{
  @Override
  public LoadDataResult execute(LoadDataAction action,
      ExecutionContext context) throws DispatchException
  {
    TestJDO.run();
    JDOSession session = JDOSession.open();
    LoadDataResult retval = new LoadDataResult();
    (new StatsDTOMapper()).toDTO(session.getPM().getExtent(StatRelation.class),
        retval.stats);
    
    (new RatedDTOMapper())
        .toDTO(session.getPM().getExtent(Rated.class), retval.allRated);
    
    (new CategoryDTOMapper()).toDTO(session.getPM().getExtent(Category.class),
        retval.allCategories);
    
    session.close();
    return retval;
  }

  @Override
  public Class<LoadDataAction> getActionType() {
    return LoadDataAction.class;
  }

  @Override
  public void rollback(LoadDataAction action, LoadDataResult stats,
      ExecutionContext context) throws DispatchException
  {
  }
}
