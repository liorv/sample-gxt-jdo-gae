package sample.client.gxt;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import sample.client.dto.GroupDTO;
import sample.client.dto.RatedDTO;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.Editor.Path;
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
  public interface RatedProps extends PropertyAccess<RatedDTO>
  {
    @Path("name")
    ModelKeyProvider<RatedDTO> key();

    ValueProvider<RatedDTO, String> name();
  }

  private RatedProps props = GWT.create(RatedProps.class);

  private List<RatedDTO> listAllRated = new LinkedList<RatedDTO>();

  private List<RatedDTO> members = new LinkedList<RatedDTO>();

  private GroupDTO group;

  public GroupEditor(GroupDTO group, List<RatedDTO> listAllRated,
      List<RatedDTO> members)
  {
    this.group = group;
    this.listAllRated = listAllRated;
    this.members = members;

    // HACK... remove later...
    //TODO
    this.group =
        new GroupDTO("Lakers", new HashSet<String>(), new HashSet<String>());
    
    listAllRated = new LinkedList<RatedDTO>();
    listAllRated.add(new RatedDTO("Michael Jordan"));
    listAllRated.add(new RatedDTO("Larry Bird"));
    listAllRated.add(new RatedDTO("Magic Johnson"));
    this.listAllRated = listAllRated;
  }

  @Override
  public Widget asWidget() {
    Container left = createLeft();
    Container right = createRight();

    FramedPanel panel = new FramedPanel();
    panel.setHeadingText("Edit Group");
    HBoxLayoutContainer hbox = new HBoxLayoutContainer(HBoxLayoutAlign.STRETCH);

    BoxLayoutData flex = new BoxLayoutData(new Margins(5, 5, 0, 0));
    flex.setFlex(1);
    hbox.add(left, flex);
    hbox.add(right, flex);
    panel.setWidget(hbox);
    panel.setHeight(350);

    return panel;
  }

  private Container createRight() {
    // right list
    VBoxLayoutContainer vc_right =
        new VBoxLayoutContainer(VBoxLayoutAlign.STRETCH);
    ListStore<RatedDTO> s2 = new ListStore<RatedDTO>(props.key());
    ListView<RatedDTO, String> listMembers = createList(s2);

    FieldLabel fl_members = new FieldLabel(listMembers, "Members");
    fl_members.setLabelAlign(LabelAlign.TOP);

    vc_right.add(fl_members);
    SimpleContainer right = makePanel();

    right.setWidget(vc_right);
    return right;
  }

  private ListView<RatedDTO, String> createList(ListStore<RatedDTO> s) {
    ListView<RatedDTO, String> list =
        new ListView<RatedDTO, String>(s, props.name());
    list.setSize("-1", "20em");

    new ListViewDragSource<RatedDTO>(list);
    new ListViewDropTarget<RatedDTO>(list);

    return list;
  }

  private Container createLeft() {
    VBoxLayoutContainer vc_left =
        new VBoxLayoutContainer(VBoxLayoutAlign.STRETCH);

    // left list
    ListStore<RatedDTO> s1 = new ListStore<RatedDTO>(props.key());
    s1.addAll(listAllRated);
    ListView<RatedDTO, String> listPopulation = createList(s1);

    FieldLabel fl_pop = new FieldLabel(listPopulation, "Population");
    fl_pop.setLabelAlign(LabelAlign.TOP);
    RatedFilterField filterPop = new RatedFilterField(listPopulation);

    vc_left.add(fl_pop);
    vc_left.add(filterPop);
    SimpleContainer left = makePanel();

    left.setWidget(vc_left);
    return left;
  }

  private SimpleContainer makePanel() {
    return new SimpleContainer();
  }
}
