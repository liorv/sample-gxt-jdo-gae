package jdo;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

public class JDOModule extends AbstractModule
{
  @Override
  protected void configure() {
    bindConstant().annotatedWith(Names.named("isGAE")).to(true);
    bindConstant().annotatedWith(Names.named("isSafePersistMode")).to(true);
  }
}
