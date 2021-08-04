package com.service.userservice.controller;

import com.service.userservice.model.Link;
import com.service.userservice.service.ServiceFeignClient;
import com.service.userservice.service.TestService;
import com.service.userservice.service.WebClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/")
public class UserController {

    //    private static final Logger LOG = Logger.getLogger(UserController.class.getName());
    Logger logger = Logger.getLogger(UserController.class.getName());


    @Autowired
    private Environment env;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TestService service;

    @Autowired
    private WebClientService webClientService;

    @RequestMapping("/")
    public String home() {
        String home = "User-Service running at port: " + env.getProperty("local.server.port");
//        LOG.log(Level.INFO, home);
        logger.info(home);
        return home;
    }

    // Using Feign Client
    @RequestMapping(path = "/getAllDataFromGalleryService")
    public List<Link> getDataByFeignClient() {
        List<Link> list = ServiceFeignClient.FeignHolder.create().getAllEmployeesList();
        logger.info("Calling through Feign Client");
        return list;
    }

    // Using RestTemplate
    @GetMapping("/data")
    public String data() {
        logger.info("Calling through RestTemplate");
        return service.data();
    }

    // Using WebClient
    @GetMapping(value = "/getDataByWebClient", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Link> getDataByWebClient() {
        logger.info("Calling through WebClient");
        return webClientService.getDataByWebClient();
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<RuntimeException> handleWebClientResponseException(DataAccessException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RuntimeException("A Link with the same title already exists"));
    }

}
