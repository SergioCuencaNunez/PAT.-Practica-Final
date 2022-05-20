package com.example.demo.repository;

import com.example.demo.model.Habitacion;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HabitacionRepository extends CrudRepository<Habitacion, String> {

    @Query("SELECT* FROM HABITACION WHERE HABITACION.HOTEL= :hotel")
    public List<Habitacion> getHabitacionesByHotel(String hotel);

    @Query("SELECT* FROM HABITACION WHERE HABITACION.ESTADO= :estado")
    public List<Habitacion> getHabitacionesByEstado(Boolean estado);

    @Query("SELECT* FROM HABITACION WHERE HABITACION.HOTEL= :hotel AND HABITACION.ESTADO= :estado")
    public List<Habitacion> getHabitacionesByHotelEstado(String hotel, Boolean estado);

    @Query("SELECT HABITACION.ESTADO FROM HABITACION WHERE HABITACION.TIPO =:tipo")
    public boolean getEstadoByTipo(String tipo);

    @Query("SELECT HABITACION.NUMERO FROM HABITACION WHERE HABITACION.TIPO =:tipo")
    public int getNumeroByTipo(String tipo);

    @Query("UPDATE HABITACION SET HABITACION.ESTADO= :estado WHERE HABITACION.TIPO= :tipo")
    @Modifying
    void updateHabitacionEstadoByTipo(Boolean estado, String tipo);

    @Query("UPDATE HABITACION SET HABITACION.NUMERO= :numero WHERE HABITACION.TIPO= :tipo")
    @Modifying
    void updateHabitacionNumeroByTipo(Long numero, String tipo);

    @Query("UPDATE HABITACION SET HABITACION.CAPACIDAD= :capacidad WHERE HABITACION.TIPO= :tipo")
    @Modifying
    void updateHabitacionCapacidadByTipo(Long capacidad, String tipo);

    @Query("INSERT INTO HABITACION (TIPO, NUMERO,PLANTA, HOTEL, CAPACIDAD, ESTADO) VALUES(:tipo, :numero, :planta, :hotel, :capacidad, :estado)")
    @Modifying
    void insertHabitacion(String tipo, Long numero, Long planta, String hotel, Long capacidad, Boolean estado);



}