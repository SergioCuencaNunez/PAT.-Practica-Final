package com.example.demo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public final class ReservaControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
        public void insertarReserva_ok(){

            //Given
            String address = "http://localhost:" + port + "/api/v1/reservas/insert";
            Reserva reserva = new Reserva();

            reserva.setId(null);
            reserva.setHotel("Melia-Madrid-Princesa");
            reserva.setDestino("Madrid");
            reserva.setTipo("Suite");
            reserva.setHuespedes(2);
            reserva.setHabitaciones(1);
            reserva.setFechaEntrada("2022-05-17");
            reserva.setFechaSalida("2022-05-25");

            //When
            ResponseEntity<LoginResponse> result = this.restTemplate.postForEntity(address, request, LoginResponse.class);

            //Then
            then(result.getStatusCode()).isEqualTo(HttpStatus.OK);

        }

}