package api.pet.scenarios;

import api.pet.endpoints.PetEndpoints;
import api.utilities.data.PetData;
import api.utilities.model.Pet;
import com.google.gson.Gson;
import io.restassured.response.Response;
import org.junit.Test;
import support.Settings;

import static org.hamcrest.Matchers.equalTo;

public class PUTScenarios {
    Gson gson = new Gson();

    @Test
    public void editPet() {
        PetEndpoints petEndpoints = new PetEndpoints(Settings.PET_ENDPOINTS_BASE_URL);
        //Create edited pet object
        Pet editedPet = PetData.editedPet();

        //Edit existing pet
        Response response = petEndpoints.putPet(gson.toJson(editedPet));

        //Validate status 200 and check that the name and status are the values we have modified
        response.then()
                .statusCode(200)
                .body("name", equalTo(editedPet.getName()))
                .body("status", equalTo(editedPet.getStatus()));
    }
}
