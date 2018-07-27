package io.reflectoring.coherentslf4j;

import static org.assertj.core.api.Assertions.*;

import io.reflectoring.coherentslf4j.model.CapturingLogger;
import org.junit.jupiter.api.Test;

/** @author Tom Hombergs */
class LogMessagesWithDuplicateIdTests {

  private CapturingLogger capturingLogger = new CapturingLogger();

  @Test
  void whenDuplicateId_thenException() {
    assertThatExceptionOfType(DuplicateLogMessageIdException.class)
        .isThrownBy(
            () -> {
              LogMessagesFactory.getLogger(LogMessagesWithDuplicateId.class, capturingLogger);
            })
        .withMessageContaining("10")
        .withMessageContaining(LogMessagesWithDuplicateId.class.getName());
  }
}
