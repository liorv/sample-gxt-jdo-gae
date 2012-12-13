package sample.client.gxt;

import java.util.List;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.ListView;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;

public class SearchList<T> implements IsWidget
{
  @Override
  public Widget asWidget() {
    FramedPanel panel = new FramedPanel();
    panel.setHeadingText("List View Test -- remove FramedPanel later...");
    VerticalLayoutContainer p = new VerticalLayoutContainer();    
    //
   
    p.add(listView);
    
    //
    panel.add(p);
    return panel;
  }

  /**
   * Construct DataProperties for POJO Data externally, T=Data,
   * PropertyAccess<T>=DataProperties
   * 
   * 
   * <pre>
   * 
   * STEP 1 -- create property interface for data class T you want to list
   * =================================================================
   * 
   * public interface DataProperties extends PropertyAccess {
   *     @Path("name")
   *     ModelKeyProvider key();              <---------- key provider
   *     ValueProvider<Data, String> name();  <---------- value provider
   *     ValueProvider<Data, String> value();
   *   }
   * 
   *   public class Data {
   *     private String name;
   *     private String value;
   * 
   *     public Data(String name, String value) {
   *       super();
   *       this.name = name;
   *       this.value = value;
   *     }
   *     public String getName() {
   *       return name;
   *     }
   *     public String getValue() {
   *       return value;
   *     }
   *     public void setName(String name) {
   *       this.name = name;
   *     }
   *     public void setValue(String value) {
   *       this.value = value;
   *     }
   *   }
   * 
   *   STEP 2 -- use GWT.create to make a properties instance
   *   ======================================================
   *   
   *     // Generate the key provider and value provider for the Data class
   *     DataProperties dp = GWT.create(DataProperties.class);
   * 
   *     // Create the store that the contains the data to display in the grid
   *     ListStore<Data> s = new ListStore<Test.Data>(dp.key());
   *     s.add(new Data("name1", "value1"));
   *     s.add(new Data("name2", "value2"));
   *     s.add(new Data("name3", "value3"));
   *     s.add(new Data("name4", "value4"));
   * 
   *     // Create the tree using the store and value provider for the name field
   *     ListView<Data, String> t = new ListView<Data, String>(s, dp.name());
   * 
   *     // Add the tree to a container
   *     RootPanel.get().add(t);
   * </pre>
   */

  public SearchList(List<T> items, ModelKeyProvider<T> keyProvider,
      ValueProvider<T, String> valProvider)
  {
    listStore = new ListStore<T>(keyProvider);
    listStore.addAll(items);
    listView = new ListView<T, String>(listStore, valProvider);
  }

  private ListStore<T> listStore;

  ListView<T, String> listView;
}
