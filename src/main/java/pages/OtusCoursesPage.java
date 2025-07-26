package pages;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import anotations.Hostname;
import anotations.Path;
import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.junit.jupiter.api.Assertions;
import java.util.List;


@Hostname("https://otus.ru")
@Path("/catalog/courses/")
public class OtusCoursesPage extends AbsBasePage<OtusCoursesPage> {

  Locator allDirectionsCheckBox = page.locator("text=Все направления");
  Locator anyLevelOfDifficulty = page.locator("text=Любой уровень");
  public OtusCoursesPage(Page page) {
    super(page);
  }

  public OtusCoursesPage checkCheckBoxes() {

    assertTrue(allDirectionsCheckBox.isChecked(), "Чекбокс \"Все направления\" должен быть включен");
    assertTrue(anyLevelOfDifficulty.isChecked(), "Чекбокс \"Любой уровень\" должен быть включен");
    return this;
  }

  public OtusCoursesPage selectDurationCourses() {
    Locator sliders = page.locator("[role='slider']");
    ElementHandle leftSliderHandle = sliders.nth(0).elementHandle();
    ElementHandle rightSliderHandle = sliders.nth(1).elementHandle();

    // Установим значения через JS
    page.evaluate("slider => slider.setAttribute('aria-valuenow', '3')", leftSliderHandle);
    page.evaluate("slider => slider.setAttribute('aria-valuenow', '10')", rightSliderHandle);

    // Проверка
    assertEquals("3", sliders.nth(0).getAttribute("aria-valuenow"));
    assertEquals("10", sliders.nth(1).getAttribute("aria-valuenow"));

    return this;
  }

  public OtusCoursesPage checkCourseDuration() {

    List<String> durationsText = page.locator("div.course-list div.course-item span.duration").allTextContents();

    for (String duration : durationsText) {
      int months = parseMonths(duration);
      System.out.println("Course duration: " + months + " months");
      assertTrue(months >= 3 && months <= 10, "Duration is out of expected range: " + months);
    }
    return this;
  }
  private int parseMonths(String text) {
    // Извлечь число из текста
    String numberOnly = text.replaceAll("[^0-9]", "");
    return Integer.parseInt(numberOnly);
  }

  public OtusCoursesPage checkDirectionArchitectureAndCheckTitlesChange() {
    // Локатор на секцию с каталогом (по тегу section и части класса, например)
    Locator section = page.locator("section[class*=riKpM]");

    // Внутри секции все ссылки на курсы (карточки)
    Locator courseCards = section.locator("a[href^='/lessons/']");


    // Для каждого курса берём текст из h6 (название курса)
    List<String> courseTitles = courseCards.locator("h6 > div").allTextContents();

    // Вывод в консоль (для проверки)
    courseTitles.forEach(System.out::println);

    // Пример assert, чтобы проверить что названия не пустые
    Assertions.assertFalse(courseTitles.isEmpty(), "Список курсов не должен быть пустым");
    courseTitles.forEach(title -> Assertions.assertFalse(title.trim().isEmpty(), "Название курса не должно быть пустым"));


    // Находим label по тексту "Архитектура"
    Locator label = page.locator("label", new Page.LocatorOptions().setHasText("Архитектура"));
    Assertions.assertTrue(label.isVisible(), "Label с текстом 'Архитектура' должен быть видим");

    // Получаем атрибут for — это id чекбокса
    String checkboxId = label.getAttribute("for");
    Assertions.assertNotNull(checkboxId, "Атрибут 'for' должен быть у label");

    // Находим чекбокс по атрибуту id
    Locator checkbox = page.locator("input[type=checkbox][id='" + checkboxId + "']");
    Assertions.assertTrue(checkbox.isVisible(), "Чекбокс должен быть видим");

    // Кликаем по чекбоксу
    checkbox.click();

    // Проверяем, что чекбокс выбран
    Assertions.assertTrue(checkbox.isChecked(), "Чекбокс должен быть выбран после клика");
    page.waitForTimeout(1000);
    List<String> coursesAfter = page.locator("h6 > div").allInnerTexts();

    // Проверка: курсы должны измениться
    Assertions.assertNotEquals(courseTitles, coursesAfter, "Курсы не изменились после применения фильтра");
    return this;

  }


}

