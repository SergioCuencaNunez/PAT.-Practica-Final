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
            return new ResponseEntity<>(reserva, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @Transactional
    @GetMapping("/reservas/nif/{nif}")
    public ResponseEntity<List<Reserva>> getReservasNif(@PathVariable("nif") String nif){
        List<Reserva> reservas = reservaServicio.getReservasbyNif(nif);
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
    @PutMapping("/reservas/update/tipo/{id}/{tipo}")
    public ResponseEntity<String> updateReservaTipoId(@PathVariable("id") String idStr, @PathVariable("tipo") String tipo){
        Long id = Long.parseLong(idStr);
        Reserva reserva = reservaServicio.updateReservaTipobyId(id, tipo);
        if(reserva != null){
            return new ResponseEntity<>("", HttpStatus.OK);
        }
        return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
    }

    @Transactional
    @PutMapping("/reservas/update/huespedes/{id}/{huespedes}")
    public ResponseEntity<ReservaResponse> updateReservaHuespedesId(@PathVariable("id") String idStr, @PathVariable("huespedes") String huespedesStr){
        Long id = Long.parseLong(idStr);
        Long huespedes = Long.parseLong(huespedesStr);
        ReservaServiceResult result = reservaServicio.updateReservaHuespedesbyId(id, huespedes);
        if (result.isFlag()) {
            ReservaResponse reservaResponse = new ReservaResponse("OK", result.getAccessToken());
            return new ResponseEntity<ReservaResponse>(reservaResponse, HttpStatus.OK);
        }else if(result.getAccessToken().equals("No ha modificado el número de huéspedes")){
            ReservaResponse reservaResponse = new ReservaResponse("No ha realizado ninguna modificación sobre el número de huéspedes en la reserva #" + id + ".");
            return new ResponseEntity<ReservaResponse>(reservaResponse, HttpStatus.BAD_REQUEST);
        }else{
            ReservaResponse reservaResponse = new ReservaResponse("No se ha podido modificar el número de huéspedes de la reserva #" + id + ".");
            return new ResponseEntity<ReservaResponse>(reservaResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    @PutMapping("/reservas/update/habitaciones/{id}/{habitaciones}")
    public ResponseEntity<ReservaResponse> updateReservaHabitacionId(@PathVariable("id") String idStr, @PathVariable("habitaciones") String habitacionesStr){
        Long id = Long.parseLong(idStr);
        Long habitaciones = Long.parseLong(habitacionesStr);
        ReservaServiceResult result = reservaServicio.updateReservaHabitacionbyId(id, habitaciones);
        if (result.isFlag()) {
            ReservaResponse reservaResponse = new ReservaResponse("OK", result.getAccessToken());
            return new ResponseEntity<ReservaResponse>(reservaResponse, HttpStatus.OK);
        }else if(result.getAccessToken().equals("No ha modificado el número de habitaciones")){
            ReservaResponse reservaResponse = new ReservaResponse("No ha realizado ninguna modificación sobre el número de habitaciones en la reserva #" + id + ".");
            return new ResponseEntity<ReservaResponse>(reservaResponse, HttpStatus.BAD_REQUEST);
        }else{
            ReservaResponse reservaResponse = new ReservaResponse("No se ha podido modificar el número de habitaciones de la reserva #" + id + ".");
            return new ResponseEntity<ReservaResponse>(reservaResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    @PutMapping("/reservas/update/fechaEntrada/{id}/{fechaEntrada}")
    public ResponseEntity<String> updateReservaFechaEntradaId(@PathVariable("id") String idStr, @PathVariable("fechaEntrada") String fechaEntradaStr){
        Long id = Long.parseLong(idStr);
        LocalDate fechaEntrada = LocalDate.parse(fechaEntradaStr);
        Reserva reserva = reservaServicio.updateReservaFechaEntradabyId(id, fechaEntrada);
        if(reserva != null){
            return new ResponseEntity<>("", HttpStatus.OK);
        }
        return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
    }

    @Transactional
    @PutMapping("/reservas/update/fechaSalida/{id}/{fechaSalida}")
    public ResponseEntity<String> updateReservaFechaSalidaId(@PathVariable("id") String idStr, @PathVariable("fechaSalida") String fechaSalidaStr){
        Long id = Long.parseLong(idStr);
        LocalDate fechaSalida = LocalDate.parse(fechaSalidaStr);
        Reserva reserva = reservaServicio.updateReservaFechaSalidabyId(id, fechaSalida);
        if(reserva != null){
            return new ResponseEntity<>("", HttpStatus.OK);
        }
        return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
    }

    @Transactional
    @GetMapping("/reservas/booking/{hotel}/{habitaciones}")
    public ResponseEntity<ReservaResponse> comprobarLaDisponibilidad(@PathVariable("hotel") String hotel, @PathVariable("habitaciones") String habitacionesStr){
        Long habitaciones = Long.parseLong(habitacionesStr);
        ReservaServiceResult result = reservaServicio.comprobarDisponibilidad(hotel, habitaciones);
        if (result.isFlag()) {
            ReservaResponse reservaResponse = new ReservaResponse("OK", result.getAccessToken());
            return new ResponseEntity<ReservaResponse>(reservaResponse, HttpStatus.OK);
        }else{
            ReservaResponse reservaResponse = new ReservaResponse("No hay más habitaciones disponibles en este destino. Disculpe las molestias.");
            return new ResponseEntity<ReservaResponse>(reservaResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    @PostMapping("/reservas/check-in")
    public ResponseEntity<ReservaResponse> checkInReserva(@RequestBody Reserva reserva, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
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
        if(bindingResult.hasErrors()){
            ReservaResponse reservaResponse = new ReservaResponse("KO");
            return new ResponseEntity<ReservaResponse>(reservaResponse, HttpStatus.BAD_REQUEST);
        }
        ReservaServiceResult result = reservaServicio.registrarReserva(reserva);
        if (result.isFlag()) {
            ReservaResponse reservaResponse = new ReservaResponse("OK", result.getAccessToken());
            return new ResponseEntity<ReservaResponse>(reservaResponse, HttpStatus.OK);
        }else if(result.getAccessToken().equals("No hay habitaciones disponibles")){
            ReservaResponse reservaResponse = new ReservaResponse("No hay más habitaciones disponibles en este hotel para el número que solicita.\nPruebe a reducir el número de habitaciones. Disculpe las molestias.");
            return new ResponseEntity<ReservaResponse>(reservaResponse, HttpStatus.BAD_REQUEST);
        }else{
            ReservaResponse reservaResponse = new ReservaResponse("Ya existe una reserva en Meliá Hotels International con ese identificador.");
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
    @DeleteMapping("/reservas/delete/{id}")
    public ResponseEntity<String> deleteReservaId(@PathVariable("id") String idStr){
        Long id = Long.parseLong(idStr);
        String resultado = reservaServicio.deleteReservabyId(id);
        if(resultado.equals("La reserva se ha borrado correctamente")){
            return new ResponseEntity<>("", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    @DeleteMapping("/reservas/delete/hotel/{hotel}")
    public ResponseEntity<ReservaResponse> deleteReservaHotelId(@RequestBody Reserva reserva, BindingResult bindingResult, @PathVariable("hotel") String hotel){
        if(bindingResult.hasErrors()){
            ReservaResponse reservaResponse = new ReservaResponse("KO");
            return new ResponseEntity<ReservaResponse>(reservaResponse, HttpStatus.BAD_REQUEST);
        }
        ReservaServiceResult result = reservaServicio.deleteReservaHotelbyId(reserva, hotel);
        if (result.isFlag()) {
            ReservaResponse reservaResponse = new ReservaResponse("OK", result.getAccessToken());
            return new ResponseEntity<ReservaResponse>(reservaResponse, HttpStatus.OK);
        }else if(result.getAccessToken().equals("La reserva no pertenece a este hotel")){
            ReservaResponse reservaResponse = new ReservaResponse("No se puede eliminar la reserva seleccionada puesto que no pertenece a este hotel.");
            return new ResponseEntity<ReservaResponse>(reservaResponse, HttpStatus.BAD_REQUEST);
        }else{
            ReservaResponse reservaResponse = new ReservaResponse("No existe ninguna reserva con ese identificador.");
            return new ResponseEntity<ReservaResponse>(reservaResponse, HttpStatus.BAD_REQUEST);
        }
    }
}