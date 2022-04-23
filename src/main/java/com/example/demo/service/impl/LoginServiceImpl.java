package com.example.demo.service.impl;

import com.example.demo.model.Cliente;
import com.example.demo.service.LoginService;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.service.LoginServiceResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;

@Service
public class LoginServiceImpl implements LoginService {

  @Autowired
  private ClienteRepository clienteRepository;

  @Override
  public LoginServiceResult inicioSesionDeCliente(Cliente cliente){

    String correo = cliente.getCorreo();
    String contrasenaValida = cliente.getContrasena();
    String contrasena;
    List<String> correos = clienteRepository.getClienteCorreos();

    if(correos.contains(correo)){
        contrasena = clienteRepository.getClienteContrasena(correo);
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
