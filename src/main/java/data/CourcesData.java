package data;

import java.time.LocalDate;

public enum CourcesData {

  Development_of_application_software_on_Qt_and_OS_Aurora_("Разработка прикладного ПО на Qt и ОС «Аврора»", CourceTypeData.Programmer, LocalDate.parse("2025-05-13")),
  Product_Marketing_Manager_в_IT("Product Marketing Manager в IT", CourceTypeData.Programmer, LocalDate.parse("2025-05-12")),
  CPO_Chief_Product_Officer("CPO / Директор по продукту", CourceTypeData.Programmer, LocalDate.parse("2025-05-12")),
  Optimization_of_business_processes("Оптимизация бизнес-процессов", CourceTypeData.Programmer, LocalDate.parse("2025-05-12")),
  Databases("Базы данных", CourceTypeData.Programmer, LocalDate.parse("2025-05-27"));

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
