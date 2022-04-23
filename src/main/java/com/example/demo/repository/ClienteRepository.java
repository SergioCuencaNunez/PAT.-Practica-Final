package com.example.demo.repository;

import com.example.demo.model.Cliente;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Iterator;

public interface ClienteRepository extends CrudRepository<Cliente, String> {

    @Query("SELECT* FROM CLIENTE WHERE CLIENTE.NOMBRE= :nombre AND CLIENTE.APELLIDO1= :apellido1 AND CLIENTE.APELLIDO2= :apellido2")
    public Cliente getClienteByNombreCompleto(String nombre, String apellido1, String apellido2);

    @Query("SELECT* FROM CLIENTE WHERE CLIENTE.CORREO= :correo")
    public Cliente getClienteByCorreo(String correo);

    @Query("SELECT CORREO FROM CLIENTE")
    public List<String> getClienteCorreos();

    @Query("SELECT CLIENTE.CONTRASENA FROM CLIENTE WHERE CLIENTE.CORREO= :correo")
    public String getClienteContrasena(String correo);

    @Query("UPDATE CLIENTE SET CLIENTE.NOMBRE= :nombre , CLIENTE.APELLIDO1= :apellido1 , CLIENTE.APELLIDO2= :apellido2 WHERE CLIENTE.NIF= :nif ")
    @Modifying
    void updateClienteNombreCompletoByNif(String nombre, String apellido1, String apellido2, String nif);

    @Query("UPDATE CLIENTE SET CLIENTE.CORREO= :correo WHERE CLIENTE.NIF= :nif ")
    @Modifying
    void updateClienteCorreoByNif(String correo, String nif);

    @Query("UPDATE CLIENTE SET CLIENTE.CUMPLEANOS= :cumpleanos WHERE CLIENTE.NIF= :nif ")
    @Modifying
    void updateClienteCumpleanosByNif(LocalDate cumpleanos, String nif);

    @Query("INSERT INTO CLIENTE (NIF, NOMBRE, APELLIDO1, APELLIDO2, CORREO, CONTRASENA, CUMPLEANOS) VALUES (:nif,:nombre,:apellido1,:apellido2,:correo,:contrasena,:cumpleanos)")
    @Modifying
    void insertCliente(String nif, String nombre, String apellido1, String apellido2, String correo, String contrasena, LocalDate cumpleanos);

}