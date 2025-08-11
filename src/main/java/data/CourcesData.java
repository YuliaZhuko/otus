package data;

import java.time.LocalDate;

public enum CourcesData {

  QA_Engineer_Basic("QA Engineer. Basic", CourceTypeData.Programmer, LocalDate.parse("2025-07-29")),
  ARTIST_3D("QA Engineer. Basic", CourceTypeData.Programmer, LocalDate.parse("2025-07-29")),
  Network_Engineer_Basic("Network Engineer. Basic", CourceTypeData.Programmer, LocalDate.parse("2025-07-29")),
  Network_Engineer("Network Engineer", CourceTypeData.Programmer, LocalDate.parse("2025-07-29")),
  Design_of_networks_TSOD("Дизайн сетей ЦОД", CourceTypeData.Programmer, LocalDate.parse("2025-07-30")),
  Devops("DevOps практики и инструменты", CourceTypeData.Programmer, LocalDate.parse("2025-08-06"));


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
