package sample.client.gxt;

import java.util.LinkedList;
import java.util.List;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextField;

public class CategoryEditor implements IsWidget
{
  @Override
  public Widget asWidget() {
    FramedPanel panel = new FramedPanel();
    panel.setHeadingText("Edit Rating Category");
    VerticalLayoutContainer p = new VerticalLayoutContainer();
    panel.add(p);

    TextField categoryName = new TextField();
    categoryName.setAllowBlank(false);
    categoryName.setEmptyText("Enter category name...");
    p.add(new FieldLabel(categoryName, "Category"), new VerticalLayoutData());
    
    
    HorizontalLayoutContainer categoryType = new HorizontalLayoutContainer();
    List<String> categoryTypes = new LinkedList<String>();
    categoryTypes.add("Percent");
    categoryTypes.add("Number");
    categoryTypes.add("Boolean");
    MyRadioGroup<String> iwidget = new MyRadioGroup<String>("categoryType", categoryType, categoryTypes);
    //MyComboBox<String> iwidget = new MyComboBox<String>(categoryTypes);

    p.add(new FieldLabel(iwidget.asWidget(), "Type"), new VerticalLayoutData());


    return panel;
  }
}
