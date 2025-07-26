package modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.microsoft.playwright.Page;
import components.popup.PaymentPopup;
import components.popup.TeacherPopup;
import pages.OtusSubscription;

public class GuiceComponentModule extends AbstractModule {

  private Page page;

  public GuiceComponentModule(Page page) {
    this.page = page;
  }

  @Provides
  @Singleton
  public TeacherPopup getTeacherPopup() {
    return new TeacherPopup(page);
  }

  @Provides
  @Singleton
  public OtusSubscription getOtusSubscription() {
    return new OtusSubscription(page);
  }

  @Provides
  @Singleton
  public PaymentPopup getPaymentPopup() {
    return new PaymentPopup(page);
  }

}
