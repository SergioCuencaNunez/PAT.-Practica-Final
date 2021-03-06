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
            LoginResponse loginResponse = new LoginResponse("El usuario no existe. Para poder enviar un mensaje debe estar registrado en Meli??Rewards.");
            return new ResponseEntity<LoginResponse>(loginResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    @PostMapping("/contactos/insert-checkIn")
    public ResponseEntity<LoginResponse> insertCheckInContacto(@RequestBody Contacto contacto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            LoginResponse loginResponse = new LoginResponse("KO");
            return new ResponseEntity<LoginResponse>(loginResponse, HttpStatus.BAD_REQUEST);
        }
        LoginServiceResult result = loginServicio.checkInContacto(contacto);
        if (result.isFlag()) {
            LoginResponse loginResponse = new LoginResponse("OK", result.getAccessToken());
            return new ResponseEntity<LoginResponse>(loginResponse, HttpStatus.OK);
        }else if(result.getAccessToken().equals("Check-in online ya realizado")){
            LoginResponse loginResponse = new LoginResponse("El check-in online ya se ha realizado para esta reserva.");
            return new ResponseEntity<LoginResponse>(loginResponse, HttpStatus.BAD_REQUEST);
        }else{
            LoginResponse loginResponse = new LoginResponse("El check-in online no ha podido realizarse correctamente. Disculpe las molestias, int??ntelo m??s tarde.");
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
        }else if(result.getAccessToken().equals("Usuario registrado en Meli??Rewards")){
            LoginResponse loginResponse = new LoginResponse("Este correo electr??nico ya est?? vinculado con una cuenta en Meli??Rewards.");
            return new ResponseEntity<LoginResponse>(loginResponse, HttpStatus.BAD_REQUEST);
        }else if(result.getAccessToken().equals("Usuario registrado en bolet??n de suscripci??n")){
            LoginResponse loginResponse = new LoginResponse("Este correo electr??nico ya est?? suscrito al bolet??n de suscripci??n.");
            return new ResponseEntity<LoginResponse>(loginResponse, HttpStatus.BAD_REQUEST);
        }else{
            LoginResponse loginResponse = new LoginResponse("Debe rellenar el campo con un correo electr??nico para suscribirse al bolet??n de suscripci??n.");
            return new ResponseEntity<LoginResponse>(loginResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    @DeleteMapping("/contactos/delete/{numero}")
    public ResponseEntity<String> deleteContactoNumero(@PathVariable("numero") String numeroStr){
        Long numero = Long.parseLong(numeroStr);
        String resultado = contactoServicio.deleteContactobyNumero(numero);
        if(resultado.equals("El mensaje se ha eliminado correctamente")){
            return new ResponseEntity<>("", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
        }
    }
}