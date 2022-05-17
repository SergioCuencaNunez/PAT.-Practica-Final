package com.example.demo.controller;

import com.example.demo.model.Habitacion;
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
    @GetMapping("/habitaciones/capacidad/{tipo}")
    public ResponseEntity<Long> getHabitacionCapacidadHotel(@PathVariable("tipo") String tipo){
        Long capacidad = habitacionServicio.getHabitacionCapacidadbyHotel(tipo);
        return ResponseEntity.ok().body(capacidad);
    }

    @Transactional
    @GetMapping("/habitaciones")
    public ResponseEntity<List<Habitacion>> getAllHabitaciones() {
        List<Habitacion> habitaciones = habitacionServicio.getHabitaciones();
        return ResponseEntity.ok().body(habitaciones);
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
    @GetMapping("/habitaciones/insert/{tipo}/{numero}/{planta}/{hotel}/{capacidad}")
    public ResponseEntity<String> insertCompareHabitacion(@PathVariable("tipo") String tipo,@PathVariable("numero") String numeroStr,@PathVariable("planta") String plantaStr,@PathVariable("hotel") String hotel,@PathVariable("capacidad") String capacidadStr){
        Long numero = Long.parseLong(numeroStr);
        Long planta = Long.parseLong(plantaStr);
        Long capacidad = Long.parseLong(capacidadStr);
        String resultado = habitacionServicio.insertAndCompareHabitacion(tipo, numero, planta, hotel, capacidad);
        return ResponseEntity.ok().body(resultado);
    }

    @Transactional
    @GetMapping("habitaciones/delete/{tipo}")
    public ResponseEntity<String> deleteHabitacionTipo(@PathVariable("tipo") String tipo){
        String resultado = habitacionServicio.deleteHabitacionbyTipo(tipo);
        return ResponseEntity.ok().body(resultado);
    }
}