package com.example.demo.controller;

import com.example.demo.model.Reserva;
import com.example.demo.service.ReservaService;
import com.example.demo.service.ReservaServiceResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class ReservaController {

    @Autowired
    private ReservaService reservaServicio;

    @Transactional
    @GetMapping("/reservas/id/{id}")
    public ResponseEntity<Reserva> getReservaId(@PathVariable("id") String idStr){
        Long id = Long.parseLong(idStr);
        Reserva reserva = reservaServicio.getReservabyId(id);
        if(reserva != null){
            return ResponseEntity.ok().body(reserva);
        }
        return ResponseEntity.notFound().build();
    }

    @Transactional
    @GetMapping("/reservas/nif/{nif}")
    public ResponseEntity<List<Reserva>> getReservasNif(@PathVariable("nif") String nif){
        List<Reserva> reservas = reservaServicio.getReservasbyNif(nif);
        return ResponseEntity.ok().body(reservas);
    }

    @Transactional
    @GetMapping("/reservas/destino/{destino}")
    public ResponseEntity<List<Reserva>> getReservasDestino(@PathVariable("destino") String destino){
        List<Reserva> reservas = reservaServicio.getReservasbyDestino(destino);
        return ResponseEntity.ok().body(reservas);
    }

    @Transactional
    @GetMapping("/reservas/hotel/{hotel}")
    public ResponseEntity<List<Reserva>> getReservasHotel(@PathVariable("hotel") String hotel){
        List<Reserva> reservas = reservaServicio.getReservasbyHotel(hotel);
        return ResponseEntity.ok().body(reservas);
    }

    @Transactional
    @GetMapping("/reservas")
    public ResponseEntity<List<Reserva>> getAllReservas() {
        List<Reserva> reservas = reservaServicio.getReservas();
        return ResponseEntity.ok().body(reservas);
    }

    @Transactional
    @GetMapping("/reservas/update/{id}/{hotel}/{destino}/{tipo}/{huespedes}/{habitaciones}/{fechaEntrada}/{fechaSalida}")
    public ResponseEntity<Reserva> updateReservaId(@PathVariable("id") String idStr,@PathVariable("hotel") String hotel,@PathVariable("destino") String destino, @PathVariable("tipo") String tipo, @PathVariable("huespedes") String huespedesStr,@PathVariable("habitaciones") String habitacionesStr,@PathVariable("fechaEntrada") String fechaEntradaStr,@PathVariable("fechaSalida") String fechaSalidaStr){
        Long id = Long.parseLong(idStr);
        Long huespedes = Long.parseLong(huespedesStr);
        Long habitaciones = Long.parseLong(habitacionesStr);
        LocalDate fechaEntrada = LocalDate.parse(fechaEntradaStr);
        LocalDate fechaSalida = LocalDate.parse(fechaSalidaStr);
        Reserva reserva = reservaServicio.updateReservabyId(id, hotel, destino, tipo, huespedes, habitaciones, fechaEntrada, fechaSalida);
        if(reserva != null){
            return ResponseEntity.ok().body(reserva);
        }
        return ResponseEntity.notFound().build();
    }

    @Transactional
    @PostMapping("/reservas/check-in")
    public ResponseEntity<ReservaResponse> checkInReserva(@RequestBody Reserva reserva, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ReservaResponse reservaResponse = new ReservaResponse("KO");
            return new ResponseEntity<ReservaResponse>(reservaResponse, HttpStatus.BAD_REQUEST);
        }
        ReservaServiceResult result = reservaServicio.checkInReserva(reserva);
        if (result.isFlag()) {
            ReservaResponse reservaResponse = new ReservaResponse("OK", result.getAccessToken());
            return new ResponseEntity<ReservaResponse>(reservaResponse, HttpStatus.OK);
        }else{
            ReservaResponse reservaResponse = new ReservaResponse("La reserva se ha localizado correctamente, pero los parámetros no coinciden con los introducidos. Por favor, revise que todos sean correctos.");
            return new ResponseEntity<ReservaResponse>(reservaResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    @PostMapping("/reservas/insert")
    public ResponseEntity<ReservaResponse> insertCompareReserva(@RequestBody Reserva reserva, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ReservaResponse reservaResponse = new ReservaResponse("KO");
            return new ResponseEntity<ReservaResponse>(reservaResponse, HttpStatus.BAD_REQUEST);
        }
        ReservaServiceResult result = reservaServicio.registrarReserva(reserva);
        if (result.isFlag()) {
            ReservaResponse reservaResponse = new ReservaResponse("OK", result.getAccessToken());
            return new ResponseEntity<ReservaResponse>(reservaResponse, HttpStatus.OK);
        }else{
            ReservaResponse reservaResponse = new ReservaResponse("Una reserva en Meliá Hotels International con ese identificador ya existe.");
            return new ResponseEntity<ReservaResponse>(reservaResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    @PostMapping(path="/reservas/registro", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> registroReserva(@Valid @RequestBody ReservaCredential reservaParam, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>("{\"result\" : \"KO\"}", HttpStatus.BAD_REQUEST);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if ((!reservaParam.nif().equals("")) && (!reservaParam.checkIn().equals("")) && (!reservaParam.checkOut().equals("")) && ((sdf.parse(reservaParam.checkOut())).after((sdf.parse(reservaParam.checkIn())))) && (!reservaParam.huespedes().equals("")) && (!reservaParam.habitaciones().equals(""))) {
                return new ResponseEntity<>("{\"result\" : \"OK\"}", HttpStatus.OK);
            }
        } catch(ParseException e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("{\"result\" : \"KO\"}", HttpStatus.UNAUTHORIZED);
    }

    @Transactional
    @GetMapping("/reservas/delete/{id}")
    public ResponseEntity<String> deleteReservaId(@PathVariable("id") String idStr){
        Long id = Long.parseLong(idStr);
        String resultado = reservaServicio.deleteReservabyId(id);
        return ResponseEntity.ok().body(resultado);
    }

}