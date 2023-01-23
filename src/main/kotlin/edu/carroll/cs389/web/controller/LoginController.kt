package edu.carroll.cs389.web.controller

import edu.carroll.cs389.web.form.LoginForm;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
class LoginController {
    @GetMapping("/login")
    fun loginGet(model: Model): String {
        model.addAttribute("loginForm", LoginForm());
        return "login";
    }

    @PostMapping("/login")
    fun loginPost(@ModelAttribute loginForm: LoginForm, result: BindingResult): String {
        print("User $loginForm.getUsername() attempted login");
        if (result.hasErrors())
            return "login"

        return "redirect:/loginSuccess";
    }

    @GetMapping("/loginSuccess")
    fun loginSuccess(): String {
        return "loginSuccess";
    }

    @GetMapping("loginFailure")
    fun loginFailure(): String {
        return "loginFailure";
    }
}