package io.reflectoring.coherentslf4j;

import org.slf4j.event.Level;

/** @author Tom Hombergs */
@LogMessages(idMdcKey = "myApp.id", idPrefix = "ABC")
interface LogMessagesWithCustomMdcId {

  @LogMessage(message = "This is a simple log message on DEBUG", level = Level.DEBUG, id = 1)
  void message();
}
