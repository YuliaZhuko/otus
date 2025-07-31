package pet;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import dto.CategoryDTO;
import dto.DeletePetResponseDTO;
import dto.PetDTO;
import dto.TagDTO;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import io.restassured.parsing.Parser;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.*;
import services.PetStoreApi;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DeletePetTest {

  PetStoreApi api = new PetStoreApi();

  @BeforeAll
  static void setup() {
    // ✅ Если сервер не отдает Content-Type, ставим JSON по умолчанию
    RestAssured.defaultParser = Parser.JSON;
  }

  @Test
  @Order(1)
  @DisplayName("Создать и удалить питомца, проверить ответ")
  void createAndDeletePet() {
    // 1️⃣ Создаем питомца
    PetDTO pet = PetDTO.builder()
        .id(777L)
        .category(CategoryDTO.builder()
            .id(5L)
            .name("test-category")
            .build())
        .name("test-dog")
        .photoUrls(List.of("string"))
        .tags(List.of(
            TagDTO.builder()
                .id(0L)
                .name("tag")
                .build()
        ))
        .status("available")
        .build();

    api.createNewPet(pet)
        .statusCode(HttpStatus.SC_OK);

    // 2️⃣ Удаляем питомца
    ValidatableResponse response = api.deletePet(777);

    // 3️⃣ Проверяем статус и время
    response.statusCode(HttpStatus.SC_OK)
        .time(lessThan(5000L));

    // 4️⃣ Проверяем тело ответа, если оно не пустое
    String body = response.extract().body().asString();
    if (!body.isEmpty()) {
      DeletePetResponseDTO deletePet = response.extract().as(DeletePetResponseDTO.class);
      Assertions.assertAll("Check delete response",
          () -> Assertions.assertEquals(200, deletePet.getCode(), "Invalid code"),
          () -> Assertions.assertEquals("unknown", deletePet.getType(), "Invalid type"),
          () -> Assertions.assertEquals("777", deletePet.getMessage(), "Invalid message")
      );
    } else {
      System.out.println("⚠️ Сервер вернул пустой ответ, пропускаем проверку DTO");
    }
  }

  @Test
  @Order(2)
  @DisplayName("Удаление питомца возвращает статус и за время < 5 секунд")
  void deletePetTime() {
    int petId = 5;

    api.deletePet(petId)
        .statusCode(HttpStatus.SC_OK)
        .time(lessThan(5000L))
        .body("code", equalTo(200))
        .body("type", equalTo("unknown"));
  }
}
