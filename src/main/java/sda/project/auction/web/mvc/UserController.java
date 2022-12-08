package sda.project.auction.web.mvc;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sda.project.auction.model.User;
import sda.project.auction.service.UserService;
import sda.project.auction.web.form.CreateUserForm;
import sda.project.auction.web.mappers.UserMapper;

import java.security.Principal;

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
    public String handleSignUp(@ModelAttribute("user") @Valid CreateUserForm form, Errors errors, RedirectAttributes redirectAttributes) {
        log.info("Signing Up from form: {}", form);
        if(errors.hasErrors()){
            return "create-user";
        }
        userService.save(UserMapper.toEntity(form));
        redirectAttributes.addAttribute("message", form.getAccount_name() + " your account was created!");
        return "redirect:/";
    }

    //principal.get name jak użytkownik uderzy w endpoint to wtedy możemy złapać name użytkownika z sobie go z bazy wyciągnąć

    @GetMapping("/update/user/{id}")
    public String update(@PathVariable Long id, ModelMap map, Principal principal) {
        User foundUser = userService.findById(id);
        map.addAttribute("user", foundUser);
        return "update-user";
    }

    @PostMapping("/update/save")
    public String saveUser(@ModelAttribute("user") @Valid CreateUserForm form, Errors errors, RedirectAttributes redirectAttributes) {
        log.info("Updating user from form {}: ", form);
        if(errors.hasErrors()){
            return "update-user";
        }
        userService.update(form);
        redirectAttributes.addAttribute("message", form.getAccount_name() + " your account was updated!");
        return "redirect:/";
    }

}
