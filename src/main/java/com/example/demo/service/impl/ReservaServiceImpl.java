package com.example.demo.service.impl;

import com.example.demo.model.Reserva;
import com.example.demo.repository.ReservaRepository;
import com.example.demo.repository.HotelRepository;
import com.example.demo.service.ReservaService;
import com.example.demo.service.ReservaServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ReservaServiceImpl implements ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private HotelRepository hotelRepository;

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
    public List<Reserva> getReservas() {
        return StreamSupport.stream(reservaRepository.findAll().spliterator(), false).collect(Collectors.toUnmodifiableList());
    }

    @Override
    @Transactional
    public Reserva updateReservabyId(Long id, String hotel, String destino, String tipo, Long huespedes, Long habitaciones, LocalDate fechaEntrada, LocalDate fechaSalida){
        Reserva reserva = null;
        Optional<Reserva> oreserva = reservaRepository.findById(id);
        if(oreserva.isPresent()){
            reserva = oreserva.get();
            reserva.setHotel(hotel);
            reserva.setDestino(destino);
            reserva.setTipo(tipo);
            reserva.setHuespedes(huespedes);
            reserva.setHabitaciones(habitaciones);
            reserva.setFechaEntrada(fechaEntrada);
            reserva.setFechaSalida(fechaSalida);
            reservaRepository.updateReservaById(reserva.getId(), reserva.getHotel(), reserva.getDestino(), reserva.getTipo(), reserva.getHuespedes(), reserva.getHabitaciones(), reserva.getFechaEntrada(), reserva.getFechaSalida());
            return reserva;
        }
        return reserva;
    }

    @Override
    @Transactional
    public void insertReserva(Long id, String nif, String hotel, String destino, String tipo, Long huespedes, Long habitaciones, LocalDate fechaEntrada, LocalDate fechaSalida){
        Reserva reserva = new Reserva();
        reserva.setId(id);
        reserva.setNif(nif);
        reserva.setHotel(hotel);
        reserva.setDestino(destino);
        reserva.setHuespedes(huespedes);
        reserva.setHabitaciones(habitaciones);
        reserva.setFechaEntrada(fechaEntrada);
        reserva.setFechaSalida(fechaSalida);
        reserva.setTipo(tipo);
        reservaRepository.insertReserva(reserva.getId(), reserva.getNif(), reserva.getHotel(), reserva.getDestino(), reserva.getTipo(), reserva.getHuespedes(), reserva.getHabitaciones(), reserva.getFechaEntrada(), reserva.getFechaSalida());
    }

    @Override
    @Transactional
    public ReservaServiceResult comprobarDisponibilidad(String hotel, Long habitaciones) {

        Long habitacionesTotales = hotelRepository.getHabitacionesTotalesByHotel(hotel);
        Long habitacionesOcupadas = hotelRepository.getHabitacionesOcupadasByHotel(hotel);

        if((habitacionesOcupadas + habitaciones) <= habitacionesTotales) {
            return new ReservaServiceResult(true);
        }else{
            return new ReservaServiceResult(false, "No hay habitaciones disponibles");
        }
    }

    @Override
    @Transactional
    public ReservaServiceResult checkInReserva(Reserva reserva){

        Long id = reserva.getId();
        String nif = reserva.getNif();
        String hotel = reserva.getHotel();
        String destino = reserva.getDestino();
        LocalDate fechaEntrada = reserva.getFechaEntrada();
        LocalDate fechaSalida = reserva.getFechaSalida();

        if(nif.equals(reservaRepository.getNifReservaById(id)) && hotel.equals(reservaRepository.getHotelReservaById(id)) && destino.equals(reservaRepository.getDestinoReservaById(id)) && fechaEntrada.equals(reservaRepository.getFechaEntradaReservaById(id)) && fechaSalida.equals(reservaRepository.getFechaSalidaReservaById(id))){
            return new ReservaServiceResult(true);
        }else{
            return new ReservaServiceResult(false, "Reserva no encontrada");
        }
    }

    @Override
    @Transactional
    public ReservaServiceResult registrarReserva(Reserva reserva){

        Long id = reserva.getId();
        String nif = reserva.getNif();
        String hotel = reserva.getHotel();
        String destino = reserva.getDestino();
        String tipo = reserva.getTipo();
        Long huespedes = reserva.getHuespedes();
        Long habitaciones = reserva.getHabitaciones();
        LocalDate fechaEntrada = reserva.getFechaEntrada();
        LocalDate fechaSalida = reserva.getFechaSalida();

        Long ocupacion = hotelRepository.getOcupacionByHotel(hotel);
        Long habitacionesOcupadas = hotelRepository.getHabitacionesOcupadasByHotel(hotel);


        if(id == null){
            ReservaServiceResult result = this.comprobarDisponibilidad(hotel, habitaciones);
            if(result.isFlag()) {
                hotelRepository.updateHotelOcupacionByNombre(ocupacion + huespedes, hotel);
                hotelRepository.updateHotelHabitacionesOcupadasByNombre(habitacionesOcupadas + habitaciones, hotel);
                ReservaServiceImpl.this.insertReserva(id, nif, hotel, destino, tipo, huespedes, habitaciones, fechaEntrada, fechaSalida);
                return new ReservaServiceResult(true);
            }else{
                return new ReservaServiceResult(false, "No hay habitaciones disponibles");
            }
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