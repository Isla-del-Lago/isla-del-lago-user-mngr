package com.isladellago.usermanager.controller;

import com.isladellago.usermanager.util.PathUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(PathUtils.Health.ROOT_PATH)
@CrossOrigin("*") //NOSONAR
public class HealthController {

    @GetMapping(PathUtils.Health.HEALTH_PATH)
    public final ResponseEntity<String> health() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Service is healthy");
    }
}
