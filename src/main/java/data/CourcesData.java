package data;

import java.time.LocalDate;

public enum CourcesData {

  iOS_Developer_Professional("iOS Developer. Professional", CourceTypeData.Programmer, LocalDate.parse("2025-08-27")),
  Platform_Kubernetes("Инфраструктурная платформа на основе Kubernetes", CourceTypeData.Programmer, LocalDate.parse("2025-08-27")),
  Pentest("Пентест. Инструменты и методы проникновения в действии", CourceTypeData.Programmer, LocalDate.parse("2025-08-27")),
  System_Analyst_Advanced("Системный аналитик. Advanced", CourceTypeData.Programmer, LocalDate.parse("2025-08-27")),
  System_Analyst("Системный аналитик", CourceTypeData.Programmer, LocalDate.parse("2025-08-27")),
  System_Analyst_Basic("Системный аналитик. Basic", CourceTypeData.Programmer, LocalDate.parse("2025-08-27")),
  NLP_Natural_Language_Processing("NLP / Natural Language Processing", CourceTypeData.Programmer, LocalDate.parse("2025-08-27")),
  C_Developer_Professional("C++ Developer. Professional", CourceTypeData.Programmer, LocalDate.parse("2025-08-27")),
  System_and_Business_Analyze("Системный и бизнес-анализ", CourceTypeData.Programmer, LocalDate.parse("2025-08-27")),
  Rust_Developer_Professional("Rust Developer. Professional", CourceTypeData.Programmer, LocalDate.parse("2025-08-27"));


  private String name;
  private CourceTypeData courceTypeData;
  private LocalDate startDate;


  CourcesData(String name, CourceTypeData courceTypeData, LocalDate startDate) {
    this.name = name;
    this.courceTypeData = courceTypeData;
    this.startDate = startDate;
  }

  public String getName() {
    return name;
  }

  public CourceTypeData getCourceTypeData() {
    return courceTypeData;
  }

  public LocalDate getStartDate() {
    return startDate;
  }
}
