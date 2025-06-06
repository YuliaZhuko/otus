package components.popups;

import annotations.Component;
import com.google.inject.Inject;
import components.AbsBaseBlock;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import scope.ScenScoped;
import java.util.List;

@Component("xpath://nav/div[3]/div")
public class EducationComponent extends AbsBaseBlock {

  @FindBy(xpath = "//div[3]/div/div/div[1]/div/div/a")
  private List<WebElement> categoryList;

  @Inject
  public EducationComponent(ScenScoped scenScoped) {
    super(scenScoped.getDriver());
  }

  public String clickOnRandomCategory() {
    waitForComponentVisibility();
    int randomIndex = (int) (Math.random() * categoryList.size());
    String categoryName = getText(categoryList.get(randomIndex)).split("\\(")[0];
    click(categoryList.get(randomIndex));
    return categoryName;
  }

}
