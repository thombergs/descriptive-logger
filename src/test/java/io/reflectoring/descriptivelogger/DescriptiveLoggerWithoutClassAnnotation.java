package io.reflectoring.descriptivelogger;

import org.slf4j.event.Level;

/** @author Tom Hombergs */
interface DescriptiveLoggerWithoutClassAnnotation {

  @LogMessage(message = "This is a simple log message", level = Level.INFO, id = 10)
  void simpleLogMessage();
}
