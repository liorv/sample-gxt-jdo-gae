package jdo.key;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class KFProvider
{

  private static IKeyFactory keyFactory;

  public static IKeyFactory getKeyfactory() {
    return keyFactory;
  }
  
  public static <T> String key(Class<T> clz, String id) {
    return getKeyfactory().createKey(clz, id);
  }

  @Autowired
  @Qualifier("keyfactory")
  public void setKeyFactory(IKeyFactory keyFactory) {
    KFProvider.keyFactory = keyFactory;
  }
}
