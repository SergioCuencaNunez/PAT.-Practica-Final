package com.example.demo.controller;

import com.example.demo.model.Habitacion;
import com.example.demo.model.Hotel;
import com.example.demo.service.HabitacionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class HabitacionController {

    @Autowired
    private HabitacionService habitacionServicio;

    @Transactional
    @GetMapping("/habitaciones/tipo/{tipo}")
    public ResponseEntity<Habitacion> getHabitacionTipo(@PathVariable("tipo") String tipo) {
        Habitacion habitacion = habitacionServicio.getHabitacionbyTipo(tipo);
        if(habitacion != null){
            return ResponseEntity.ok().body(habitacion);
        }
        return ResponseEntity.notFound().build();
    }

    @Transactional
    @GetMapping("/habitaciones/hotel/{hotel}")
    public ResponseEntity<List<Habitacion>> getHabitacionesHotel(@PathVariable("hotel") String hotel){
        List<Habitacion> habitaciones = habitacionServicio.getHabitacionesbyHotel(hotel);
        return ResponseEntity.ok().body(habitaciones);
    }

    @Transactional
    @GetMapping("/habitaciones/estado/{estado}")
    public ResponseEntity<List<Habitacion>> getHabitacionesEstado(@PathVariable("estado") String estadoStr){
        Boolean estado = Boolean.parseBoolean(estadoStr);
        List<Habitacion> habitaciones = habitacionServicio.getHabitacionesbyEstado(estado);
        return ResponseEntity.ok().body(habitaciones);
    }

    @Transactional
    @GetMapping("/habitaciones/hotel-estado/{hotel}/{estado}")
    public ResponseEntity<List<Habitacion>> getHabitacionesHotelEstado(@PathVariable("hotel") String hotel, @PathVariable("estado") String estadoStr){
        Boolean estado = Boolean.parseBoolean(estadoStr);
        List<Habitacion> habitaciones = habitacionServicio.getHabitacionesbyHotelEstado(hotel, estado);
        return ResponseEntity.ok().body(habitaciones);
    }

    @Transactional
    @GetMapping("/habitaciones")
    public ResponseEntity<List<Habitacion>> getAllHabitaciones() {
        List<Habitacion> habitaciones = habitacionServicio.getHabitaciones();
        return ResponseEntity.ok().body(habitaciones);
    }

    @Transactional
    @GetMapping("/habitaciones/update/estado/{estado}/{tipo}")
    public ResponseEntity<Habitacion> updateHabitacionEstadoTipo(@PathVariable("estado") String estadoStr,@PathVariable("tipo") String tipo){
        Boolean estado = Boolean.parseBoolean(estadoStr);
        Habitacion habitacion = habitacionServicio.updateHabitacionEstadobyTipo(estado, tipo);
        if(habitacion != null){
            return ResponseEntity.ok().body(habitacion);
        }
        return ResponseEntity.notFound().build();
    }

    @Transactional
    @GetMapping("/habitaciones/update/numero/{numero}/{tipo}")
    public ResponseEntity<Habitacion> updateHabitacionNumeroTipo(@PathVariable("numero") String numeroStr,@PathVariable("tipo") String tipo){
        Long numero = Long.parseLong(numeroStr);
        Habitacion habitacion = habitacionServicio.updateHabitacionNumerobyTipo(numero, tipo);
        if(habitacion != null){
            return ResponseEntity.ok().body(habitacion);
        }
        return ResponseEntity.notFound().build();
    }

    @Transactional
    @GetMapping("/habitaciones/update/capacidad/{capacidad}/{tipo}")
    public ResponseEntity<Habitacion> updateHabitacionCapacidadTipo(@PathVariable("capacidad") String capacidadStr,@PathVariable("tipo") String tipo){
        Long capacidad = Long.parseLong(capacidadStr);
        Habitacion habitacion = habitacionServicio.updateHabitacionCapacidadbyTipo(capacidad, tipo);
        if(habitacion != null){
            return ResponseEntity.ok().body(habitacion);
        }
        return ResponseEntity.notFound().build();
    }

    @Transactional
    @GetMapping("/habitaciones/insert/{tipo}/{numero}/{planta}/{hotel}/{capacidad}/{estado}")
    public ResponseEntity<String> insertCompareHabitacion(@PathVariable("tipo") String tipo,@PathVariable("numero") String numeroStr,@PathVariable("planta") String plantaStr,@PathVariable("hotel") String hotel,@PathVariable("capacidad") String capacidadStr,@PathVariable("estado") String estadoStr){
        Long numero = Long.parseLong(numeroStr);
        Long planta = Long.parseLong(plantaStr);
        Long capacidad = Long.parseLong(capacidadStr);
        Boolean estado = Boolean.parseBoolean(estadoStr);
        String resultado = habitacionServicio.insertAndCompareHabitacion(tipo, numero, planta, hotel, capacidad, estado);
        return ResponseEntity.ok().body(resultado);
    }

    @Transactional
    @GetMapping("habitaciones/delete/{tipo}")
    public ResponseEntity<String> deleteHabitacionTipo(@PathVariable("tipo") String tipo){
        String resultado = habitacionServicio.deleteHabitacionbyTipo(tipo);
        return ResponseEntity.ok().body(resultado);
    }
}