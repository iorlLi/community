package life.majiang.community.community.dto;

import lombok.Data;
import org.springframework.stereotype.Component;
@Data
@Component
public class GithubUser {
    private String login;
    private String name;
    private Long id;
    private String bio;
    private String avatarUrl;

}
