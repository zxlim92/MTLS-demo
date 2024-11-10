package com.app.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OtherService {

    private final RestTemplate restTemplate = new RestTemplate();
    String url ="https://localhost:1234/mtlsData";
    @Scheduled(fixedRate = 5000)
    public void fetchData(){
        try {
            String response = restTemplate.getForObject(url, String.class);
            System.out.println("Data from endpoint: " + response);
        } catch (Exception e) {
            System.err.println("Failed to fetch data: " + e.getMessage());
        }
    }
}
