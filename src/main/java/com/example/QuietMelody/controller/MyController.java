package com.example.QuietMelody.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyController {
    @GetMapping("/my")
    public String MyMain(Model model) {
        return "/my/main";
    }

}
