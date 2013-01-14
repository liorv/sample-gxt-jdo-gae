package sample.server;

import jdo.JDOModule;
import jdo.JDOSession;
import net.customware.gwt.dispatch.server.guice.GuiceStandardDispatchServlet;
import net.customware.gwt.dispatch.server.guice.ServerDispatchModule;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;

public class BootstrapListener extends GuiceServletContextListener
{
  public static class ServeUrlModule extends ServletModule
  {
    @Override
    protected void configureServlets() {
      requestStaticInjection(JDOSession.class);
      
      serve("/samplegxtproject/dispatch").with(
          GuiceStandardDispatchServlet.class);
    }
  }

  @Override
  protected Injector getInjector() {
    return Guice.createInjector(new ServerDispatchModule(), new JDOModule(),
        new ActionsModule(), new ServeUrlModule());
  }

}
