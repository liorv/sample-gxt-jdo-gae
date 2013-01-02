package sample.client;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

@GinModules(WidgetClientModule.class)
public interface WidgetInjector extends Ginjector
{
  AppController getAppViewer();
  
  RatingServiceAsync getRatingService();
}
