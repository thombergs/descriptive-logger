package io.reflectoring.coherentslf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.slf4j.event.Level;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.util.ReflectionUtils;

/**
 * Creates coherent loggers from interfaces annotated with @{@link LogMessages}.
 *
 * @author Tom Hombergs
 */
public class LogMessagesFactory {

  private static LogMessagesFactory INSTANCE = new LogMessagesFactory();

  private LogMessagesFactory() {}

  /**
   * Creates a logger from a logging interface annotated with @{@link LogMessages}. Methods in this
   * interface will be forwared to an SLF4J logger.
   *
   * @param logMessagesInterface the interface annotated with @{@link LogMessages} for which to
   *     create a proxy.
   * @param loggerName the name of the SLF4J logger to which the log messages are being forwarded.
   */
  public static <T> T getLogger(Class<T> logMessagesInterface, String loggerName) {
    Logger logger = LoggerFactory.getLogger(loggerName);
    return getLogger(logMessagesInterface, logger);
  }

  /**
   * Creates a logger from a logging interface annotated with @{@link LogMessages}. Methods in this
   * interface will be forwared to an SLF4J logger.
   *
   * @param logMessagesInterface the interface annotated with @{@link LogMessages} for which to
   *     create a proxy.
   * @param forClass the class for which to create an SLF4J logger to which the log messages are
   *     being forwarded.
   */
  public static <T> T getLogger(Class<T> logMessagesInterface, Class<?> forClass) {
    Logger logger = LoggerFactory.getLogger(forClass);
    return getLogger(logMessagesInterface, logger);
  }

  /**
   * Creates a logger from a logging interface annotated with @{@link LogMessages}. Methods in this
   * interface will be forwared to an SLF4J logger.
   *
   * @param logMessagesInterface the interface annotated with @{@link LogMessages} for which to
   *     create a proxy.
   * @param logger the SLF4J logger to which the log messages are being forwarded.
   */
  public static <T> T getLogger(Class<T> logMessagesInterface, Logger logger) {
    return INSTANCE.doCreate(logMessagesInterface, logger);
  }

  private <T> T doCreate(Class<T> logMessagesInterface, Logger logger) {

    LogMessages classAnnotation = logMessagesInterface.getAnnotation(LogMessages.class);
    if (classAnnotation == null) {
      throw new MissingLogMessageAnnotationException(logMessagesInterface);
    }

    List<LogMessage> methodAnnotations = getMethodAnnotations(logMessagesInterface);
    validateNoDuplicateIds(logMessagesInterface, methodAnnotations);
    validateIdsInRange(logMessagesInterface, classAnnotation, methodAnnotations);

    return ProxyFactory.getProxy(
        logMessagesInterface,
        (MethodInterceptor)
            invocation -> {
              LogMessage annotation = getLogMessageAnnotation(invocation);

              MDCValues mdc = getMDCValues(invocation);
              if (annotation.id() >= 0) {
                mdc.withValue(
                    classAnnotation.idMdcKey(),
                    classAnnotation.idPrefix() + String.valueOf(annotation.id()));
              }

              updateMDC(mdc);
              Object[] arguments = getLogArguments(invocation);
              log(logger, annotation.message(), annotation.level(), arguments);
              clearMDC(mdc);
              return null;
            });
  }

  private void validateNoDuplicateIds(
      Class<?> logMessagesInterface, List<LogMessage> methodAnnotations) {
    Set<Integer> duplicateIds = getDuplicateIds(methodAnnotations);
    if (!duplicateIds.isEmpty()) {
      throw new DuplicateLogMessageIdException(logMessagesInterface, duplicateIds);
    }
  }

  private void validateIdsInRange(
      Class<?> logMessagesInterface,
      LogMessages classAnnotation,
      List<LogMessage> methodAnnotations) {
    Set<Integer> outOfRangeIds =
        methodAnnotations
            .stream()
            .map(LogMessage::id)
            .filter(id -> id >= 0) // negative ID -1 is default and thus ignored
            .filter(id -> id < classAnnotation.min() || id > classAnnotation.max())
            .collect(Collectors.toSet());

    if (!outOfRangeIds.isEmpty()) {
      throw new MessageIdOutOfRangeException(
          logMessagesInterface, classAnnotation.min(), classAnnotation.max(), outOfRangeIds);
    }
  }

  private Set<Integer> getDuplicateIds(List<LogMessage> logMessages) {
    List<Integer> allIds =
        logMessages
            .stream()
            .map(LogMessage::id)
            .filter(id -> id >= 0) // negative ID -1 is default and thus ignored
            .collect(Collectors.toList());

    return allIds
        .stream()
        .filter(id -> Collections.frequency(allIds, id) > 1)
        .collect(Collectors.toSet());
  }

  private List<LogMessage> getMethodAnnotations(Class<?> logMessagesInterface) {
    List<LogMessage> annotations = new ArrayList<>();
    ReflectionUtils.doWithMethods(
        logMessagesInterface,
        (method) -> {
          LogMessage annotation = method.getAnnotation(LogMessage.class);
          if (annotation != null) {
            annotations.add(annotation);
          }
        });
    return annotations;
  }

  private void log(Logger logger, String message, Level level, Object[] arguments) {

    if (arguments.length == 0) {
      if (level == Level.TRACE) {
        logger.trace(message);
      } else if (level == Level.DEBUG) {
        logger.debug(message);
      } else if (level == Level.INFO) {
        logger.info(message);
      } else if (level == Level.WARN) {
        logger.warn(message);
      } else if (level == Level.ERROR) {
        logger.error(message);
      } else {
        throw new IllegalArgumentException(
            String.format("Logging Level %s is not supported!", level));
      }
    } else {
      if (level == Level.TRACE) {
        logger.trace(message, arguments);
      } else if (level == Level.DEBUG) {
        logger.debug(message, arguments);
      } else if (level == Level.INFO) {
        logger.info(message, arguments);
      } else if (level == Level.WARN) {
        logger.warn(message, arguments);
      } else if (level == Level.ERROR) {
        logger.error(message, arguments);
      } else {
        throw new IllegalArgumentException(
            String.format("Logging Level %s is not supported!", level));
      }
    }
  }

  private LogMessage getLogMessageAnnotation(MethodInvocation invocation) {
    return invocation.getMethod().getAnnotation(LogMessage.class);
  }

  private Object[] getLogArguments(MethodInvocation invocation) {
    List<Object> arguments = new ArrayList<>();
    for (Object argument : invocation.getArguments()) {
      if (!(argument instanceof MDCValues)) {
        arguments.add(argument);
      }
    }
    return arguments.toArray();
  }

  private MDCValues getMDCValues(MethodInvocation invocation) {
    for (Object argument : invocation.getArguments()) {
      if (argument instanceof MDCValues) {
        return (MDCValues) argument;
      }
    }
    return new MDCValues();
  }

  private void updateMDC(MDCValues mdcValues) {
    for (Map.Entry<String, String> entry : mdcValues) {
      MDC.put(entry.getKey(), entry.getValue());
    }
  }

  private void clearMDC(MDCValues mdcValues) {
    for (Map.Entry<String, String> entry : mdcValues) {
      MDC.remove(entry.getKey());
    }
  }
}
