package io.reflectoring.descriptivelogger;

import org.slf4j.event.Level;

/** @author Tom Hombergs */
@DescriptiveLogger
interface DescriptiveLoggerWithDuplicateId {

  @LogMessage(message = "This is a simple log message", level = Level.INFO, id = 10)
  void message1();

  @LogMessage(
    message = "This is a simple log message with the same ID",
    level = Level.INFO,
    id = 10
  )
  void message2();
}
