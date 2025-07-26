package pages;

import anotations.Hostname;
import anotations.Path;
import com.google.inject.Inject;
import com.microsoft.playwright.Page;

@Hostname("https://otus.ru")
@Path("/uslugi-kompaniyam")
public class OtusUslugiKompaniyamPage extends AbsBasePage<OtusUslugiKompaniyamPage> {


  private final OtusCustomCoursesPage otusCustomCoursesPage;

  @Inject
  public OtusUslugiKompaniyamPage(Page page, OtusCustomCoursesPage otusCustomCoursesPage) {
    super(page);
    this.otusCustomCoursesPage = otusCustomCoursesPage;
  }

  public OtusCustomCoursesPage clickDetailsButton() {

    Page popup = page.waitForPopup(() -> {
      page.click("button:has-text(\"Подробнее\")"); // кнопка "Подробнее"
    });

    // Получаем URL новой вкладки
    String popupUrl = popup.url();
    System.out.println("Popup URL: " + popupUrl);
    return new OtusCustomCoursesPage(popup);
  }


}
