package com.example.demo.service.impl;

import com.example.demo.model.Usuario;
import com.example.demo.service.LoginService;
import com.example.demo.service.UsuarioService;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.service.LoginServiceResult;

import com.example.demo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.time.LocalDate;
import java.util.Optional;


@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioServicio;

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

    @Override
    public LoginServiceResult registroUsuario(Usuario usuario) {

        String nif = usuario.getNif();
        String nombre = usuario.getNombre();
        String apellido1 = usuario.getApellido1();
        String apellido2 = usuario.getApellido2();
        String correo = usuario.getCorreo();
        String contrasena = usuario.getContrasena();
        LocalDate cumpleanos = usuario.getCumpleanos();
        String rol = usuario.getRol();

        Optional<Usuario> ousuario = usuarioRepository.findById(nif);
        List<String> correos = usuarioRepository.getUsuarioCorreos();

        if (ousuario.isPresent()) {
            return new LoginServiceResult(false, "NIF ya registrado");
        } else if (correos.contains(correo)) {
            return new LoginServiceResult(false, "Correo ya registrado");
        } else {
            usuarioServicio.insertUsuario(nif, nombre, apellido1, apellido2, correo, contrasena, cumpleanos, rol);
            return new LoginServiceResult(true);
        }
    }
}
