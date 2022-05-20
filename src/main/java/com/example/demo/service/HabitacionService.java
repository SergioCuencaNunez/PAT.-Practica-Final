package com.example.demo.service;

import com.example.demo.model.Habitacion;

import java.util.List;

public interface HabitacionService {

    Habitacion getHabitacionbyTipo(String tipo);
    List<Habitacion> getHabitacionesbyHotel(String hotel);
    Long getHabitacionCapacidadbyHotel(String tipo);
    List<Habitacion> getHabitaciones();
    Habitacion updateHabitacionCapacidadbyTipo(Long capacidad, String tipo);
}