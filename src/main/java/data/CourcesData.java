package data;

import java.time.LocalDate;

public enum CourcesData {

  SRE_practice("SRE практики и инструменты", CourceTypeData.Programmer, LocalDate.parse("2025-07-28")),
  Golang_Developer_Professional("Golang Developer. Professional", CourceTypeData.Programmer, LocalDate.parse("2025-07-30")),
  Android_Developer_Basic("Android Developer. Basic", CourceTypeData.Programmer, LocalDate.parse("2025-07-30")),
  Design_of_networks_TSOD("Дизайн сетей ЦОД", CourceTypeData.Programmer, LocalDate.parse("2025-07-30")),
  Android_Developer("Android Developer", CourceTypeData.Programmer, LocalDate.parse("2025-07-30"));


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
