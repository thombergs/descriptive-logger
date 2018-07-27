package io.reflectoring.descriptivelogger;

import java.util.Collection;

/** @author Tom Hombergs */
public class DuplicateLogMessageIdException extends LogMessagesException {

  public DuplicateLogMessageIdException(
      Class<?> logMessagesInterface, Collection<Integer> duplicateIds) {
    super(
        String.format(
            "Duplicate log message ids %s in @LogMessages interface %s",
            duplicateIds, logMessagesInterface));
  }
}
