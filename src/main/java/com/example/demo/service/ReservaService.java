package com.example.demo.service;

import com.example.demo.model.Reserva;

import java.util.List;
import java.time.LocalDate;

public interface ReservaService {
    Reserva getReservabyId(Long id);
    List<Reserva> getReservasbyNif(String nif);
    List<Reserva> getReservasbyDestino(String destino);
    List<Reserva> getReservasbyHotel(String hotel);
    List<Reserva> getReservasbyHotelFechaEntrada(String hotel, LocalDate fechaEntrada);
    Reserva updateReservabyId(Long id, String hotel, String destino, Long huespedes, Long habitaciones, LocalDate fechaEntrada, LocalDate fechaSalida);
    List<Reserva> getReservas();
    void insertReserva(Long id, String nif, String hotel, String destino, Long huespedes, Long habitaciones, LocalDate fechaEntrada, LocalDate fechaSalida);
    ReservaServiceResult registrarReserva(Reserva reserva);
    String deleteReservabyId(Long id);
}