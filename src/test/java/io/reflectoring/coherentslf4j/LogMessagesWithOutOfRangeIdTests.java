package io.reflectoring.coherentslf4j;

import static org.assertj.core.api.Assertions.*;

import io.reflectoring.coherentslf4j.model.CapturingLogger;
import org.junit.jupiter.api.Test;

/** @author Tom Hombergs */
class LogMessagesWithOutOfRangeIdTests {

  private CapturingLogger capturingLogger = new CapturingLogger();

  @Test
  void whenIdOutOfRange_thenException() {
    assertThatExceptionOfType(MessageIdOutOfRangeException.class)
        .isThrownBy(
            () -> {
              LogMessagesFactory.getLogger(LogMessagesWithOutOfRangeId.class, capturingLogger);
            })
        .withMessageContaining("9")
        .withMessageContaining("101")
        .withMessageContaining(LogMessagesWithOutOfRangeId.class.getName())
        .withMessageContaining("min: 10")
        .withMessageContaining("max: 100");
  }
}
