package com.example.demo.repository;

import com.example.demo.model.Reserva;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReservaRepository extends CrudRepository<Reserva, Long> {

    @Query("SELECT* FROM RESERVA WHERE RESERVA.NIF= :nif")
    public List<Reserva> getReservasByNif(String nif);

    @Query("SELECT* FROM RESERVA WHERE RESERVA.HOTEL= :hotel")
    public List<Reserva> getReservasByHotel(String hotel);

    @Query("SELECT NIF FROM RESERVA WHERE RESERVA.ID= :id")
    public String getNifReservaById(Long id);

    @Query("SELECT DESTINO FROM RESERVA WHERE RESERVA.ID= :id")
    public String getDestinoReservaById(Long id);

    @Query("SELECT HOTEL FROM RESERVA WHERE RESERVA.ID= :id")
    public String getHotelReservaById(Long id);

    @Query("SELECT FECHAENTRADA FROM RESERVA WHERE RESERVA.ID= :id")
    public LocalDate getFechaEntradaReservaById(Long id);

    @Query("SELECT FECHASALIDA FROM RESERVA WHERE RESERVA.ID= :id")
    public LocalDate getFechaSalidaReservaById(Long id);

    @Query("UPDATE RESERVA SET RESERVA.HOTEL= :hotel, RESERVA.DESTINO= :destino, RESERVA.TIPO= :tipo, RESERVA.HUESPEDES= :huespedes, RESERVA.HABITACIONES= :habitaciones, RESERVA.FECHAENTRADA= :fechaEntrada, RESERVA.FECHASALIDA= :fechaSalida WHERE RESERVA.ID= :id")
    @Modifying
    void updateReservaById(Long id, String hotel, String destino, String tipo, Long huespedes, Long habitaciones, LocalDate fechaEntrada, LocalDate fechaSalida);

    @Query("INSERT INTO RESERVA (ID, NIF, HOTEL, DESTINO, TIPO, HUESPEDES, HABITACIONES, FECHAENTRADA, FECHASALIDA) VALUES (:id,:nif,:hotel,:destino,:tipo,:huespedes,:habitaciones,:fechaEntrada,:fechaSalida)")
    @Modifying
    void insertReserva(Long id, String nif, String hotel, String destino, String tipo, Long huespedes, Long habitaciones, LocalDate fechaEntrada, LocalDate fechaSalida);

}