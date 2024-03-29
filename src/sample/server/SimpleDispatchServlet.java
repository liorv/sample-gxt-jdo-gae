package sample.server;

import sample.server.handlers.CreateGroupActionHandler;
import sample.server.handlers.GetStats_Category_Group_ActionHandler;
import sample.server.handlers.GetStats_Category_Rated_ActionHandler;
import sample.server.handlers.GetStats_Group_ActionHandler;
import sample.server.handlers.GetStats_Rated_ActionHandler;
import sample.server.handlers.LoadDataActionHandler;
import sample.server.handlers.RateActionHandler;
import net.customware.gwt.dispatch.client.standard.StandardDispatchService;
import net.customware.gwt.dispatch.server.DefaultActionHandlerRegistry;
import net.customware.gwt.dispatch.server.Dispatch;
import net.customware.gwt.dispatch.server.InstanceActionHandlerRegistry;
import net.customware.gwt.dispatch.server.SimpleDispatch;
import net.customware.gwt.dispatch.shared.Action;
import net.customware.gwt.dispatch.shared.DispatchException;
import net.customware.gwt.dispatch.shared.Result;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class SimpleDispatchServlet extends RemoteServiceServlet implements StandardDispatchService
{

  private Dispatch dispatch;

  public SimpleDispatchServlet() {

    InstanceActionHandlerRegistry registry = new DefaultActionHandlerRegistry();
    registry.addHandler(new LoadDataActionHandler());
    
    registry.addHandler(new GetStats_Rated_ActionHandler());
    registry.addHandler(new GetStats_Group_ActionHandler());
    registry.addHandler(new GetStats_Category_Rated_ActionHandler());
    registry.addHandler(new GetStats_Category_Group_ActionHandler());
    
    registry.addHandler(new RateActionHandler());
    registry.addHandler(new CreateGroupActionHandler());
    
    dispatch = new SimpleDispatch(registry);
  }

  public Result execute(Action<?> action) throws DispatchException {

    try {
      return dispatch.execute(action);
    }
    catch (RuntimeException e) {
      log("Exception while executing " + action.getClass().getName() + ": "
          + e.getMessage(), e);
      throw e;
    }
  }

}
