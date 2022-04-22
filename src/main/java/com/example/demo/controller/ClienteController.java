package com.example.demo.controller;

import com.example.demo.model.Cliente;
import com.example.demo.service.ClienteService;
import com.example.demo.service.dto.ClienteReservaDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class ClienteController {

    @Autowired
    private ClienteService clienteServicio;

    @Transactional
    @GetMapping("/clientes/nif/{nif}")
    public ResponseEntity<Cliente> getClienteNif(@PathVariable("nif") String nif) {
        Cliente cliente= clienteServicio.getClientebyNif(nif);
        if(cliente != null){
            return ResponseEntity.ok().body(cliente);
        }
        return ResponseEntity.notFound().build();
    }

    @Transactional
    @GetMapping("/clientes/nombre/{nombre}/{apellido1}/{apellido2}")
    public ResponseEntity<Cliente> getClienteNombreCompleto(@PathVariable("nombre") String nombre, @PathVariable("apellido1") String apellido1,@PathVariable("apellido2") String apellido2){
        Cliente cliente = clienteServicio.getClientebyNombreCompleto(nombre,apellido1,apellido2);
        return ResponseEntity.ok().body(cliente);
    }

    @Transactional
    @GetMapping("/clientes/correo/{correo}")
    public ResponseEntity<Cliente> getClienteCorreo(@PathVariable("correo") String correo){
        Cliente cliente = clienteServicio.getClientebyCorreo(correo);
        return ResponseEntity.ok().body(cliente);
    }

    @Transactional
    @GetMapping("/clientes")
    public ResponseEntity<List<Cliente>> getAllClientes() {
        List<Cliente> clientes = clienteServicio.getClientes();
        return ResponseEntity.ok().body(clientes);
    }

    @Transactional
    @GetMapping("/clientes/update/nombre/{nif}/{nombre}/{apellido1}/{apellido2}")
    public ResponseEntity<Cliente> updateClienteNombreNif(@PathVariable("nif") String nif, @PathVariable("nombre") String nombre, @PathVariable("apellido1") String apellido1,@PathVariable("apellido2") String apellido2) {
        Cliente cliente= clienteServicio.updateClienteNombreCompletobyNif(nif,nombre,apellido1,apellido2);
        if(cliente != null){
            return ResponseEntity.ok().body(cliente);
        }
        return ResponseEntity.notFound().build();
    }

    @Transactional
    @GetMapping("/clientes/update/correo/{nif}/{correo}")
    public ResponseEntity<Cliente> updateClienteCorreoNif(@PathVariable("nif") String nif, @PathVariable("correo") String correo) {
        Cliente cliente= clienteServicio.updateClienteCorreobyNif(nif,correo);
        if(cliente != null){
            return ResponseEntity.ok().body(cliente);
        }
        return ResponseEntity.notFound().build();
    }

    @Transactional
    @GetMapping("/clientes/update/cumpleanos/{nif}/{cumpleanos}")
    public ResponseEntity<Cliente> updateClienteCumpleanosNif(@PathVariable("nif") String nif, @PathVariable("cumpleanos") String cumpleanosStr) {
        LocalDate cumpleanos = LocalDate.parse(cumpleanosStr);
        Cliente cliente= clienteServicio.updateClienteCumpleanosbyNif(nif,cumpleanos);
        if(cliente != null){
            return ResponseEntity.ok().body(cliente);
        }
        return ResponseEntity.notFound().build();
    }

    @Transactional
    @GetMapping("/clientes/insert/{nif}/{nombre}/{apellido1}/{apellido2}/{correo}/{cumpleanos}")
    public ResponseEntity<String> insertCompareCliente(@PathVariable("nif") String nif, @PathVariable("nombre") String nombre, @PathVariable("apellido1") String apellido1,@PathVariable("apellido2") String apellido2,@PathVariable("correo") String correo,@PathVariable("cumpleanos") String cumpleanosStr) {
        LocalDate cumpleanos = LocalDate.parse(cumpleanosStr);
        String resultado = clienteServicio.insertAndCompareCliente(nif,nombre,apellido1,apellido2,correo,cumpleanos);
        return ResponseEntity.ok().body(resultado);
    }

    @Transactional
    @GetMapping("/clientes/delete/{nif}")
    public ResponseEntity<String> deleteClienteNif(@PathVariable("nif") String nif) {
        String resultado = clienteServicio.deleteClientebyNif(nif);
        return ResponseEntity.ok().body(resultado);
    }

    // INNER-JOIN
    @Transactional
    @GetMapping("/clientes/reservas")
    public ResponseEntity<List<ClienteReservaDTO>> getClientesReservas() {
        var reservas = clienteServicio.getClientesConReservas();
        return ResponseEntity.ok().body(reservas);
    }
}