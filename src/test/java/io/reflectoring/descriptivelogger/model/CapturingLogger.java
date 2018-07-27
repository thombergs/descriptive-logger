package io.reflectoring.descriptivelogger.model;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import io.reflectoring.descriptivelogger.MDCValues;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.MDC;
import org.slf4j.Marker;
import org.slf4j.event.Level;

/**
 * Logger implementation that captures log output for testing purposes.
 *
 * @author Tom Hombergs
 */
public class CapturingLogger implements Logger {

  private List<LogEvent> capturedLogEvents = new ArrayList<>();

  private int cursor = 0;

  private void capture(Level level, String message, Object... args) {
    capturedLogEvents.add(new LogEvent(level, message, currentMDC(), args));
  }

  private void capture(Level level, String message, Throwable throwable) {
    capturedLogEvents.add(new LogEvent(level, message, currentMDC(), throwable));
  }

  private MDCValues currentMDC() {
    return new MDCValues().fromMap(MDC.getCopyOfContextMap());
  }

  public void assertCaptured(Level expectedLevel, String expectedMessage) {
    LogEvent event = capturedLogEvents.get(cursor++);
    assertThat(event.getLevel()).isEqualTo(expectedLevel);
    assertThat(event.getMessage()).isEqualTo(expectedMessage);
  }

  public void assertCaptured(Level expectedLevel, String expectedMessage, Object... expectedArgs) {
    LogEvent event = capturedLogEvents.get(cursor++);
    assertThat(event.getLevel()).isEqualTo(expectedLevel);
    assertThat(event.getMessage()).isEqualTo(expectedMessage);
    assertThat(event.getArgs()).isEqualTo(expectedArgs);
  }

  public void assertCaptured(
      Level expectedLevel, String expectedMessage, MDCValues expectedMdc, Object... expectedArgs) {
    LogEvent event = capturedLogEvents.get(cursor++);
    assertThat(event.getLevel()).isEqualTo(expectedLevel);
    assertThat(event.getMessage()).isEqualTo(expectedMessage);
    assertThat(event.getArgs()).isEqualTo(expectedArgs);
    for (Map.Entry<String, String> entry : expectedMdc) {
      assertThat(event.getMdcValues().getValue(entry.getKey())).isEqualTo(entry.getValue());
    }
    for (Map.Entry<String, String> entry : event.getMdcValues()) {
      assertThat(expectedMdc.getValue(entry.getKey())).isEqualTo(entry.getValue());
    }
  }

  public void assertMDCIsCleared(MDCValues mdc) {
    for (Map.Entry<String, String> entry : mdc) {
      assertThat(MDC.get(entry.getKey())).isNull();
    }
  }

  @Override
  public String getName() {
    throw new IllegalStateException("not implemented");
  }

  @Override
  public boolean isTraceEnabled() {
    throw new IllegalStateException("not implemented");
  }

  @Override
  public void trace(String s) {
    capture(Level.TRACE, s);
  }

  @Override
  public void trace(String s, Object o) {
    capture(Level.TRACE, s, o);
  }

  @Override
  public void trace(String s, Object o, Object o1) {
    capture(Level.TRACE, s, o, o1);
  }

  @Override
  public void trace(String s, Object... objects) {
    capture(Level.TRACE, s, objects);
  }

  @Override
  public void trace(String s, Throwable throwable) {
    capture(Level.TRACE, s, throwable);
  }

  @Override
  public boolean isTraceEnabled(Marker marker) {
    throw new IllegalStateException("not implemented");
  }

  @Override
  public void trace(Marker marker, String s) {
    throw new IllegalStateException("not implemented");
  }

  @Override
  public void trace(Marker marker, String s, Object o) {
    throw new IllegalStateException("not implemented");
  }

  @Override
  public void trace(Marker marker, String s, Object o, Object o1) {
    throw new IllegalStateException("not implemented");
  }

  @Override
  public void trace(Marker marker, String s, Object... objects) {
    throw new IllegalStateException("not implemented");
  }

  @Override
  public void trace(Marker marker, String s, Throwable throwable) {
    throw new IllegalStateException("not implemented");
  }

  @Override
  public boolean isDebugEnabled() {
    throw new IllegalStateException("not implemented");
  }

  @Override
  public void debug(String s) {
    capture(Level.DEBUG, s);
  }

  @Override
  public void debug(String s, Object o) {
    capture(Level.DEBUG, s, o);
  }

  @Override
  public void debug(String s, Object o, Object o1) {
    capture(Level.DEBUG, s, o, o1);
  }

  @Override
  public void debug(String s, Object... objects) {
    capture(Level.DEBUG, s, objects);
  }

  @Override
  public void debug(String s, Throwable throwable) {
    capture(Level.DEBUG, s, throwable);
  }

