package io.reflectoring.coherentslf4j;

import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

/** @author Tom Hombergs */
class LogMessagesFactoryTests {

  @Test
  void constructorWithStringParamWorks() {
    BasicLogMessages log = LogMessagesFactory.getLogger(BasicLogMessages.class, "mylogger");
    log.simpleLogMessage();
  }

  @Test
  void constructorWithClassParamWorks() {
    BasicLogMessages log =
        LogMessagesFactory.getLogger(BasicLogMessages.class, LogMessagesFactory.class);
    log.simpleLogMessage();
  }

  @Test
  void constructorWithLoggerParamWorks() {
    BasicLogMessages log =
        LogMessagesFactory.getLogger(
            BasicLogMessages.class, LoggerFactory.getLogger(LogMessagesFactory.class));
    log.simpleLogMessage();
  }
}
