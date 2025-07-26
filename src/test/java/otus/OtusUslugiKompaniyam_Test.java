package otus;

import com.google.inject.Inject;
import extensions.UIExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.OtusUslugiKompaniyamPage;
import java.net.URISyntaxException;

@ExtendWith(UIExtension.class)
public class OtusUslugiKompaniyam_Test {
  @Inject
  private OtusUslugiKompaniyamPage otusUslugiKompaniyamPage;

  @Test
  public void checkDirectionsEducation() throws URISyntaxException {
    otusUslugiKompaniyamPage
        .open()
        .clickDetailsButton()
        .chekhPageForBusines()
        .checkVisibilityEducation()
        .clickRandomDirectionAndCheckDirection();

  }

}
