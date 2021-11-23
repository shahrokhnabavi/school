package com.mary.school.general;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GeneralController {

    @GetMapping
    public String home() {
        return "Home Page";
    }

    @GetMapping("/about")
    public String about() {
        return "About Page";
    }

    @GetMapping("/contact-us")
    public String contact() {
        return "Contact us Page";
    }
}
