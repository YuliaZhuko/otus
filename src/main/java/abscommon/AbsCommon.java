package abscommon;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.microsoft.playwright.Page;
import modules.GuiceComponentModule;

public abstract class AbsCommon {

  protected Page page;

  public AbsCommon(Page page) {
    this.page = page;
    Guice.createInjector(new GuiceComponentModule(page)).injectMembers(this);
  }
}
