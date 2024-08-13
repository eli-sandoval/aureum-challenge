package api.pet.endpoints;

import api.BaseRequest;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PetEndpoints extends BaseRequest {

    public PetEndpoints(String url) {
        setBaseUrl(url);
        setRequestPath("/pet");
    }

    public Response getPetById(int petId) {
        return given()
                .baseUri(getBaseUrl() + getRequestPath())
                .header("Content-Type", "application/json")
                .pathParam("petId", petId)
                .when()
                .get("/{petId}");
    }

    public Response getPetByStatus(String status) {
        return given()
                .baseUri(getBaseUrl() + getRequestPath())
                .basePath("findByStatus")
                .queryParam("status", status)
                .when()
                .get();
    }

    public Response postPet(String petJson) {
        return given()
                .baseUri(getBaseUrl() + getRequestPath())
                .header("Content-Type", "application/json")
                .body(petJson)
                .when()
                .post();
    }

    public Response putPet(String petJson) {
        return given()
                .baseUri(getBaseUrl() + getRequestPath())
                .header("Content-Type", "application/json")
                .body(petJson)
                .when()
                .put();
    }

    public Response deletePet(String petId) {
        return given()
                .baseUri(getBaseUrl() + getRequestPath())
                .header("Content-Type", "application/json")
                .pathParam("petId", petId)
                .when()
                .delete("/{petId}");
    }
}