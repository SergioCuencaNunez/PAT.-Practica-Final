package com.example.demo.repository;

import com.example.demo.model.Hotel;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Iterator;

public interface HotelRepository extends CrudRepository<Hotel, String> {

    @Query("SELECT* FROM HOTEL WHERE HOTEL.DESTINO= :destino ")
    public List<Hotel> getHotelesByDestino(String destino);

    @Query("SELECT* FROM HOTEL WHERE HOTEL.ESTADO= :estado ")
    public List<Hotel> getHotelesByEstado(Boolean estado);

    @Query("UPDATE HOTEL SET HOTEL.CAPACIDAD= :capacidad WHERE HOTEL.NOMBRE= :nombre ")
    @Modifying
    void updateHotelCapacidadByNombre(Long capacidad, String nombre);

    @Query("UPDATE HOTEL SET HOTEL.OCUPACION= :ocupacion WHERE HOTEL.NOMBRE= :nombre ")
    @Modifying
    void updateHotelOcupacionByNombre(Long ocupacion, String nombre);

    @Query("UPDATE HOTEL SET HOTEL.ESTADO= :estado WHERE HOTEL.NOMBRE= :nombre ")
    @Modifying
    void updateHotelEstadoByNombre(Boolean estado, String nombre);

    @Query("INSERT INTO HOTEL (NOMBRE, DESTINO, CAPACIDAD, OCUPACION, ESTADO) VALUES (:nombre,:destino,:capacidad,:ocupacion,:estado)")
    @Modifying
    void insertHotel(String nombre, String destino, Long capacidad, Long ocupacion, Boolean estado);

}