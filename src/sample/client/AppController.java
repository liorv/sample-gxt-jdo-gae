package sample.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;

import sample.client.events.LoadEvent;
import sample.client.events.LoadEventHandler;
import sample.client.gxt.RootView;
import sample.client.presenter.Presenter;
import sample.client.presenter.RootPresenter;
import sample.shared.action.LoadDataAction;
import sample.shared.result.StatsDTO;

public class AppController implements Presenter
{
  private enum ActionType {
    RESET_VIEW;
  }

  private final WidgetInjector injector = GWT.create(WidgetInjector.class);

  private final HandlerManager eventBus;

  private RatingServiceAsync ratingSrv;

  private HasWidgets container;

  @Override
  public void go(HasWidgets container) {
    this.container = container;

    managePresenter(ActionType.RESET_VIEW);
  }

  public AppController() {
    this.eventBus = new HandlerManager(null);
    ratingSrv = injector.getRatingService();
    bind();
  }

  private void bind() {
    //Register all the handlers
    eventBus.addHandler(LoadEvent.TYPE, new LoadEventHandler() {
      @Override
      public void onLoad(LoadEvent loadEvent) {
        ratingSrv.<StatsDTO> execute(new LoadDataAction(), new AsyncCallback<StatsDTO>() {
          @Override
          public void onFailure(Throwable caught) {}

          @Override
          public void onSuccess(StatsDTO result) {}
        });
      }
    });
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