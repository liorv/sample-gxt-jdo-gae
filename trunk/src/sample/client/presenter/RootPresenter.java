package sample.client.presenter;

import java.util.List;
import java.util.Vector;

import net.customware.gwt.dispatch.client.DefaultExceptionHandler;
import net.customware.gwt.dispatch.client.DispatchAsync;
import net.customware.gwt.dispatch.client.standard.StandardDispatchAsync;

import sample.shared.action.GetStats_Category_Group_Action;
import sample.shared.action.GetStats_Category_Rated_Action;
import sample.shared.action.GetStats_Group_Action;
import sample.shared.action.GetStats_Rated_Action;
import sample.shared.action.LoadDataAction;
import sample.shared.action.RateAction;
import sample.shared.result.CategoryDTO;
import sample.shared.result.ListStatsResult;
import sample.shared.result.LoadDataResult;
import sample.shared.result.NullResult;
import sample.shared.result.RatedDTO;
import sample.shared.result.StatsDTO;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class RootPresenter implements Presenter
{
  public interface Display
  {
    public HasClickHandlers getLoadButton();

    public HasClickHandlers getRatedButton();

    public HasClickHandlers getGroupButton();

    public HasClickHandlers getCategoryRatedButton();

    public HasClickHandlers getCategoryGroupButton();

    public HasClickHandlers getRateButton();

    public void setStats(List<StatsDTO> stats);

    public void setAllRated(Vector<RatedDTO> allRated);

    public void setAllCategories(Vector<CategoryDTO> allCategories);

    public Widget asWidget();
  }

  final HandlerManager eventBus;

  private final Display display;

  public RootPresenter(HandlerManager eventBus, Display display) {
    super();
    this.eventBus = eventBus;
    this.display = display;
  }

  private final DispatchAsync dispatch = new StandardDispatchAsync(
      new DefaultExceptionHandler());

  public void bind() {

    display.getLoadButton().addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        dispatch.execute(new LoadDataAction(),
            new AsyncCallback<LoadDataResult>() {
              @Override
              public void onFailure(Throwable caught) {}

              @Override
              public void onSuccess(LoadDataResult result) {
                display.setStats(result.stats);
                display.setAllRated(result.allRated);
                display.setAllCategories(result.allCategories);
              }
            });
      }
    });

    display.getRatedButton().addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        dispatch.execute(new GetStats_Rated_Action("Nadine"), new GetStatActionCB());
      }
    });

    display.getGroupButton().addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        dispatch.execute(new GetStats_Group_Action("Girls"), new GetStatActionCB());
      }
    });

    display.getCategoryGroupButton().addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        dispatch.execute(new GetStats_Category_Group_Action("Looks"),
            new GetStatActionCB());
      }
    });

    display.getCategoryRatedButton().addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        dispatch.execute(new GetStats_Category_Rated_Action("Looks"),
            new GetStatActionCB());
      }
    });

    display.getRateButton().addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        dispatch.execute(new RateAction("Looks", "Nadine", 10), new AsyncCallback<NullResult>() {
          @Override
          public void onFailure(Throwable caught) {}

          @Override
          public void onSuccess(NullResult result) {}
        });
      }
    });

  }

  @Override
  public void go(HasWidgets container) {
    bind();
    container.clear();
    container.add(display.asWidget());
  }

  class GetStatActionCB implements AsyncCallback<ListStatsResult>
  {
    @Override
    public void onFailure(Throwable caught) {}

    @Override
    public void onSuccess(ListStatsResult result) {
      display.setStats(result.stats);
    }
  }
}
