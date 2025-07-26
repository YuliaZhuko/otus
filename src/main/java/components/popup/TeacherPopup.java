package components.popup;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import abscommon.AbsCommon;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class TeacherPopup extends AbsCommon implements IPopup<TeacherPopup> {

  Locator popup = page.locator("div.swiper-slide-active > div> h3");
  String initialText;
  private String teacherName = "Алексей Железной";

  public TeacherPopup(Page page) {
    super(page);
  }

  @Override
  public TeacherPopup popupShouldNotBeVisible() {
    popup.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.HIDDEN));
    return this;
  }

  @Override
  public TeacherPopup popupShouldBeVisible() {
    //popup.waitFor(); // дождаться появления
    assertThat(popup).containsText(teacherName); // проверка появления
    return this;
  }

  public TeacherPopup clickLeftButton() {
    initialText = popup.textContent().trim();
    Locator svgLocator = page.locator("#__PORTAL__ button div > svg > path").first();
    svgLocator.click();
    page.waitForFunction(
        "([initialText, selector]) => { "
            + "const el = document.querySelector(selector); "
            + "return el && el.textContent.trim() !== initialText;"
            + "}",
        new Object[]{initialText, "div.swiper-slide-active > div > h3"}

    );

    return this;
  }

  public TeacherPopup checkTeacherName() {
    // Получение нового текста
    String newText = popup.textContent();

    // Проверка, что текст действительно изменился
    assertNotEquals(initialText, newText, "Имя преподавателя не изменилось после нажатия кнопки");
    return this;

  }

  public TeacherPopup clickWrighttButton() throws InterruptedException {
    initialText = popup.textContent().trim();

    Locator rightButton = page.locator("#__PORTAL__ button").last();
    rightButton.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    Thread.sleep(2000);
    rightButton.click();

    page.waitForFunction(
        "([initialText, selector]) => { "
            + "const el = document.querySelector(selector); "
            + "return el && el.textContent.trim() !== initialText;"
            + "}",
        new Object[]{initialText, "div.swiper-slide-active > div > h3"} //
    );

    return this;
  }
}