package main;

import com.google.inject.Inject;
import data.CourcesData;
import extensions.UIExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import pages.CatalogPage;
import java.util.List;
import java.util.Map;

@ExtendWith(UIExtension.class)
public class CatalogPage_Test {

  @Inject
  private WebDriver driver;

  @Inject
  private CatalogPage catalogPage;


  @Test
  @DisplayName("Open course and check title")
  public void checkCourceName() {
    catalogPage.open("catalog", "courses");
    catalogPage.findCourceByName(CourcesData.Python_Developer);
    catalogPage.clickCourse(CourcesData.Python_Developer);
    catalogPage.checkCoursePageTitle(CourcesData.Python_Developer);
  }


  @Test
  @DisplayName("Open catalog courses page and check earliest and latest course dates")
  public void checkCourseDateAndName() {
    catalogPage.open("catalog", "courses");
    Map<String, String> coursesMap = catalogPage.findDataOnPage(driver.getPageSource());
    Map<String, String> earliestCourses = catalogPage.findCoursesWithEarliestDate(coursesMap);
    Map<String, String> latestCourses = catalogPage.findCoursesWithLatestDate(coursesMap);

    catalogPage.assertCoursesMatch(
        List.of(
            CourcesData.Load_testing
        ),
        earliestCourses
    );

    catalogPage.assertCoursesMatch(
        List.of(
            CourcesData.Golang_Developer_Professional,
            CourcesData.React_js_Developer
        ),
        latestCourses
    );
  }


}
