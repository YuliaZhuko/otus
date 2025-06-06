package data;

import java.time.LocalDate;

public enum CourcesData {

  Python_Developer("Python Developer", CourceTypeData.Programmer, LocalDate.parse("2025-05-28")),
  Python_QA_Engineer("Python QA Engineer", CourceTypeData.Programmer, LocalDate.parse("2025-05-27")),
  Golang_Developer_Professional("Golang Developer. Professional", CourceTypeData.Programmer, LocalDate.parse("2025-05-29")),
  React_js_Developer("React.js Developer", CourceTypeData.Programmer, LocalDate.parse("2025-05-29")),
  Enterprise_Architect("Enterprise Architect", CourceTypeData.Programmer, LocalDate.parse("2025-05-29"));

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
