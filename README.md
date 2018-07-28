# A Descriptive Logger for SLF4J

With this library, we can define our log messages in a descriptive way in order
to make them better identifiable in our code base and log files. This works on top
of SLF4J, so all log messages will be logged by a standard SLF4J Logger.

The idea is inspired by the jboss-logging framework, where a similar concept is
available.

## Usage

### Define a Descriptive Logging Interface

First, we create an interface annotated with `@DescriptiveLogger` with methods
each annotated with `@LogMessage`. With `@LogMessage`, we describe what we
want to have logged when the method is called.

```java
@DescriptiveLogger
interface MyLogger {

  /**
   * Logs a message. The ID is added to the MDC.
   */
  @LogMessage(value="Hello World", level=Level.INFO, id=10042)
  void helloWorld();
  
  /**
   * Logs a message, replacing placeholders with method parameter values.
   * Placeholders work just like in SLF4J loggers.
   */
  @LogMessage(value="Hello {}", level=Level.INFO, id=10043)
  void helloWithName(String name);
  
  /**
   * Logs a message, replacing placeholders with method parameter values and adding 
   * the specified MDCValues to the MDC.
   */
  @LogMessage(value="Hello {}", level=Level.INFO, id=10044)
  void helloWithNameAndMDC(MDCValues mdc, String name);
  
  /**
  * Logs a message with a stacktrace, replacing placeholders with method parameter values and adding 
  * the specified MDCValues to the MDC.
  */
 @LogMessage(value="Hello {}", level=Level.ERROR, id=10045)
 void helloWithNameAndMDCAndStacktrace(MDCValues mdc, String name, Throwable t);

}
```

### Create a Descriptive Logger from an Interface

Next, we let the `LoggerFactory` create a descriptive logger for us and use it in our code.

```
class MyService {
  
  // The LoggerFactory needs to know which descriptive interface to build a logger for.
  // We also pass MyService.class to let the factory build an SLF4J logger for this class
  // which ultimately logs the message. 
  private static final MyLogger myLogger = LoggerFactory.getLogger(MyLogger.class, MyService.class);
  
  void doSomething(){
    myLogger.helloWithName("Bob");
  }
  
} 
```

### Log Message IDs

The (optional) `id`s defined in the `@LogMessage` annotations above are being passed into
the Message Diagnostic Context (MDC) with the key `id`. The key can be changed by using
the field `idMdcKey` in the `@DescriptiveLogger` annotation.

If we want to add the (optional) `id`s defined in the `@LogMessage` annotations above to out log output ,
we need to add it to our log pattern with `%X{id}` (replace `id` with the name we defined
in `idMdcKey`).

Example pattern with logback:
```
%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %X{id} - %msg%n
``` 

### Add a Prefix to each ID

We can use the `idPrefix` field in `@DescriptiveLogger` to add a prefix to each id.

```java
@DescriptiveLogger(idPrefix="ERR")
interface ErrorLogger {

  @LogMessage(value="This is a serious error!", level=Level.ERROR, id=10042)
  void error();
  
}
```

Calling `error()` would result in a log output something like this (depending on the 
logging pattern):

```
2018-07-28 10:11:34 [main] INFO  mylogger - ERR10042 - This is a serious error!
```

### Define ID ranges

We can use the `min` and `max` fields in `@DescriptiveLogger` to define a valid ID range
for the logger:

```java
@DescriptiveLogger(min=1000, max=1999)
interface InvalidIdLogger {

  @LogMessage(value="This will fail because the ID is out of range!", level=Level.ERROR, id=1)
  void error();
  
}
```

A call to `LoggerFactory.getLogger(InvalidIdLogger.class, ...)` will fail
because the id 1 is not in the range between 1000 and 1999.

Use this feature to define non-overlapping log ranges between multiple descriptive loggers
in order to keep the message ids unique.

## Adding Descriptive Logger to your Project

### Maven

```xml
<repositories>
    <repository>
      <id>jcenter</id>
      <url>https://jcenter.bintray.com/</url>
    </repository>
</repositories>

...

<dependency>
  <groupId>io.reflectoring</groupId>
  <artifactId>descriptive-logger</artifactId>
  <version>1.0</version>
</dependency>

```

### Gradle

```groovy
repositories {
    jcenter()
}

dependencies{
    compile 'io.reflectoring:descriptive-logger:1.0'
}

```