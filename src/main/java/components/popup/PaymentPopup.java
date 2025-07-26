package components.popup;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import abscommon.AbsCommon;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class PaymentPopup extends AbsCommon implements IPopup<PaymentPopup> {
  String paymentPage = "Подписка на доступ к курсам";
  Locator popup = page.locator("div", new Page.LocatorOptions()
      .setHasText("Подписка на доступ к курсам")).nth(6);

  public PaymentPopup(Page page) {
    super(page);
  }

  @Override
  public PaymentPopup popupShouldNotBeVisible() {
    popup.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.HIDDEN));
    return this;

  }

  @Override
  public PaymentPopup popupShouldBeVisible() {
    int count = popup.count();
    System.out.println("Найдено кнопок 'Подписка на доступ к курсам': " + count);
    popup.waitFor();// дождаться появления
    assertThat(popup).containsText(paymentPage); // проверка появления
    return this;
  }
}
