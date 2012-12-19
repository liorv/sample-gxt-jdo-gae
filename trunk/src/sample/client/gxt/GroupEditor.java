package sample.client.gxt;

import java.util.HashSet;
import java.util.List;

import sample.client.RatingService;
import sample.client.RatingServiceAsync;
import sample.client.dto.NamedDTO;
import sample.client.dto.CategoryDTO;
import sample.client.dto.GroupDTO;
import sample.client.dto.RatedDTO;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.sencha.gxt.dnd.core.client.ListViewDragSource;
import com.sencha.gxt.dnd.core.client.ListViewDropTarget;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.ListView;
import com.sencha.gxt.widget.core.client.PlainTabPanel;
import com.sencha.gxt.widget.core.client.TabItemConfig;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutData;
import com.sencha.gxt.widget.core.client.container.Container;
import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer.HBoxLayoutAlign;
import com.sencha.gxt.widget.core.client.container.SimpleContainer;
import com.sencha.gxt.widget.core.client.container.VBoxLayoutContainer.VBoxLayoutAlign;
import com.sencha.gxt.widget.core.client.container.VBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.FormPanel.LabelAlign;

public class GroupEditor implements IsWidget
{
  public GroupEditor() {
    namedProps = GWT.create(NamedProps.class);

    allStoreRated = new ListStore<RatedDTO>(namedProps.key());

    groupStoreRated = new ListStore<RatedDTO>(namedProps.key());

    allStoreCat = new ListStore<CategoryDTO>(namedProps.key());

    groupStoreCat = new ListStore<CategoryDTO>(namedProps.key());

    group = new GroupDTO("", new HashSet<RatedDTO>(), new HashSet<CategoryDTO>());
  }

  public interface NamedProps<T extends NamedDTO> extends PropertyAccess<T>
  {
    @Path("name")
    ModelKeyProvider<NamedDTO> key();

    ValueProvider<NamedDTO, String> name();
  }

  private final RatingServiceAsync ratingService = GWT
      .create(RatingService.class);

  private NamedProps<?> namedProps;

  private ListStore<RatedDTO> allStoreRated;

  private ListStore<RatedDTO> groupStoreRated;

  private ListStore<CategoryDTO> allStoreCat;

  private ListStore<CategoryDTO> groupStoreCat;

  private GroupDTO group;

  @Override
  public Widget asWidget() {
    FramedPanel panel = new FramedPanel();
    panel.setHeadingText("Edit group [" + group.getName() + "]");

    Container membershipPanel = createEditMembershipPanel();
    Container categoryPanel = createEditCategoriesPanel();

    PlainTabPanel tabPanel = new PlainTabPanel();
    tabPanel.setPixelSize(-1, 400);
    TabItemConfig membersTab = new TabItemConfig("Membership");
    TabItemConfig categoryTab = new TabItemConfig("Categories");

    tabPanel.add(membershipPanel, membersTab);
    tabPanel.add(categoryPanel, categoryTab);
    tabPanel.setHeight(350);

    panel.add(tabPanel);
    return panel;
  }

  private Container createEditMembershipPanel() {
    Container left = createLeftPanel("Population", allStoreRated);
    Container right = createRightPanel("Members", groupStoreRated);

    SimpleContainer panel = new SimpleContainer();
    HBoxLayoutContainer hbox = new HBoxLayoutContainer(HBoxLayoutAlign.STRETCH);

    BoxLayoutData flex = new BoxLayoutData(new Margins(5, 5, 0, 0));
    flex.setFlex(1);
    hbox.add(left, flex);
    hbox.add(right, flex);
    panel.setWidget(hbox);
    return panel;
  }

  private Container createEditCategoriesPanel() {
    Container left = createLeftPanel("All Categories", allStoreCat);
    Container right = createRightPanel("Attached", groupStoreCat);

    SimpleContainer panel = new SimpleContainer();
    HBoxLayoutContainer hbox = new HBoxLayoutContainer(HBoxLayoutAlign.STRETCH);

    BoxLayoutData flex = new BoxLayoutData(new Margins(5, 5, 0, 0));
    flex.setFlex(1);
    hbox.add(left, flex);
    hbox.add(right, flex);
    panel.setWidget(hbox);
    return panel;
  }

  private <T extends NamedDTO> Container createRightPanel(String label,
      ListStore<T> listStore)
  {
    // right list
    VBoxLayoutContainer vc_right =
        new VBoxLayoutContainer(VBoxLayoutAlign.STRETCH);
    ListView<T, String> listViewAll = createList(listStore);

    FieldLabel fieldLabelAll = new FieldLabel(listViewAll, label);
    fieldLabelAll.setLabelAlign(LabelAlign.TOP);

    vc_right.add(fieldLabelAll);
    SimpleContainer right = makePanel();

    right.setWidget(vc_right);
    return right;
  }

  private <T extends NamedDTO> Container createLeftPanel(String label,
      ListStore<T> allStore)
  {
    VBoxLayoutContainer vc_left =
        new VBoxLayoutContainer(VBoxLayoutAlign.STRETCH);

    // left list
    ListView<T, String> lvAll = createList(allStore);

    FieldLabel fl_pop = new FieldLabel(lvAll, label);
    fl_pop.setLabelAlign(LabelAlign.TOP);
    NamedFilterField<T> filterPop = new NamedFilterField<T>(lvAll);

    vc_left.add(fl_pop);
    vc_left.add(filterPop);
    SimpleContainer left = makePanel();

    left.setWidget(vc_left);
    return left;
  }

  private <T extends NamedDTO> ListView<T, String> createList(ListStore<T> s) {
    ListView<T, String> list = new ListView<T, String>(s, namedProps.name());
    list.setSize("-1", "20em");

    new ListViewDragSource<T>(list);
    new ListViewDropTarget<T>(list);

    return list;
  }

  private SimpleContainer makePanel() {
    return new SimpleContainer();
  }

  public void reload() {    
    ratingService.getAllCategories(new AsyncCallback<List<CategoryDTO>>() {
      @Override
      public void onSuccess(List<CategoryDTO> result) {
        allStoreCat.clear();
        allStoreCat.addAll(result);
      }

      @Override
      public void onFailure(Throwable caught) {}
    });

    ratingService.getAllRated(new AsyncCallback<List<RatedDTO>>() {
      @Override
      public void onSuccess(List<RatedDTO> result) {
        allStoreRated.clear();
        allStoreRated.addAll(result);
      }

      @Override
      public void onFailure(Throwable caught) {}
    });

    if (group != null && group.getName().length() > 0) {
      ratingService.getGroup(group.getName(), new AsyncCallback<GroupDTO>() {
        @Override
        public void onSuccess(GroupDTO result) {
          groupStoreCat.clear();
          groupStoreRated.clear();          
          groupStoreCat.addAll(result.getCategories());
          groupStoreRated.addAll(result.getMembers());
        }

        @Override
        public void onFailure(Throwable caught) {}
      });
    }
  }
}
