package components.popups;

import annotations.Component;
import com.google.inject.Inject;
import components.AbsBaseBlock;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import scope.ScenScoped;


@Component("xpath://*[@id=\"__next\"]/div[1]/div[2]/div/nav/div[2]/span")
public class HeaderEducationComponent extends AbsBaseBlock {

  @FindBy(xpath = "//nav/div[2]/span")
  private WebElement educationField;

  @Inject
  public HeaderEducationComponent(ScenScoped scenScoped) {
    super(scenScoped.getDriver());
  }

  public void moveToEducationField() {
    waitForComponentVisibility();
    actionUtils.moveToElement(educationField);
  }
}
