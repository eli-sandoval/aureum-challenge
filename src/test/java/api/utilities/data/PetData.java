package api.utilities.data;

import api.utilities.Statuses;
import api.utilities.model.Category;
import api.utilities.model.Pet;
import api.utilities.model.Tags;

import java.util.Collections;

public class PetData {
    //Object mother for a new basic pet
    public static Pet newPet() {
        return new Pet(
                new Category("10", "Poodle"),
                "John",
                Collections.singletonList("google.com"),
                Collections.singletonList(new Tags("20", "Eli")),
                Statuses.AVAILABLE
        );
    }

    //Object mother for the basic pet above with two modified values
    public static Pet editedPet() {
        Pet pet = newPet();
        pet.setName("Edited");
        pet.setStatus(Statuses.PENDING);
        return pet;
    }
}
