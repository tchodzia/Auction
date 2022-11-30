package sda.project.auction.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sda.project.auction.service.UserService;
import sda.project.auction.web.form.CreateUserForm;
import sda.project.auction.web.mappers.UserMapper;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/signup")
public class UserController {
    private final UserService userService;

    @GetMapping
    public String signUp(ModelMap map) {
        map.addAttribute("user", new CreateUserForm());
        return "create-user";
    }

    @PostMapping
    public String handleSignUp(@ModelAttribute("user") CreateUserForm form, RedirectAttributes redirectAttributes, ModelMap model) {
        log.info("Signing Up from form: {}", form);
        userService.save(UserMapper.toEntity(form));
        return "create-user";
    }

}
