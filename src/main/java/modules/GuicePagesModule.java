package modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.microsoft.playwright.Page;
import pages.OtusCoursesPage;
import pages.OtusCustomCoursesPage;
import pages.OtusLessonsPage;
import pages.OtusUslugiKompaniyamPage;

public class GuicePagesModule extends AbstractModule {

  private Page page;


  public GuicePagesModule(Page page) {
    this.page = page;
  }


  @Provides
  @Singleton
  public OtusLessonsPage getOtusLessonsPage() {
    return new OtusLessonsPage(page);
  }

  @Provides
  @Singleton
  public OtusCoursesPage getOtusCoursesPage() {
    return new OtusCoursesPage(page);
  }

  @Provides
  @Singleton
  public OtusUslugiKompaniyamPage getOtusUslugiKompaniyamPage(OtusCustomCoursesPage otusCustomCoursesPage) {
    return new OtusUslugiKompaniyamPage(page, otusCustomCoursesPage);
  }


  @Provides
  @Singleton
  public OtusCustomCoursesPage getOtusCustonCoursesPage() {

    return new OtusCustomCoursesPage(page);
  }


}

