package chess_hub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {

        model.addAttribute("clubName", "Harare Chess Club");
        model.addAttribute("motto", "Think. Plan. Win.");

        return "index";
    }
}
