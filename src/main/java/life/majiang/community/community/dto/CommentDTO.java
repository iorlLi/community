package life.majiang.community.community.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class CommentDTO {
    private String content;
    private Long parentId;
    private int type;
}
