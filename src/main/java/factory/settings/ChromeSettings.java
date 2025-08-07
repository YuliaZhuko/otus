package factory.settings;

import org.openqa.selenium.chrome.ChromeOptions;


public class ChromeSettings implements IBrowserSettings {

  @Override
  public ChromeOptions setting() {
    ChromeOptions chromeOptions = new ChromeOptions();
    chromeOptions.setBinary("/usr/bin/chromium-browser");
    chromeOptions.addArguments("--start-fullscreen");
    return chromeOptions;
  }
}
