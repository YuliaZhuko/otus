package factory.settings;

import org.openqa.selenium.chrome.ChromeOptions;
import java.util.HashMap;
import java.util.Map;


public class ChromeSettings implements IBrowserSettings {

  @Override
  public ChromeOptions setting() {
    ChromeOptions chromeOptions = new ChromeOptions();
    chromeOptions.addArguments("--start-fullscreen");
    chromeOptions.addArguments("--no-sandbox");
    chromeOptions.addArguments("--disable-dev-shm-usage");
    Map<String, Object> selenoidOptions = new HashMap<>();
    selenoidOptions.put("enableVideo", true);
    chromeOptions.setCapability("selenoid:options", selenoidOptions);
    return chromeOptions;
  }
}
