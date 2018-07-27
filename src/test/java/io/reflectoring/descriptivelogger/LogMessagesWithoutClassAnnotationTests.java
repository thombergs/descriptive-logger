package io.reflectoring.descriptivelogger;

import io.reflectoring.descriptivelogger.model.CapturingLogger;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

/** @author Tom Hombergs */
class LogMessagesWithoutClassAnnotationTests {

  private CapturingLogger capturingLogger = new CapturingLogger();

  @Test
  void whenClassNotAnnotated_thenException() {
    Assertions.assertThatExceptionOfType(MissingLogMessageAnnotationException.class)
        .isThrownBy(
            () -> {
              LogMessagesFactory.getLogger(
                  LogMessagesWithoutClassAnnotation.class, capturingLogger);
            })
        .withMessageContaining(LogMessagesWithoutClassAnnotation.class.getName());
  }
}
