package factory;

import exceptions.BrowserNotSupportedException;
import factory.settings.ChromeSettings;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverFactory {

  private String browserName = System.getProperty("browser");

  public WebDriver create() {
    if (browserName == null) {
      throw new IllegalStateException("System property 'browser' is not set. Please run tests with -Dbrowser=chrome");
    }

    switch (browserName) {
      case "chrome": {
        return new ChromeDriver(new ChromeSettings().setting());
      }
      default:
        throw new BrowserNotSupportedException(browserName);
    }
  }
}
