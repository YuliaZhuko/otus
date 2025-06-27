package otus.hooks;

import com.google.inject.Inject;
import io.cucumber.java.After;
import org.openqa.selenium.WebDriver;
import scope.ScenScoped;


public class Hooks {

  @Inject
  ScenScoped scenScoped;

  @After
  public void after() {
    if (scenScoped.getDriver() != null) {
      scenScoped.getDriver().quit();
    }
  }
}
