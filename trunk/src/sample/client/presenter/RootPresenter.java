package sample.client.presenter;

import java.util.List;

import net.customware.gwt.dispatch.client.DefaultExceptionHandler;
import net.customware.gwt.dispatch.client.DispatchAsync;
import net.customware.gwt.dispatch.client.standard.StandardDispatchAsync;

import sample.shared.action.LoadDataAction;
import sample.shared.result.LoadDataResult;
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

    public Widget asWidget();
  }

  private final HandlerManager eventBus;

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
        dispatch.execute(new LoadDataAction(), new AsyncCallback<LoadDataResult>() {

          @Override
          public void onFailure(Throwable caught) {            
          }

          @Override
          public void onSuccess(LoadDataResult result) {
            display.setStats(result);       
          }
        });
      }
    });

    display.getRatedButton().addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
      }
    });
    
    display.getGroupButton().addClickHandler(new ClickHandler() {      
      @Override
      public void onClick(ClickEvent event) {
      }
    });
    
    display.getCategoryGroupButton().addClickHandler(new ClickHandler() {      
      @Override
      public void onClick(ClickEvent event) {      
      }
    });
    
    display.getCategoryRatedButton().addClickHandler(new ClickHandler() {      
      @Override
      public void onClick(ClickEvent event) { 
      }
    });
    
    display.getRateButton().addClickHandler(new ClickHandler() {      
      @Override
      public void onClick(ClickEvent event) {      
      }
    });
    
  }

  @Override
  public void go(HasWidgets container) {
    bind();
    container.clear();
    container.add(display.asWidget());
  }
}
