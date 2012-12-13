package sample.client.gxt;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.ListView;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;

public class GroupEditor implements IsWidget
{
  public interface GroupProperties extends PropertyAccess<GroupDTO> {
    @Path("name")
    ModelKeyProvider<GroupDTO> key();
    ValueProvider<GroupDTO, String> name();
    ValueProvider<GroupDTO, Set<String>> members();
    ValueProvider<GroupDTO, Set<String>> categories();
  }

  private List<GroupDTO> groups = new LinkedList<GroupDTO>();
  
  public GroupEditor(List<GroupDTO> groupsList) {
    this.groups = groupsList;
    
    // HACK... remove later...
    groups = new LinkedList<GroupDTO>();    
    HashSet<String> members = new HashSet<String>();
    members.add("Michael Jordan");
    members.add("Larry Bird");
    members.add("Magic Johnson");
    groups.add(new GroupDTO("raptors", members, new HashSet<String>()));
    groups.add(new GroupDTO("bulls", new HashSet<String>(), new HashSet<String>()));
    groups.add(new GroupDTO("nets", new HashSet<String>(), new HashSet<String>()));
  }

  @Override
  public Widget asWidget() {
    FramedPanel panel = new FramedPanel();
    panel.setHeadingText("Edit Group");
    VerticalLayoutContainer p = new VerticalLayoutContainer();

    GroupProperties props = GWT.create(GroupProperties.class);
    ListStore<GroupDTO> s = new ListStore<GroupDTO>(props.key());
    s.addAll(groups);
    ListView<GroupDTO, String> l = new ListView<GroupDTO, String>(s, props.name());
    p.add(l);
    
    GroupFilterField sl = new GroupFilterField(l);
    p.add(sl);

    panel.add(p);
    return panel;
  }
}
