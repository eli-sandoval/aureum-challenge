package api.pet.scenarios;

import api.pet.endpoints.PetEndpoints;
import api.utilities.data.PetData;
import api.utilities.model.Pet;
import com.google.gson.Gson;
import io.restassured.response.Response;
import org.junit.Test;
import support.Settings;

public class DELETEScenarios {
    Gson gson = new Gson();

    @Test()
    public void deletePet() {
        PetEndpoints petEndpoints = new PetEndpoints(Settings.PET_ENDPOINTS_BASE_URL);
        //Create pet object
        Pet pet = PetData.newPet();

        //POST pet object to endpoint
        Response createResponse = petEndpoints.postPet(gson.toJson(pet));

        //Validate status 200
        createResponse.then()
                .statusCode(200);
        //Extract id from json response
        String id = createResponse.jsonPath().get("id").toString();

        //DELETE pet object
        Response deleteResponse = petEndpoints.deletePet(id);

        //Validate status 200
        deleteResponse.then()
                .statusCode(200);
    }
}
