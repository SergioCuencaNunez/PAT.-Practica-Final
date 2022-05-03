package com.example.demo.service.impl;

import com.example.demo.model.Contacto;
import com.example.demo.repository.ContactoRepository;
import com.example.demo.service.ContactoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ContactoServiceImpl implements ContactoService {

    @Autowired
    private ContactoRepository contactoRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public Contacto getContactobyNumero(Long numero) {
        Contacto contacto = null;
        Optional<Contacto> ocontacto = contactoRepository.findById(numero);
        if(ocontacto.isPresent()){
            contacto = ocontacto.get();
            return contacto;
        }
        return contacto;
    }

    @Override
    @Transactional
    public Contacto getContactobyCorreo(String correo) {
        return contactoRepository.getContactoByCorreo(correo);
    }

    @Override
    @Transactional
    public String getMensajebyCorreo(String correo) {
        return contactoRepository.getMensajeByCorreo(correo);
    }

    @Override
    @Transactional
    public List<Contacto> getContactos() {
        return StreamSupport.stream(contactoRepository.findAll().spliterator(), false).collect(Collectors.toUnmodifiableList());
    }

    @Override
    @Transactional
    public void insertContacto(Long numero, String correo, String nombre, String mensaje){
        Contacto contacto = new Contacto();
        contacto.setNumero(numero);
        contacto.setCorreo(correo);
        contacto.setNombre(nombre);
        contacto.setMensaje(mensaje);
        contactoRepository.insertarContacto(contacto.getNumero(),contacto.getCorreo(), contacto.getNombre(), contacto.getMensaje());
    }

    @Override
    @Transactional
    public String deleteContactobyNumero(Long numero) {
        Optional<Contacto> ocontacto = contactoRepository.findById(numero);
        if(ocontacto.isPresent()) {
            contactoRepository.deleteById(numero);
            return "El mensaje número " + numero + " se ha eliminado correctamente";
        }else{
            return "No hay ningún mensaje con ese numero " + numero;
        }
    }
}