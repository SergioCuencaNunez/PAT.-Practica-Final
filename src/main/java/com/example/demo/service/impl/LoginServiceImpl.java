package com.example.demo.service.impl;

import com.example.demo.model.Usuario;
import com.example.demo.service.LoginService;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.service.LoginServiceResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;

@Service
public class LoginServiceImpl implements LoginService {

  @Autowired
  private UsuarioRepository usuarioRepository;

  @Override
  public LoginServiceResult inicioSesionDeUsuario(Usuario usuario){

    String correo = usuario.getCorreo();
    String contrasenaValida = usuario.getContrasena();
    String contrasena;
    List<String> correos = usuarioRepository.getUsuarioCorreos();

    if(correos.contains(correo)){
        contrasena = usuarioRepository.getUsuarioContrasena(correo);
        if(contrasena.equals(contrasenaValida)){
            String value = correo + ":" + contrasenaValida;
            String accessToken = Base64.getEncoder().encodeToString(value.getBytes());
            return new LoginServiceResult(true, accessToken);

        }else{
            return new LoginServiceResult(false, "Contraseña Errónea");
        }
    }else{
        return new LoginServiceResult(false, "Usuario no encontrado");
    }
  }
}
