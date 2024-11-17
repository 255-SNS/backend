package com.sns255.sns255;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

public class HelloController {

    @GetMapping("/")
    public String sayHello() {
        return "Hello Java";
    }

}
