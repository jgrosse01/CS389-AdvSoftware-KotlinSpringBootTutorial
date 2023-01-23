package edu.carroll.cs389.web.controller

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam

@Controller
class IndexController {
    @GetMapping("/")
    fun index(@RequestParam(value="name", defaultValue="Student")name: String, model: Model): String {
        model.addAttribute("name", name);
        return "index";
    }
}