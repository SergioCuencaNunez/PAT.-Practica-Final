package com.example.demo.controller;

import com.example.demo.model.Contacto;
import com.example.demo.model.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public final class UsuarioControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void insertarUsuario_ok(){

        //Given
        String address = "http://localhost:" + port + "/api/v1/usuarios/insert";
        Usuario usuario = new Usuario();

        usuario.setNif("51507247N");
        usuario.setNombre("Elena");
        usuario.setApellido1("Conderana");
        usuario.setApellido2("Medem");
        usuario.setCorreo("ecm@gmail.com");
        usuario.setContrasena("1234");
        String date = "2000-08-16";
        LocalDate localDate = LocalDate.parse(date);
        usuario.setCumpleanos(localDate);
        usuario.setRol("cliente");
        HttpHeaders headers = new HttpHeaders();
        HttpEntity <Usuario> request = new HttpEntity<>(usuario, headers);

        //When
        ResponseEntity<LoginResponse> result = this.restTemplate.postForEntity(address, request, LoginResponse.class);

        //Then
        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

    @Test
    public void insertarUsuario_ko(){

        //Given
        String address = "http://localhost:" + port + "/api/v1/usuarios/insert";
        Usuario usuario1 = new Usuario();
        Usuario usuario2 = new Usuario();

        //NIF registrado
        usuario1.setNif("74575576P");
        usuario1.setNombre("Laura");
        usuario1.setApellido1("Hernandez");
        usuario1.setApellido2("Pardo");
        usuario1.setCorreo("laura@gmail.com");
        usuario1.setContrasena("Lauritaa");
        String date1 = "2000-08-27";
        LocalDate localDate1 = LocalDate.parse(date1);
        usuario1.setCumpleanos(localDate1);
        usuario1.setRol("cliente");

        //email registrado
        usuario2.setNif("74578790P");
        usuario2.setNombre("Laura");
        usuario2.setApellido1("Hernandez");
        usuario2.setApellido2("Pardo");
        usuario2.setCorreo("laura.h@gmail.com");
        usuario2.setContrasena("Lauritaa");
        String date2 = "2000-08-27";
        LocalDate localDate2 = LocalDate.parse(date2);
        usuario2.setCumpleanos(localDate2);
        usuario2.setRol("cliente");

        HttpHeaders headers = new HttpHeaders();
        HttpEntity <Usuario> request1 = new HttpEntity<>(usuario1, headers);
        HttpEntity <Usuario> request2 = new HttpEntity<>(usuario2, headers);

        //When
        ResponseEntity<LoginResponse> result1 = this.restTemplate.postForEntity(address, request1, LoginResponse.class);
        ResponseEntity<LoginResponse> result2 = this.restTemplate.postForEntity(address, request2, LoginResponse.class);

        //Then
        then(result1.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        then(result2.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

    }

    @Test
    public void inicioSesionCliente_ok(){

        //Given
        String address = "http://localhost:" + port + "/api/v1/usuarios/inicio-sesion";
        Usuario usuario = new Usuario();

        usuario.setNif("74575576P");
        usuario.setNombre("Laura");
        usuario.setApellido1("Hernandez");
        usuario.setApellido2("Pardo");
        usuario.setCorreo("laura.h@gmail.com");
        usuario.setContrasena("Lauritaa");
        String date = "2000-08-27";
        LocalDate localDate = LocalDate.parse(date);
        usuario.setCumpleanos(localDate);
        usuario.setRol("cliente");
        HttpHeaders headers = new HttpHeaders();
        HttpEntity <Usuario> request = new HttpEntity<>(usuario, headers);

        //When
        ResponseEntity<LoginResponse> result = this.restTemplate.postForEntity(address, request, LoginResponse.class);

        //Then
        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

    @Test
    public void inicioSesionCliente_ko(){

        //Given
        String address = "http://localhost:" + port + "/api/v1/usuarios/inicio-sesion";
        Usuario usuario1 = new Usuario();
        Usuario usuario2 = new Usuario();

        //Contraseña errónea
        usuario1.setNif("74575576P");
        usuario1.setNombre("Laura");
        usuario1.setApellido1("Hernandez");
        usuario1.setApellido2("Pardo");
        usuario1.setCorreo("laura.h@gmail.com");
        usuario1.setContrasena("Wrong");
        String date1 = "2000-08-27";
        LocalDate localDate1 = LocalDate.parse(date1);
        usuario1.setCumpleanos(localDate1);
        usuario1.setRol("cliente");

        //email no registrado
        usuario2.setNif("74578790P");
        usuario2.setNombre("Laura");
        usuario2.setApellido1("Hernandez");
        usuario2.setApellido2("Pardo");
        usuario2.setCorreo("incorrecta@gmail.com");
        usuario2.setContrasena("Lauritaa");
        String date2 = "2000-08-27";
        LocalDate localDate2 = LocalDate.parse(date2);
        usuario2.setCumpleanos(localDate2);
        usuario2.setRol("cliente");

        HttpHeaders headers = new HttpHeaders();
        HttpEntity <Usuario> request1 = new HttpEntity<>(usuario1, headers);
        HttpEntity <Usuario> request2 = new HttpEntity<>(usuario2, headers);

        //When
        ResponseEntity<LoginResponse> result1 = this.restTemplate.postForEntity(address, request1, LoginResponse.class);
        ResponseEntity<LoginResponse> result2 = this.restTemplate.postForEntity(address, request2, LoginResponse.class);

        //Then
        then(result1.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        then(result2.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

    }

    @Test
    public void registroCliente_ok(){

        //Given
        String address = "http://localhost:" + port + "/api/v1/usuarios/registro";
        LoginCredential loginc = new LoginCredential("Elena","Conderana","Medem","51507247N","2000-08-16","ecm@gmail.com","1234","1234");

        HttpHeaders headers = new HttpHeaders();
        HttpEntity <LoginCredential> request = new HttpEntity<>(loginc, headers);

        //When
        ResponseEntity<LoginResponse> result = this.restTemplate.postForEntity(address, request, LoginResponse.class);

        //Then
        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

    @Test
    public void registroCliente_ko(){

        //Given
        String address = "http://localhost:" + port + "/api/v1/usuarios/registro";
        //No coincide contrasena1 y contrasena2
        LoginCredential loginc1 = new LoginCredential("Elena","Conderana","Medem","51507247N","2000-08-16","ecm@gmail.com","1234","12");
        //Nombre vacío
        LoginCredential loginc2 = new LoginCredential("","Conderana","Medem","51507247N","2000-08-16","ecm@gmail.com","1234","1234");

        HttpHeaders headers = new HttpHeaders();
        HttpEntity <LoginCredential> request1 = new HttpEntity<>(loginc1, headers);
        HttpEntity <LoginCredential> request2 = new HttpEntity<>(loginc2, headers);

        //When
        ResponseEntity<LoginResponse> result1 = this.restTemplate.postForEntity(address, request1, LoginResponse.class);
        ResponseEntity<LoginResponse> result2 = this.restTemplate.postForEntity(address, request2, LoginResponse.class);

        //Then
        then(result1.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
        then(result2.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);

    }



}