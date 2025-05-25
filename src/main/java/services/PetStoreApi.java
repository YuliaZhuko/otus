package services;

import static io.restassured.RestAssured.given;

import dto.PetDTO;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class PetStoreApi {
  private RequestSpecification spec;
  private String baseUrl = System.getProperty("base.url");

  public PetStoreApi() {
    spec = given()
        .baseUri(baseUrl)
        .contentType(ContentType.JSON);
  }


  public ValidatableResponse createNewPet(PetDTO petDTO) {
    return given(spec)
        .basePath("pet")
        .body(petDTO)
        .log().all()
        .when()
        .post()
        .then()
        .log().all();
  }

  public ValidatableResponse deleteUser(String userName) {

    return given(spec)
        .basePath("user/{username}")
        .pathParam("username", userName)
        .log().all()
        .when()
        .delete()
        .then()
        .log().all();
  }

  public ValidatableResponse deletePet(int petId) {
    return given(spec)
        .basePath("pet/{petId}")
        .pathParam("petId", petId)
        .log().all()
        .when()
        .delete()
        .then()
        .log().all();

  }
}
