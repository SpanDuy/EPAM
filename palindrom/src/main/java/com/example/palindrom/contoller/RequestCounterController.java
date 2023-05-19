package com.example.palindrom.contoller;

import com.example.palindrom.counters.CounterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestCounterController {
    private static final Logger logger = LoggerFactory.getLogger(Controller.class);
    private CounterService counterService;
    @Autowired
    public RequestCounterController(CounterService counterService) {
        this.counterService = counterService;
    }
    //@GetMapping("/counter")
    @GetMapping(value = "/counter", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getCounter() {
        logger.info("Visited RequestCounterController");
        return new ResponseEntity<>(counterService, HttpStatus.OK);
    }
    @GetMapping("/reset")
    public String resetCounter() {
        logger.info("Reset RequestCounterController");
        counterService.cleanCountService();
        return "Reseted";
    }
}
