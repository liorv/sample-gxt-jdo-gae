package jdo.key;


import org.springframework.stereotype.Component;

@Component
public class BasicKeyFactory implements IKeyFactory
{

  @Override
  public <T> String createKey(Class<T> clz, String id) {
    return clz.getSimpleName()+"("+id+")";
  }
}
