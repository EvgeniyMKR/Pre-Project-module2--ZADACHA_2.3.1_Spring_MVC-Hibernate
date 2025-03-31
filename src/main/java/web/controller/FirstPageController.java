package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstPageController {

    @GetMapping("/")
    public String firstPage() {
        return "firstPage";
    }
}
