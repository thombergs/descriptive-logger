package io.reflectoring.descriptivelogger;

import io.reflectoring.descriptivelogger.model.CapturingLogger;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

/** @author Tom Hombergs */
class DescriptiveLoggerWithoutClassAnnotationTests {

  private CapturingLogger capturingLogger = new CapturingLogger();

  @Test
  void whenClassNotAnnotated_thenException() {
    Assertions.assertThatExceptionOfType(MissingLogMessageAnnotationException.class)
        .isThrownBy(
            () -> {
              LoggerFactory.getLogger(
                  DescriptiveLoggerWithoutClassAnnotation.class, capturingLogger);
            })
        .withMessageContaining(DescriptiveLoggerWithoutClassAnnotation.class.getName());
  }
}
