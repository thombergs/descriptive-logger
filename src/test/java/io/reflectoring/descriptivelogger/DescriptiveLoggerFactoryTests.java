package io.reflectoring.descriptivelogger;

import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

/** @author Tom Hombergs */
class DescriptiveLoggerFactoryTests {

  @Test
  void constructorWithStringParamWorks() {
    BasicDescriptiveLogger log = LogMessagesFactory.getLogger(BasicDescriptiveLogger.class, "mylogger");
    log.simpleLogMessage();
  }

  @Test
  void constructorWithClassParamWorks() {
    BasicDescriptiveLogger log =
        LogMessagesFactory.getLogger(BasicDescriptiveLogger.class, LogMessagesFactory.class);
    log.simpleLogMessage();
  }

  @Test
  void constructorWithLoggerParamWorks() {
    BasicDescriptiveLogger log =
        LogMessagesFactory.getLogger(
            BasicDescriptiveLogger.class, LoggerFactory.getLogger(LogMessagesFactory.class));
    log.simpleLogMessage();
  }
}
