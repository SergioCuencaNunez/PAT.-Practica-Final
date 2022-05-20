package com.example.demo.controller;

import com.example.demo.model.Hotel;
import com.example.demo.repository.HotelRepository;
import com.example.demo.service.dto.HotelHabitacionDTO;
import com.example.demo.service.dto.HotelReservaDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HotelControllerTest {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int port;

    @Test
    public void getHotelByNombre(){

        Hotel hotel = hotelRepository.getNombre("Melia-Madrid-Princesa");

        String url = "http://localhost:" + Integer.toString(port) + "/api/v1/hoteles/nombre/Melia-Madrid-Princesa";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity entity = new HttpEntity<>(headers);

        ResponseEntity<Hotel> result = testRestTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<Hotel>(){}
        );

        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(result.getBody()).isEqualTo(hotel);
    }


    @Test
    public void getAllHoteles(){

        Iterable<Hotel> hoteles = hotelRepository.findAll();

        String url = "http://localhost:" + Integer.toString(port) + "/api/v1/hoteles";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity entity = new HttpEntity<>(headers);

        ResponseEntity<Iterable<Hotel>> result = testRestTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<Iterable<Hotel>>(){}
        );

        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(result.getBody()).isEqualTo(hoteles);
    }

    @Test
    public void ampliarHabitacionesHotel(){
        String url = "http://localhost:" + Integer.toString(port) + "/api/v1/hoteles/update/ampliar/habitacionesTotales/Melia-White-House/16";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity entity = new HttpEntity<>(headers);

        ResponseEntity<String> result = testRestTemplate.exchange(
                url,
                HttpMethod.PUT,
                entity,
                new ParameterizedTypeReference<String>(){}
        );

        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(hotelRepository.getHabitacionesTotalesByHotel("Melia-White-House")).isEqualTo(26);
    }

    @Test
    public void actualizarEstadoHotel(){
        String url = "http://localhost:" + Integer.toString(port) + "/api/v1/hoteles/update/estado/Melia-White-House/false";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity entity = new HttpEntity<>(headers);

        ResponseEntity<String> result = testRestTemplate.exchange(
                url,
                HttpMethod.PUT,
                entity,
                new ParameterizedTypeReference<String>(){}
        );

        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(hotelRepository.getEstadoByNombre("Melia-White-House")).isEqualTo(false);
    }
    @Test
    public void reducirHabitacionesHotel(){
        String url = "http://localhost:" + Integer.toString(port) + "/api/v1/hoteles/update/reducir/habitacionesTotales/ME-London/6";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity entity = new HttpEntity<>(headers);

        ResponseEntity<String> result = testRestTemplate.exchange(
                url,
                HttpMethod.PUT,
                entity,
                new ParameterizedTypeReference<String>(){}
        );

        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(hotelRepository.getHabitacionesTotalesByHotel("ME-London")).isEqualTo(6);
    }
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    public void hotelesReservasJoin(){

        String query = """ 
        SELECT HOTEL.NOMBRE, HOTEL.DESTINO, HOTEL.CAPACIDAD, HOTEL.OCUPACION, HOTEL.ESTADO, RESERVA.ID, RESERVA.NIF, RESERVA.HUESPEDES, RESERVA.HABITACIONES, RESERVA.FECHAENTRADA, RESERVA.FECHASALIDA
        FROM RESERVA
        INNER JOIN HOTEL ON HOTEL.NOMBRE=RESERVA.HOTEL;
        """;

        List<HotelReservaDTO> hotelesLista = jdbcTemplate.query(
                query,
                (rs, rowNum) ->
                        new HotelReservaDTO(
                                rs.getString("NOMBRE"),
                                rs.getString("DESTINO"),
                                rs.getLong("CAPACIDAD"),
                                rs.getLong("OCUPACION"),
                                rs.getBoolean("ESTADO"),
                                rs.getLong("ID"),
                                rs.getString("NIF"),
                                rs.getLong("HUESPEDES"),
                                rs.getLong("HABITACIONES"),
                                (rs.getTimestamp("FECHAENTRADA")!=null) ? rs.getTimestamp("FECHAENTRADA").toLocalDateTime() : null,
                                (rs.getTimestamp("FECHASALIDA")!=null) ? rs.getTimestamp("FECHASALIDA").toLocalDateTime() : null
                        )
        );

        String url = "http://localhost:" + Integer.toString(port) + "/api/v1/hoteles/reservas";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<Iterable<HotelReservaDTO>> result = testRestTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<Iterable<HotelReservaDTO>>(){}
        );

        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(result.getBody()).isEqualTo(hotelesLista);
    }

    @Test
    public void hotelesHabitacionesJoin(){

        String query = """ 
        SELECT HOTEL.NOMBRE, HOTEL.DESTINO, HOTEL.CAPACIDAD, HOTEL.OCUPACION, HOTEL.ESTADO, HABITACION.TIPO, HABITACION.CAPACIDAD
        FROM HABITACION
        INNER JOIN HOTEL ON HOTEL.NOMBRE=HABITACION.HOTEL;
        """;

        List<HotelHabitacionDTO> hotelesLista = jdbcTemplate.query(
                query,
                (rs, rowNum) ->
                        new HotelHabitacionDTO(
                                rs.getString("HOTEL.NOMBRE"),
                                rs.getString("HOTEL.DESTINO"),
                                rs.getLong("HOTEL.CAPACIDAD"),
                                rs.getLong("HOTEL.OCUPACION"),
                                rs.getBoolean("HOTEL.ESTADO"),
                                rs.getString("HABITACION.TIPO"),
                                rs.getLong("HABITACION.CAPACIDAD")
                        )
        );

        String url = "http://localhost:" + Integer.toString(port) + "/api/v1/hoteles/habitaciones";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<Iterable<HotelHabitacionDTO>> result = testRestTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<Iterable<HotelHabitacionDTO>>(){}
        );

        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(result.getBody()).isEqualTo(hotelesLista);
    }


}
