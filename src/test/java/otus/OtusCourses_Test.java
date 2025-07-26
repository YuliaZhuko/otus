package otus;

import com.google.inject.Inject;
import extensions.UIExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.OtusCoursesPage;

@ExtendWith(UIExtension.class)
public class OtusCourses_Test {

  @Inject
  private OtusCoursesPage otusCoursesPage;


  @Test
  public void checkFilters() {
    otusCoursesPage
        .open()
        .checkCheckBoxes()
        .selectDurationCourses()
        .checkCourseDuration()
        .checkDirectionArchitectureAndCheckTitlesChange();
  }
}
