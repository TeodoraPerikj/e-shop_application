package mk.ukim.finki.servlet_thymeleaf.web.controller;

import mk.ukim.finki.servlet_thymeleaf.model.exceptions.InvalidArguementsException;
import mk.ukim.finki.servlet_thymeleaf.model.exceptions.PasswordsDoNotMatchException;
import mk.ukim.finki.servlet_thymeleaf.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private final AuthService authService;

    public RegisterController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping
    public String getRegisterPage(@RequestParam(required = false) String error, Model model){

        if (error != null && !error.isEmpty()){
            model.addAttribute("hasError",true);
            model.addAttribute("error",error);
        }

        model.addAttribute("bodyContent","register");
        return "master-template";
    }

    @PostMapping
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String repeatedPassword,
                           @RequestParam String name,
                           @RequestParam String surname){

        try{
            authService.register(username, password, repeatedPassword, name, surname);
            return "redirect:/login";
        }
        catch (InvalidArguementsException | PasswordsDoNotMatchException exception){

            return "redirect:/register?error="+exception.getMessage();

        }

    }

}
