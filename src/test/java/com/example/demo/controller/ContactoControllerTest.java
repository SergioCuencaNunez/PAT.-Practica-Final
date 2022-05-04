package com.example.demo.controller;

import com.example.demo.model.Contacto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public final class ContactoControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void insertarMensajes_ok(){

        //Given
        String address = "http://localhost:" + port + "/api/v1/contactos/insert-mensaje";
        Contacto contacto = new Contacto();

        contacto.setNumero(4L);
        contacto.setCorreo("javier_barneda@gmail.com");
        contacto.setNombre("Javier");
        contacto.setMensaje("Querria saber más información acerca del hotel Melia Madrid Princesa. Gracias");
        HttpHeaders headers = new HttpHeaders();
        HttpEntity <Contacto> request = new HttpEntity<>(contacto, headers);

        //When
        ResponseEntity<LoginResponse> result = this.restTemplate.postForEntity(address, request, LoginResponse.class);

        //Then
        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

}
