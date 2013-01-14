package sample.client;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;

import sample.client.presenter.Presenter;
import sample.client.presenter.RootPresenter;
import sample.client.view.RootView;

public class AppController implements Presenter
{
  private enum ActionType {
    RESET_VIEW;
  }

  private final HandlerManager eventBus;

  private HasWidgets container;

  @Override
  public void go(HasWidgets container) {
    this.container = container;

    managePresenter(ActionType.RESET_VIEW);
  }

  public AppController() {
    eventBus = new HandlerManager(null);
  }

  private void managePresenter(ActionType actionType) {
    if (actionType != null) {
      Presenter presenter = null;
      switch (actionType) {
        case RESET_VIEW:
          presenter = new RootPresenter(eventBus, new RootView());
          break;
      }
      if (presenter != null) presenter.go(container);
    }
  }

}