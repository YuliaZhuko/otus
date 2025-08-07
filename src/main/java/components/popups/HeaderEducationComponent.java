package components.popups;

import annotations.Component;
import components.AbsBaseBlock;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Component("xpath://*[@id='__next']/div[2]/div[3]")
public class HeaderEducationComponent extends AbsBaseBlock {

  public HeaderEducationComponent(WebDriver driver) {
    super(driver);
  }

  @FindBy(xpath = "//nav/div[2]/span")
  private WebElement educationField;

  public void moveToEducationField() {

    educationField.click();

  }
}
