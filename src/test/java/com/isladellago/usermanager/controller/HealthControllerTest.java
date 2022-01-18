package com.isladellago.usermanager.controller;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class HealthControllerTest {

    private HealthController healthController = new HealthController();

    @Test
    public final void testHealth() {
        final ResponseEntity<String> response =
                healthController.health();

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
