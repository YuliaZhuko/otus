package data;

public enum NewsArticleData {

  DTP(67064247, NewsCategoryData.SOCIETY);

  private int id;
  private NewsCategoryData newsCategoryData;

  NewsArticleData(int id, NewsCategoryData newsCategoryData) {
    this.id = id;
    this.newsCategoryData = newsCategoryData;
  }

  public int getId() {
    return id;
  }

  public NewsCategoryData getNewsCategoryData() {
    return newsCategoryData;
  }
}
