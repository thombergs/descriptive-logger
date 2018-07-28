package io.reflectoring.descriptivelogger;

import static org.assertj.core.api.Assertions.*;

import io.reflectoring.descriptivelogger.model.CapturingLogger;
import org.junit.jupiter.api.Test;

/** @author Tom Hombergs */
class DescriptiveLoggerWithDuplicateIdTests {

  private CapturingLogger capturingLogger = new CapturingLogger();

  @Test
  void whenDuplicateId_thenException() {
    assertThatExceptionOfType(DuplicateMessageIdException.class)
        .isThrownBy(
            () -> {
              LogMessagesFactory.getLogger(DescriptiveLoggerWithDuplicateId.class, capturingLogger);
            })
        .withMessageContaining("10")
        .withMessageContaining(DescriptiveLoggerWithDuplicateId.class.getName());
  }
}
