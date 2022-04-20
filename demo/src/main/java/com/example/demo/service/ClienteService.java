package com.example.demo.service;

import com.example.demo.model.Cliente;
import com.example.demo.service.dto.ClienteReservaDTO;
import com.example.demo.service.dto.HotelReservaDTO;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

public interface ClienteService {

    Cliente getClientebyNif(String nif);
    Cliente getClientebyNombreCompleto(String nombre, String apellido1, String apellido2);
    Cliente getClientebyCorreo(String correo);
    List<Cliente> getClientes();
    Cliente updateClienteNombreCompletobyNif(String nif, String nombre, String apellido1, String apellido2);
    Cliente updateClienteCorreobyNif(String nif, String correo);
    Cliente updateClienteCumpleanosbyNif(String nif, LocalDate cumpleanos);
    String insertAndCompareCliente(String nif, String nombre, String apellido1, String apellido2, String correo, LocalDate cumpleanos);
    String deleteClientebyNif(String nif);
    //INNER-JOIN
    List<ClienteReservaDTO> getClientesConReservas();
}