package sample.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class SampleGxtProject implements EntryPoint
{
  WidgetInjector injector = GWT.create(WidgetInjector.class);
  
  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
    AppController appViewer = injector.getAppViewer();
    appViewer.go(RootPanel.get());
  }
}
