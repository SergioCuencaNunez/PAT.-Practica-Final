package com.example.demo.service;

import com.example.demo.model.Habitacion;

import java.util.List;

public interface HabitacionService {

    Habitacion getHabitacionbyTipo(String tipo);
    List<Habitacion> getHabitacionesbyHotel(String hotel);
    List<Habitacion> getHabitacionesbyEstado(Boolean estado);
    List<Habitacion> getHabitacionesbyHotelEstado(String hotel, Boolean estado);
    List<Habitacion> getHabitaciones();
    Habitacion updateHabitacionEstadobyTipo(Boolean estado, String tipo);
    Habitacion updateHabitacionNumerobyTipo(Long numero, String tipo);
    Habitacion updateHabitacionCapacidadbyTipo(Long capacidad, String tipo);
    String insertAndCompareHabitacion(String tipo, Long numero, Long planta, String hotel, Long capacidad, Boolean estado);
    String deleteHabitacionbyTipo(String tipo);
}