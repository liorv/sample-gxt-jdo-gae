package sample.client;

import java.util.List;

import sample.client.gxt.RatedCategoryGrid;
import sample.client.gxt.StatsDTO;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;

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

    final RatedCategoryGrid grid = new RatedCategoryGrid();

    RootPanel.get("sendDiv").add(loadButton);
    RootPanel.get("sendDiv").add(ratedButton);
    RootPanel.get("sendDiv").add(groupButton);
    RootPanel.get("sendDiv").add(categoryRatedButton);
    RootPanel.get("sendDiv").add(categoryGroupButton);

    RootPanel.get("gridDiv").add(grid);

    class AsyncCB implements AsyncCallback<List<StatsDTO>>
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
        ratingService.getCategoryRatedStats("Looks", new AsyncCB());
      }
    }

    class requestCategoryGroupHandler implements ClickHandler
    {
      @Override
      public void onClick(ClickEvent event) {
        ratingService.getCategoryGroupStats("Looks", new AsyncCB());
      }
    }

    class requestRatedHandler implements ClickHandler
    {
      @Override
      public void onClick(ClickEvent event) {
        ratingService.getRatedStats("Nadine", new AsyncCB());
      }
    }

    class requestGroupHandler implements ClickHandler
    {
      @Override
      public void onClick(ClickEvent event) {
        ratingService.getGroupStats("Girls", new AsyncCB());
      }
    }

    class requestLoadHandler implements ClickHandler
    {
      @Override
      public void onClick(ClickEvent event) {
        ratingService.loadData(new AsyncCB());
      }
    }

    // Add a handler to send the name to the server
    loadButton.addClickHandler(new requestLoadHandler());
    ratedButton.addClickHandler(new requestRatedHandler());
    groupButton.addClickHandler(new requestGroupHandler());
    categoryRatedButton.addClickHandler(new requestCategoryRatedHandler());
    categoryGroupButton.addClickHandler(new requestCategoryGroupHandler());
  }
}
