package io.reflectoring.descriptivelogger;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Spliterator;
import java.util.function.Consumer;

/** @author Tom Hombergs */
public class MDCValues implements Iterable<Map.Entry<String, String>> {

  private Map<String, String> values = new HashMap<>();

  public MDCValues() {}

  public MDCValues(String key, String value) {
    withValue(key, value);
  }

  public MDCValues withValue(String key, String value) {
    values.put(key, value);
    return this;
  }

  public MDCValues fromMap(Map<String, String> map) {
    if (map != null) {
      this.values = map;
    } else {
      this.values = new HashMap<>();
    }
    return this;
  }

  public Object getValue(String key) {
    return values.get(key);
  }

  @Override
  public Iterator<Map.Entry<String, String>> iterator() {
    return values.entrySet().iterator();
  }

  @Override
  public void forEach(Consumer<? super Map.Entry<String, String>> action) {
    values.entrySet().forEach(action);
  }

  @Override
  public Spliterator<Map.Entry<String, String>> spliterator() {
    return values.entrySet().spliterator();
  }
}
