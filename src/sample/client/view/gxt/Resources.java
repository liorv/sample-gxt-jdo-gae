package sample.client.view.gxt;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public interface Resources extends ClientBundle
{
  public Resources INSTANCE = GWT.create(Resources.class);
  
  @Source("button-cancel_search.png")
  ImageResource cancelSearchButton();
}
