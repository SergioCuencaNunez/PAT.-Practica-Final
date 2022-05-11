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
    public List<Hotel> getHotelesbyDestino(String destino) {
        return hotelRepository.getHotelesByDestino(destino);
    }

    @Override
    @Transactional
    public List<Hotel> getHotelesbyEstado(Boolean estado) {
        return hotelRepository.getHotelesByEstado(estado);
    }

    @Override
    @Transactional
    public List<Hotel> getHoteles() {
        return StreamSupport.stream(hotelRepository.findAll().spliterator(), false).collect(Collectors.toUnmodifiableList());
    }

    @Override
    @Transactional
    public Hotel updateHotelCapacidadbyNombre(String nombre, Long capacidad) {
        Hotel hotel = null;
        Optional<Hotel> ohotel = hotelRepository.findById(nombre);
        if(ohotel.isPresent()){
            hotel = ohotel.get();
            hotel.setCapacidad(capacidad);
            hotelRepository.updateHotelCapacidadByNombre(hotel.getCapacidad(), hotel.getNombre());
            return hotel;
        }
        return hotel;
    }

    @Override
    @Transactional
    public Hotel updateHotelOcupacionbyNombre(String nombre, Long ocupacion) {
        Hotel hotel = null;
        Optional<Hotel> ohotel = hotelRepository.findById(nombre);
        if(ohotel.isPresent()){
            hotel = ohotel.get();
            hotel.setOcupacion(ocupacion);
            hotelRepository.updateHotelOcupacionByNombre(hotel.getOcupacion(), hotel.getNombre());
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
            hotel.setEstado(estado);
            hotelRepository.updateHotelEstadoByNombre(hotel.getEstado(), hotel.getNombre());
            return hotel;
        }
        return hotel;
    }

    @Override
    @Transactional
    public String insertAndCompareHotel(String nombre, String destino, Long habitacionesTotales, Long habitacionesOcupadas, Long capacidad, Long ocupacion, Boolean estado){
        Optional<Hotel> ohotel = hotelRepository.findById(nombre);
        if(ohotel.isPresent()) {
            return "El hotel " + nombre + " ya está registrado en Meliá Hotels International";
        }else{
            Hotel hotel = new Hotel();
            hotel.setNombre(nombre);
            hotel.setDestino(destino);
            hotel.setHabitacionesTotales(habitacionesTotales);
            hotel.setHabitacionesOcupadas(habitacionesOcupadas);
            hotel.setCapacidad(capacidad);
            hotel.setOcupacion(ocupacion);
            hotel.setEstado(estado);
            hotelRepository.insertHotel(hotel.getNombre(), hotel.getDestino(), hotel.getHabitacionesTotales(), hotel.getHabitacionesOcupadas(), hotel.getCapacidad(), hotel.getOcupacion(),hotel.getEstado());
            return "El hotel " + nombre + " se ha registrado correctamente en Meliá Hotels International";
        }
    }

    @Override
    @Transactional
    public String deleteHotelbyNombre(String nombre) {
        Optional<Hotel> ohotel = hotelRepository.findById(nombre);
        if(ohotel.isPresent()) {
            hotelRepository.deleteById(nombre);
            return "El hotel " + nombre + " se ha eliminado correctamente de los activos de Meliá Hotels International";
        }else{
            return "No hay ningún hotel llamado " + nombre + " en los activos de Meliá Hotels International";
        }
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