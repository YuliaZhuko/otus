package components;

import annotations.Component;
import common.AbsCommon;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class AbsBaseBlock extends AbsCommon {


  public AbsBaseBlock(WebDriver driver) {
    super(driver);
  }

  private WebElement findComponenElement() {
    By locator = fetchComponentLocator();
    return driver.findElement(locator);
  }

  public void waitForComponentVisibility() {
    try {
      waiters.waitElementShouldBeVisibility(findComponenElement());
    } catch (Exception e) {
      throw new RuntimeException("Failed to wait component to be visible:" + e.getMessage(), e);
    }
  }

  private By fetchComponentLocator() {
    String componentLocator = annotationUtils
        .getAnnotationInstanse(this.getClass(), Component.class)
        .value();
    String[] locatorParts = componentLocator.split(":");
    String locatorType = locatorParts[0];
    String locatorValue = locatorParts[1];
    return switch (locatorType) {
      case "css" -> By.cssSelector(locatorValue);
      case "xpath" -> By.xpath(locatorValue);
      case "id" -> By.id(locatorValue);
      default -> throw new IllegalArgumentException("Bad locator" + locatorType);
    };
  }

  private String getComponentLocator() {
    Class clazz = getClass();
    if (clazz.isAnnotationPresent(Component.class)) {
      Component component = (Component) (clazz.getDeclaredAnnotation(Component.class));
      return component.value();
    }
    return null;
  }

  private By getByAnalizator() {
    String[] componentLocatorStr = getComponentLocator().split(":");
    String componentStrategy = componentLocatorStr[0];
    String componentLocatorValue = componentLocatorStr[1];
    switch (componentStrategy) {
      case "css": {
        return By.cssSelector(componentLocatorValue);
      }
      case "xpath": {
        return By.xpath(componentLocatorValue);
      }
    }
    return null;
  }

  public WebElement getComponentEntry() {
    return $(getByAnalizator());
  }

}
