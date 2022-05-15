package com.example.demo.service.impl;

import com.example.demo.model.Hotel;
import com.example.demo.repository.HotelRepository;
import com.example.demo.service.HotelService;
import com.example.demo.service.dto.HotelHabitacionDTO;
import com.example.demo.service.dto.HotelReservaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public Hotel getHotelbyNombre(String nombre) {
        Hotel hotel = null;
        Optional<Hotel> ohotel = hotelRepository.findById(nombre);
        if(ohotel.isPresent()){
            hotel = ohotel.get();
            return hotel;
        }
        return hotel;
    }

    @Override
    @Transactional
    public List<Hotel> getHoteles() {
        return StreamSupport.stream(hotelRepository.findAll().spliterator(), false).collect(Collectors.toUnmodifiableList());
    }

    @Override
    @Transactional
    public Hotel updateAmpliarHotelHabitacionesTotalesbyNombre(String nombre, Long nuevasHabitacionesTotales){
        Hotel hotel = null;
        Optional<Hotel> ohotel = hotelRepository.findById(nombre);
        if(ohotel.isPresent()){
            hotel = ohotel.get();
            Long habitacionesTotales = hotel.getHabitacionesTotales();
            if(nombre.equals("Gran-Melia-Palacio-de-los-Duques") || nombre.equals("ME-London")){
                hotelRepository.updateHotelCapacidadByNombre(((habitacionesTotales + nuevasHabitacionesTotales)*6), nombre);
            }else if(nombre.equals("TRYP-New-York-Times-Square")){
                hotelRepository.updateHotelCapacidadByNombre(((habitacionesTotales + nuevasHabitacionesTotales)*4), nombre);
            }else{
                hotelRepository.updateHotelCapacidadByNombre(((habitacionesTotales + nuevasHabitacionesTotales)*5), nombre);
            }
            hotelRepository.updateHotelHabitacionesTotalesByNombre(habitacionesTotales + nuevasHabitacionesTotales, nombre);
            return hotel;
        }
        return hotel;
    }

    @Override
    @Transactional
    public Hotel updateReducirHotelHabitacionesTotalesbyNombre(String nombre, Long nuevasHabitacionesTotales){
        Hotel hotel = null;
        Optional<Hotel> ohotel = hotelRepository.findById(nombre);
        if(ohotel.isPresent()){
            hotel = ohotel.get();
            Long habitacionesTotales = hotel.getHabitacionesTotales();
            if(nombre.equals("Gran-Melia-Palacio-de-los-Duques") || nombre.equals("ME-London")){
                hotelRepository.updateHotelCapacidadByNombre(((habitacionesTotales - nuevasHabitacionesTotales)*6), nombre);
            }else if(nombre.equals("TRYP-New-York-Times-Square")){
                hotelRepository.updateHotelCapacidadByNombre(((habitacionesTotales - nuevasHabitacionesTotales)*4), nombre);
            }else{
                hotelRepository.updateHotelCapacidadByNombre(((habitacionesTotales - nuevasHabitacionesTotales)*5), nombre);
            }
            hotelRepository.updateHotelHabitacionesTotalesByNombre(habitacionesTotales - nuevasHabitacionesTotales, nombre);
            return hotel;
        }
        return hotel;
    }

    @Override
    @Transactional
    public Hotel updateHotelEstadobyNombre(String nombre, Boolean estado) {
        Hotel hotel = null;
        Optional<Hotel> ohotel = hotelRepository.findById(nombre);
        if(ohotel.isPresent()){
            hotel = ohotel.get();
            hotelRepository.updateHotelEstadoByNombre(estado, nombre);
            return hotel;
        }
        return hotel;
    }

    // INNER-JOIN
    @Override
    public List<HotelReservaDTO> getHotelesConReservas(){

        String query = """ 
        SELECT HOTEL.NOMBRE, HOTEL.DESTINO, HOTEL.CAPACIDAD, HOTEL.OCUPACION, HOTEL.ESTADO, RESERVA.ID, RESERVA.NIF, RESERVA.HUESPEDES, RESERVA.HABITACIONES, RESERVA.FECHAENTRADA, RESERVA.FECHASALIDA
        FROM RESERVA
        INNER JOIN HOTEL ON HOTEL.NOMBRE=RESERVA.HOTEL;
        """;

        List<HotelReservaDTO> hotelesLista = jdbcTemplate.query(
                query,
                (rs, rowNum) ->
                        new HotelReservaDTO(
                                rs.getString("NOMBRE"),
                                rs.getString("DESTINO"),
                                rs.getLong("CAPACIDAD"),
                                rs.getLong("OCUPACION"),
                                rs.getBoolean("ESTADO"),
                                rs.getLong("ID"),
                                rs.getString("NIF"),
                                rs.getLong("HUESPEDES"),
                                rs.getLong("HABITACIONES"),
                                (rs.getTimestamp("FECHAENTRADA")!=null) ? rs.getTimestamp("FECHAENTRADA").toLocalDateTime() : null,
                                (rs.getTimestamp("FECHASALIDA")!=null) ? rs.getTimestamp("FECHASALIDA").toLocalDateTime() : null
                        )
        );
        return hotelesLista;
    }

    // INNER-JOIN
    @Override
    public List<HotelHabitacionDTO> getHotelesConHabitaciones(){

        String query = """ 
        SELECT HOTEL.NOMBRE, HOTEL.DESTINO, HOTEL.CAPACIDAD, HOTEL.OCUPACION, HOTEL.ESTADO, HABITACION.TIPO, HABITACION.NUMERO, HABITACION.PLANTA, HABITACION.CAPACIDAD, HABITACION.ESTADO 
        FROM HABITACION
        INNER JOIN HOTEL ON HOTEL.NOMBRE=HABITACION.HOTEL;
        """;

        List<HotelHabitacionDTO> hotelesLista = jdbcTemplate.query(
                query,
                (rs, rowNum) ->
                        new HotelHabitacionDTO(
                                rs.getString("HOTEL.NOMBRE"),
                                rs.getString("HOTEL.DESTINO"),
                                rs.getLong("HOTEL.CAPACIDAD"),
                                rs.getLong("HOTEL.OCUPACION"),
                                rs.getBoolean("HOTEL.ESTADO"),
                                rs.getString("HABITACION.TIPO"),
                                rs.getLong("HABITACION.NUMERO"),
                                rs.getLong("HABITACION.PLANTA"),
                                rs.getLong("HABITACION.CAPACIDAD"),
                                rs.getBoolean("HABITACION.ESTADO")
                        )
        );
        return hotelesLista;
    }
}