package com.example.demo.controller;

import com.example.demo.controller.LoginResponse;
import com.example.demo.model.Contacto;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

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
        contacto.setNumero(1L);
        contacto.setCorreo("econderana@gmail.com");
        contacto.setNombre("Ana");
        contacto.setMensaje("Prueba de Testing");
        HttpHeaders headers = new HttpHeaders();
        HttpEntity <Contacto> request = new HttpEntity<>(contacto, headers);

        //When
        ResponseEntity<LoginResponse> result = this.restTemplate.postForEntity(address, request, LoginResponse.class);

        //Then
        then(result.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

    }

}
