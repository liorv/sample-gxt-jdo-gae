package sample.client;

import java.util.List;

import sample.client.dto.StatsDTO;
import sample.client.gxt.CategoryEditor;
import sample.client.gxt.GroupEditor;
import sample.client.gxt.RatedCategoryGrid;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.Viewport;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class SampleGxtProject implements EntryPoint
{
  /**
   * Create a remote service proxy to talk to the server-side Greeting service.
   */
  private final RatingServiceAsync ratingService = GWT
      .create(RatingService.class);

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
    final Button loadButton = new Button("load");
    final Button ratedButton = new Button("rated");
    final Button groupButton = new Button("group");
    final Button categoryRatedButton = new Button("c-rated");
    final Button categoryGroupButton = new Button("c-group");
    final Button rateButton = new Button("RATE");

    final RatedCategoryGrid grid = new RatedCategoryGrid();
    final GroupEditor groupEditor = new GroupEditor();

    VerticalLayoutContainer vc = new VerticalLayoutContainer();
    //BoxLayoutData layoutData = new BoxLayoutData(new Margins(5, 0, 0, 5));
    
    vc.add(grid);
    
    vc.add(new CategoryEditor());
    
    vc.add(groupEditor);
   
    HorizontalLayoutContainer panelTestButtons = new HorizontalLayoutContainer();
    panelTestButtons.add(loadButton);
    panelTestButtons.add(ratedButton);
    panelTestButtons.add(groupButton);
    panelTestButtons.add(categoryRatedButton);
    panelTestButtons.add(categoryGroupButton);
    panelTestButtons.add(rateButton);
    
    vc.add(panelTestButtons);
    
    Viewport vp = new Viewport();
    vp.add(vc);
    RootPanel.get().add(vp);

    class GetStatsAsyncCB implements AsyncCallback<List<StatsDTO>>
    {
      @Override
      public void onFailure(Throwable caught) {}

      @Override
      public void onSuccess(List<StatsDTO> result) {        
        grid.setStats(result);
      }
    }

    class requestCategoryRatedHandler implements ClickHandler
    {
      @Override
      public void onClick(ClickEvent event) {
        ratingService.getCategoryRatedStats("Looks", new GetStatsAsyncCB());
      }
    }

    class requestCategoryGroupHandler implements ClickHandler
    {
      @Override
      public void onClick(ClickEvent event) {
        ratingService.getCategoryGroupStats("Looks", new GetStatsAsyncCB());
      }
    }

    class requestRatedHandler implements ClickHandler
    {
      @Override
      public void onClick(ClickEvent event) {
        ratingService.getRatedStats("Nadine", new GetStatsAsyncCB());
      }
    }

    class requestGroupHandler implements ClickHandler
    {
      @Override
      public void onClick(ClickEvent event) {
        ratingService.getGroupStats("Girls", new GetStatsAsyncCB());
      }
    }

    class requestLoadHandler implements ClickHandler
    {
      @Override
      public void onClick(ClickEvent event) {
        ratingService.loadData(new GetStatsAsyncCB());
        groupEditor.reload();
      }
    }
    
    class rateHandler implements ClickHandler
    {
      @Override
      public void onClick(ClickEvent event) {
        ratingService.rate("Looks", "Nadine", 10, new AsyncCallback<Void>() {
          @Override
          public void onFailure(Throwable caught) {
          }

          @Override
          public void onSuccess(Void result) {
          }
        });
      }
    }

    // Add a handler to send the name to the server
    loadButton.addClickHandler(new requestLoadHandler());
    ratedButton.addClickHandler(new requestRatedHandler());
    groupButton.addClickHandler(new requestGroupHandler());
    categoryRatedButton.addClickHandler(new requestCategoryRatedHandler());
    categoryGroupButton.addClickHandler(new requestCategoryGroupHandler());
    rateButton.addClickHandler(new rateHandler());
  }
}
