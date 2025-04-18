package com.podcast.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//Send users to the default Swagger page using the url "/api/documentation".
@Controller
public class SwaggerRedirectController {

    @GetMapping("/api/documentation")
    public String redirectToSwagger() {
        return "redirect:/swagger-ui/index.html";
    }
}

