package com.example.demo.repository;

import com.example.demo.model.Hotel;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HotelRepository extends CrudRepository<Hotel, String> {

    @Query("SELECT* FROM HOTEL WHERE HOTEL.NOMBRE= :nombre ")
    Hotel getNombre(String nombre);

    @Query("SELECT* FROM HOTEL WHERE HOTEL.DESTINO= :destino ")
    public List<Hotel> getHotelesByDestino(String destino);

    @Query("SELECT* FROM HOTEL WHERE HOTEL.ESTADO= :estado ")
    public List<Hotel> getHotelesByEstado(Boolean estado);

    @Query("SELECT HOTEL.HABITACIONES_TOTALES FROM HOTEL WHERE HOTEL.NOMBRE= :nombre ")
    public Long getHabitacionesTotalesByHotel(String nombre);

    @Query("SELECT HOTEL.HABITACIONES_OCUPADAS FROM HOTEL WHERE HOTEL.NOMBRE= :nombre ")
    public Long getHabitacionesOcupadasByHotel(String nombre);

    @Query("SELECT HOTEL.OCUPACION FROM HOTEL WHERE HOTEL.NOMBRE= :nombre ")
    public Long getOcupacionByHotel(String nombre);

    @Query("SELECT HOTEL.ESTADO FROM HOTEL WHERE HOTEL.NOMBRE= :nombre ")
    public boolean getEstadoByNombre(String nombre);

    @Query("UPDATE HOTEL SET HOTEL.CAPACIDAD= :capacidad WHERE HOTEL.NOMBRE= :nombre ")
    @Modifying
    void updateHotelCapacidadByNombre(Long capacidad, String nombre);

    @Query("UPDATE HOTEL SET HOTEL.OCUPACION= :ocupacion WHERE HOTEL.NOMBRE= :nombre ")
    @Modifying
    void updateHotelOcupacionByNombre(Long ocupacion, String nombre);

    @Query("UPDATE HOTEL SET HOTEL.HABITACIONES_OCUPADAS= :habitacionesOcupadas WHERE HOTEL.NOMBRE= :nombre ")
    @Modifying
    void updateHotelHabitacionesOcupadasByNombre(Long habitacionesOcupadas, String nombre);

    @Query("UPDATE HOTEL SET HOTEL.HABITACIONES_TOTALES= :habitacionesTotales WHERE HOTEL.NOMBRE= :nombre ")
    @Modifying
    void updateHotelHabitacionesTotalesByNombre(Long habitacionesTotales, String nombre);

    @Query("UPDATE HOTEL SET HOTEL.ESTADO= :estado WHERE HOTEL.NOMBRE= :nombre")
    @Modifying
    void updateHotelEstadoByNombre(Boolean estado, String nombre);



}