package com.example.demo.service;

import com.example.demo.model.Contacto;
import com.example.demo.model.Usuario;

public interface LoginService {

  LoginServiceResult inicioSesionDeUsuario(Usuario usuario);
<<<<<<< HEAD
  APP_ROLES getRole(String accessToken);
=======
  ROLES getRole(String accessToken);
>>>>>>> 66c03206cd986c9289f8fd205852ab1c0c21b01a
  LoginServiceResult registroUsuario(Usuario usuario);
  LoginServiceResult mensajeContacto(Contacto contacto);
  LoginServiceResult checkInContacto(Contacto contacto);
  LoginServiceResult suscripcionContacto(Contacto contacto);
}
