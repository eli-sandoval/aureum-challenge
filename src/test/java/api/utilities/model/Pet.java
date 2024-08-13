package api.utilities.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@AllArgsConstructor
@Getter
public class Pet {
    Category category;
    String name;
    List<String> photoUrls;
    List<Tags> tags;
    String status;
}