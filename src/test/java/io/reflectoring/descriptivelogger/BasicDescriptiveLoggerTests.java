package io.reflectoring.descriptivelogger;

import io.reflectoring.descriptivelogger.model.CapturingLogger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.event.Level;

/** @author Tom Hombergs */
class BasicDescriptiveLoggerTests {

  private BasicDescriptiveLogger log;

  private CapturingLogger capturingLogger = new CapturingLogger();

  @BeforeEach
  void setup() {
    log = LogMessagesFactory.getLogger(BasicDescriptiveLogger.class, capturingLogger);
  }

  @Test
  void whenSimpleLogMessage_thenMessageIsForwardedToSlf4j() {
    log.simpleLogMessage();
    capturingLogger.assertCaptured(
        Level.INFO, "This is a simple log message", new MDCValues("id", "10"));
  }

  @Test
  void whenSimpleLogMessageOnDebugLevel_thenMessageIsLoggedOnDebugLevel() {
    log.simpleLogMessageOnDebug();
    capturingLogger.assertCaptured(
        Level.DEBUG, "This is a simple log message on DEBUG", new MDCValues("id", "14"));
  }

  @Test
  void whenMessageWithPlaceholder_thenPlaceholderIsForwardedToSlf4j() {
    log.messageWithPlaceholder("foo");
    capturingLogger.assertCaptured(
        Level.TRACE,
        "This is a log message with a placeholder: {}",
        new MDCValues("id", "11"),
        "foo");
  }

  @Test
  void whenMessageWitTwoPlaceholders_thenPlaceholdersAreForwardedToSlf4j() {
    log.messageWithTwoPlaceholders("foo", 42L);
    capturingLogger.assertCaptured(
        Level.ERROR,
        "This is a log message with two placeholders: {} and {}",
        new MDCValues("id", "15"),
        "foo",
        42L);
  }

  @Test
  void whenMessageWithException_thenExceptionIsForwardedToSlf4j() {
    Exception e = new NullPointerException("BWAAAAH");
    log.messageWithException(e);
    capturingLogger.assertCaptured(
        Level.WARN, "This is a log message with an exception", new MDCValues("id", "12"), e);
  }

  @Test
  void whenMessageWithMDCValues_thenMDCValuesAreForwardedToMDC() {
    MDCValues mdc = new MDCValues("foo", "bar").withValue("baz", "boom");
    log.messageWithMDCValues(mdc);
    capturingLogger.assertCaptured(Level.INFO, "This is a log message with MDC values", mdc);
    capturingLogger.assertMDCIsCleared(mdc);
  }

  @Test
  void whenMessageWithMDCValuesAndPlaceholder_thenMDCValuesAreForwardedToMDC() {
    MDCValues mdc = new MDCValues("foo", "bar").withValue("baz", "boom");
    log.messageWithMDCValuesAndPlaceholder(mdc, "bah");
    capturingLogger.assertCaptured(
        Level.INFO, "This is a log message with MDC values and a placeholder: {}", mdc, "bah");
    capturingLogger.assertMDCIsCleared(mdc);
  }

  @Test
  void whenMessageWithMDCValuesAndTwoPlaceholders_thenMDCValuesAreForwardedToMDC() {
    MDCValues mdc = new MDCValues("foo", "bar").withValue("baz", "boom");
    log.messageWithMDCValuesAndTwoPlaceholders(mdc, "bah", "boo");
    capturingLogger.assertCaptured(
        Level.INFO,
        "This is a log message with MDC values and two placeholders: {} and {}",
        mdc,
        "bah",
        "boo");
    capturingLogger.assertMDCIsCleared(mdc);
  }

  @Test
  void whenMessageWithoutId_thenNoIdForwardedToMDC() {
    MDCValues mdc = new MDCValues();
    log.messageWithoutId();
    capturingLogger.assertCaptured(Level.ERROR, "This is a message without id", mdc);
    capturingLogger.assertMDCIsCleared(mdc);
  }
}
