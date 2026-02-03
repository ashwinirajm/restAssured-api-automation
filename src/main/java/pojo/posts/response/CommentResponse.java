package pojo.posts.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponse {
    private int postId;
    private int id;
    private String name;
    private String email;
    private String body;
}
