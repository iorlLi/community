package life.majiang.community.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @GetMapping("/publish")
    public String index(HttpServletRequest request) {
        return "publish";
    }

}
