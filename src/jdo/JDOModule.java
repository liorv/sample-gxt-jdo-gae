package jdo;

import com.google.inject.name.Names;
import com.google.inject.servlet.ServletModule;

public class JDOModule extends ServletModule
{
  @Override
  protected void configureServlets() {
    bindConstant().annotatedWith(Names.named("isGAE")).to(true);
    bindConstant().annotatedWith(Names.named("isSafePersistMode")).to(true);
  }
}
