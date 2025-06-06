package main.otus.steps;

import com.google.inject.Inject;
import data.CourcesData;
import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.Пусть;
import io.cucumber.java.ru.Тогда;
import org.openqa.selenium.WebDriver;
import pages.CatalogPage;
import scope.ScenScoped;
import java.util.List;
import java.util.Map;

public class CatalogPageSteps {

  @Inject
  CatalogPage catalogPage;


  @Inject
  ScenScoped scenScoped;

  //WebDriver driver = scenScoped.getDriver();

  @Пусть("Открыта страница каталога курсов")
  public void openCatalogPage() {
    catalogPage.open("catalog", "courses");
  }

  @Пусть("Найти курс по названию")
  public void findCourseByName() {
    catalogPage.findCourceByName(CourcesData.Python_Developer);
  }

  @Пусть("Собрать со страницы все курсы и их даты")
  public void searchData() {
    Map<String, String> coursesMap = catalogPage.findDataOnPage(scenScoped.getDriver().getPageSource());
  }

  @Если("Кликнуть по плитке курса")
  public void clickCourse() {
    catalogPage.clickCourse(CourcesData.Python_Developer);
  }

  @Тогда("Страница курса успешно открыта")
  public void checkCoursePageTitle() {
    catalogPage.checkCoursePageTitle(CourcesData.Python_Developer);
  }

  @Тогда("Найти среди курсов самые ранние и проверить совпадают ли они с ожидаемыми")
  public void searchEarliest() {
    Map<String, String> coursesMap = catalogPage.findDataOnPage(scenScoped.getDriver().getPageSource());
    Map<String, String> earliestCourses = catalogPage.findCoursesWithEarliestDate(coursesMap);
    catalogPage.assertCoursesMatch(
        List.of(
            CourcesData.Python_QA_Engineer
        ),
        earliestCourses
    );
  }

  @Тогда("Найти среди курсов самые поздние и проверить совпадают ли они с ожидаемыми")
  public void searchLatest() {
    Map<String, String> coursesMap = catalogPage.findDataOnPage(scenScoped.getDriver().getPageSource());
    Map<String, String> latestCourses = catalogPage.findCoursesWithLatestDate(coursesMap);
    catalogPage.assertCoursesMatch(
        List.of(
            CourcesData.Golang_Developer_Professional,
            CourcesData.React_js_Developer,
            CourcesData.Enterprise_Architect
        ),
        latestCourses
    );
  }
}
