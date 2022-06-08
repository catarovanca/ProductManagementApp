package ro.itschool.productmanagementapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavigationController {
    @GetMapping("/")
    public String homePage(){
        return "index";
    }

    @GetMapping("help")
    public String helpPage(){
        return "help";
    }
}
