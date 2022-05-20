package com.example.demo.service.impl;

import com.example.demo.model.Habitacion;
import com.example.demo.model.Hotel;
import com.example.demo.repository.HotelRepository;
import com.example.demo.repository.HabitacionRepository;
import com.example.demo.service.HabitacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class HabitacionServiceImpl implements HabitacionService {

    @Autowired
    private HabitacionRepository habitacionRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    @Transactional
    public Habitacion getHabitacionbyTipo(String tipo){
        Habitacion habitacion = null;
        Optional<Habitacion> ohabitacion = habitacionRepository.findById(tipo);
        if(ohabitacion.isPresent()){
            habitacion = ohabitacion.get();
            return habitacion;
        }
        return habitacion;
    }

    @Override
    @Transactional
    public List<Habitacion> getHabitacionesbyHotel(String hotel){
        return habitacionRepository.getHabitacionesByHotel(hotel);
    }

    @Override
    @Transactional
    public List<Habitacion> getHabitacionesbyEstado(Boolean estado){
        return habitacionRepository.getHabitacionesByEstado(estado);
    }

    @Override
    @Transactional
    public List<Habitacion> getHabitacionesbyHotelEstado(String hotel, Boolean estado){
        return habitacionRepository.getHabitacionesByHotelEstado(hotel, estado);
    }

    @Override
    @Transactional
    public List<Habitacion> getHabitaciones() {
        return StreamSupport.stream(habitacionRepository.findAll().spliterator(), false).collect(Collectors.toUnmodifiableList());
    }

    @Override
    @Transactional
    public Habitacion updateHabitacionEstadobyTipo(Boolean estado, String tipo){
        Habitacion habitacion = null;
        Optional<Habitacion> ohabitacion = habitacionRepository.findById(tipo);
        if(ohabitacion.isPresent()){
            habitacion = ohabitacion.get();
            habitacion.setEstado(estado);
            habitacionRepository.updateHabitacionEstadoByTipo(habitacion.getEstado(), habitacion.getTipo());
            return habitacion;
        }
        return habitacion;
    }

    @Override
    @Transactional
    public Habitacion updateHabitacionNumerobyTipo(Long numero, String tipo){
        Habitacion habitacion = null;
        Optional<Habitacion> ohabitacion = habitacionRepository.findById(tipo);
        if(ohabitacion.isPresent()){
            habitacion = ohabitacion.get();
            habitacion.setNumero(numero);
            habitacionRepository.updateHabitacionNumeroByTipo(habitacion.getNumero(), habitacion.getTipo());
            return habitacion;
        }
        return habitacion;
    }

    @Override
    @Transactional
    public Habitacion updateHabitacionCapacidadbyTipo(Long capacidad, String tipo){
        Habitacion habitacion = null;
        Optional<Habitacion> ohabitacion = habitacionRepository.findById(tipo);
        if(ohabitacion.isPresent()){
            habitacion = ohabitacion.get();
            habitacion.setCapacidad(capacidad);
            habitacionRepository.updateHabitacionCapacidadByTipo(habitacion.getCapacidad(), habitacion.getTipo());
            return habitacion;
        }
        return habitacion;
    }

    @Override
    @Transactional
    public String insertAndCompareHabitacion(String tipo, Long numero, Long planta, String hotel, Long capacidad, Boolean estado){
        Optional<Habitacion> ohabitacion = habitacionRepository.findById(tipo);
        Optional<Hotel> ohotel = hotelRepository.findById(hotel);
        if(ohabitacion.isPresent()){
            return "La habitación " + tipo + " ya está registrada en el hotel " + hotel;
        }else if(!ohotel.isPresent()){
            return "No hay ningún hotel llamado " + hotel + " en los activos de Meliá Hotels International";
        }else{
            Habitacion habitacion = new Habitacion();
            habitacion.setTipo(tipo);
            habitacion.setNumero(numero);
            habitacion.setPlanta(planta);
            habitacion.setHotel(hotel);
            habitacion.setCapacidad(capacidad);
            habitacion.setEstado(estado);
            habitacionRepository.insertHabitacion(habitacion.getTipo(), habitacion.getNumero(), habitacion.getPlanta(), habitacion.getHotel(), habitacion.getCapacidad(), habitacion.getEstado());
            return "La habitación " + tipo + " se ha registrado correctamente en el hotel " + hotel;
        }
    }

    @Override
    @Transactional
    public String deleteHabitacionbyTipo(String tipo){
        Optional<Habitacion> ohabitacion = habitacionRepository.findById(tipo);
        if(ohabitacion.isPresent()){
            habitacionRepository.deleteById(tipo);
            return "La habitación " + tipo + " se ha eliminado correctamente";
        }else{
            return "No hay ninguna habitacion del tipo " + tipo;
        }
    }
}