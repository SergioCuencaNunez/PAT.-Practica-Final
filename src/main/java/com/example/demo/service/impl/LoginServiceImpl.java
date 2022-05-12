package com.example.demo.service.impl;

import com.example.demo.model.Contacto;
import com.example.demo.model.Usuario;
import com.example.demo.service.*;
import com.example.demo.repository.ContactoRepository;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.service.UsuarioService;
import com.example.demo.service.ContactoService;
import org.apache.logging.log4j.spi.AbstractLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.time.LocalDate;
import java.util.Optional;


@Service
public class LoginServiceImpl implements LoginService {

    Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ContactoRepository contactoRepository;

    @Autowired
    private UsuarioService usuarioServicio;

    @Autowired
    private ContactoService contactoServicio;

    @Override
    public LoginServiceResult inicioSesionDeUsuario(Usuario usuario){

        String correo = usuario.getCorreo();
        String contrasena = usuario.getContrasena();
        String contrasenaValida;
        List<String> correos = usuarioRepository.getUsuarioCorreos();

        if(correos.contains(correo)){
            contrasenaValida = usuarioRepository.getUsuarioByContrasena(correo);
            if(contrasena.equals(contrasenaValida)){
                 String value = correo + ":" + contrasena;
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
    public ROLES getRole(String accessToken) {
        String access_token_raw = accessToken.replace("Bearer ", "");
        String access_token = new String(Base64.getDecoder().decode(access_token_raw));
        logger.info("Access token raw: " + access_token_raw);
        String[] parts = access_token.split(":");

        Usuario usuario = usuarioRepository.getUsuarioByCorreo(parts[0]);
        return ROLES.get(usuario.getRol());
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

    @Override
    public LoginServiceResult mensajeContacto(Contacto contacto){

        Long numero = contacto.getNumero();
        String correo = contacto.getCorreo();
        String nombre = contacto.getNombre();
        String mensaje = contacto.getMensaje();
        List<String> correos = usuarioRepository.getUsuarioCorreos();

        if(correos.contains(correo)){
            String value = correo + ":" + mensaje;
            String accessToken = Base64.getEncoder().encodeToString(value.getBytes());
            contactoServicio.insertContacto(numero, correo, nombre, mensaje);
            return new LoginServiceResult(true, accessToken);
        }else{
            return new LoginServiceResult(false, "Usuario no encontrado");
        }
    }

    @Override
    public LoginServiceResult checkInContacto(Contacto contacto){

        Long numero = contacto.getNumero();
        String nif = contacto.getCorreo();
        String idStr = contacto.getNombre();
        Long id = Long.parseLong(idStr);
        String mensaje = contacto.getMensaje();
        String correo = usuarioRepository.getUsuarioCorreoByNif(nif);
        String nombre = usuarioRepository.getUsuarioNombreByNif(nif);
        List<String> mensajesContactos = contactoRepository.getContactoMensajes();
        if(mensajesContactos.contains("Check-in online de la reserva con identificador " + id + " y NIF " + nif)){
            return new LoginServiceResult(false, "Check-in online ya realizado");
        }else{
            contactoServicio.insertContacto(numero, correo, nombre, mensaje);
            return new LoginServiceResult(true);
        }
    }

    @Override
    public LoginServiceResult suscripcionContacto(Contacto contacto){

        Long numero = contacto.getNumero();
        String correo = contacto.getCorreo();
        String nombre = contacto.getNombre();
        String mensaje = contacto.getMensaje();
        List<String> correosUsuarios = usuarioRepository.getUsuarioCorreos();
        List<String> correosContactos = contactoRepository.getContactoCorreos();

        if(correo != "") {
            if (correosUsuarios.contains(correo)) {
                return new LoginServiceResult(false, "Usuario registrado en MeliáRewards");
            } else if (correosContactos.contains(correo)) {
                return new LoginServiceResult(false, "Usuario registrado en boletín de suscripción");
            } else {
                String value = correo + ":" + mensaje;
                String accessToken = Base64.getEncoder().encodeToString(value.getBytes());
                contactoServicio.insertContacto(numero, correo, nombre, mensaje);
                return new LoginServiceResult(true, accessToken);
            }
        }else{
            return new LoginServiceResult(false, "Correo no rellenado");
        }
    }
}
