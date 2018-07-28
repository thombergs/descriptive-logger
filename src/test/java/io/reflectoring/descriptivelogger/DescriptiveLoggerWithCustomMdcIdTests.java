package io.reflectoring.descriptivelogger;

import io.reflectoring.descriptivelogger.model.CapturingLogger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.event.Level;

/** @author Tom Hombergs */
class DescriptiveLoggerWithCustomMdcIdTests {

  private DescriptiveLoggerWithCustomMdcId log;

  private CapturingLogger capturingLogger = new CapturingLogger();

  @BeforeEach
  void setup() {
    log = LoggerFactory.getLogger(DescriptiveLoggerWithCustomMdcId.class, capturingLogger);
  }

  @Test
  void whenCustomMdcId_thenIdIsForwardedToMDC() {
    MDCValues mdc = new MDCValues("myApp.id", "ABC1");
    log.message();
    capturingLogger.assertCaptured(Level.DEBUG, "This is a simple log message on DEBUG", mdc);
  }
}
