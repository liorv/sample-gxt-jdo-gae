package jdo.key;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class KeyFactoryProvider
{

  private static IKeyFactory keyFactory;

  public static IKeyFactory getKeyfactory() {
    return keyFactory;
  }

  @Autowired
  @Qualifier("keyfactory")
  public void setKeyFactory(IKeyFactory keyFactory) {
    KeyFactoryProvider.keyFactory = keyFactory;
  }
}
