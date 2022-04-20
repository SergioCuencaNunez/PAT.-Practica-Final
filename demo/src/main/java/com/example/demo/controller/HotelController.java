package com.example.demo.controller;

import com.example.demo.model.Hotel;
import com.example.demo.service.HotelService;
import com.example.demo.service.dto.ClienteReservaDTO;
import com.example.demo.service.dto.HotelHabitacionDTO;
import com.example.demo.service.dto.HotelReservaDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
    @GetMapping("/hoteles/destino/{destino}")
    public ResponseEntity<List<Hotel>> getHotelesDestino(@PathVariable("destino") String destino){
        List<Hotel> hoteles = hotelServicio.getHotelesbyDestino(destino);
        return ResponseEntity.ok().body(hoteles);
    }

    @Transactional
    @GetMapping("/hoteles/estado/{estado}")
    public ResponseEntity<List<Hotel>> getHotelesEstado(@PathVariable("estado") String estadoStr){
        Boolean estado = Boolean.parseBoolean(estadoStr);
        List<Hotel> hoteles = hotelServicio.getHotelesbyEstado(estado);
        return ResponseEntity.ok().body(hoteles);
    }

    @Transactional
    @GetMapping("/hoteles")
    public ResponseEntity<List<Hotel>> getAllHoteles() {
        List<Hotel> hoteles = hotelServicio.getHoteles();
        return ResponseEntity.ok().body(hoteles);
    }

    @Transactional
    @GetMapping("/hoteles/update/capacidad/{nombre}/{capacidad}")
    public ResponseEntity<Hotel> updateHotelCapacidadNombre(@PathVariable("nombre") String nombre, @PathVariable("capacidad") String capacidadStr) {
        Long capacidad = Long.parseLong(capacidadStr);
        Hotel hotel= hotelServicio.updateHotelCapacidadbyNombre(nombre,capacidad);
        if(hotel != null){
            return ResponseEntity.ok().body(hotel);
        }
        return ResponseEntity.notFound().build();
    }

    @Transactional
    @GetMapping("/hoteles/update/ocupacion/{nombre}/{ocupacion}")
    public ResponseEntity<Hotel> updateHotelOcupacionNombre(@PathVariable("nombre") String nombre, @PathVariable("ocupacion") String ocupacionStr) {
        Long ocupacion = Long.parseLong(ocupacionStr);
        Hotel hotel= hotelServicio.updateHotelOcupacionbyNombre(nombre,ocupacion);
        if(hotel != null){
            return ResponseEntity.ok().body(hotel);
        }
        return ResponseEntity.notFound().build();
    }

    @Transactional
    @GetMapping("/hoteles/update/estado/{nombre}/{estado}")
    public ResponseEntity<Hotel> updateHotelEstadoNombre(@PathVariable("nombre") String nombre, @PathVariable("estado") String estadoStr) {
        Boolean estado = Boolean.parseBoolean(estadoStr);
        Hotel hotel= hotelServicio.updateHotelEstadobyNombre(nombre,estado);
        if(hotel != null){
            return ResponseEntity.ok().body(hotel);
        }
        return ResponseEntity.notFound().build();
    }

    @Transactional
    @GetMapping("/hoteles/insert/{nombre}/{destino}/{capacidad}/{ocupacion}/{estado}")
    public ResponseEntity<String> insertCompareHotel(@PathVariable("nombre") String nombre, @PathVariable("destino") String destino, @PathVariable("capacidad") String capacidadStr,@PathVariable("ocupacion") String ocupacionStr,@PathVariable("estado") String estadoStr) {
        Long capacidad = Long.parseLong(capacidadStr);
        Long ocupacion = Long.parseLong(ocupacionStr);
        Boolean estado = Boolean.parseBoolean(estadoStr);
        String resultado = hotelServicio.insertAndCompareHotel(nombre,destino,capacidad,ocupacion,estado);
        return ResponseEntity.ok().body(resultado);
    }

    @Transactional
    @GetMapping("/hoteles/delete/{nombre}")
    public ResponseEntity<String> deleteHotelNombre(@PathVariable("nombre") String nombre) {
        String resultado = hotelServicio.deleteHotelbyNombre(nombre);
        return ResponseEntity.ok().body(resultado);
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