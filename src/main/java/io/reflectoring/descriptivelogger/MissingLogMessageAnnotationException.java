package io.reflectoring.descriptivelogger;

/** @author Tom Hombergs */
public class MissingLogMessageAnnotationException extends LogMessagesException {

  public MissingLogMessageAnnotationException(Class<?> logMessagesInterface) {
    super(String.format("Annotation @DescriptiveLogger is missing on class %s", logMessagesInterface));
  }
}
