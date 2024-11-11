package com.app.server;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class Controller {
    private final SecurityConfig _securityConfig;
    private final String _mtlsDataString = "Hello, this is your data";
    public Controller(SecurityConfig securityConfig){
        _securityConfig = securityConfig;
    }

    // Define a GET endpoint at "/mydata"
    @GetMapping("/mtlsData")
    public Map<String, String> getData() {
        Map<String, String> response = new HashMap<>();
        response.put("Data", _mtlsDataString);
        return response;
    }
    @GetMapping("/unprotectedData")
    public Map<String, String> getDataUnprotected() {
        Map<String, String> response = new HashMap<>();
        response.put("Data", _mtlsDataString);
        return response;
    }

    @GetMapping("/uniqueClientCount")
    public Map<String, Object> getUniqueClientCount(){
        Map<String, Object> response = new HashMap<>();
        response.put("uniqueClientCount", _securityConfig.getUniqueClientCount());
        return response;

    }
}
