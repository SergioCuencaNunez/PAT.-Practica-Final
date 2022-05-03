package com.example.demo.repository;

import com.example.demo.model.Reserva;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Iterator;

public interface ReservaRepository extends CrudRepository<Reserva, Long> {

    @Query("SELECT* FROM RESERVA WHERE RESERVA.NIF= :nif")
    public List<Reserva> getReservasByNif(String nif);

    @Query("SELECT* FROM RESERVA WHERE RESERVA.DESTINO= :destino")
    public List<Reserva> getReservasByDestino(String destino);

    @Query("SELECT* FROM RESERVA WHERE RESERVA.HOTEL= :hotel")
    public List<Reserva> getReservasByHotel(String hotel);

    @Query("SELECT* FROM RESERVA WHERE RESERVA.HOTEL= :hotel AND RESERVA.FECHAENTRADA= :fechaEntrada")
    public List<Reserva> getReservasByHotelFechaEntrada(String hotel, LocalDate fechaEntrada);

    @Query("UPDATE RESERVA SET RESERVA.HOTEL= :hotel, RESERVA.DESTINO= :destino, RESERVA.TIPO= :tipo, RESERVA.HUESPEDES= :huespedes, RESERVA.HABITACIONES= :habitaciones, RESERVA.FECHAENTRADA= :fechaEntrada, RESERVA.FECHASALIDA= :fechaSalida WHERE RESERVA.ID= :id")
    @Modifying
    void updateReservaById(Long id, String hotel, String destino, String tipo, Long huespedes, Long habitaciones, LocalDate fechaEntrada, LocalDate fechaSalida);

    @Query("INSERT INTO RESERVA (ID, NIF, HOTEL, DESTINO, TIPO, HUESPEDES, HABITACIONES, FECHAENTRADA, FECHASALIDA) VALUES (:id,:nif,:hotel,:destino,:tipo,:huespedes,:habitaciones,:fechaEntrada,:fechaSalida)")
    @Modifying
    void insertReserva(Long id, String nif, String hotel, String destino, String tipo, Long huespedes, Long habitaciones, LocalDate fechaEntrada, LocalDate fechaSalida);

}