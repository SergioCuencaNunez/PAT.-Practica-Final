package com.example.demo.controller;

import com.example.demo.model.Contacto;
import com.example.demo.service.ContactoService;
import com.example.demo.service.LoginServiceResult;
import com.example.demo.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class ContactoController {

    @Autowired
    private ContactoService contactoServicio;

    @Autowired
    private LoginService loginServicio;

    @Transactional
    @GetMapping("/contactos/numero/{numero}")
    public ResponseEntity<Contacto> getContactoNumero(@PathVariable("numero") String numeroStr) {
        Long numero = Long.parseLong(numeroStr);
        Contacto contacto = contactoServicio.getContactobyNumero(numero);
        if (contacto != null) {
            return ResponseEntity.ok().body(contacto);
        }
        return ResponseEntity.notFound().build();
    }

    @Transactional
    @GetMapping("/contactos/correo/{correo}")
    public ResponseEntity<Contacto> getContactoCorreo(@PathVariable("correo") String correo) {
        Contacto contacto = contactoServicio.getContactobyCorreo(correo);
        if (contacto != null) {
            return ResponseEntity.ok().body(contacto);
        }
        return ResponseEntity.notFound().build();
    }

    @Transactional
    @GetMapping("/contactos/mensaje/{correo}")
    public ResponseEntity<String> getMensajeCorreo(@PathVariable("correo") String correo) {
        String mensaje = contactoServicio.getMensajebyCorreo(correo);
        return ResponseEntity.ok().body(mensaje);
    }

    @Transactional
    @GetMapping("/contactos")
    public ResponseEntity<List<Contacto>> getAllContactos() {
        List<Contacto> contactos = contactoServicio.getContactos();
        return ResponseEntity.ok().body(contactos);
    }

    @Transactional
    @PostMapping("/contactos/insert-mensaje")
    public ResponseEntity<LoginResponse> insertMensajeContacto(@RequestBody Contacto contacto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            LoginResponse loginResponse = new LoginResponse("KO");
            return new ResponseEntity<LoginResponse>(loginResponse, HttpStatus.BAD_REQUEST);
        }
        LoginServiceResult result = loginServicio.mensajeContacto(contacto);
        if (result.isFlag()) {
            LoginResponse loginResponse = new LoginResponse("OK", result.getAccessToken());
            return new ResponseEntity<LoginResponse>(loginResponse, HttpStatus.OK);
        } else {
            LoginResponse loginResponse = new LoginResponse("El usuario no existe. Para poder enviar un mensaje debe estar registrado en MeliáRewards.");
            return new ResponseEntity<LoginResponse>(loginResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    @PostMapping("/contactos/insert-suscripcion")
    public ResponseEntity<LoginResponse> insertSuscripcionContacto(@RequestBody Contacto contacto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            LoginResponse loginResponse = new LoginResponse("KO");
            return new ResponseEntity<LoginResponse>(loginResponse, HttpStatus.BAD_REQUEST);
        }
        LoginServiceResult result = loginServicio.suscripcionContacto(contacto);
        if (result.isFlag()) {
            LoginResponse loginResponse = new LoginResponse("OK", result.getAccessToken());
            return new ResponseEntity<LoginResponse>(loginResponse, HttpStatus.OK);
        }else if(result.getAccessToken() == "Usuario registrado en MeliáRewards") {
            LoginResponse loginResponse = new LoginResponse("Este correo electrónico ya está vinculado con una cuenta en MeliáRewards.");
            return new ResponseEntity<LoginResponse>(loginResponse, HttpStatus.BAD_REQUEST);
        }else if(result.getAccessToken() == "Usuario registrado en boletín de suscripción"){
            LoginResponse loginResponse = new LoginResponse("Este correo electrónico ya está suscrito al boletín de suscripción.");
            return new ResponseEntity<LoginResponse>(loginResponse, HttpStatus.BAD_REQUEST);
        }else{
            LoginResponse loginResponse = new LoginResponse("Debe rellenar el campo con un correo electrónico para suscribirse al boletín de suscripción.");
            return new ResponseEntity<LoginResponse>(loginResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    @GetMapping("/usuarios/delete/{numero}")
    public ResponseEntity<String> deleteContactoNif(@PathVariable("numero") String numeroStr) {
        Long numero = Long.parseLong(numeroStr);
        String resultado = contactoServicio.deleteContactobyNumero(numero);
        return ResponseEntity.ok().body(resultado);
    }
}