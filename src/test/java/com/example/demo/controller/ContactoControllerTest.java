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
        contacto.setMensaje("Prueba de Testing");
        HttpHeaders headers = new HttpHeaders();
        HttpEntity <Contacto> request = new HttpEntity<>(contacto, headers);

        //When
        ResponseEntity<LoginResponse> result = this.restTemplate.postForEntity(address, request, LoginResponse.class);

        //Then
        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

    @Test
    public void insertarMensajes_ko(){

        //Given
        String address = "http://localhost:" + port + "/api/v1/contactos/insert-mensaje";
        Contacto contacto = new Contacto();

        //email no registrado en Contacto
        contacto.setNumero(4L);
        contacto.setCorreo("elena@gmail.com");
        contacto.setNombre("Elena");
        contacto.setMensaje("Prueba Fallida de Testing");
        HttpHeaders headers = new HttpHeaders();
        HttpEntity <Contacto> request = new HttpEntity<>(contacto, headers);

        //When
        ResponseEntity<LoginResponse> result = this.restTemplate.postForEntity(address, request, LoginResponse.class);

        //Then
        then(result.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

    }


    @Test
    public void insertarSuscripcion_ok(){

        //Given
        String address = "http://localhost:" + port + "/api/v1/contactos/insert-suscripcion";
        Contacto contacto = new Contacto();

        //email no registrado en Contacto
        contacto.setNumero(5L);
        contacto.setCorreo("elena@gmail.com");
        contacto.setNombre("Elena");
        contacto.setMensaje("Prueba de Testing");
        HttpHeaders headers = new HttpHeaders();
        HttpEntity <Contacto> request = new HttpEntity<>(contacto, headers);

        //When
        ResponseEntity<LoginResponse> result = this.restTemplate.postForEntity(address, request, LoginResponse.class);

        //Then
        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

    @Test
    public void insertarSuscripcion_ko(){

        //Given
        String address = "http://localhost:" + port + "/api/v1/contactos/insert-suscripcion";
        Contacto contacto1 = new Contacto();
        Contacto contacto2 = new Contacto();
        Contacto contacto3 = new Contacto();

        //Correo contenido en Contactos
        contacto1.setNumero(6L);
        contacto1.setCorreo("javier_barneda@gmail.com");
        contacto1.setNombre("Javier");
        contacto1.setMensaje("Querria saber más información acerca del hotel Melia Madrid Princesa. Gracias");

        //Correo vacío
        contacto2.setNumero(7L);
        contacto2.setCorreo("");
        contacto2.setNombre("Ana");
        contacto2.setMensaje("Testing Fallido 1");

        //Correo contenido en Usuarios
        contacto3.setNumero(8L);
        contacto3.setCorreo("econderana@melia.com");
        contacto3.setNombre("Elena");
        contacto3.setMensaje("Prueba Fallida Testing");

        HttpHeaders headers = new HttpHeaders();
        HttpEntity <Contacto> request1 = new HttpEntity<>(contacto1, headers);
        HttpEntity <Contacto> request2 = new HttpEntity<>(contacto2, headers);
        HttpEntity <Contacto> request3 = new HttpEntity<>(contacto3, headers);

        //When
        ResponseEntity<LoginResponse> result1 = this.restTemplate.postForEntity(address, request1, LoginResponse.class);
        ResponseEntity<LoginResponse> result2 = this.restTemplate.postForEntity(address, request2, LoginResponse.class);
        ResponseEntity<LoginResponse> result3 = this.restTemplate.postForEntity(address, request3, LoginResponse.class);

        //Then
        then(result1.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        then(result2.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        then(result3.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);


    }

}