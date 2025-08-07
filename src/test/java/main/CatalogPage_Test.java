package main;

import com.google.inject.Inject;
import data.CourcesData;
import extensions.UIExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.CatalogPage;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@ExtendWith(UIExtension.class)
public class CatalogPage_Test {

  @Inject
  private CatalogPage catalogPage;


  @Test
  @DisplayName("Open course and check title")
  public void checkCourceName() {
    catalogPage.open("catalog", "courses");
    catalogPage.findCourceByName(CourcesData.SRE_practice);
    catalogPage.clickCourse(CourcesData.SRE_practice);
    catalogPage.checkCoursePageTitle(CourcesData.SRE_practice);
  }


  @Test
  @DisplayName("Open catalog courses page and check earliest and latest course dates")
  public void checkCourseDateAndName() throws IOException {
    catalogPage.open("catalog", "courses");
    Map<String, String> coursesMap = catalogPage.findDataOnPage();
    Map<String, String> earliestCourses = catalogPage.findCoursesWithEarliestDate(coursesMap);
    Map<String, String> latestCourses = catalogPage.findCoursesWithLatestDate(coursesMap);

    catalogPage.assertCoursesMatch(
        List.of(
            CourcesData.SRE_practice
        ),
        earliestCourses
    );

    catalogPage.assertCoursesMatch(
        List.of(
            CourcesData.Design_of_networks_TSOD,
            CourcesData.Android_Developer,
            CourcesData.Android_Developer_Basic,
            CourcesData.Golang_Developer_Professional
        ),
        latestCourses
    );
  }


}
