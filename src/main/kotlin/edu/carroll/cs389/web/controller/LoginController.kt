package edu.carroll.cs389.web.controller

import edu.carroll.cs389.service.LoginService
import edu.carroll.cs389.web.form.LoginForm;
import jakarta.validation.Valid

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes


@Controller
class LoginController(private val loginService: LoginService) {

    @GetMapping("/login")
    fun loginGet(model: Model): String {
        model.addAttribute("loginForm", LoginForm());
        return "login";
    }

    @PostMapping("/login")
    fun loginPost(@Valid @ModelAttribute loginForm: LoginForm, result: BindingResult, attrs: RedirectAttributes): String {
        // IMPLEMENT USER VALIDATION CALLS HERE FOR GLOBAL ERRORS
        print("User ${loginForm.getUsername()} attempted login");
        if (result.hasErrors())
            return "login";

        attrs.addAttribute("username", loginForm.getUsername());
        return "redirect:/loginSuccess";
    }

    @GetMapping("/loginSuccess")
    fun loginSuccess(username: String, model: Model): String {
        model.addAttribute("username", username);
        return "loginSuccess";
    }

    @GetMapping("loginFailure")
    fun loginFailure(): String {
        return "loginFailure";
    }
}