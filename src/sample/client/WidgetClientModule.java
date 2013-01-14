package sample.client;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.inject.rebind.binding.BindProviderBinding;
import com.google.inject.Singleton;

public class WidgetClientModule extends AbstractGinModule
{
  @Override
  protected void configure() {
    bind(AppController.class).in(Singleton.class);
  }
}
