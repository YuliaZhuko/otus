package extensions;

import com.google.inject.Guice;
import com.google.inject.Injector;
import factory.WebDriverFactory;
import listeners.MouseListener;
import modules.GuiceComponentsModule;
import modules.GuicePagesModule;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;

import java.net.MalformedURLException;

public class UIExtension implements BeforeEachCallback, AfterEachCallback {


  private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

  @Override
  public void afterEach(ExtensionContext context) {
    WebDriver webDriver = DRIVER.get();
    if (webDriver != null) {
      webDriver.quit();
      DRIVER.remove();
    }
  }

  @Override
  public void beforeEach(ExtensionContext context) throws MalformedURLException {
    WebDriver webDriver = new WebDriverFactory().create();
    DRIVER.set(webDriver);
    Injector injector = Guice.createInjector(new GuicePagesModule(webDriver), new GuiceComponentsModule(webDriver));
    injector.injectMembers(context.getTestInstance().get());
  }
}
