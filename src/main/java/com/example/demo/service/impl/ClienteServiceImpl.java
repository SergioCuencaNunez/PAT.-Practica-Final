package com.example.demo.service.impl;

import com.example.demo.model.Cliente;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.service.ClienteService;
import com.example.demo.service.dto.ClienteReservaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public Cliente getClientebyNif(String nif) {
        Cliente cliente = null;
        Optional<Cliente> ocliente = clienteRepository.findById(nif);
        if(ocliente.isPresent()){
            cliente = ocliente.get();
            return cliente;
        }
        return cliente;
    }

    @Override
    @Transactional
    public Cliente getClientebyNombreCompleto(String nombre, String apellido1, String apellido2) {
        return clienteRepository.getClienteByNombreCompleto(nombre,apellido1,apellido2);
    }

    @Override
    @Transactional
    public Cliente getClientebyCorreo(String correo) {
        return clienteRepository.getClienteByCorreo(correo);
    }

    @Override
    @Transactional
    public List<Cliente> getClientes() {
        return StreamSupport.stream(clienteRepository.findAll().spliterator(), false).collect(Collectors.toUnmodifiableList());
    }

    @Override
    @Transactional
    public Cliente updateClienteNombreCompletobyNif(String nif, String nombre, String apellido1, String apellido2) {
        Cliente cliente = null;
        Optional<Cliente> ocliente = clienteRepository.findById(nif);
        if(ocliente.isPresent()){
            cliente = ocliente.get();
            cliente.setNombre(nombre);
            cliente.setApellido1(apellido1);
            cliente.setApellido2(apellido2);
            clienteRepository.updateClienteNombreCompletoByNif(cliente.getNombre(), cliente.getApellido1(), cliente.getApellido2(), cliente.getNif());
            return cliente;
        }
        return cliente;
    }

    @Override
    @Transactional
    public Cliente updateClienteCorreobyNif(String nif, String correo) {
        Cliente cliente = null;
        Optional<Cliente> ocliente = clienteRepository.findById(nif);
        if(ocliente.isPresent()){
            cliente = ocliente.get();
            cliente.setCorreo(correo);
            clienteRepository.updateClienteCorreoByNif(cliente.getCorreo(), cliente.getNif());
            return cliente;
        }
        return cliente;
    }

    @Override
    @Transactional
    public Cliente updateClienteCumpleanosbyNif(String nif, LocalDate cumpleanos) {
        Cliente cliente = null;
        Optional<Cliente> ocliente = clienteRepository.findById(nif);
        if(ocliente.isPresent()){
            cliente = ocliente.get();
            cliente.setCumpleanos(cumpleanos);
            clienteRepository.updateClienteCumpleanosByNif(cliente.getCumpleanos(), cliente.getNif());
            return cliente;
        }
        return cliente;
    }

    @Override
    @Transactional
    public String insertAndCompareCliente(String nif, String nombre, String apellido1, String apellido2, String correo, String contrasena, LocalDate cumpleanos){
        Optional<Cliente> ocliente = clienteRepository.findById(nif);
        List<String> correos = clienteRepository.getClienteCorreos();
        if(ocliente.isPresent()) {
            return "El cliente con NIF " + nif + " ya está registrado";
        }else if(correos.contains(correo)){
           return "El cliente con correo " + correo + " ya está registrado";
        }else{
            Cliente cliente = new Cliente();
            cliente.setNif(nif);
            cliente.setNombre(nombre);
            cliente.setApellido1(apellido1);
            cliente.setApellido2(apellido2);
            cliente.setCorreo(correo);
            cliente.setContrasena(contrasena);
            cliente.setCumpleanos(cumpleanos);
            clienteRepository.insertCliente(cliente.getNif(), cliente.getNombre(), cliente.getApellido1(), cliente.getApellido2(),cliente.getCorreo(),cliente.getContrasena(),cliente.getCumpleanos());
            return "El cliente con NIF " + nif + " se ha registrado correctamente";
        }
    }

    @Override
    @Transactional
    public String deleteClientebyNif(String nif) {
        Optional<Cliente> ocliente = clienteRepository.findById(nif);
        if(ocliente.isPresent()) {
            clienteRepository.deleteById(nif);
            return "El cliente con NIF " + nif + " se ha eliminado correctamente";
        }else{
            return "No hay ningún cliente con NIF " + nif;
        }
    }

    // INNER-JOIN
    @Override
    public List<ClienteReservaDTO> getClientesConReservas(){

        String query = """ 
        SELECT CLIENTE.NIF, CLIENTE.NOMBRE, CLIENTE.APELLIDO1, CLIENTE.APELLIDO2, CLIENTE.CORREO, RESERVA.ID, RESERVA.HOTEL, RESERVA.DESTINO, RESERVA.HUESPEDES, RESERVA.HABITACIONES, RESERVA.FECHAENTRADA, RESERVA.FECHASALIDA
        FROM RESERVA
        INNER JOIN CLIENTE ON CLIENTE.NIF=RESERVA.NIF;
        """;

        List<ClienteReservaDTO> clientesLista = jdbcTemplate.query(
                query,
                (rs, rowNum) ->
                        new ClienteReservaDTO(
                                rs.getString("NIF"),
                                rs.getString("NOMBRE"),
                                rs.getString("APELLIDO1"),
                                rs.getString("APELLIDO2"),
                                rs.getString("CORREO"),
                                rs.getLong("ID"),
                                rs.getString("HOTEL"),
                                rs.getString("DESTINO"),
                                rs.getLong("HUESPEDES"),
                                rs.getLong("HABITACIONES"),
                                (rs.getTimestamp("FECHAENTRADA")!=null) ? rs.getTimestamp("FECHAENTRADA").toLocalDateTime() : null,
                                (rs.getTimestamp("FECHASALIDA")!=null) ? rs.getTimestamp("FECHASALIDA").toLocalDateTime() : null
                        )
        );
        return clientesLista;
    }
}