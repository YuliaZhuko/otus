package main;


import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.inject.Inject;
import components.popups.EducationComponent;
import components.popups.HeaderEducationComponent;
import extensions.UIExtension;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import pages.CatalogPage;
import pages.MainPage;


@ExtendWith(UIExtension.class)
public class MainPage_Test {
  @Inject
  private WebDriver driver;

  @Inject
  private MainPage mainPage;

  @Inject
  private CatalogPage catalogPage;

  @Inject
  EducationComponent educationComponent;

  @Inject
  HeaderEducationComponent headerEducationComponent;


  @Test
  @DisplayName("Open education and find random course category")
  public void checkOpeningRightCategory() {
    mainPage.open();
    headerEducationComponent.moveToEducationField();
    String categoryName = educationComponent.clickOnRandomCategory();
    assertTrue(mainPage.isCategorySelected(mainPage.getSelectedCategoryByName(categoryName)),
        categoryName + " category is selected");
  }


}
