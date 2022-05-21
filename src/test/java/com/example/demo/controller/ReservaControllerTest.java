package com.example.demo.controller;


import com.example.demo.model.Contacto;
import com.example.demo.model.Reserva;
import com.example.demo.repository.ReservaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import java.time.LocalDate;
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

    @Test
    public void Reserva_ok(){

        //Given
        String address = "http://localhost:" + port + "/api/v1/reservas/check-in";
        Reserva reserva = new Reserva();

        reserva.setId(100016L);
        reserva.setNif("15563677Z");
        reserva.setHotel("TRYP-New-York-Times-Square");
        reserva.setDestino("Nueva-York");
        reserva.setFechaEntrada(LocalDate.parse("2022-05-17"));
        reserva.setFechaSalida(LocalDate.parse("2022-05-20"));

        HttpHeaders headers = new HttpHeaders();
        HttpEntity <Reserva> request = new HttpEntity<>(reserva, headers);

        //When
        ResponseEntity<ReservaResponse> result = this.testRestTemplate.postForEntity(address, request, ReservaResponse.class);

        //Then
        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

    @Test
    public void Reserva_ko(){

        //Given
        String address = "http://localhost:" + port + "/api/v1/reservas/check-in";
        //Id erróneo
        Reserva reserva1 = new Reserva();
        //Nif erróneo y Destino erróneo
        Reserva reserva2 = new Reserva();

        reserva1.setId(100017L);
        reserva1.setNif("15563677Z");
        reserva1.setHotel("TRYP-New-York-Times-Square");
        reserva1.setDestino("Nueva-York");
        reserva1.setFechaEntrada(LocalDate.parse("2022-05-17"));
        reserva1.setFechaSalida(LocalDate.parse("2022-05-20"));

        reserva2.setId(100016L);
        reserva2.setNif("15564577Z");
        reserva2.setHotel("TRYP-New-York-Times-Square");
        reserva2.setDestino("Washington");
        reserva2.setFechaEntrada(LocalDate.parse("2022-05-17"));
        reserva2.setFechaSalida(LocalDate.parse("2022-05-20"));



        HttpHeaders headers = new HttpHeaders();
        HttpEntity <Reserva> request1 = new HttpEntity<>(reserva1, headers);
        HttpEntity <Reserva> request2 = new HttpEntity<>(reserva2, headers);

        //When
        ResponseEntity<ReservaResponse> result1 = this.testRestTemplate.postForEntity(address, request1, ReservaResponse.class);
        ResponseEntity<ReservaResponse> result2 = this.testRestTemplate.postForEntity(address, request2, ReservaResponse.class);

        //Then
        then(result1.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        then(result2.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

    }

    @Test
    public void hacerReserva_ok(){

        //Given
        String address = "http://localhost:" + port + "/api/v1/reservas/insert";
        Reserva reserva = new Reserva();

        reserva.setId(null);
        reserva.setNif("15563677Z");
        reserva.setHotel("TRYP-New-York-Times-Square");
        reserva.setDestino("Nueva-York");
        reserva.setTipo("King");
        reserva.setHabitaciones(2L);
        reserva.setHuespedes(3L);
        reserva.setFechaEntrada(LocalDate.parse("2022-08-17"));
        reserva.setFechaSalida(LocalDate.parse("2022-08-20"));

        HttpHeaders headers = new HttpHeaders();
        HttpEntity <Reserva> request = new HttpEntity<>(reserva, headers);

        //When
        ResponseEntity<ReservaResponse> result = this.testRestTemplate.postForEntity(address, request, ReservaResponse.class);

        //Then
        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

    @Test
    public void hacerReserva_ko(){

        //Given
        String address = "http://localhost:" + port + "/api/v1/reservas/check-in";
        //Id existe
        Reserva reserva1 = new Reserva();
        //Demasiadas habitaciones
        Reserva reserva2 = new Reserva();

        reserva1.setId(100017L);
        reserva1.setNif("15563677Z");
        reserva1.setHotel("TRYP-New-York-Times-Square");
        reserva1.setDestino("Nueva-York");
        reserva1.setTipo("King");
        reserva1.setHabitaciones(2L);
        reserva1.setHuespedes(3L);
        reserva1.setFechaEntrada(LocalDate.parse("2022-05-17"));
        reserva1.setFechaSalida(LocalDate.parse("2022-05-20"));

        reserva2.setId(100069L);
        reserva2.setNif("15564577Z");
        reserva2.setHotel("TRYP-New-York-Times-Square");
        reserva2.setDestino("Washington");
        reserva2.setTipo("King");
        reserva2.setHabitaciones(8L);
        reserva2.setHuespedes(3L);
        reserva2.setFechaEntrada(LocalDate.parse("2022-05-17"));
        reserva2.setFechaSalida(LocalDate.parse("2022-05-20"));



        HttpHeaders headers = new HttpHeaders();
        HttpEntity <Reserva> request1 = new HttpEntity<>(reserva1, headers);
        HttpEntity <Reserva> request2 = new HttpEntity<>(reserva2, headers);

        //When
        ResponseEntity<ReservaResponse> result1 = this.testRestTemplate.postForEntity(address, request1, ReservaResponse.class);
        ResponseEntity<ReservaResponse> result2 = this.testRestTemplate.postForEntity(address, request2, ReservaResponse.class);

        //Then
        then(result1.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        then(result2.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

    }
    @Test
    public void borrarReserva(){
        String url = "http://localhost:" + Integer.toString(port) + "/api/v1/reservas/delete/100017";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity entity = new HttpEntity<>(headers);

        ResponseEntity<String> result = testRestTemplate.exchange(
                url,
                HttpMethod.DELETE,
                entity,
                new ParameterizedTypeReference<String>(){}
        );

        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(repository.findById(100017L)).isEqualTo(Optional.empty());
    }
}
