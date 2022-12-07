package sda.project.auction.web.mvc;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import sda.project.auction.model.User;
import sda.project.auction.service.UserService;
import sda.project.auction.web.form.CreateUserForm;
import sda.project.auction.web.mappers.UserMapper;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping()
public class UserController {
    private final UserService userService;

    @GetMapping("/signup")
    public String signUp(ModelMap map) {
        map.addAttribute("user", new CreateUserForm());
        return "create-user";
    }

    @PostMapping("/signup")
    public String handleSignUp(@ModelAttribute("user") CreateUserForm form, ModelMap model) {
        log.info("Signing Up from form: {}", form);
        userService.save(UserMapper.toEntity(form));
        return "create-user";
    }

    @GetMapping("/update/user/{id}")
    public String update(@PathVariable Long id, ModelMap map) {
        User foundUser = userService.findById(id);
        map.addAttribute("user", foundUser);
        return "update-user";
    }

    @PostMapping("/update/save")
    public String saveUser(@ModelAttribute("user") CreateUserForm form) {
        log.info("Updating user from form {}: ", form);
        User user = userService.findById(form.getID());
        userService.save(UserMapper.toUpdateEntity(user, form));
        return "redirect:/";
    }

}
