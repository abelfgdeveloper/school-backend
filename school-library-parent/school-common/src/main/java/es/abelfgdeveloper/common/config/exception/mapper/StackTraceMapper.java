package es.abelfgdeveloper.common.config.exception.mapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class StackTraceMapper {

  private static final List<String> excludeClass =
      Arrays.asList(
          "org.apache.catalina",
          "org.apache.tomcat",
          "org.apache.coyote",
          "java.util",
          "com.sun",
          "sun.net",
          "net.sf.cglib.proxy.MethodProxy.invoke",
          "org.springframework.",
          "jdk.internal",
          "java.lang",
          "javax.servlet",
          "brave.servlet",
          "org.zalando");

  public List<String> map(StackTraceElement[] stackTraceElements) {
    List<String> stackTrace = new ArrayList<>();
    for (StackTraceElement stackTraceElement : stackTraceElements) {
      if (!excludeTrace(stackTraceElement.getClassName())) {
        stackTrace.add(
            new StringBuilder()
                .append(stackTraceElement.getClassName())
                .append(":")
                .append(stackTraceElement.getLineNumber())
                .toString());
      }
    }
    return stackTrace;
  }

  private boolean excludeTrace(String className) {
    return excludeClass.stream().anyMatch(className::startsWith);
  }
}
