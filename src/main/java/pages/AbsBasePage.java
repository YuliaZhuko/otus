package pages;

import abscommon.AbsCommon;
import anotations.Hostname;
import anotations.Path;
import anotations.PathTemplate;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitUntilState;

public abstract class AbsBasePage<T> extends AbsCommon {


  private String baseUrl = System.getProperty("base.url", "https://news.mail.ru");

  public AbsBasePage(Page page) {
    super(page);
  }

  private String getHostname() {
    Class<T> clazz = (Class<T>) getClass();
    if (clazz.isAnnotationPresent(Hostname.class)) {
      Hostname hostname = clazz.getDeclaredAnnotation(Hostname.class);
      return hostname.value();
    }
    return "";
  }

  private String getPath() {
    Class<T> clazz = (Class<T>) getClass();
    if (clazz.isAnnotationPresent(Path.class)) {
      Path path = clazz.getDeclaredAnnotation(Path.class);
      return path.value();
    }
    return "";
  }

  private String getPathTemplate() {
    Class clazz = this.getClass();
    if (clazz.isAnnotationPresent(PathTemplate.class)) {
      PathTemplate pathTemplate = (PathTemplate) clazz.getDeclaredAnnotation(PathTemplate.class);
      return pathTemplate.value();
    }
    return "";
  }

  public T open() {
    String hostname = getHostname();
    if (!hostname.isEmpty()) {
      page.navigate(hostname + getPath(),
          new Page.NavigateOptions()
              .setTimeout(60000)  // 60 секунд
              .setWaitUntil(WaitUntilState.DOMCONTENTLOADED));
      return (T) this;
    }
    page.navigate(this.baseUrl + getPath());
    return (T) this;
  }

  public T open(String... data) {
    String pathTemplate = getPathTemplate();
    for (int i = 0; i < data.length; i++) {
      pathTemplate = pathTemplate.replace(String.format("{%d}", i + 1), data[i]);
    }
    page.navigate(this.baseUrl + pathTemplate);
    return (T) this;
  }

  public T pageHeaderShouldBeSameAs(String header) {
    page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName(header)).isVisible();
    return (T) this;
  }

}
