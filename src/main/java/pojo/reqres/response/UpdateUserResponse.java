package pojo.reqres.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateUserResponse {
    private String updatedAt;
    private String job;
    private String name;
}
