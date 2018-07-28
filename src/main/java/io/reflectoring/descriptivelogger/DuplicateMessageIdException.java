package io.reflectoring.descriptivelogger;

import java.util.Collection;

/** @author Tom Hombergs */
public class DuplicateMessageIdException extends LogMessagesException {

  public DuplicateMessageIdException(
      Class<?> logMessagesInterface, Collection<Integer> duplicateIds) {
    super(
        String.format(
            "Duplicate log message ids %s in @DescriptiveLogger interface %s",
            duplicateIds, logMessagesInterface));
  }
}
