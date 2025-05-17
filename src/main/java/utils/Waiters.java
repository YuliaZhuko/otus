package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class Waiters {

  private WebDriver driver;
  private final WebDriverWait webDriverWait;

  private static final int WAITERSTIMEOUT = Integer.parseInt(System.getProperty("timeout", "10"));

  public Waiters(WebDriver driver) {
    webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(WAITERSTIMEOUT));
    this.driver = driver;
  }

  public boolean waitForCondition(ExpectedCondition condition) {
    try {
      new WebDriverWait(driver, Duration.ofSeconds(WAITERSTIMEOUT)).until(condition);
      return true;
    } catch (TimeoutException ignore) {
      return false;
    }
  }

  public boolean waitElementShouldBePresent(By locator) {
    return waitForCondition(ExpectedConditions.presenceOfElementLocated(locator));
  }

  public boolean waitElementShouldBeVisibility(WebElement element) {
    return waitForCondition(ExpectedConditions.visibilityOf(element));
  }

  public void waitForElementShouldBeClicable(WebElement element) {
    webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
  }
}

