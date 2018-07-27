package io.reflectoring.coherentslf4j;

import java.util.Collection;

/** @author Tom Hombergs */
public class MessageIdOutOfRangeException extends LogMessagesException {

  public MessageIdOutOfRangeException(
      Class<?> logMessagesInterface, int min, int max, Collection<Integer> invalidIds) {
    super(
        String.format(
            "Message ids %s in @LogMessages interface %s are out of range (min: %d, max: %d)",
            invalidIds, logMessagesInterface, min, max));
  }
}
