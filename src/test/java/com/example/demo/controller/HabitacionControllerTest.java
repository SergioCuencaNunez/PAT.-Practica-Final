package com.example.demo.controller;

import com.example.demo.model.Habitacion;
import com.example.demo.repository.HabitacionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HabitacionControllerTest {

    @Autowired
    private HabitacionRepository habitacionRepository;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    public int port;

    @Test
    public void getHabitacionTipo(){
        Optional<Habitacion> ohabitacion = habitacionRepository.findById("Marylebone-Suite");
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
    public void getHabitacionesHotel(){
        List<Habitacion> habitaciones = habitacionRepository.getHabitacionesByHotel("Melia-White-House");

        String url = "http://localhost:" + Integer.toString(port) + "/api/v1/habitaciones/hotel/Melia-White-House";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity entity = new HttpEntity<>(headers);

        ResponseEntity<List<Habitacion>> result = testRestTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<Habitacion>>(){}
        );

        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(result.getBody()).isEqualTo(habitaciones);
    }

    @Test
    public void getHabitacionesCapacidad(){
        Long capacidad = habitacionRepository.getHabitacionCapacidadByHotel("Melia-White-House");

        String url = "http://localhost:" + Integer.toString(port) + "/api/v1/habitaciones/capacidad/Melia-White-House";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity entity = new HttpEntity<>(headers);

        ResponseEntity<List<Habitacion>> result = testRestTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<Habitacion>>(){}
        );

        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(result.getBody()).isEqualTo(capacidad);
    }

    @Test
    public void getHabitaciones(){
        Iterable<Habitacion> habitaciones = habitacionRepository.findAll();

        String url = "http://localhost:" + Integer.toString(port) + "/api/v1/habitaciones";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity entity = new HttpEntity<>(headers);

        ResponseEntity<List<Habitacion>> result = testRestTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<Habitacion>>(){}
        );

        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(result.getBody()).isEqualTo(habitaciones);
    }

    @Test
    public void actualizarCapacidadHabitacion(){
        String url = "http://localhost:" + Integer.toString(port) + "/api/v1/habitaciones/update/capacidad/6/Premium";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity entity = new HttpEntity<>(headers);

        ResponseEntity<String> result = testRestTemplate.exchange(
                url,
                HttpMethod.PUT,
                entity,
                new ParameterizedTypeReference<String>(){}
        );

        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(habitacionRepository.getHabitacionCapacidadByHotel("Premium")).isEqualTo(6);
    }

}
