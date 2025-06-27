package data;

import dto.TagDTO;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum PetData {

  DOG(
      5L,
      5L,
      "Dogs",
      "doggie",
      List.of(
          "https://example.com/photo1.jpg",
          "https://example.com/photo2.jpg",
          "https://example.com/photo3.jpg"
      ),
      Arrays.asList(
          new TagDTO(1L, "funny"),
          new TagDTO(2L, "cute")
      ),
      "available"
  );

  // 🔹 Поля
  private final Long petId;
  private final Long categoryId;
  private final String categoryName;
  private final String petName;
  private final List<String> photoUrls;
  private final List<TagDTO> tags;
  private final String status;

  // 🔹 Конструктор
  PetData(Long petId,
          Long categoryId,
          String categoryName,
          String petName,
          List<String> photoUrls,
          List<TagDTO> tags,
          String status) {
    this.petId = petId;
    this.categoryId = categoryId;
    this.categoryName = categoryName;
    this.petName = petName;
    this.photoUrls = new ArrayList<>(photoUrls);
    this.tags = new ArrayList<>(tags);
    this.status = status;
  }

  // 🔹 Геттеры

  public Long getPetId() {
    return petId;
  }

  public Long getCategoryId() {
    return categoryId;
  }

  public String getCategoryName() {
    return categoryName;
  }

  public String getPetName() {
    return petName;
  }

  public List<String> getPhotoUrls() {
    return new ArrayList<>(photoUrls);
  }

  public List<TagDTO> getTags() {
    return new ArrayList<>(tags);
  }

  public String getStatus() {
    return status;
  }
}
