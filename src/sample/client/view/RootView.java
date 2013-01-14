package sample.client.view;

import java.util.List;
import java.util.Vector;

import sample.client.presenter.RootPresenter;
import sample.client.gxt.CategoryEditor;
import sample.client.gxt.GroupEditor;
import sample.client.gxt.LVGrid;
import sample.client.gxt.RatedCategoryGrid;
import sample.client.gxt.StatInterfaces.StatRelationCategoryRatedProps;
import sample.shared.result.CategoryDTO;
import sample.shared.result.RatedDTO;
import sample.shared.result.StatsDTO;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.Viewport;

public class RootView extends Composite implements RootPresenter.Display
{
  final LVGrid<StatsDTO, StatRelationCategoryRatedProps> grid =
      new RatedCategoryGrid();
  
  final CategoryEditor categoryEditor = new CategoryEditor();
  
  final GroupEditor groupEditor = new GroupEditor();

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

    vc.add(categoryEditor);

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

  @Override
  public void setAllRated(Vector<RatedDTO> allRated) {
    groupEditor.setAllRated(allRated);
  }

  @Override
  public void setAllCategories(Vector<CategoryDTO> allCategories) {
    groupEditor.setAllCategories(allCategories);
  }
}
