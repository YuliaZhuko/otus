package scope;

import factory.WebDriverFactory;
import io.cucumber.guice.ScenarioScoped;
import org.openqa.selenium.WebDriver;

@ScenarioScoped
public class ScenScoped {

  private WebDriver driver = new WebDriverFactory().create();

  public WebDriver getDriver() {
    return this.driver;
  }

}
