package pages;


import annotations.Path;
import annotations.UrlTemplate;
import data.CourcesData;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import org.assertj.core.api.Assertions;

@Path("/")
@UrlTemplate("/$1/$2/")
public class CatalogPage extends AbsBasePage {
  String nameCourseLocator = "//h6/div[text()='%s']";
  String baseCoursePageTitle = "//h1";

  public CatalogPage(WebDriver driver) {
    super(driver);
  }


  public WebElement findCourceByName(CourcesData courcesData) {
    List<WebElement> coursesNames = $$(By.xpath("//h6/div"));
    return coursesNames.stream()
        .filter(course ->
            course.getText().replaceAll("\\s+", " ").trim()
                .equalsIgnoreCase(courcesData.getName().replaceAll("\\s+", " ").trim()))
        .findFirst()
        .orElseThrow(() ->
            new RuntimeException(String.format("Course by name %s не найден", courcesData.getName())));
  }


  public void clickCourse(CourcesData courcesData) {
    $(By.xpath(String.format(nameCourseLocator, courcesData.getName()))).click();
  }

  public void checkCoursePageTitle(CourcesData courcesData) {
    String pageTitle = $(By.xpath(String.format(baseCoursePageTitle))).getText();
    Assertions.assertThat(pageTitle).isEqualTo(courcesData.getName());

  }


  public Map<String, String> findDataOnPage(String html) {
    Document doc = Jsoup.parse(html);

    // Найти все карточки курсов
    Elements courseCards = doc.select("a.sc-zzdkm7-0"); // <-- класс карточки курса

    Map<String, String> courseMap = new LinkedHashMap<>();

    for (int i = 1; i < courseCards.size(); i++) {
      Element course = courseCards.get(i);

      Element titleEl = course.selectFirst("h6 div");
      Element dateEl = course.selectFirst(".sc-157icee-1 .sc-hrqzy3-1");

      if (titleEl != null && dateEl != null) {
        String title = titleEl.text().trim();
        String date = dateEl.text().trim();
        courseMap.put(title, date);
      }
    }

    // Отладочный вывод
    courseMap.forEach((key, value) ->
        System.out.println("Курс такой: " + key + ", Дата: " + value));
    System.out.println(courseMap.size() + " размер");
    return courseMap;
  }

  public void assertCoursesMatch(List<CourcesData> expectedCourses, Map<String, String> courseMap) {
    List<String> errors = new ArrayList<>();

    for (CourcesData expectedCourse : expectedCourses) {
      String expectedName = expectedCourse.getName();
      LocalDate expectedDate = expectedCourse.getStartDate();

      Optional<Map.Entry<String, String>> matchingEntry = courseMap.entrySet().stream()
          .filter(entry -> entry.getKey().equalsIgnoreCase(expectedName))
          .findFirst();

      if (matchingEntry.isEmpty()) {
        errors.add(" Курс с таким заголовком не найден: " + expectedName);
        continue;
      }

      String rawDate = matchingEntry.get().getValue();

      try {
        LocalDate actualDate = LocalDate.parse(rawDate);
        if (!actualDate.equals(expectedDate)) {
          errors.add(String.format(
              "Дата курса \"%s\" не совпадает: ожидалась %s, а получена %s",
              expectedName, expectedDate, actualDate
          ));
        }
      } catch (Exception e) {
        errors.add(String.format(" Не удалось распарсить дату курса \"%s\": %s", expectedName, rawDate));
      }
    }

    // Если были ошибки — собрать их в один текст и выбросить исключение
    if (!errors.isEmpty()) {
      String message = String.join("\n", errors);
      throw new AssertionError("Обнаружены ошибки при проверке курсов:\n" + message);
    }
  }


  public Map<String, String> findCoursesWithEarliestDate(Map<String, String> courseMap) {
    Map<String, LocalDate> validCourses = parseValidCourseDates(courseMap);

    if (validCourses.isEmpty()) {
      System.out.println("Нет курсов с корректными датами.");
      return Map.of();
    }

    // Используем reduce для поиска самой ранней даты
    LocalDate minDate = validCourses.values().stream()
        .reduce((d1, d2) -> d1.isBefore(d2) ? d1 : d2)
        .orElse(null);

    // Отфильтровываем курсы с этой датой
    Map<String, String> result = validCourses.entrySet().stream()
        .filter(entry -> entry.getValue().equals(minDate))
        .collect(Collectors.toMap(
            Map.Entry::getKey,
            entry -> minDate.toString()
        ));

    System.out.println("Курсы с самой ранней датой: " + minDate);
    result.forEach((title, date) ->
        System.out.println("Курс: " + title + " | Дата: " + date)
    );

    return result;
  }


  public Map<String, String> findCoursesWithLatestDate(Map<String, String> courseMap) {
    Map<String, LocalDate> validCourses = parseValidCourseDates(courseMap);

    if (validCourses.isEmpty()) {
      System.out.println("Нет курсов с корректными датами.");
      return Map.of();
    }

    // Используем reduce для поиска самой поздней даты
    LocalDate maxDate = validCourses.values().stream()
        .reduce((d1, d2) -> d1.isAfter(d2) ? d1 : d2)
        .orElse(null);

    Map<String, String> result = validCourses.entrySet().stream()
        .filter(entry -> entry.getValue().equals(maxDate))
        .collect(Collectors.toMap(
            Map.Entry::getKey,
            entry -> maxDate.toString()
        ));

    System.out.println("Курсы с самой поздней датой: " + maxDate);
    result.forEach((title, date) ->
        System.out.println("Курс: " + title + " | Дата: " + date)
    );

    return result;
  }


  private Map<String, LocalDate> parseValidCourseDates(Map<String, String> courseMap) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM, yyyy", new Locale("ru"));

    return courseMap.entrySet().stream()
        .map(entry -> {
          String courseTitle = entry.getKey();
          String rawDate = entry.getValue();
          try {
            if (rawDate != null && !rawDate.toLowerCase().contains("дате старта") && !rawDate.trim().isEmpty()) {
              String datePart = rawDate.split(" · ")[0].trim();
              LocalDate date = LocalDate.parse(datePart, formatter);
              return new AbstractMap.SimpleEntry<>(courseTitle, date);
            }
          } catch (Exception ignored) {
            // Некорректные даты пропускаем
          }
          return null;
        })
        .filter(Objects::nonNull)
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
  }


}



