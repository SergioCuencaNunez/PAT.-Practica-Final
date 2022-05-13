package com.example.demo.service;

import com.example.demo.model.Usuario;
import com.example.demo.service.dto.UsuarioReservaDTO;
import com.example.demo.service.LoginServiceResult;

import java.time.LocalDate;
import java.util.List;

public interface UsuarioService {

    Usuario getUsuariobyNif(String nif);
    Usuario getUsuariobyCorreo(String correo);
    List<Usuario> getUsuariosbyRol(String rol);
    List<Usuario> getUsuarios();
    LoginServiceResult updateUsuario(Usuario usuario);
    void insertUsuario(String nif, String nombre, String apellido1, String apellido2, String correo, String contrasena,LocalDate cumpleanos, String rol);
    String deleteUsuariobyNif(String nif);
    //INNER-JOIN
    List<UsuarioReservaDTO> getUsuariosConReservas();
}