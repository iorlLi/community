package life.majiang.community.community.controller;

import life.majiang.community.community.dto.AccessTokenDTO;
import life.majiang.community.community.dto.GithubUser;
import life.majiang.community.community.provider.GitHubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AutorizeController {
    @Autowired
    private GitHubProvider gitHubProvider;

    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecert;
    @Value("${github.redirect.uri}")
    private String redirectUti;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code, @RequestParam(name = "state") String state) {
        System.out.println("code = " + code);
        System.out.println("state = " + state);
        //Ctrl+Alt+V 类似 eclipse ctrl + 1
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setRedirect_uri(redirectUti);
        accessTokenDTO.setClient_secret(clientSecert);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        String accessToken = gitHubProvider.getAccessToken(accessTokenDTO);
        System.out.println(accessToken);
        GithubUser user = gitHubProvider.getUser(accessToken);
        System.out.println(user.toString());
        return "index";
    }
}
