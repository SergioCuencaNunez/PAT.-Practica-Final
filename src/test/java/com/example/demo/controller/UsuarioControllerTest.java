/*package com.example.demo.controller;

import com.example.demo.model.Habitacion;
import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public final class UsuarioControllerTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getUsuarioNif(){
        Optional<Habitacion> ousuario = usuarioRepository.findById("");
        Habitacion habitacion = ohabitacion.get();

        String url = "http://localhost:" + Integer.toString(port) + "/api/v1/habitaciones/tipo/Marylebone-Suite";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity entity = new HttpEntity<>(headers);

        ResponseEntity<Habitacion> result = testRestTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<Habitacion>(){}
        );

        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(result.getBody()).isEqualTo(habitacion);
    }

    @Test
    public void insertarUsuario_ok(){

        //Given
        String address = "http://localhost:" + port + "/api/v1/usuarios/insert-cliente";
        Usuario usuario = new Usuario();

        usuario.setNif("78272775A");
        usuario.setNombre("Mario");
        usuario.setApellido1("Conde");
        usuario.setApellido2("Villarejo");
        usuario.setCorreo("mario.conde.villarejo@yahoo.com");
        usuario.setContrasena("MarioCondeVillarejo03");
        String date = "2001-04-15";
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
        String address = "http://localhost:" + port + "/api/v1/usuarios/insert-cliente";
        Usuario usuario1 = new Usuario();
        Usuario usuario2 = new Usuario();

        //NIF registrado
        usuario1.setNif("74575576P");
        usuario1.setNombre("Laura");
        usuario1.setApellido1("Hernandez");
        usuario1.setApellido2("Pardo");
        usuario1.setCorreo("laura@gmail.com");
        usuario1.setContrasena("LauritaaHeranP09");
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
        usuario.setContrasena("LauritaaHeranP09");
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
        String address = "http://localhost:" + port + "/api/v1/usuarios/registro-cliente";
        LoginCredential loginc = new LoginCredential("Javier","Barneda","Castillejo","68060671Z","2000-02-08","javier_barneda@gmail.com","JavierBarneda654","JavierBarneda654");

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
        String address = "http://localhost:" + port + "/api/v1/usuarios/registro-cliente";
        //No coincide contrasena1 y contrasena2
        LoginCredential loginc1 = new LoginCredential("Javier","Barneda","Castillejo","68060671Z","2000-02-08","javier_barneda@gmail.com","JavierBarneda653","JavierBarneda654");
        //Nombre vacío
        LoginCredential loginc2 = new LoginCredential("","Barneda","Castillejo","6806067Z","2000-02-08","javier_barneda@gmail.com","JavierBarneda654","JavierBarneda654");

        HttpHeaders headers = new HttpHeaders();
        HttpEntity <LoginCredential> request1 = new HttpEntity<>(loginc1, headers);
        HttpEntity <LoginCredential> request2 = new HttpEntity<>(loginc2, headers);

        //When
        ResponseEntity<LoginResponse> result1 = this.restTemplate.postForEntity(address, request1, LoginResponse.class);
        ResponseEntity<LoginResponse> result2 = this.restTemplate.postForEntity(address, request2, LoginResponse.class);

        //Then
        then(result1.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
        then(result2.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

    }
}*/