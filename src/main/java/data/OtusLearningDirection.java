package data;

import java.util.Arrays;
import java.util.Optional;

public enum OtusLearningDirection {
  PROGRAMMING("programming", "Программирование"),
  INFRASTRUCTURE("operations", "Инфраструктура"),
  TESTING("testing", "Тестирование"),
  MANAGEMENT("marketing-business", "Управление"),
  ANALYTICS("analytics", "Аналитика и анализ"),
  DATA_SCIENCE("data-science", "Data Science");

  private final String displayName;
  private final String labelText; // 👈 Новый параметр – текст чекбокса на странице

  OtusLearningDirection(String displayName, String labelText) {
    this.displayName = displayName;
    this.labelText = labelText;
  }

  // Метод для поиска по displayName
  public static Optional<OtusLearningDirection> fromDisplayName(String displayName) {
    return Arrays.stream(values())
        .filter(v -> v.displayName.equalsIgnoreCase(displayName))
        .findFirst();
  }

  public String getDisplayName() {
    return displayName;
  }

  public String getLabelText() {
    return labelText;
  }
}

