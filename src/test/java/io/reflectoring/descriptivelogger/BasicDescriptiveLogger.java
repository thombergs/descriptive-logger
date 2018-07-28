package io.reflectoring.descriptivelogger;

import org.slf4j.event.Level;

/** @author Tom Hombergs */
@DescriptiveLogger
interface BasicDescriptiveLogger {

  @LogMessage(message = "This is a simple log message", level = Level.INFO, id = 10)
  void simpleLogMessage();

  @LogMessage(
    message = "This is a log message with a placeholder: {}",
    level = Level.TRACE,
    id = 11
  )
  void messageWithPlaceholder(String param);

  @LogMessage(
    message = "This is a log message with two placeholders: {} and {}",
    level = Level.ERROR,
    id = 15
  )
  void messageWithTwoPlaceholders(String param1, Long param2);

  @LogMessage(message = "This is a log message with an exception", level = Level.WARN, id = 12)
  void messageWithException(Exception e);

  @LogMessage(message = "This is a log message with MDC values", level = Level.INFO, id = 13)
  void messageWithMDCValues(MDCValues mdc);

  @LogMessage(
    message = "This is a log message with MDC values and a placeholder: {}",
    level = Level.INFO,
    id = 17
  )
  void messageWithMDCValuesAndPlaceholder(MDCValues mdc, String param);

  @LogMessage(
    message = "This is a log message with MDC values and two placeholders: {} and {}",
    level = Level.INFO,
    id = 16
  )
  void messageWithMDCValuesAndTwoPlaceholders(MDCValues mdc, String param1, String param2);

  @LogMessage(message = "This is a message without id", level = Level.ERROR)
  void messageWithoutId();

  @LogMessage(message = "This is a simple log message on DEBUG", level = Level.DEBUG, id = 14)
  void simpleLogMessageOnDebug();
}
