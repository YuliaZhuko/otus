package pet;

import static org.hamcrest.Matchers.lessThan;

import dto.CategoryDTO;
import dto.NewPetResponseDTO;
import dto.PetDTO;
import dto.TagDTO;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import services.PetStoreApi;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class CreatePetTest {

  PetStoreApi api = new PetStoreApi();
  Long petId = 5L;
  Long categoryId = 5L;
  String categoryName = "Dogs";
  String petName = "doggie";
  List<String> photoUrls = List.of(
      "https://example.com/photo1.jpg",
      "https://example.com/photo2.jpg",
      "https://example.com/photo3.jpg"
  );
  String tagName1 = "funny";
  String tagName2 = "cute";
  Long tagId1 = 1L;
  Long tagId2 = 2L;

  TagDTO tag1 = new TagDTO(tagId1, tagName1);
  TagDTO tag2 = new TagDTO(tagId2, tagName2);

  List<TagDTO> tags = Arrays.asList(tag1, tag2);
  String status = "available";

  static Stream<Arguments> dataProvider() {
    CategoryDTO category = CategoryDTO.builder()
        .id(5L)
        .name("Dogs")
        .build();

    List<String> photoUrls = List.of("https://example.com/photo1.jpg", "https://example.com/photo2.jpg");

    List<TagDTO> tags = List.of(
        TagDTO.builder().id(1L).name("cute").build(),
        TagDTO.builder().id(2L).name("small").build()
    );

    PetDTO validPet = PetDTO.builder()
        .id(5L)
        .category(category)
        .name("Buddy")
        .photoUrls(photoUrls)
        .tags(tags)
        .status("available")
        .build();

    PetDTO petNoName = PetDTO.builder()
        .id(5L)
        .category(category)
        .photoUrls(photoUrls)
        .tags(tags)
        .status("available")
        .build();

    PetDTO petNoCategory = PetDTO.builder()
        .id(5L)
        .name("Rex")
        .photoUrls(photoUrls)
        .tags(tags)
        .status("available")
        .build();

    PetDTO petNoPhotoUrls = PetDTO.builder()
        .id(5L)
        .category(category)
        .name("Charlie")
        .tags(tags)
        .status("available")
        .build();

    PetDTO petNoTags = PetDTO.builder()
        .id(5L)
        .category(category)
        .name("Max")
        .photoUrls(photoUrls)
        .status("available")
        .build();

    PetDTO petNoStatus = PetDTO.builder()
        .id(5L)
        .category(category)
        .name("Bella")
        .photoUrls(photoUrls)
        .tags(tags)
        .build();

    return Stream.of(
        Arguments.of(validPet),
        Arguments.of(petNoName),
        Arguments.of(petNoCategory),
        Arguments.of(petNoPhotoUrls),
        Arguments.of(petNoTags),
        Arguments.of(petNoStatus)
    );
  }

  //Тест проверяет все параметры после создания животного
  @Test
  @DisplayName("Check parameters creating pet")
  void createPet() {
    PetDTO petDTO = PetDTO.builder()
        .id(petId)
        .category(CategoryDTO.builder()
            .id(categoryId)
            .name(categoryName)
            .build())
        .name(petName)
        .photoUrls(photoUrls)
        .tags(tags)
        .status(status)
        .build();

    api.createNewPet(petDTO)
        .statusCode(HttpStatus.SC_OK)
        .time(lessThan(5000L));

    NewPetResponseDTO actualPet = api.createNewPet(petDTO).extract().body().as(NewPetResponseDTO.class);

    Assertions.assertAll("Check create new pet",
        () -> Assertions.assertEquals(actualPet.getId(), petId.intValue(), "Invalid petId"),
        () -> Assertions.assertEquals(actualPet.getCategory().getId(), categoryId.intValue(), "Invalid CategoryId"),
        () -> Assertions.assertEquals(actualPet.getCategory().getName(), "Dogs", "Invalid CategoryName"),
        () -> Assertions.assertEquals(actualPet.getName(), petName, "Invalid petName"),
        () -> Assertions.assertEquals(actualPet.getPhotoUrls(), photoUrls, "Invalid PhotoUrls"),
        () -> Assertions.assertEquals(actualPet.getTags().getFirst().getName(), tagName1, "Invalid tagName!"),
        () -> Assertions.assertEquals(actualPet.getTags().getFirst().getId(), tagId1, "Invalid tagId1"),
        () -> Assertions.assertEquals(actualPet.getStatus(), status, "Invalid status")
    );
  }

  //Тест проверяет необязательность заполнения полей при создании питомца
  @ParameterizedTest
  @DisplayName("Check if fields in an object are optional")
  @MethodSource("dataProvider")
  void createPet(PetDTO petDTO) {
    PetStoreApi api = new PetStoreApi();
    api.createNewPet(petDTO);
  }
}


