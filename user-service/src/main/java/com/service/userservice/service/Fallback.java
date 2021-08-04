package com.service.userservice.service;

import com.service.userservice.model.Link;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Component
public class Fallback implements ServiceFeignClient {

//    private static final Logger LOG = Logger.getLogger(TestService.class.getName());
    Logger logger = Logger.getLogger(Fallback.class.getName());

    @Override
    public List<Link> getAllEmployeesList() {
//        LOG.log(Level.INFO, "ERROR: Service is not available now");
        logger.info("ERROR: Service is not available now");
        return new ArrayList<>();
    }

}
