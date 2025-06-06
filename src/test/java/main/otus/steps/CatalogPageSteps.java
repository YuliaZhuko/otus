package main.steps;

import com.google.inject.Inject;


import data.CourcesData;
import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.Пусть;
import io.cucumber.java.ru.Тогда;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.CatalogPage;

import java.util.List;

public class CatalogPageSteps {

  @Inject
  private CatalogPage catalogPage;

 @Пусть("Открыта страница каталога курсов")
  public void  openCatalogPage(){
  catalogPage.open();
  }

  @Пусть("Найти курс по названию")
  public void findCourseByName(){
    catalogPage.findCourceByName(CourcesData.Python_Developer);
  }
  @Если("Если Кликнуть по плитке курса")
  public void clickCourse(){
    catalogPage.clickCourse(CourcesData.Python_Developer);
  }

  @Тогда("Страница курса успешно открыта")
  public void checkCoursePageTitle(){
    catalogPage.checkCoursePageTitle(CourcesData.Python_Developer);
  }

}
