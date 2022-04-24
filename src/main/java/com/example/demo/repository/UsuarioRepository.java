package com.example.demo.repository;

import com.example.demo.model.Usuario;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Iterator;

public interface UsuarioRepository extends CrudRepository<Usuario, String> {

    @Query("SELECT* FROM USUARIO WHERE USUARIO.NOMBRE= :nombre AND USUARIO.APELLIDO1= :apellido1 AND USUARIO.APELLIDO2= :apellido2 AND USUARIO.ROL= :rol")
    public Usuario getUsuarioByNombreCompleto(String nombre, String apellido1, String apellido2, String rol);

    // Los correos son únicos. No es necesario pasar el rol
    @Query("SELECT* FROM USUARIO WHERE USUARIO.CORREO= :correo")
    public Usuario getUsuarioByCorreo(String correo);

    // Los correos son únicos. No es necesario pasar el rol
    @Query("SELECT CORREO FROM USUARIO")
    public List<String> getUsuarioCorreos();

    @Query("SELECT* FROM USUARIO WHERE USUARIO.ROL= :rol")
    public List<Usuario> getUsuariosByRol(String rol);

    // Los correos son únicos. No es necesario pasar el rol
    @Query("SELECT USUARIO.CONTRASENA FROM USUARIO WHERE USUARIO.CORREO= :correo")
    public String getUsuarioContrasena(String correo);

    // Los NIFs son únicos. No es necesario pasar el rol
    @Query("UPDATE USUARIO SET USUARIO.NOMBRE= :nombre , USUARIO.APELLIDO1= :apellido1 , USUARIO.APELLIDO2= :apellido2 WHERE USUARIO.NIF= :nif")
    @Modifying
    void updateUsuarioNombreCompletoByNif(String nombre, String apellido1, String apellido2, String nif);

    // Los NIFs son únicos. No es necesario pasar el rol
    @Query("UPDATE USUARIO SET USUARIO.CORREO= :correo WHERE USUARIO.NIF= :nif")
    @Modifying
    void updateUsuarioCorreoByNif(String correo, String nif);

    // Los NIFs son únicos. No es necesario pasar el rol
    @Query("UPDATE USUARIO SET USUARIO.CUMPLEANOS= :cumpleanos WHERE USUARIO.NIF= :nif")
    @Modifying
    void updateUsuarioCumpleanosByNif(LocalDate cumpleanos, String nif);

    @Query("INSERT INTO USUARIO (NIF, NOMBRE, APELLIDO1, APELLIDO2, CORREO, CONTRASENA, CUMPLEANOS, ROL) VALUES (:nif,:nombre,:apellido1,:apellido2,:correo,:contrasena,:cumpleanos,:rol)")
    @Modifying
    void insertUsuario(String nif, String nombre, String apellido1, String apellido2, String correo, String contrasena, LocalDate cumpleanos, String rol);

}