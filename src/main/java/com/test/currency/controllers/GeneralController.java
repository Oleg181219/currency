package com.test.currency.controllers;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@Hidden
@Tag(name="General Controller", description="Стартовый контроллер")
public class GeneralController {





    @GetMapping("/")
    public String root(Model model) {
        model.addAttribute("name", "Main page");
        return "redirect:/swagger-ui.html";
    }


}
