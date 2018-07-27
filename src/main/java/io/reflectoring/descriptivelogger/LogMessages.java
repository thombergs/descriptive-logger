package io.reflectoring.descriptivelogger;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation marks a logging interface that provides methods that each log a certain, unique
 * log message. The methods must be annotated with @{@link LogMessage} to define the content of the
 * log message.
 *
 * <p>An interface marked with @{@link LogMessages} is not intended to be implemented manually.
 * Instead, use {@link LogMessagesFactory} to generate a proxy that forwards the log messages to an
 * SLF4J logger.
 *
 * @author Tom Hombergs
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogMessages {

  /**
   * Defines the minimum for the {@code id} of all @{@link LogMessage} annotations within this
   * interface. If an id below this minimum is found, a {@link MessageIdOutOfRangeException} is
   * thrown.
   *
   * <p>Use {@code min} and {@code max} to avoid id clashes when using multiple @{@link LogMessage}
   * interfaces.
   */
  int min() default 0;

  /**
   * Defines the maximum for the {@code id} of all @{@link LogMessage} annotations within this
   * interface. If an id above this maximum is found, a {@link MessageIdOutOfRangeException} is
   * thrown.
   *
   * <p>Use {@code min} and {@code max} to avoid id clashes when using multiple @{@link LogMessage}
   * interfaces.
   */
  int max() default Integer.MAX_VALUE;

  /**
   * Name of the key under which the {@code id} of each @{@link LogMessage} should be published into
   * the Mapped Diagnostic Context (MDC).
   */
  String idMdcKey() default "id";

  /** Prefix that will be prepended to the id in the log output. */
  String idPrefix() default "";
}
