package com.example.demo.service;

import com.example.demo.model.Usuario;
import com.example.demo.service.dto.UsuarioReservaDTO;

import java.time.LocalDate;
import java.util.List;

public interface UsuarioService {

    Usuario getUsuariobyNif(String nif);
    Usuario getUsuariobyCorreo(String correo);
    List<Usuario> getUsuariosbyRol(String rol);
    List<Usuario> getUsuarios();
    Usuario updateUsuarioNombreCompletobyNif(String nif, String nombre, String apellido1, String apellido2);
    Usuario updateUsuarioCorreobyNif(String nif, String correo);
    Usuario updateUsuarioCumpleanosbyNif(String nif, LocalDate cumpleanos);
    void insertUsuario(String nif, String nombre, String apellido1, String apellido2, String correo, String contrasena,LocalDate cumpleanos, String rol);
    String deleteUsuariobyNif(String nif);
    //INNER-JOIN
    List<UsuarioReservaDTO> getUsuariosConReservas();
}