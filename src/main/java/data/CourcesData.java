package data;

import java.time.LocalDate;

public enum CourcesData {

  Fullstack_developer("Fullstack developer", CourceTypeData.Programmer, LocalDate.parse("2025-06-25")),
  STO_technical_director("CTO / Технический директор", CourceTypeData.Programmer, LocalDate.parse("2025-05-29")),
  Administrator_Linux_Professional("Administrator Linux. Professional", CourceTypeData.Programmer, LocalDate.parse("2025-06-26")),
  MLOps("MLOps", CourceTypeData.Programmer, LocalDate.parse("2025-06-26"));


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
