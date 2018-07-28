package io.reflectoring.descriptivelogger;

import org.slf4j.event.Level;

/** @author Tom Hombergs */
@DescriptiveLogger(idMdcKey = "myApp.id", idPrefix = "ABC")
interface DescriptiveLoggerWithCustomMdcId {

  @LogMessage(message = "This is a simple log message on DEBUG", level = Level.DEBUG, id = 1)
  void message();
}
