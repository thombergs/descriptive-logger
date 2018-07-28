package io.reflectoring.descriptivelogger;

import org.slf4j.event.Level;

/** @author Tom Hombergs */
@DescriptiveLogger(min = 10, max = 100)
interface DescriptiveLoggerWithOutOfRangeId {

  @LogMessage(message = "This is a simple log message", level = Level.INFO, id = 9)
  void message1();

  @LogMessage(
    message = "This is a simple log message with the same ID",
    level = Level.INFO,
    id = 101
  )
  void message2();
}
