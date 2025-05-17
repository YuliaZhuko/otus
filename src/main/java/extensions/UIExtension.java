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

public class UIExtension implements BeforeEachCallback, AfterEachCallback {

  private Injector injector = null;

  @Override
  public void afterEach(ExtensionContext context) {
    WebDriver driver = injector.getInstance(WebDriver.class);
    if (driver != null) {
      driver.quit();
    }
  }

  @Override
  public void beforeEach(ExtensionContext context) {
    WebDriver baseDriver = new WebDriverFactory().create();

    // оборачиваем в listener
    WebDriverListener listener = new MouseListener(baseDriver);
    WebDriver decoratedDriver = new EventFiringDecorator(listener).decorate(baseDriver);

    // передаём уже обёрнутый драйвер
    injector = Guice.createInjector(
        new GuicePagesModule(decoratedDriver),
        new GuiceComponentsModule(decoratedDriver)
    );

    injector.injectMembers(context.getTestInstance().get());
  }
}
