package com.service.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class LinkController {

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/getAllLinks")
    public String getAllLinks() {
        return restTemplate.getForObject("http://link-service/getAll", String.class);
    }

   /* @RequestMapping("/get")
    public String getInfoFromLinksService() {
        String result = this.restTemplate.getForObject("http://link-service/", String.class);
        return result;
    }*/

}
