package com.example.demo.service;

import com.example.demo.model.Habitacion;

import java.util.List;

public interface HabitacionService {

    Habitacion getHabitacionbyTipo(String tipo);
    List<Habitacion> getHabitacionesbyHotel(String hotel);
    Long getHabitacionCapacidadbyHotel(String tipo);
    List<Habitacion> getHabitaciones();
    Habitacion updateHabitacionNumerobyTipo(Long numero, String tipo);
    Habitacion updateHabitacionCapacidadbyTipo(Long capacidad, String tipo);
    String insertAndCompareHabitacion(String tipo, Long numero, Long planta, String hotel, Long capacidad);
    String deleteHabitacionbyTipo(String tipo);
}