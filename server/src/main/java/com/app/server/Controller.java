package com.app.server;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    // Define a GET endpoint at "/mydata"
    @GetMapping("/mtlsData")
    public String getData() {
        return "Hello, this is your data!";
    }
}
