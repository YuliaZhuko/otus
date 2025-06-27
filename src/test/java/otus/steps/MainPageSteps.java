package steps;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.inject.Inject;
import components.popups.EducationComponent;
import components.popups.HeaderEducationComponent;
import io.cucumber.java.ru.Пусть;
import io.cucumber.java.ru.Тогда;
import org.openqa.selenium.WebDriver;
import pages.MainPage;
import scope.ScenScoped;

public class MainPageSteps {

  @Inject
  MainPage mainPage;


  @Inject
  ScenScoped scenScoped;
  WebDriver driver = scenScoped.getDriver();

  @Inject
  EducationComponent educationComponent;
  @Inject
  HeaderEducationComponent headerEducationComponent;

  @Пусть("Открыта главная страница")
  public void openMainPage() {
    mainPage.open();
  }
  @Пусть("Открыть меню Обучение и выбрать случайную категорию курсов")
  public void clickRandomCategory(){
    headerEducationComponent.moveToEducationField();

  }
  @Тогда("Проверить, что открыт каталог курсов верной категории")
  public void checkCategory(){
    String categoryName = educationComponent.clickOnRandomCategory();
    assertTrue(mainPage.isCategorySelected(mainPage.getSelectedCategoryByName(categoryName)),
        categoryName + " category is selected");
  }

}
