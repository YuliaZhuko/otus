package pet;

import static org.hamcrest.Matchers.lessThan;

import data.PetData;
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
        .id(PetData.DOG.getPetId())
        .category(CategoryDTO.builder()
            .id(PetData.DOG.getCategoryId())
            .name(PetData.DOG.getCategoryName())
            .build())
        .name(PetData.DOG.getPetName())
        .photoUrls(PetData.DOG.getPhotoUrls())
        .tags(PetData.DOG.getTags())
        .status(PetData.DOG.getStatus())
        .build();

    api.createNewPet(petDTO)
        .statusCode(HttpStatus.SC_OK);

    NewPetResponseDTO actualPet = api.createNewPet(petDTO).extract().body().as(NewPetResponseDTO.class);

    Assertions.assertAll("Check create new pet",
        () -> Assertions.assertEquals(actualPet.getId(), PetData.DOG.getPetId().intValue(), "Invalid petId"),
        () -> Assertions.assertEquals(actualPet.getCategory().getId(), PetData.DOG.getCategoryId().intValue(), "Invalid CategoryId"),
        () -> Assertions.assertEquals(actualPet.getCategory().getName(), PetData.DOG.getCategoryName(), "Invalid CategoryName"),
        () -> Assertions.assertEquals(actualPet.getName(), PetData.DOG.getPetName(), "Invalid petName"),
        () -> Assertions.assertEquals(actualPet.getPhotoUrls(), PetData.DOG.getPhotoUrls(), "Invalid PhotoUrls"),
        () -> Assertions.assertEquals(actualPet.getTags().getFirst().getName(), PetData.DOG.getTags().getFirst().getName(), "Invalid tagName!"),
        () -> Assertions.assertEquals(actualPet.getTags().getFirst().getId(), PetData.DOG.getTags().getFirst().getId(), "Invalid tagId1"),
        () -> Assertions.assertEquals(actualPet.getStatus(), PetData.DOG.getStatus(), "Invalid status")
    );
  }

  //Тест проверяет необязательность заполнения полей при создании питомца
  @ParameterizedTest
  @DisplayName("Check if fields in an object are optional")
  @MethodSource("dataProvider")
  void createPetAndCheck(PetDTO petDTO) {
    PetStoreApi api = new PetStoreApi();

    // Отправляем запрос и получаем ответ с созданным питомцем
    NewPetResponseDTO actualPet = api.createNewPet(petDTO)
        .statusCode(HttpStatus.SC_OK)
        .extract()
        .body()
        .as(NewPetResponseDTO.class);

    // Сравниваем пришедший объект с тем, который отправили
    Assertions.assertAll("Validate created pet",
        () -> Assertions.assertEquals(actualPet.getId(), petDTO.getId().intValue(), "Invalid petId"),

        () -> {
          if (petDTO.getCategory() != null) {
            Assertions.assertNotNull(actualPet.getCategory(), "Category should not be null");
            Assertions.assertEquals(actualPet.getCategory().getId(), petDTO.getCategory().getId().intValue(), "Invalid categoryId");
            Assertions.assertEquals(actualPet.getCategory().getName(), petDTO.getCategory().getName(), "Invalid categoryName");
          } else {
            Assertions.assertNull(actualPet.getCategory(), "Category should be null");
          }
        },

        () -> Assertions.assertEquals(actualPet.getName(), petDTO.getName(), "Invalid name"),

        () -> {
          if (petDTO.getPhotoUrls() != null) {
            Assertions.assertEquals(actualPet.getPhotoUrls(), petDTO.getPhotoUrls(), "Invalid photoUrls");
          } else {
            Assertions.assertTrue(actualPet.getPhotoUrls() == null || actualPet.getPhotoUrls().isEmpty(), "PhotoUrls should be null or empty");
          }
        },

        () -> {
          if (petDTO.getTags() != null) {
            Assertions.assertEquals(actualPet.getTags(), petDTO.getTags(), "Invalid tags");
          } else {
            Assertions.assertTrue(actualPet.getTags() == null || actualPet.getTags().isEmpty(), "Tags should be null or empty");
          }
        },

        () -> {
          if (petDTO.getStatus() != null) {
            Assertions.assertEquals(actualPet.getStatus(), petDTO.getStatus(), "Invalid status");
          } else {
            Assertions.assertNull(actualPet.getStatus(), "Status should be null");
          }
        }
    );

  }
}


