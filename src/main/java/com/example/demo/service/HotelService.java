package com.example.demo.service;

import com.example.demo.model.Hotel;
import com.example.demo.service.dto.HotelHabitacionDTO;
import com.example.demo.service.dto.HotelReservaDTO;

import java.util.List;

public interface HotelService {

    Hotel getHotelbyNombre(String nombre);
    List<Hotel> getHoteles();
    Hotel updateAmpliarHotelHabitacionesTotalesbyNombre(String nombre, Long nuevasHabitacionesTotales);
    Hotel updateReducirHotelHabitacionesTotalesbyNombre(String nombre, Long nuevasHabitacionesTotales);
    Hotel updateHotelEstadobyNombre(String nombre, Boolean estado);
    //INNER-JOIN
    List<HotelReservaDTO> getHotelesConReservas();
    //INNER-JOIN
    List<HotelHabitacionDTO> getHotelesConHabitaciones();
}