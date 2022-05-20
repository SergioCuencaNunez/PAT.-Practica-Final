package com.example.demo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SecureControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int port;
    @Test
    public void autorizacion(){


        String url = "http://localhost:" + Integer.toString(port) + "/api/v1/secure";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity entity = new HttpEntity<>(headers);

        ResponseEntity<String> result = testRestTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<String>(){}
        );

        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

}
