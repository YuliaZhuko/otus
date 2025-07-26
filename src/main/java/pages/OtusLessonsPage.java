package pages;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import anotations.Hostname;
import anotations.Path;
import com.google.inject.Inject;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.BoundingBox;
import components.popup.TeacherPopup;

@Hostname("https://otus.ru")
@Path("/lessons/clickhouse/")
public class OtusLessonsPage extends AbsBasePage<OtusLessonsPage> {

  Locator teacher2 = page.locator("text=Константин Трофимов");
  Locator teacher1 = page.locator("text=Алексей Железной");
  BoundingBox boxBefore;
  @Inject
  private TeacherPopup teacherPopup;


  public OtusLessonsPage(Page page) {
    super(page);
  }

  public OtusLessonsPage scrollToTeacher() {

    teacher1.scrollIntoViewIfNeeded();

    return this;
  }

  public OtusLessonsPage swipeTeacherAndCheckChange() {

    // 1 Берём имя активного преподавателя ДО свайпа
    Locator teacherBlock = page.locator("section > div > div.swiper-ui> div> div > div > div:nth-child(4)>p").first();
    String nameBefore = teacher1.innerText().trim();

    // 2 Кликаем по кнопке влево (стрелка)
    page.locator("section > div > div.swiper-ui> div> button> div > div > div > svg").first().click(); // правая стрелка
    page.waitForTimeout(2000); // короткая пауза на анимацию

    // 3 Берём имя ПОСЛЕ свайпа
    String nameAfter = teacherBlock.innerText().trim();

    // 4 Проверяем, что изменилось
    assertNotEquals(nameBefore, nameAfter, "Преподаватель не сменился после свайпа");
    System.out.println(nameBefore + "  nameBefore");
    System.out.println(nameAfter + "  nameAfter");
    return this;

  }

  public TeacherPopup clickTeacherBox() {
    teacher1.click();
    return teacherPopup;


  }


}

