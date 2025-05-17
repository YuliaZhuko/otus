package modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import components.popups.EducationComponent;
import components.popups.HeaderEducationComponent;
import org.openqa.selenium.WebDriver;

public class GuiceComponentsModule extends AbstractModule {

  private WebDriver driver = null;

  public GuiceComponentsModule(WebDriver driver) {
    this.driver = driver;
  }

  @Provides
  @Singleton
  public HeaderEducationComponent getHeaderEducationComponent() {
    return new HeaderEducationComponent(driver);
  }

  @Provides
  @Singleton
  public EducationComponent getEducationComponent() {
    return new EducationComponent(driver);
  }
}
