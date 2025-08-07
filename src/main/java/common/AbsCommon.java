package common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utils.ActionUtils;
import utils.AnnotationUtils;
import utils.Waiters;
import java.util.List;

public class AbsCommon {

  protected WebDriver driver;

  protected Waiters waiters;

  protected AnnotationUtils annotationUtils;


  public AbsCommon(WebDriver driver) {
    this.driver = driver;
    this.annotationUtils = new AnnotationUtils();
    this.waiters = new Waiters(driver);
    PageFactory.initElements(driver, this);

  }

  public WebElement $(By locator) {
    return driver.findElement(locator);
  }

  public List<WebElement> $$(By locator) {
    return driver.findElements(locator);
  }

  public String getText(WebElement element) {
    waiters.waitElementShouldBeVisibility(element);
    return element.getText();
  }

  protected void click(WebElement element) {
    waiters.waitForElementShouldBeClicable(element);
    element.click();
  }

  protected String getElementAttribute(WebElement element) {
    return element.getDomAttribute("value");
  }
}
