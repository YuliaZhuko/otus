package pet;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import data.PetData;
import dto.CategoryDTO;
import dto.DeletePetResponseDTO;
import dto.PetDTO;
import dto.TagDTO;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import services.PetStoreApi;
import java.util.List;

public class DeletePetTest {
  PetStoreApi api = new PetStoreApi();
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

  int code = 200;
  String type = "unknown";
  String message = String.valueOf(PetData.DOG.getPetId());

  static void setup() {
    RestAssured.defaultParser = Parser.JSON;
  }

  //Тест проверяет параметры ответа после удаления питомца
  @Test
  @DisplayName("Check response after delete pet")
  void deletePet() {

    DeletePetResponseDTO deletePet = api.deletePet(PetData.DOG.getPetId().intValue()).extract().body().as(DeletePetResponseDTO.class);
    Assertions.assertAll("Check create new pet",
        () -> Assertions.assertEquals(deletePet.getCode(), code, "Invalid code"),
        () -> Assertions.assertEquals(deletePet.getType(), type, "Invalid type"),
        () -> Assertions.assertEquals(deletePet.getMessage(), message, "Invalid message")
    );
  }

  //Тест проверяет параметры ответа после удаления питомца
  @Test
  @DisplayName("Check response after delete pet with time")
  void deletePetTime() {
    PetDTO pet = PetDTO.builder()
        .id(0L)
        .category(CategoryDTO.builder()
            .id(5L)
            .name("string")
            .build())
        .name("doggie")
        .photoUrls(List.of("string"))
        .tags(List.of(
            TagDTO.builder()
                .id(0L)
                .name("string")
                .build()
        ))
        .status("available")
        .build();

    int petId = 5;

    api.deletePet(petId)
        .statusCode(HttpStatus.SC_OK)
        .time(lessThan(5000L))
        .body("code", equalTo(code))
        .body("type", equalTo(type));

  }
}





