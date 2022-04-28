package com.example.demo.service.impl;

import com.example.demo.model.Reserva;
import com.example.demo.model.Usuario;
import com.example.demo.repository.ReservaRepository;
import com.example.demo.service.ReservaServiceResult;
import com.example.demo.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.time.LocalDate;

@Service
public class ReservaServiceImpl implements ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Override
    @Transactional
    public Reserva getReservabyId(Long id){
        Reserva reserva = null;
        Optional<Reserva> oreserva = reservaRepository.findById(id);
        if(oreserva.isPresent()){
            reserva = oreserva.get();
            return reserva;
        }
        return reserva;
    }

    @Override
    @Transactional
    public List<Reserva> getReservasbyNif(String nif) {
        return reservaRepository.getReservasByNif(nif);
    }

    @Override
    @Transactional
    public List<Reserva> getReservasbyDestino(String destino) {
        return reservaRepository.getReservasByDestino(destino);
    }

    @Override
    @Transactional
    public List<Reserva> getReservasbyHotel(String hotel) {
        return reservaRepository.getReservasByHotel(hotel);
    }

    @Override
    @Transactional
    public List<Reserva> getReservasbyHotelFechaEntrada(String hotel, LocalDate fechaEntrada){
        return reservaRepository.getReservasByHotelFechaEntrada(hotel, fechaEntrada);
    }

    @Override
    @Transactional
    public List<Reserva> getReservas() {
        return StreamSupport.stream(reservaRepository.findAll().spliterator(), false).collect(Collectors.toUnmodifiableList());
    }

    @Override
    @Transactional
    public Reserva updateReservabyId(Long id, String hotel, String destino, Long huespedes, Long habitaciones, LocalDate fechaEntrada, LocalDate fechaSalida){
        Reserva reserva = null;
        Optional<Reserva> oreserva = reservaRepository.findById(id);
        if(oreserva.isPresent()){
            reserva = oreserva.get();
            reserva.setHotel(hotel);
            reserva.setDestino(destino);
            reserva.setHuespedes(huespedes);
            reserva.setHabitaciones(habitaciones);
            reserva.setFechaEntrada(fechaEntrada);
            reserva.setFechaSalida(fechaSalida);
            reservaRepository.updateReservaById(reserva.getId(), reserva.getHotel(), reserva.getDestino(), reserva.getHuespedes(), reserva.getHabitaciones(), reserva.getFechaEntrada(), reserva.getFechaSalida());
            return reserva;
        }
        return reserva;
    }

    @Override
    @Transactional
    public void insertReserva(Long id, String nif, String hotel, String destino, Long huespedes, Long habitaciones, LocalDate fechaEntrada, LocalDate fechaSalida){
        Reserva reserva = new Reserva();
        reserva.setId(id);
        reserva.setNif(nif);
        reserva.setHotel(hotel);
        reserva.setDestino(destino);
        reserva.setHuespedes(huespedes);
        reserva.setHabitaciones(habitaciones);
        reserva.setFechaEntrada(fechaEntrada);
        reserva.setFechaSalida(fechaSalida);
        reservaRepository.insertReserva(reserva.getId(), reserva.getNif(), reserva.getHotel(), reserva.getDestino(), reserva.getHuespedes(), reserva.getHabitaciones(), reserva.getFechaEntrada(), reserva.getFechaSalida());
    }

    @Override
    public ReservaServiceResult registrarReserva(Reserva reserva){

        Long id = reserva.getId();
        String nif = reserva.getNif();
        String hotel = reserva.getHotel();
        String destino = reserva.getDestino();
        Long huespedes = reserva.getHuespedes();
        Long habitaciones = reserva.getHabitaciones();
        LocalDate fechaEntrada = reserva.getFechaEntrada();
        LocalDate fechaSalida = reserva.getFechaSalida();

        if(id == null){
            ReservaServiceImpl.this.insertReserva(id, nif, hotel, destino, huespedes, habitaciones, fechaEntrada, fechaSalida);
            return new ReservaServiceResult(true);
        }else{
            return new ReservaServiceResult(false, "Reserva ya registrada");
        }
    }

    @Override
    @Transactional
    public String deleteReservabyId(Long id){
        Optional<Reserva> oreserva = reservaRepository.findById(id);
        if(oreserva.isPresent()){
            reservaRepository.deleteById(id);
            return "La reserva con ID: " + id + " se ha borrado correctamente";
        }else{
            return "No existe la reserva con ID: "+id;
        }
    }

}