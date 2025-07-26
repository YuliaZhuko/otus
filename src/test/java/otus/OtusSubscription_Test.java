package otus;

import com.google.inject.Inject;
import extensions.UIExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.OtusSubscription;

@ExtendWith(UIExtension.class)
public class OtusSubscription_Test {
  @Inject
  private OtusSubscription otusSubscription;

  @Test
  public void checkSubscriptions() {
    otusSubscription
        .open()
        .checkSubscriptionTypes()
        .clickButtonDetailsAndCheckContent()
        .ckickCollapseBunnonAndCheckDescription()
        .clickBuyButtonAndCheckPayPage();
  }
}
