package scope;

import factory.WebDriverFactory;
import io.cucumber.guice.ScenarioScoped;
import org.openqa.selenium.WebDriver;
import java.util.HashMap;
import java.util.Map;

@ScenarioScoped
public class ScenScoped {

  private WebDriver driver = new WebDriverFactory().create();

  public WebDriver getDriver() {
    return this.driver;
  }
  private final Map<String,Object> storeObject = new HashMap<>();

}
