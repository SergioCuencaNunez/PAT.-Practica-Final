package com.example.demo.repository;

import com.example.demo.model.Habitacion;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Iterator;

public interface HabitacionRepository extends CrudRepository<Habitacion, String> {

    @Query("SELECT* FROM HABITACION WHERE HABITACION.HOTEL= :hotel")
    public List<Habitacion> getHabitacionesByHotel(String hotel);

    @Query("SELECT CAPACIDAD FROM HABITACION WHERE HABITACION.TIPO= :tipo")
    public Long getHabitacionCapacidadByHotel(String tipo);

    @Query("UPDATE HABITACION SET HABITACION.NUMERO= :numero WHERE HABITACION.TIPO= :tipo")
    @Modifying
    void updateHabitacionNumeroByTipo(Long numero, String tipo);

    @Query("UPDATE HABITACION SET HABITACION.CAPACIDAD= :capacidad WHERE HABITACION.TIPO= :tipo")
    @Modifying
    void updateHabitacionCapacidadByTipo(Long capacidad, String tipo);

    @Query("INSERT INTO HABITACION (TIPO, NUMERO,PLANTA, HOTEL, CAPACIDAD) VALUES(:tipo, :numero, :planta, :hotel, :capacidad)")
    @Modifying
    void insertHabitacion(String tipo, Long numero, Long planta, String hotel, Long capacidad);


}