package factory;

import exceptions.BrowserNotSupportedException;
import factory.settings.ChromeSettings;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverFactory {

  private String browserName = System.getProperty("browser");

  public WebDriver create() {
    switch (browserName) {
      case "chrome": {
        return new ChromeDriver(new ChromeSettings().setting());
      }
    }
    throw new BrowserNotSupportedException(browserName);
  }
}
