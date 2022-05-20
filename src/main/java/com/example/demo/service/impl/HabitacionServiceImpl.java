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
    public Long getHabitacionCapacidadbyHotel(String tipo){
        return habitacionRepository.getHabitacionCapacidadByHotel(tipo);
    }

    @Override
    @Transactional
    public List<Habitacion> getHabitaciones() {
        return StreamSupport.stream(habitacionRepository.findAll().spliterator(), false).collect(Collectors.toUnmodifiableList());
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
}