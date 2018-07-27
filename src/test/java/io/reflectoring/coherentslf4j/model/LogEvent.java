package io.reflectoring.coherentslf4j.model;

import io.reflectoring.coherentslf4j.MDCValues;
import org.slf4j.event.Level;

/** @author Tom Hombergs */
public class LogEvent {

  private final Level level;

  private final String message;

  private final Object[] args;

  private final Throwable throwable;

  private final MDCValues mdcValues;

  public LogEvent(Level level, String message, MDCValues mdcValues, Object[] args) {
    this.level = level;
    this.message = message;
    this.mdcValues = mdcValues;
    this.args = args;
    this.throwable = null;
  }

  public LogEvent(Level level, String message, MDCValues mdcValues, Throwable throwable) {
    this.level = level;
    this.message = message;
    this.mdcValues = mdcValues;
    this.args = null;
    this.throwable = throwable;
  }

  public Level getLevel() {
    return level;
  }

  public String getMessage() {
    return message;
  }

  public Object[] getArgs() {
    return args;
  }

  public Throwable getThrowable() {
    return throwable;
  }

  public MDCValues getMdcValues() {
    return mdcValues;
  }
}
