package extensions;

import com.google.inject.Guice;
import com.microsoft.playwright.*;
import modules.GuiceComponentModule;
import modules.GuicePagesModule;
import org.junit.jupiter.api.extension.*;
import java.io.File;

public class UIExtension implements BeforeEachCallback, BeforeAllCallback, AfterEachCallback, AfterAllCallback {

  private Playwright playwright;
  private Browser browser;
  private BrowserContext browserContext;
  private Page page;

  @Override
  public void beforeEach(ExtensionContext context) throws Exception {
    this.browserContext = browser.newContext();
    this.browserContext.tracing().start(new Tracing.StartOptions().setSnapshots(true).setSources(true).setScreenshots(true));
    this.page = browserContext.newPage();

    Guice.createInjector(new GuicePagesModule(page), new GuiceComponentModule(page)).injectMembers(context.getTestInstance().get());

  }

  @Override
  public void afterAll(ExtensionContext context) throws Exception {
    this.playwright.close();

  }

  @Override
  public void afterEach(ExtensionContext context) throws Exception {
    this.browserContext.tracing().stop(new Tracing.StopOptions().setPath(new File("./trace.zip").toPath()));
    page.close();
    browserContext.close();
  }


  @Override
  public void beforeAll(ExtensionContext context) throws Exception {
    this.playwright = Playwright.create(new Playwright.CreateOptions());
    this.browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
  }
}
