package springboot.web.dto;

import lombok.Data;
import lombok.Getter;

@Data
public class PostsSearchCondition {

    private String title;
    private String author;
    private String content;

}
