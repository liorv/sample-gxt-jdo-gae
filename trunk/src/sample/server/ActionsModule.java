package sample.server;

import sample.server.handlers.LoadDataActionHandler;
import sample.shared.action.LoadDataAction;
import net.customware.gwt.dispatch.server.guice.ActionHandlerModule;

public class ActionsModule extends ActionHandlerModule
{

  @Override
  protected void configureHandlers() {
    bindHandler(LoadDataAction.class, LoadDataActionHandler.class);
  }

}
