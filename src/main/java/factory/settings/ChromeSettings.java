package factory.settings;

import org.openqa.selenium.chrome.ChromeOptions;


public class ChromeSettings implements IBrowserSettings {

  @Override
  public ChromeOptions setting() {
    ChromeOptions chromeOptions = new ChromeOptions();
    chromeOptions.addArguments("--start-fullscreen");
    chromeOptions.addArguments("--no-sandbox");
    chromeOptions.addArguments("--disable-dev-shm-usage");
    chromeOptions.setCapability("enableVideo", true);
    return chromeOptions;
  }
}
