package com.example.demo.service;

import com.example.demo.model.Reserva;

import java.util.List;
import java.time.LocalDate;

public interface ReservaService {
    Reserva getReservabyId(Long id);
    List<Reserva> getReservasbyNif(String nif);
    List<Reserva> getReservasbyHotel(String hotel);
    List<Reserva> getReservas();
    Reserva updateReservaTipobyId(Long id, String tipo);
    ReservaServiceResult updateReservaHuespedesbyId(Long id, Long huespedesNuevos);
    ReservaServiceResult updateReservaHabitacionbyId(Long id, Long habitacionesNuevas);
    Reserva updateReservaFechaEntradabyId(Long id, LocalDate fechaEntrada);
    Reserva updateReservaFechaSalidabyId(Long id, LocalDate fechaSalida);
    void insertReserva(Long id, String nif, String hotel, String destino, String tipo, Long huespedes, Long habitaciones, LocalDate fechaEntrada, LocalDate fechaSalida);
    ReservaServiceResult registrarReserva(Reserva reserva);
    ReservaServiceResult comprobarDisponibilidad(String hotel, Long habitaciones);
    ReservaServiceResult checkInReserva(Reserva reserva);
    String deleteReservabyId(Long id);
    ReservaServiceResult deleteReservaHotelbyId(Reserva reserva, String hotel);
}