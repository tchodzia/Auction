package sda.project.auction.web.mvc;

import sda.project.auction.model.User;
import sda.project.auction.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("")
public class WelcomeController {
    private final UserService userService;
    @GetMapping("/")
    public String welcomePage(ModelMap map) {
        User user = userService.findById(1L);
        //map.addAttribute("userName", "Jan Kowalski");
        map.addAttribute("user", user);
        return "index";
    }
}