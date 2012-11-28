package jdo.key;

import org.springframework.stereotype.Component;

@Component
public interface IKeyFactory
{
  public Object createKey(String kind, String id);
  
  public Object createKey(String kind, long id);
}
