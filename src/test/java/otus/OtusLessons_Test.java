package otus;

import com.google.inject.Inject;
import extensions.UIExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.OtusLessonsPage;

@ExtendWith(UIExtension.class)
public class OtusLessons_Test {

  @Inject
  private OtusLessonsPage otusLessonsPage;

  @Test
  public void dragAndDropTeachers() throws InterruptedException {
    otusLessonsPage
        .open()
        .scrollToTeacher()
        .swipeTeacherAndCheckChange()
        .clickTeacherBox()
        .popupShouldBeVisible()
        .clickLeftButton()
        .checkTeacherName()
        .clickWrighttButton()
        .checkTeacherName();

  }

}
