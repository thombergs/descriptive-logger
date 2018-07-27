package io.reflectoring.descriptivelogger;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.slf4j.event.Level;

/**
 * Used to annotate methods within an interface annotated with @{@link LogMessages}. The log message
 * described by this annotation will be forwarded to an SLF4J logger when the method is invoked.
 *
 * <p>The signature of the method must have no parameters at all or must match to a valid log method
 * signature of the {@link org.slf4j.Logger} interface, i.e:
 *
 * <ul>
 *   <li><strong>no parameters:</strong> the message defined by the {@code message} field will be
 *       logged as is
 *   <li><strong>one or more parameters of any type:</strong> the parameter values will be passed to
 *       the logger to replace placeholders ("{}")
 *   <li><strong>one or more parameters of any type followed by a parameter of type {@link
 *       Throwable}:</strong> same as above, stacktrace of the throwable will be logged
 * </ul>
 *
 * All signatures above may be combined with a parameter of type {@link MDCValues} as <strong>the
 * first parameter</strong>, allowing to define parameters that will be added to the "Mapped
 * Diagnostic Context" via {@link org.slf4j.MDC#put(String, String)} before the log message is
 * forwarded to SLF4J.
 *
 * @author Tom Hombergs
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogMessage {

  /**
   * The log message to be logged when calling the annotated method. Can be a String including
   * placeholders in the SLF4J fashion. Placeholders in the message will be replaced by SLF4J with
   * the values of the parameters of the annotated method.
   */
  String message();

  /** The log level the message should be logged at. */
  Level level();

  /**
   * Unique ID of the log message. If defined, this ID will be published in the Mapped Diagnostic
   * Context via {@link org.slf4j.MDC#put(String, String)} just before the log message is forwarded
   * to the SLF4J logger.
   */
  int id() default -1;
}
