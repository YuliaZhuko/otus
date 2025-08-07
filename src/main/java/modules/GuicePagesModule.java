package modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import org.openqa.selenium.WebDriver;
import pages.CatalogPage;
import pages.MainPage;

public class GuicePagesModule extends AbstractModule {

  private final WebDriver driver;

  public GuicePagesModule(WebDriver driver) {
    this.driver = driver;
  }

  @Provides
  public WebDriver getDriver() {
    return driver;
  }

  @Provides
  @Singleton
  public MainPage getMainPage() {
    return new MainPage(driver);
  }

  @Provides
  @Singleton
  public CatalogPage getCatalogPage() {
    return new CatalogPage(driver);
  }

}
