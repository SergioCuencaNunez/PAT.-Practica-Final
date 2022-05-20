package com.example.demo.controller;

import com.example.demo.model.Contacto;
import com.example.demo.repository.ContactoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public final class ContactoControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ContactoRepository contactoRepository;

    @Test
    public void getContactoNumero(){
        Contacto contacto = contactoRepository.getContactoByNumero(2L);

        String url = "http://localhost:" + Integer.toString(port) + "/api/v1/contactos/numero/2";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<Contacto> result = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<Contacto>(){}
        );

        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(result.getBody()).isEqualTo(contacto);

    }

    @Test
    public void getContactoCorreo(){
        Contacto contacto = contactoRepository.getContactoByCorreo("laura.h@gmail.com");

        String url = "http://localhost:" + Integer.toString(port) + "/api/v1/contactos/correo/laura.h@gmail.com";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<Contacto> result = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<Contacto>(){}
        );

        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(result.getBody()).isEqualTo(contacto);

    }

    @Test
    public void getMensajeCorreo(){

        String mensaje = contactoRepository.getMensajeByCorreo("laura.h@gmail.com");

        String url = "http://localhost:" + Integer.toString(port) + "/api/v1/contactos/mensaje/laura.h@gmail.com";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> result = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<String>(){}
        );

        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(result.getBody()).isEqualTo(mensaje);
    }

    @Test
    public void getAllContactos(){
        Iterable<Contacto> contactos = contactoRepository.findAll();

        String url = "http://localhost:" + Integer.toString(port) + "/api/v1/contactos";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<List<Contacto>> result = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<Contacto>>(){}
        );

        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(result.getBody()).isEqualTo(contactos);
    }

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
/*
    @Test
    public void insertarCheckIn_ok(){

        String address = "http://localhost:" + Integer.toString(port) + "/api/v1/contactos/insert-checkIn";
        Contacto contacto = new Contacto();



    }
*/
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