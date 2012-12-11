package sample.client.gxt;

import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public class Example_EmailInterfaces
{
  public static interface Email
  {
    String getName();

    String getEmail();

    String getPhone();

    String getState();

    String getZip();
  }

  public static interface EmailProperties extends PropertyAccess<Email>
  {
    @Path("name")
    ModelKeyProvider<Email> key();

    ValueProvider<Email, String> name();

    ValueProvider<Email, String> email();

    ValueProvider<Email, String> phone();

    ValueProvider<Email, String> state();

    ValueProvider<Email, String> zip();
  }
}
