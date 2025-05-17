package utils;

import java.lang.annotation.Annotation;

public class AnnotationUtils {

  public <T extends Annotation> T getAnnotationInstanse(Class<?> targetClass, Class<T> annotationClass) {
    if (targetClass.isAnnotationPresent(annotationClass)) {
      return targetClass.getDeclaredAnnotation(annotationClass);
    }
    throw new RuntimeException(String.format("Annotation @" + annotationClass.getSimpleName() + " is missing on" + targetClass.getCanonicalName()));
  }
}
