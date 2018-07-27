package io.reflectoring.coherentslf4j;

import io.reflectoring.coherentslf4j.model.CapturingLogger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.event.Level;

/** @author Tom Hombergs */
class LogMessagesWithCustomMdcIdTests {

  private LogMessagesWithCustomMdcId log;

  private CapturingLogger capturingLogger = new CapturingLogger();

  @BeforeEach
  void setup() {
    log = LogMessagesFactory.getLogger(LogMessagesWithCustomMdcId.class, capturingLogger);
  }

  @Test
  void whenCustomMdcId_thenIdIsForwardedToMDC() {
    MDCValues mdc = new MDCValues("myApp.id", "ABC1");
    log.message();
    capturingLogger.assertCaptured(Level.DEBUG, "This is a simple log message on DEBUG", mdc);
  }
}
