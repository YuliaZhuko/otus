package pages;


import static org.junit.jupiter.api.Assertions.assertEquals;

import anotations.Hostname;
import anotations.Path;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import data.OtusLearningDirection;
import org.junit.jupiter.api.Assertions;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


@Hostname("https://otus.ru")
@Path("/uslugi-kompaniyam")
public class OtusCustomCoursesPage extends AbsBasePage<OtusCustomCoursesPage> {

  Locator links = page.locator("a.tn-atom[href^='https://otus.ru/']");
  private static final Random RAND = new Random();

  public OtusCustomCoursesPage(Page page) {
    super(page);
  }

  public OtusCustomCoursesPage chekhPageForBusines() throws URISyntaxException {
    // Получаем URL текущей страницы
    String url = page.url();

    // Извлекаем Path из URL
    URI uri = new URI(url);
    String currentPath = uri.getPath();

    String expectedPath = "/custom_courses/";
    // Сравниваем
    assertEquals(expectedPath, currentPath);

    return this;
  }


  public OtusCustomCoursesPage checkVisibilityEducation() throws URISyntaxException {
    //Ждем загрузки страницы
    Locator title = page.locator("h1");
    title.first().waitFor();


    List<String> hrefs = (List<String>) links.evaluateAll("elements => elements.map(el => el.href)");
    Set<String> categories = new HashSet<>();

    for (String href : hrefs) {
      URI uri = new URI(href);
      String path = uri.getPath();  // например: /categories/programming/ или /catalog/courses
      String query = uri.getQuery(); // например: categories=analytics

      if (path != null && path.startsWith("/categories/")) {
        // Получаем последнее слово пути (например "programming" из "/categories/programming/")
        String[] parts = path.split("/");
        for (int i = parts.length - 1; i >= 0; i--) {
          if (!parts[i].isEmpty()) {
            categories.add(parts[i]);
            break;
          }
        }
      } else if (path != null && path.equals("/catalog/courses") && query != null) {
        // Ищем параметр categories в query
        String[] params = query.split("&");
        for (String param : params) {
          if (param.startsWith("categories=")) {
            String value = param.substring("categories=".length());
            if (!value.isEmpty()) {
              categories.add(value);
            }
            break;
          }
        }
      }
    }
    // Выводим все последние слова
    categories.forEach(word -> System.out.println("Extracted category: " + word));
    // Сравнение с перечислением OtusLearningDirection
    Set<String> expected = Arrays.stream(OtusLearningDirection.values())
        .map(OtusLearningDirection::getDisplayName)
        .collect(Collectors.toSet());

    // Проверка: всё ли из enum нашлось на странице
    if (!categories.equals(expected)) {
      Set<String> missing = new HashSet<>(expected);
      missing.removeAll(categories);

      Set<String> unexpected = new HashSet<>(categories);
      unexpected.removeAll(expected);

      System.out.println(" Несовпадения найдено:");
      if (!missing.isEmpty()) {
        System.out.println("   Отсутствуют категории из enum: " + missing);
      }
      if (!unexpected.isEmpty()) {
        System.out.println("   Лишние категории на странице: " + unexpected);
      }

      throw new AssertionError("Категории на странице не совпадают с ожидаемыми.");
    } else {
      System.out.println("✅ Все категории совпадают с enum OtusLearningDirection");
    }
    return this;
  }

  public OtusCustomCoursesPage clickRandomDirectionAndCheckDirection() {
    Locator links = page.locator("a.tn-atom[href^='https://otus.ru/']");

    // Дождаться, пока заголовок страницы  станет видимым
    Locator title = page.locator("h1");
    title.waitFor(new Locator.WaitForOptions().setTimeout(10_000));

    int count = links.count();
    System.out.println("🔗 Всего видимых ссылок: " + count);

    if (count == 0) {
      throw new IllegalStateException(" Нет доступных ссылок для клика");
    }

    int randomIndex = RAND.nextInt(count);
    Locator randomLink = links.nth(randomIndex);

    // Получить ожидаемый URL
    String href = randomLink.getAttribute("href"); //  сохраняем href
    System.out.println("▶️ Кликаем по ссылке: " + href);

    // Прокрутить до элемента (если он невидим, возможно он вне экрана)
    randomLink.scrollIntoViewIfNeeded();

    // Явно дождаться, что он стал видимым и кликабельным
    randomLink.waitFor(new Locator.WaitForOptions().setTimeout(5000));

    // Клик и ожидание загрузки

    randomLink.click(new Locator.ClickOptions().setTimeout(10_000));
    page.waitForLoadState(LoadState.DOMCONTENTLOADED); // Альтернатива waitForURL

    System.out.println("✅ Перешли на: " + page.url());

    String categoryFromUrl = extractCategoryFromUrl(href);
    Optional<OtusLearningDirection> directionOpt = OtusLearningDirection.fromDisplayName(categoryFromUrl);

    if (directionOpt.isEmpty()) {
      throw new RuntimeException("Неизвестная категория: " + categoryFromUrl);
    }

    OtusLearningDirection direction = directionOpt.get();
    String labelText = direction.getLabelText();

    //  Создаём локатор по тексту
    Locator checkbox = page.locator("label:has-text(\"" + labelText + "\")");
    checkbox.waitFor(new Locator.WaitForOptions().setTimeout(5000));

    Assertions.assertTrue(checkbox.isVisible(), " Чекбокс не найден: " + labelText);
    System.out.println("✅ Чекбокс виден: " + labelText);


    return this;
  }

  private String extractCategoryFromUrl(String href) {
    // Пример 1: https://otus.ru/categories/data-science/
    Pattern pattern1 = Pattern.compile("/categories/([a-zA-Z0-9-_]+)/?");
    Matcher matcher1 = pattern1.matcher(href);
    if (matcher1.find()) {
      return matcher1.group(1);
    }

    // Пример 2: https://otus.ru/catalog/courses?categories=analytics
    Pattern pattern2 = Pattern.compile("[?&]categories=([a-zA-Z0-9-_]+)");
    Matcher matcher2 = pattern2.matcher(href);
    if (matcher2.find()) {
      return matcher2.group(1);
    }

    return null;
  }


}



