package com.service.userservice.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TestService {

    private static final Logger LOG = Logger.getLogger(TestService.class.getName());

//    @Autowired
//    private EurekaClient eurekaClient;

    @Autowired
    private RestTemplate template;

    @HystrixCommand(fallbackMethod = "failed")
    public String data() {
        String response = template.getForObject("http://gallery-service/data", String.class);
        LOG.log(Level.INFO, response);
        return response;
    }

    public String failed() {
        String error = "Service is not available now. Please try later";
        LOG.log(Level.INFO, error);
        return error;
    }

}
