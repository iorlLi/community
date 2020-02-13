package life.majiang.community.community.controller;

import life.majiang.community.community.mapper.UserMapper;
import life.majiang.community.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/")
    public String index(HttpServletRequest request) {
        System.out.println("index:step1---------------");
        Cookie[] cookies = request.getCookies();
        User user =null;
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    String token = cookie.getValue();
                    user = userMapper.selectByToken(token);
                    System.out.println("index:token = " + token);
                    if (user != null) {
                        System.out.println(user.toString());
                        request.getSession().setAttribute("user", user);
                    }
                }
            }
        }
        if(user == null){
            System.out.println("no login");
        }
        return "index";
    }

}
