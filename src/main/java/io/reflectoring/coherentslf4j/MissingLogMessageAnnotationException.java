package io.reflectoring.coherentslf4j;

/** @author Tom Hombergs */
public class MissingLogMessageAnnotationException extends LogMessagesException {

  public MissingLogMessageAnnotationException(Class<?> logMessagesInterface) {
    super(String.format("Annotation @LogMessages is missing on class %s", logMessagesInterface));
  }
}
