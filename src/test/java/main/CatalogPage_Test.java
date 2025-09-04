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
    catalogPage.findCourceByName(CourcesData.C_Developer_Professional);
    catalogPage.clickCourse(CourcesData.C_Developer_Professional);
    catalogPage.checkCoursePageTitle(CourcesData.C_Developer_Professional);
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
            CourcesData.NLP_Natural_Language_Processing,
            CourcesData.System_and_Business_Analyze,
            CourcesData.Pentest,
            CourcesData.C_Developer_Professional,
            CourcesData.Platform_Kubernetes,
            CourcesData.Rust_Developer_Professional,
            CourcesData.System_Analyst,
            CourcesData.System_Analyst_Advanced,
            CourcesData.System_Analyst_Basic,
            CourcesData.iOS_Developer_Professional
        ),
        earliestCourses
    );

    catalogPage.assertCoursesMatch(
        List.of(
            CourcesData.NLP_Natural_Language_Processing,
            CourcesData.System_and_Business_Analyze,
            CourcesData.Pentest,
            CourcesData.C_Developer_Professional,
            CourcesData.Platform_Kubernetes,
            CourcesData.Rust_Developer_Professional,
            CourcesData.System_Analyst,
            CourcesData.System_Analyst_Advanced,
            CourcesData.System_Analyst_Basic,
            CourcesData.iOS_Developer_Professional

        ),
        latestCourses
    );
  }


}
