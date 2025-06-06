package pages;

import annotations.Path;
import com.google.inject.Inject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import scope.ScenScoped;
import java.util.List;

@Path("/")
public class MainPage extends AbsBasePage {

  @FindBy(xpath = "//section[1]/div[1]/div[2]/div/div/div")
  private List<WebElement> categoryList;

  @Inject
  public MainPage(ScenScoped scenScoped) {
    super(scenScoped.getDriver());
  }

  public boolean isCategorySelected(WebElement element) {
    return getElementAttribute(element).equals("true");
  }

  public WebElement getSelectedCategoryByName(String categoryName) {

    return categoryList.stream()
        .filter(category -> normalize(category.getText()).equalsIgnoreCase(normalize(categoryName)))
        .findFirst()
        .orElseThrow(() -> new RuntimeException(categoryName + " category not found."));
  }

  private String normalize(String text) {
    return text.replaceAll("\\s+", " ") // заменяем множественные пробелы на один
        .replace('\u00A0', ' ')   // заменяем неразрывные пробелы (если есть)
        .trim();                  // обрезаем по краям
  }
}
