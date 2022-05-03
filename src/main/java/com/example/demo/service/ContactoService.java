package com.example.demo.service;

import com.example.demo.model.Contacto;

import java.util.List;

public interface ContactoService {

    Contacto getContactobyNumero(Long numero);
    Contacto getContactobyCorreo(String correo);
    String getMensajebyCorreo(String correo);
    List<Contacto> getContactos();
    void insertContacto(Long numero, String correo, String nombre, String mensaje);
    String deleteContactobyNumero(Long numero);
}