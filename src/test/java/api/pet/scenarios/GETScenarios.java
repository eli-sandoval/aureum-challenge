package api.pet.scenarios;

import api.pet.endpoints.PetEndpoints;
import api.utilities.Statuses;
import io.restassured.response.Response;
import org.junit.Test;
import support.Settings;

import static org.hamcrest.Matchers.equalTo;

public class GETScenarios {

    @Test
    public void getPetById() {
        PetEndpoints petEndpoints = new PetEndpoints(Settings.PET_ENDPOINTS_BASE_URL);

        //GET pet with ID 1
        Response response = petEndpoints.getPetById(1);

        //Validate 200 response and that the object in the response payload has id = 1
        response.then()
                .statusCode(200)
                .body("id", equalTo(1));
    }

    @Test
    public void getPetByStatus() {
        PetEndpoints petEndpoints = new PetEndpoints(Settings.PET_ENDPOINTS_BASE_URL);

        //GET ALL pets with status = available
        Response response = petEndpoints.getPetByStatus(Statuses.AVAILABLE);

        //Validate 200 response
        response.then()
                .statusCode(200)
                //Validate that all the statuses found on the response payload = available
                .body("findAll { it.status != 'available' }.size()", equalTo(0));
    }
}
