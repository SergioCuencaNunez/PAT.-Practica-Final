package com.example.demo.controller;

import com.example.demo.model.Hotel;
import com.example.demo.service.HotelService;
import com.example.demo.service.dto.HotelHabitacionDTO;
import com.example.demo.service.dto.HotelReservaDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class HotelController {

    @Autowired
    private HotelService hotelServicio;

    @Transactional
    @GetMapping("/hoteles/nombre/{nombre}")
    public ResponseEntity<Hotel> getHotelNombre(@PathVariable("nombre") String nombre) {
        Hotel hotel= hotelServicio.getHotelbyNombre(nombre);
        if(hotel != null){
            return ResponseEntity.ok().body(hotel);
        }
        return ResponseEntity.notFound().build();
    }

    @Transactional
    @GetMapping("/hoteles")
    public ResponseEntity<List<Hotel>> getAllHoteles() {
        List<Hotel> hoteles = hotelServicio.getHoteles();
        return ResponseEntity.ok().body(hoteles);
    }

    @Transactional
    @PutMapping("/hoteles/update/ampliar/habitacionesTotales/{nombre}/{habitacionesTotales}")
    public ResponseEntity<String> updateAmpliarHotelHabitacionesTotalesNombre(@PathVariable("nombre") String nombre, @PathVariable("habitacionesTotales") String habitacionesTotalesStr) {
        Long habitacionesTotales = Long.parseLong(habitacionesTotalesStr);
        Hotel hotel= hotelServicio.updateAmpliarHotelHabitacionesTotalesbyNombre(nombre,habitacionesTotales);
        if(hotel != null){
            return new ResponseEntity<>("", HttpStatus.OK);
        }
        return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
    }

    @Transactional
    @PutMapping("/hoteles/update/reducir/habitacionesTotales/{nombre}/{habitacionesTotales}")
    public ResponseEntity<String> updateReducirHotelHabitacionesTotalesNombre(@PathVariable("nombre") String nombre, @PathVariable("habitacionesTotales") String habitacionesTotalesStr) {
        Long habitacionesTotales = Long.parseLong(habitacionesTotalesStr);
        Hotel hotel= hotelServicio.updateReducirHotelHabitacionesTotalesbyNombre(nombre,habitacionesTotales);
        if(hotel != null){
            return new ResponseEntity<>("", HttpStatus.OK);
        }
        return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
    }

    @Transactional
    @PutMapping("/hoteles/update/estado/{nombre}/{estado}")
    public ResponseEntity<String> updateHotelEstadoNombre(@PathVariable("nombre") String nombre, @PathVariable("estado") String estadoStr) {
        Boolean estado = Boolean.parseBoolean(estadoStr);
        Hotel hotel= hotelServicio.updateHotelEstadobyNombre(nombre,estado);
        if(hotel != null){
            return new ResponseEntity<>("", HttpStatus.OK);
        }
        return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
    }

    // INNER-JOIN
    @Transactional
    @GetMapping("/hoteles/reservas")
    public ResponseEntity<List<HotelReservaDTO>> getHotelesReservas() {
        var reservas = hotelServicio.getHotelesConReservas();
        return ResponseEntity.ok().body(reservas);
    }

    // INNER-JOIN
    @Transactional
    @GetMapping("/hoteles/habitaciones")
    public ResponseEntity<List<HotelHabitacionDTO>> getHotelesHabitaciones() {
        var habitaciones = hotelServicio.getHotelesConHabitaciones();
        return ResponseEntity.ok().body(habitaciones);
    }
}