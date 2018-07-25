# IDs for SLF4J

```java
/**
 * @LogMessages allows the definition of methods annotated with @LogMessage declaring ids between 42 and 44 as valid,
 * throwing an exception if an id is not in this range.
 */
@LogMessages(min = 42, max = 44)
public interface MyLogMessages {

  /**
   * Logs a message. The ID is added to the MDC.
   */
  @LogMessage(value="Hello World", level=Level.INFO, id=42)
  void helloWorld();
  
  /**
   * Logs a message, replacing placeholders with method parameter values.
   */
  @LogMessage(value="Hello {}", level=Level.INFO, id=43)
  void helloWithName(String name);
  
  /**
   * Logs a message, replacing placeholders with method parameter values and adding 
   * the specified MDCParams to the MDC.
   */
  @LogMessage(value="Hello {}", level=Level.INFO, id=44)
  void helloWithNameAndMDC(String name, MDCParams mdc);

}
```

* initialization of a @LogMessages class programmatically
* initialization of a @LogMessages class via Spring Boot Starter
