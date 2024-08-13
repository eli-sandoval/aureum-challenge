package api;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseRequest {
    String baseUrl;
    String requestPath;
}
