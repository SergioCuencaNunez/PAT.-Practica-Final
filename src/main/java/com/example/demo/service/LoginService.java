package com.example.demo.service;

import com.example.demo.model.Contacto;
import com.example.demo.model.Usuario;

public interface LoginService {

  LoginServiceResult inicioSesionDeUsuario(Usuario usuario);
  LoginServiceResult registroUsuario(Usuario usuario);
  LoginServiceResult mensajeContacto(Contacto contacto);
  LoginServiceResult suscripcionContacto(Contacto contacto);
}