  @Override
  public boolean isDebugEnabled(Marker marker) {
    throw new IllegalStateException("not implemented");
  }

  @Override
  public void debug(Marker marker, String s) {
    throw new IllegalStateException("not implemented");
  }

  @Override
  public void debug(Marker marker, String s, Object o) {
    throw new IllegalStateException("not implemented");
  }

  @Override
  public void debug(Marker marker, String s, Object o, Object o1) {
    throw new IllegalStateException("not implemented");
  }

  @Override
  public void debug(Marker marker, String s, Object... objects) {
    throw new IllegalStateException("not implemented");
  }

  @Override
  public void debug(Marker marker, String s, Throwable throwable) {
    throw new IllegalStateException("not implemented");
  }

  @Override
  public boolean isInfoEnabled() {
    throw new IllegalStateException("not implemented");
  }

  @Override
  public void info(String s) {
    capture(Level.INFO, s);
  }

  @Override
  public void info(String s, Object o) {
    capture(Level.INFO, s, o);
  }

  @Override
  public void info(String s, Object o, Object o1) {
    capture(Level.INFO, s, o, o1);
  }

  @Override
  public void info(String s, Object... objects) {
    capture(Level.INFO, s, objects);
  }

  @Override
  public void info(String s, Throwable throwable) {
    capture(Level.INFO, s, throwable);
  }

  @Override
  public boolean isInfoEnabled(Marker marker) {
    throw new IllegalStateException("not implemented");
  }

  @Override
  public void info(Marker marker, String s) {
    throw new IllegalStateException("not implemented");
  }

  @Override
  public void info(Marker marker, String s, Object o) {
    throw new IllegalStateException("not implemented");
  }

  @Override
  public void info(Marker marker, String s, Object o, Object o1) {
    throw new IllegalStateException("not implemented");
  }

  @Override
  public void info(Marker marker, String s, Object... objects) {
    throw new IllegalStateException("not implemented");
  }

  @Override
  public void info(Marker marker, String s, Throwable throwable) {
    throw new IllegalStateException("not implemented");
  }

  @Override
  public boolean isWarnEnabled() {
    throw new IllegalStateException("not implemented");
  }

  @Override
  public void warn(String s) {
    capture(Level.WARN, s);
  }

  @Override
  public void warn(String s, Object o) {
    capture(Level.WARN, s, o);
  }

  @Override
  public void warn(String s, Object o, Object o1) {
    capture(Level.WARN, s, o, o1);
  }

  @Override
  public void warn(String s, Object... objects) {
    capture(Level.WARN, s, objects);
  }

  @Override
  public void warn(String s, Throwable throwable) {
    capture(Level.WARN, s, throwable);
  }

  @Override
  public boolean isWarnEnabled(Marker marker) {
    throw new IllegalStateException("not implemented");
  }

  @Override
  public void warn(Marker marker, String s) {
    throw new IllegalStateException("not implemented");
  }

  @Override
  public void warn(Marker marker, String s, Object o) {
    throw new IllegalStateException("not implemented");
  }

  @Override
  public void warn(Marker marker, String s, Object o, Object o1) {
    throw new IllegalStateException("not implemented");
  }

  @Override
  public void warn(Marker marker, String s, Object... objects) {
    throw new IllegalStateException("not implemented");
  }

  @Override
  public void warn(Marker marker, String s, Throwable throwable) {
    throw new IllegalStateException("not implemented");
  }

  @Override
  public boolean isErrorEnabled() {
    throw new IllegalStateException("not implemented");
  }

  @Override
  public void error(String s) {
    capture(Level.ERROR, s);
  }

  @Override
  public void error(String s, Object o) {
    capture(Level.ERROR, s, o);
  }

  @Override
  public void error(String s, Object o, Object o1) {
    capture(Level.ERROR, s, o, o1);
  }

  @Override
  public void error(String s, Object... objects) {
    capture(Level.ERROR, s, objects);
  }

  @Override
  public void error(String s, Throwable throwable) {
    capture(Level.ERROR, s, throwable);
  }

  @Override
  public boolean isErrorEnabled(Marker marker) {
    throw new IllegalStateException("not implemented");
  }

  @Override
  public void error(Marker marker, String s) {
    throw new IllegalStateException("not implemented");
  }

  @Override
  public void error(Marker marker, String s, Object o) {
    throw new IllegalStateException("not implemented");
  }

  @Override
  public void error(Marker marker, String s, Object o, Object o1) {
    throw new IllegalStateException("not implemented");
  }

  @Override
  public void error(Marker marker, String s, Object... objects) {
    throw new IllegalStateException("not implemented");
  }

  @Override
  public void error(Marker marker, String s, Throwable throwable) {
    throw new IllegalStateException("not implemented");
  }
}
