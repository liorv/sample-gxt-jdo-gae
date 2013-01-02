package sample.client.gxt;

import java.util.List;

import sample.client.presenter.RootPresenter;
import sample.client.RatingService;
import sample.client.RatingServiceAsync;
import sample.client.gxt.StatInterfaces.StatRelationCategoryRatedProps;
import sample.shared.result.StatsDTO;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.Viewport;

public class RootView extends Composite implements RootPresenter.Display
{
  RatingServiceAsync ratingSrv = GWT.create(RatingService.class);

  final GroupEditor groupEditor = new GroupEditor();

  final LVGrid<StatsDTO, StatRelationCategoryRatedProps> grid =
      new RatedCategoryGrid();

  final Button loadButton = new Button("load");
  final Button ratedButton = new Button("rated");
  final Button groupButton = new Button("group");
  final Button categoryRatedButton = new Button("c-rated");
  final Button categoryGroupButton = new Button("c-group");
  final Button rateButton = new Button("RATE");

  public RootView() {
    VerticalLayoutContainer vc = new VerticalLayoutContainer();
    //BoxLayoutData layoutData = new BoxLayoutData(new Margins(5, 0, 0, 5));

    vc.add(grid);

    vc.add(new CategoryEditor());

    vc.add(groupEditor);

    HorizontalLayoutContainer panelTestButtons =
        new HorizontalLayoutContainer();
    panelTestButtons.add(loadButton);
    panelTestButtons.add(ratedButton);
    panelTestButtons.add(groupButton);
    panelTestButtons.add(categoryRatedButton);
    panelTestButtons.add(categoryGroupButton);
    panelTestButtons.add(rateButton);

    vc.add(panelTestButtons);
    Viewport vp = new Viewport();
    vp.add(vc);

    initWidget(vp);
  }

  @Override
  public HasClickHandlers getLoadButton() {
    return loadButton;
  }

  @Override
  public HasClickHandlers getRatedButton() {
    return ratedButton;
  }

  @Override
  public HasClickHandlers getGroupButton() {
    return groupButton;
  }

  @Override
  public HasClickHandlers getCategoryRatedButton() {
    return categoryRatedButton;
  }

  @Override
  public HasClickHandlers getCategoryGroupButton() {
    return categoryGroupButton;
  }

  @Override
  public HasClickHandlers getRateButton() {
    return rateButton;
  }

  @Override
  public void setStats(List<StatsDTO> stats) {
    grid.setData(stats);
  }
}
