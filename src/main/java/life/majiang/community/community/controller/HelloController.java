package life.majiang.community.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
public class HelloController {
    private String count;

    @GetMapping("/hello")
    public String hello(@RequestParam(name = "name") String name, Model model) {

        model.addAttribute("name", name);
        //ctrl+alt+v
        Date date = new Date();
        printLog();
        return "hello";
    }

    private void printLog() {
        System.out.println("如鱼得水");
    }
}
