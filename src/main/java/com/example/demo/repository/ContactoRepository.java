package com.example.demo.repository;

import com.example.demo.model.Contacto;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ContactoRepository extends CrudRepository<Contacto, Long> {

    @Query("SELECT* FROM CONTACTO WHERE CONTACTO.CORREO= :correo")
    public Contacto getContactoByCorreo(String correo);

    @Query("SELECT* FROM CONTACTO WHERE CONTACTO.NUMERO= :numero")
    public Contacto getContactoByNumero(Long numero);

    @Query("SELECT CORREO FROM CONTACTO")
    public List<String> getContactoCorreos();

    @Query("SELECT CONTACTO.MENSAJE FROM CONTACTO WHERE CONTACTO.CORREO= :correo")
    public String getMensajeByCorreo(String correo);

    @Query("SELECT CONTACTO.MENSAJE FROM CONTACTO")
    public List<String> getContactoMensajes();

    @Query("INSERT INTO CONTACTO (NUMERO, CORREO, NOMBRE, MENSAJE) VALUES (:numero,:correo,:nombre,:mensaje)")
    @Modifying
    void insertarContacto(Long numero, String correo, String nombre, String mensaje);


}