package com.example.demo.controller;

import com.example.demo.model.Usuario;
import com.example.demo.service.UsuarioService;
import com.example.demo.service.dto.UsuarioReservaDTO;
import com.example.demo.service.LoginService;
import com.example.demo.service.LoginServiceResult;
import org.springframework.validation.BindingResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioServicio;

    @Autowired
    private LoginService loginServicio;

    @Transactional
    @GetMapping("/usuarios/nif/{nif}")
    public ResponseEntity<Usuario> getUsuarioNif(@PathVariable("nif") String nif) {
        Usuario usuario= usuarioServicio.getUsuariobyNif(nif);
        if(usuario != null){
            return ResponseEntity.ok().body(usuario);
        }
        return ResponseEntity.notFound().build();
    }

    @Transactional
    @GetMapping("/usuarios/nombre/{nombre}/{apellido1}/{apellido2}/{rol}")
    public ResponseEntity<Usuario> getUsuarioNombreCompleto(@PathVariable("nombre") String nombre, @PathVariable("apellido1") String apellido1,@PathVariable("apellido2") String apellido2,@PathVariable("rol") String rol){
        Usuario usuario = usuarioServicio.getUsuariobyNombreCompleto(nombre,apellido1,apellido2,rol);
        return ResponseEntity.ok().body(usuario);
    }

    @Transactional
    @GetMapping("/usuarios/correo/{correo}")
    public ResponseEntity<Usuario> getUsuarioCorreo(@PathVariable("correo") String correo){
        Usuario usuario = usuarioServicio.getUsuariobyCorreo(correo);
        return ResponseEntity.ok().body(usuario);
    }

    @Transactional
    @GetMapping("/usuarios/rol/{rol}")
    public ResponseEntity<List<Usuario>> getUsuariosRol(@PathVariable("rol") String rol){
        List<Usuario> usuarios = usuarioServicio.getUsuariosbyRol(rol);
        return ResponseEntity.ok().body(usuarios);
    }

    @Transactional
    @GetMapping("/usuarios")
    public ResponseEntity<List<Usuario>> getAllUsuarios() {
        List<Usuario> usuarios = usuarioServicio.getUsuarios();
        return ResponseEntity.ok().body(usuarios);
    }

    @Transactional
    @GetMapping("/usuarios/update/nombre/{nif}/{nombre}/{apellido1}/{apellido2}")
    public ResponseEntity<Usuario> updateClienteNombreCompletoNif(@PathVariable("nif") String nif, @PathVariable("nombre") String nombre, @PathVariable("apellido1") String apellido1,@PathVariable("apellido2") String apellido2) {
        Usuario usuario= usuarioServicio.updateUsuarioNombreCompletobyNif(nif,nombre,apellido1,apellido2);
        if(usuario != null){
            return ResponseEntity.ok().body(usuario);
        }
        return ResponseEntity.notFound().build();
    }

    @Transactional
    @GetMapping("/usuarios/update/correo/{nif}/{correo}")
    public ResponseEntity<Usuario> updateClienteCorreoNif(@PathVariable("nif") String nif, @PathVariable("correo") String correo) {
        Usuario usuario= usuarioServicio.updateUsuarioCorreobyNif(nif,correo);
        if(usuario != null){
            return ResponseEntity.ok().body(usuario);
        }
        return ResponseEntity.notFound().build();
    }

    @Transactional
    @GetMapping("/usuarios/update/cumpleanos/{nif}/{cumpleanos}")
    public ResponseEntity<Usuario> updateUsuarioCumpleanosNif(@PathVariable("nif") String nif, @PathVariable("cumpleanos") String cumpleanosStr) {
        LocalDate cumpleanos = LocalDate.parse(cumpleanosStr);
        Usuario usuario= usuarioServicio.updateUsuarioCumpleanosbyNif(nif,cumpleanos);
        if(usuario != null){
            return ResponseEntity.ok().body(usuario);
        }
        return ResponseEntity.notFound().build();
    }

    @Transactional
    @GetMapping("/usuarios/insert/{nif}/{nombre}/{apellido1}/{apellido2}/{correo}/{contrasena}/{cumpleanos}/{rol}")
    public ResponseEntity<String> insertCompareUsuario(@PathVariable("nif") String nif, @PathVariable("nombre") String nombre, @PathVariable("apellido1") String apellido1,@PathVariable("apellido2") String apellido2,@PathVariable("correo") String correo,@PathVariable("contrasena") String contrasena,@PathVariable("cumpleanos") String cumpleanosStr, @PathVariable("rol") String rol) {
        LocalDate cumpleanos = LocalDate.parse(cumpleanosStr);
        String resultado = usuarioServicio.insertAndCompareUsuario(nif,nombre,apellido1,apellido2,correo,contrasena,cumpleanos,rol);
        return ResponseEntity.ok().body(resultado);
    }

    @Transactional
    @GetMapping("/usuarios/delete/{nif}")
    public ResponseEntity<String> deleteUsuarioNif(@PathVariable("nif") String nif) {
        String resultado = usuarioServicio.deleteUsuariobyNif(nif);
        return ResponseEntity.ok().body(resultado);
    }

    // INNER-JOIN
    @Transactional
    @GetMapping("/usuarios/reservas")
    public ResponseEntity<List<UsuarioReservaDTO>> getUsuariosReservas() {
        var reservas = usuarioServicio.getUsuariosConReservas();
        return ResponseEntity.ok().body(reservas);
    }

    @Transactional
    @PostMapping("/usuarios/inicio-sesion")
    public ResponseEntity<LoginResponse> inicioSesionCliente(@RequestBody Usuario usuario, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            LoginResponse loginResponse = new LoginResponse("KO");
            return new ResponseEntity<LoginResponse>(loginResponse, HttpStatus.BAD_REQUEST);
        }
        LoginServiceResult result = loginServicio.inicioSesionDeUsuario(usuario);
        if (result.isFlag()) {
            LoginResponse loginResponse = new LoginResponse("OK", result.getAccessToken());
            return new ResponseEntity<LoginResponse>(loginResponse, HttpStatus.OK);
        }else if(result.getAccessToken() == "Contraseña Errónea") {
            LoginResponse loginResponse = new LoginResponse("Contraseña errónea");
            return new ResponseEntity<LoginResponse>(loginResponse, HttpStatus.BAD_REQUEST);
        }else{
            LoginResponse loginResponse = new LoginResponse("Usuario no existe");
            return new ResponseEntity<LoginResponse>(loginResponse, HttpStatus.BAD_REQUEST);
        }
    }
}