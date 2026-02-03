package pojo.posts.response;

import lombok.Data;

@Data
public class PostResponse {
    private int userId;
    private int id;
    private String title;
    private String body;
}
