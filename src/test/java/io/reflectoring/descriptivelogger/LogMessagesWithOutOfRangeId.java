package io.reflectoring.descriptivelogger;

import org.slf4j.event.Level;

/** @author Tom Hombergs */
@LogMessages(min = 10, max = 100)
interface LogMessagesWithOutOfRangeId {

  @LogMessage(message = "This is a simple log message", level = Level.INFO, id = 9)
  void message1();

  @LogMessage(
    message = "This is a simple log message with the same ID",
    level = Level.INFO,
    id = 101
  )
  void message2();
}
