package sample.server;

import sample.server.handlers.CreateGroupActionHandler;
import sample.server.handlers.GetStats_Category_Group_ActionHandler;
import sample.server.handlers.GetStats_Category_Rated_ActionHandler;
import sample.server.handlers.GetStats_Group_ActionHandler;
import sample.server.handlers.GetStats_Rated_ActionHandler;
import sample.server.handlers.LoadDataActionHandler;
import sample.server.handlers.RateActionHandler;
import sample.shared.action.CreateGroupAction;
import sample.shared.action.GetStats_Category_Group_Action;
import sample.shared.action.GetStats_Category_Rated_Action;
import sample.shared.action.GetStats_Group_Action;
import sample.shared.action.GetStats_Rated_Action;
import sample.shared.action.LoadDataAction;
import sample.shared.action.RateAction;
import net.customware.gwt.dispatch.server.guice.ActionHandlerModule;

public class ActionsModule extends ActionHandlerModule
{

  @Override
  protected void configureHandlers() {
    bindHandler(LoadDataAction.class, LoadDataActionHandler.class);
    bindHandler(GetStats_Rated_Action.class, GetStats_Rated_ActionHandler.class);
    bindHandler(GetStats_Group_Action.class, GetStats_Group_ActionHandler.class);
    bindHandler(GetStats_Category_Rated_Action.class,
        GetStats_Category_Rated_ActionHandler.class);
    bindHandler(GetStats_Category_Group_Action.class,
        GetStats_Category_Group_ActionHandler.class);
    bindHandler(RateAction.class, RateActionHandler.class);
    bindHandler(CreateGroupAction.class, CreateGroupActionHandler.class);
  }

}
