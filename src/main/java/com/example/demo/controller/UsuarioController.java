package com.example.demo.controller;

import com.example.demo.model.Usuario;
import com.example.demo.service.LoginService;
import com.example.demo.service.LoginServiceResult;
import com.example.demo.service.UsuarioService;
import com.example.demo.service.dto.UsuarioReservaDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioServicio;

    @Autowired
    private LoginService loginServicio;

    @Transactional
    @GetMapping("/usuarios/nif/{nif}")
    public ResponseEntity<Usuario> getUsuarioNif(@PathVariable("nif") String nif) {
        Usuario usuario= usuarioServicio.getUsuariobyNif(nif);
        if(usuario != null){
            return ResponseEntity.ok().body(usuario);
        }
        return ResponseEntity.notFound().build();
    }

    @Transactional
    @GetMapping("/usuarios/correo/{correo}")
    public ResponseEntity<Usuario> getUsuarioCorreo(@PathVariable("correo") String correo){
        Usuario usuario = usuarioServicio.getUsuariobyCorreo(correo);
        return ResponseEntity.ok().body(usuario);
    }

    @Transactional
    @GetMapping("/usuarios/rol/{rol}")
    public ResponseEntity<List<Usuario>> getUsuariosRol(@PathVariable("rol") String rol){
        List<Usuario> usuarios = usuarioServicio.getUsuariosbyRol(rol);
        return ResponseEntity.ok().body(usuarios);
    }

    @Transactional
    @GetMapping("/usuarios")
    public ResponseEntity<List<Usuario>> getAllUsuarios() {
        List<Usuario> usuarios = usuarioServicio.getUsuarios();
        return ResponseEntity.ok().body(usuarios);
    }

    @Transactional
    @PutMapping("/usuarios/update/{correoAntiguo}")
    public ResponseEntity<LoginResponse> updateClienteNombreCompletoNif(@RequestBody Usuario usuario, @PathVariable String correoAntiguo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            LoginResponse loginResponse = new LoginResponse("KO");
            return new ResponseEntity<LoginResponse>(loginResponse, HttpStatus.BAD_REQUEST);
        }
        LoginServiceResult result = usuarioServicio.updateUsuario(usuario, correoAntiguo);
        if (result.isFlag()) {
            LoginResponse loginResponse = new LoginResponse("OK", result.getAccessToken());
            return new ResponseEntity<LoginResponse>(loginResponse, HttpStatus.OK);
        }else if(result.getAccessToken().equals("Correo ya registrado")) {
            LoginResponse loginResponse = new LoginResponse("El correo introducido pertenece a otro usuario ya registrado.");
            return new ResponseEntity<LoginResponse>(loginResponse, HttpStatus.BAD_REQUEST);
        }
        else if(result.getAccessToken().equals("Este no es el NIF adjunto")) {
            LoginResponse loginResponse = new LoginResponse("El NIF introducido pertenece a otro usuario ya registrado.");
            return new ResponseEntity<LoginResponse>(loginResponse, HttpStatus.BAD_REQUEST);
        }else{
            LoginResponse loginResponse = new LoginResponse("No es posible modificar el NIF introducido en el momento del registro.\nSi desea contactar con el servicio de atenci??n al cliente del programa Meli??Rewards, llame al tel??fono 912 76 47 40.");
            return new ResponseEntity<LoginResponse>(loginResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    @PostMapping("/usuarios/insert-cliente")
    public ResponseEntity<LoginResponse> insertCompareCliente(@RequestBody Usuario usuario, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            LoginResponse loginResponse = new LoginResponse("KO");
            return new ResponseEntity<LoginResponse>(loginResponse, HttpStatus.BAD_REQUEST);
        }
        LoginServiceResult result = loginServicio.registroUsuario(usuario);
        if (result.isFlag()) {
            LoginResponse loginResponse = new LoginResponse("OK", result.getAccessToken());
            return new ResponseEntity<LoginResponse>(loginResponse, HttpStatus.OK);
        }else if(result.getAccessToken().equals("NIF ya registrado")){
            LoginResponse loginResponse = new LoginResponse("Un usuario con ese NIF ya est?? registrado en Meli??Rewards.");
            return new ResponseEntity<LoginResponse>(loginResponse, HttpStatus.BAD_REQUEST);
        }else{
            LoginResponse loginResponse = new LoginResponse("Un usuario con ese correo electr??nico ya est?? registrado en Meli??Rewards.");
            return new ResponseEntity<LoginResponse>(loginResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    @PostMapping("/usuarios/insert-gerente")
    public ResponseEntity<LoginResponse> insertCompareGerente(@RequestBody Usuario usuario, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            LoginResponse loginResponse = new LoginResponse("KO");
            return new ResponseEntity<LoginResponse>(loginResponse, HttpStatus.BAD_REQUEST);
        }
        LoginServiceResult result = loginServicio.registroUsuario(usuario);
        if (result.isFlag()) {
            LoginResponse loginResponse = new LoginResponse("OK", result.getAccessToken());
            return new ResponseEntity<LoginResponse>(loginResponse, HttpStatus.OK);
        }else if(result.getAccessToken().equals("NIF ya registrado")){
            LoginResponse loginResponse = new LoginResponse("Un usuario con ese NIF ya est?? registrado en MyMeli??Benefits.");
            return new ResponseEntity<LoginResponse>(loginResponse, HttpStatus.BAD_REQUEST);
        }else{
            LoginResponse loginResponse = new LoginResponse("Un usuario con ese correo electr??nico ya est?? registrado en MyMeli??Benefits.");
            return new ResponseEntity<LoginResponse>(loginResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    @DeleteMapping("/usuarios/delete/{nif}")
    public ResponseEntity<String> deleteUsuarioNif(@PathVariable("nif") String nif) {
        String resultado = usuarioServicio.deleteUsuariobyNif(nif);
        if(resultado.equals("El usuario se ha eliminado correctamente")){
            return new ResponseEntity<>("", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
        }
    }

    // INNER-JOIN
    @Transactional
    @GetMapping("/usuarios/reservas")
    public ResponseEntity<List<UsuarioReservaDTO>> getUsuariosReservas() {
        var reservas = usuarioServicio.getUsuariosConReservas();
        return ResponseEntity.ok().body(reservas);
    }

    @Transactional
    @PostMapping("/usuarios/inicio-sesion")
    public ResponseEntity<LoginResponse> inicioSesionCliente(@RequestBody Usuario usuario, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            LoginResponse loginResponse = new LoginResponse("KO");
            return new ResponseEntity<LoginResponse>(loginResponse, HttpStatus.BAD_REQUEST);
        }
        LoginServiceResult result = loginServicio.inicioSesionDeUsuario(usuario);
        if (result.isFlag()) {
            LoginResponse loginResponse = new LoginResponse("OK", result.getAccessToken());
            return new ResponseEntity<LoginResponse>(loginResponse, HttpStatus.OK);
        }else if(result.getAccessToken().equals("Contrase??a Err??nea")){
            LoginResponse loginResponse = new LoginResponse("Contrase??a err??nea");
            return new ResponseEntity<LoginResponse>(loginResponse, HttpStatus.BAD_REQUEST);
        }else{
            LoginResponse loginResponse = new LoginResponse("Usuario no existe");
            return new ResponseEntity<LoginResponse>(loginResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    @PostMapping(path="/usuarios/registro-cliente", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> registroCliente(@Valid @RequestBody LoginCredential loginParam, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>("{\"result\" : \"KO\"}", HttpStatus.BAD_REQUEST);
        }
        if((!loginParam.nombre().equals("")) && (!loginParam.apellido1().equals("")) && (!loginParam.apellido2().equals("")) && (!loginParam.nif().equals("")) && (!loginParam.cumpleanos().equals("")) && (!loginParam.correo().equals("")) && (!loginParam.correo().contains("@melia")) && (loginParam.contrasena().equals(loginParam.contrasena2())) && loginParam.validar_DNI() && loginParam.validar_Contrasena()){
            return new ResponseEntity<>("{\"result\" : \"OK\"}", HttpStatus.OK);
        }
        return new ResponseEntity<>("{\"result\" : \"KO\"}", HttpStatus.UNAUTHORIZED);
    }

    @Transactional
    @PostMapping(path="/usuarios/registro-gerente", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> registroGerente(@Valid @RequestBody LoginCredential loginParam, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>("{\"result\" : \"KO\"}", HttpStatus.BAD_REQUEST);
        }
        if((!loginParam.nombre().equals("")) && (!loginParam.apellido1().equals("")) && (!loginParam.apellido2().equals("")) && (!loginParam.nif().equals("")) && (!loginParam.cumpleanos().equals("")) && (!loginParam.correo().equals("")) && (loginParam.correo().contains("@melia")) && (loginParam.contrasena().equals(loginParam.contrasena2())) && loginParam.validar_DNI() && loginParam.validar_Contrasena()){
            return new ResponseEntity<>("{\"result\" : \"OK\"}", HttpStatus.OK);
        }
        return new ResponseEntity<>("{\"result\" : \"KO\"}", HttpStatus.UNAUTHORIZED);
    }

}