package pages;

import anotations.Hostname;
import anotations.Path;
import com.google.inject.Inject;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;
import components.popup.PaymentPopup;
import data.OtusTypeSubscription;
import org.junit.jupiter.api.Assertions;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Hostname("https://otus.ru")
@Path("/subscription")
public class OtusSubscription extends AbsBasePage<OtusSubscription> {

  @Inject
  public PaymentPopup paymentPopup;

  public OtusSubscription(Page page) {
    super(page);
  }

  public OtusSubscription checkSubscriptionTypes() {
    // Получаем тексты из всех h4 в блоке #packages
    List<String> actualSubscriptions = page.locator("#packages h4")
        .allTextContents()
        .stream()
        .map(String::trim)
        .collect(Collectors.toList());

    // Получаем список отображаемых значений из енама
    List<String> expectedSubscriptions = OtusTypeSubscription.getAllDisplayNames();

    // Сравниваем (можно с сортировкой, если порядок не важен)
    boolean match = new HashSet<>(actualSubscriptions).equals(new HashSet<>(expectedSubscriptions));

    System.out.println("Actual from page: " + actualSubscriptions);
    System.out.println("Expected from enum: " + expectedSubscriptions);
    System.out.println("Do they match? " + match);
    return this;
  }

  public OtusSubscription clickButtonDetailsAndCheckContent() {

    Locator buttonDetails = page.locator("button:has-text(\"Подробнее\")").first();
    buttonDetails.waitFor();
    page.waitForTimeout(1000);
    buttonDetails.click();
    // Ждём, пока появится кнопка "Свернуть"
    Locator collapseButton = page.locator("//button[normalize-space(text())='Свернуть']");

    try {
      collapseButton.waitFor(new Locator.WaitForOptions()
          .setState(WaitForSelectorState.VISIBLE)
          .setTimeout(10_000));
      System.out.println("✅ Кнопка изменилась на 'Свернуть'");
    } catch (PlaywrightException e) {
      System.out.println(" Кнопка 'Свернуть' не появилась. Доступные тексты кнопок:");
      List<String> allButtons = page.locator("button").allTextContents();
      allButtons.forEach(btn -> System.out.println("• " + btn));
      throw new RuntimeException("Тест не прошёл: кнопка 'Свернуть' не найдена", e);
    }
    //Описание дополнительное расположено в блоке который видим но имеет нулевую высоту.
    // Локатор данного блока ниже, если высота этого блока не нулевая - значит он развернут
    Locator block = page.locator("xpath=//*[@id=\"packages\"]/div/div[2]/div[2]/div/div[2]/div");

    String height = block.evaluate("el => window.getComputedStyle(el).height").toString();

    System.out.println("Высота блока: " + height);

    Assertions.assertNotEquals("0px", height, "Высота развернутого блока не должна быть равна 0px");

    return this;
  }

  public OtusSubscription ckickCollapseBunnonAndCheckDescription() {
    Locator buttonDCollapse = page.locator("button:has-text(\"Свернуть\")");
    buttonDCollapse.waitFor();
    buttonDCollapse.click();
    page.waitForTimeout(1000);
    //Описание дополнительное расположено в блоке который видим и имеет не нулевую высоту.
    // Локатор данного блока ниже, если высота этого блока не нулевая - значит он развернут
    Locator block = page.locator("xpath=//*[@id=\"packages\"]/div/div[2]/div[2]/div/div[2]/div");

    String height = block.evaluate("el => window.getComputedStyle(el).height").toString();

    System.out.println("Высота блока: " + height);

    Assertions.assertEquals("0px", height, "Высота свернутого блока должна быть равна 0px");

    return this;
  }

  public PaymentPopup clickBuyButtonAndCheckPayPage() {

    Locator buyButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Купить")).nth(1);
    buyButton.click();
    int count = buyButton.count();
    System.out.println("Найдено кнопок 'Купить': " + count);
    new PaymentPopup(page).popupShouldBeVisible();
    return new PaymentPopup(page);
  }

}


