package io.reflectoring.descriptivelogger;

import org.junit.jupiter.api.Test;

/** @author Tom Hombergs */
class LoggerFactoryTests {

  @Test
  void constructorWithStringParamWorks() {
    BasicDescriptiveLogger log = LoggerFactory.getLogger(BasicDescriptiveLogger.class, "mylogger");
    log.simpleLogMessage();
  }

  @Test
  void constructorWithClassParamWorks() {
    BasicDescriptiveLogger log =
        LoggerFactory.getLogger(BasicDescriptiveLogger.class, LoggerFactory.class);
    log.simpleLogMessage();
  }

  @Test
  void constructorWithLoggerParamWorks() {
    BasicDescriptiveLogger log =
        LoggerFactory.getLogger(
            BasicDescriptiveLogger.class, org.slf4j.LoggerFactory.getLogger(LoggerFactory.class));
    log.simpleLogMessage();
  }
}
