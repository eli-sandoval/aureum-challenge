package api.pet.scenarios;

import api.pet.endpoints.PetEndpoints;
import api.utilities.data.PetData;
import api.utilities.model.Pet;
import com.google.gson.Gson;
import io.restassured.response.Response;
import org.junit.Test;
import support.Settings;

import static org.hamcrest.Matchers.equalTo;

public class POSTScenarios {
    Gson gson = new Gson();

    @Test
    public void createPet() {
        PetEndpoints petEndpoints = new PetEndpoints(Settings.PET_ENDPOINTS_BASE_URL);
        //Create pet object
        Pet pet = PetData.newPet();

        //POST pet object to endpoint
        Response response = petEndpoints.postPet(gson.toJson(pet));

        //Validate status 200 and match all the fields from the payload to the object created above
        response.then()
                .statusCode(200)
                .body("category.id", equalTo(Integer.valueOf(pet.getCategory().getId())))
                .body("category.name", equalTo(pet.getCategory().getName()))
                .body("photoUrls[0]", equalTo(pet.getPhotoUrls().get(0)))
                .body("tags[0].id", equalTo(Integer.valueOf(pet.getTags().get(0).getId())))
                .body("tags[0].name", equalTo(pet.getTags().get(0).getName()))
                .body("name", equalTo(pet.getName()))
                .body("status", equalTo(pet.getStatus()));
    }
}
