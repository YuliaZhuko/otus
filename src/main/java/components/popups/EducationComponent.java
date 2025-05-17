package components.popups;

import annotations.Component;
import components.AbsBaseBlock;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

@Component("xpath://nav/div[3]/div")
public class EducationComponent extends AbsBaseBlock {

  public EducationComponent(WebDriver driver) {
    super(driver);
  }

  @FindBy(xpath = "//div[3]/div/div/div[1]/div/div/a")
  private List<WebElement> categoryList;

  public String clickOnRandomCategory() {
    waitForComponentVisibility();
    int randomIndex = (int) (Math.random() * categoryList.size());
    String categoryName = getText(categoryList.get(randomIndex)).split("\\(")[0];
    click(categoryList.get(randomIndex));
    return categoryName;
  }

}
