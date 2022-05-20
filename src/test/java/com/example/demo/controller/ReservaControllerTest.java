package com.example.demo.controller;


import com.example.demo.model.Reserva;
import com.example.demo.repository.ReservaRepository;
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
public class ReservaControllerTest {

    @Autowired
    private ReservaRepository repository;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int port;

    @Test
    public void getReserva(){

        Optional<Reserva> oreserva = repository.findById(100000L);
        Reserva reserva = oreserva.get();

        String url = "http://localhost:" + Integer.toString(port) + "/api/v1/reservas/id/100000";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity entity = new HttpEntity<>(headers);

        ResponseEntity<Reserva> result = testRestTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<Reserva>(){}
        );

        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(result.getBody()).isEqualTo(reserva);
    }

    @Test
    public void getReservasByNif(){

        List<Reserva> reservas = repository.getReservasByNif("35980493Y");

        String url = "http://localhost:" + Integer.toString(port) + "/api/v1/reservas/nif/35980493Y";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity entity = new HttpEntity<>(headers);

        ResponseEntity<List<Reserva>> result = testRestTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<Reserva>>(){}
        );

        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(result.getBody()).isEqualTo(reservas);
    }

    @Test
    public void getReservasByHotel(){

        List<Reserva> reservas = repository.getReservasByHotel("Gran-Melia-Palacio-de-los-Duques");

        String url = "http://localhost:" + Integer.toString(port) + "/api/v1/reservas/hotel/Gran-Melia-Palacio-de-los-Duques";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity entity = new HttpEntity<>(headers);

        ResponseEntity<List<Reserva>> result = testRestTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<Reserva>>(){}
        );

        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(result.getBody()).isEqualTo(reservas);
    }

    @Test
    public void getReservas(){

        Iterable<Reserva> reservas = repository.findAll();

        String url = "http://localhost:" + Integer.toString(port) + "/api/v1/reservas";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity entity = new HttpEntity<>(headers);

        ResponseEntity<List<Reserva>> result = testRestTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<Reserva>>(){}
        );

        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(result.getBody()).isEqualTo(reservas);
    }

    @Test
    public void actualizarTipoById(){
        String url = "http://localhost:" + Integer.toString(port) + "/api/v1/reservas/update/tipo/100000/Suite";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity entity = new HttpEntity<>(headers);

        ResponseEntity<String> result = testRestTemplate.exchange(
                url,
                HttpMethod.PUT,
                entity,
                new ParameterizedTypeReference<String>(){}
        );

        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(repository.getTipoById(100000L)).isEqualTo("Suite");
    }

    @Test
    public void actualizarHuespedesById(){
        String url = "http://localhost:" + Integer.toString(port) + "/api/v1/reservas/update/huespedes/100001/3";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity entity = new HttpEntity<>(headers);

        ResponseEntity<String> result = testRestTemplate.exchange(
                url,
                HttpMethod.PUT,
                entity,
                new ParameterizedTypeReference<String>(){}
        );

        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(repository.getHuespedesById(100001L)).isEqualTo(3);
    }

    @Test
    public void actualizarFechaEntradaById(){
        String url = "http://localhost:" + Integer.toString(port) + "/api/v1/reservas/update/fechaEntrada/100001/2022-05-20";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity entity = new HttpEntity<>(headers);

        ResponseEntity<String> result = testRestTemplate.exchange(
                url,
                HttpMethod.PUT,
                entity,
                new ParameterizedTypeReference<String>(){}
        );

        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(repository.getFechaEntradaReservaById(100001L)).isEqualTo("2022-05-20");
    }

    @Test
    public void actualizarFechaSalidaById(){
        String url = "http://localhost:" + Integer.toString(port) + "/api/v1/reservas/update/fechaSalida/100001/2022-05-27";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity entity = new HttpEntity<>(headers);

        ResponseEntity<String> result = testRestTemplate.exchange(
                url,
                HttpMethod.PUT,
                entity,
                new ParameterizedTypeReference<String>(){}
        );

        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(repository.getFechaSalidaReservaById(100001L)).isEqualTo("2022-05-27");
    }
/*
    @Test
    public void getDisponibilidadHotel(){

        ReservaResponse reserva = repository.getDisponibilidadByHotel("Gran-Melia-Palacio-de-los-Duques",100);

        String url = "http://localhost:" + Integer.toString(port) + "/api/v1/reservas/hotel/Gran-Melia-Palacio-de-los-Duques";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity entity = new HttpEntity<>(headers);

        ResponseEntity<List<Reserva>> result = testRestTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<Reserva>>(){}
        );

        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(result.getBody()).isEqualTo(reservas);
    }
*/


}
