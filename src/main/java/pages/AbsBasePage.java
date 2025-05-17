package pages;

import annotations.Path;
import annotations.UrlTemplate;
import common.AbsCommon;
import org.openqa.selenium.WebDriver;
import java.lang.annotation.Annotation;

public abstract class AbsBasePage extends AbsCommon {
  public AbsBasePage(WebDriver driver) {
    super(driver);
  }

  private String baseUrl = System.getProperty("base.url");

  private <T extends Annotation> T getAnnotationInstance(Class<T> annotationClass, boolean isException) {
    Class<?> clazz = getClass();

    if (clazz.isAnnotationPresent(annotationClass)) {
      return clazz.getDeclaredAnnotation(annotationClass);
    }

    if (isException) {
      throw new RuntimeException(String.format("Annotation %s is absent on class %s",
          annotationClass.getSimpleName(), clazz.getCanonicalName()));
    }

    return null;
  }

  private String getPath() {
    return ((Path) getAnnotationInstance(Path.class, true)).value();
  }

  private String getPathTemplate() {
    UrlTemplate urlTemplate = getAnnotationInstance(UrlTemplate.class, false);
    return urlTemplate != null ? urlTemplate.value() : "";
  }

  private String getBaseUrl() {
    return baseUrl.endsWith("/")
        ? baseUrl.substring(0, baseUrl.length() - 1)
        : baseUrl;
  }

  public void open() {
    driver.get(getBaseUrl() + getPath());

  }

  public void open(String... data) {
    String pathTemplate = getPathTemplate();
    if (pathTemplate.isEmpty()) {
      throw new RuntimeException("UrlTemplate is absent on page class");
    }
    for (int i = 0; i < data.length; i++) {
      pathTemplate = pathTemplate.replace("$" + (i + 1), data[i]);
    }
    driver.get(getBaseUrl() + pathTemplate);

  }
}

