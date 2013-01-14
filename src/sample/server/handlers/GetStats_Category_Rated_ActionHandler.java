package sample.server.handlers;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;
import jdo.Category;
import jdo.JDOSession;
import sample.server.dtomapper.StatsDTOMapper;
import sample.shared.action.GetStats_Category_Rated_Action;
import sample.shared.result.ListStatsResult;

public class GetStats_Category_Rated_ActionHandler implements ActionHandler<GetStats_Category_Rated_Action, ListStatsResult>
{
  @Override
  public ListStatsResult execute(GetStats_Category_Rated_Action a,
      ExecutionContext c) throws DispatchException
  {
    JDOSession session = JDOSession.open();
    Category cat = session.find(Category.class, a.category);

    ListStatsResult retval = new ListStatsResult();
    (new StatsDTOMapper()).toDTO(cat.getRatedStats(), retval.stats);

    session.close();
    return retval;
  }

  @Override
  public Class<GetStats_Category_Rated_Action> getActionType() {
    return GetStats_Category_Rated_Action.class;
  }

  @Override
  public void rollback(GetStats_Category_Rated_Action a, ListStatsResult r,
      ExecutionContext c) throws DispatchException
  {}
}