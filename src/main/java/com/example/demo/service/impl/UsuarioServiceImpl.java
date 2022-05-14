package com.example.demo.service.impl;

import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.service.UsuarioService;
import com.example.demo.service.LoginServiceResult;
import com.example.demo.service.dto.UsuarioReservaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public Usuario getUsuariobyNif(String nif) {
        Usuario usuario = null;
        Optional<Usuario> ousuario = usuarioRepository.findById(nif);
        if(ousuario.isPresent()){
            usuario = ousuario.get();
            return usuario;
        }
        return usuario;
    }

    @Override
    @Transactional
    public Usuario getUsuariobyCorreo(String correo) {
        return usuarioRepository.getUsuarioByCorreo(correo);
    }

    @Override
    @Transactional
    public List<Usuario> getUsuariosbyRol(String rol) {
        return usuarioRepository.getUsuariosByRol(rol);
    }

    @Override
    @Transactional
    public List<Usuario> getUsuarios() {
        return StreamSupport.stream(usuarioRepository.findAll().spliterator(), false).collect(Collectors.toUnmodifiableList());
    }

    @Override
    public LoginServiceResult updateUsuario(Usuario usuario) {

        String nif = usuario.getNif();
        String nombre = usuario.getNombre();
        String apellido1 = usuario.getApellido1();
        String apellido2 = usuario.getApellido2();
        String correo = usuario.getCorreo();
        String contrasena = usuario.getContrasena();
        LocalDate cumpleanos = usuario.getCumpleanos();
        String correoAntiguo = usuario.getRol();

        List<String> nifs = usuarioRepository.getUsuarioNifs();
        List<String> correos = usuarioRepository.getUsuarioCorreos();

        if(nifs.contains(nif)){
            String nifValido = usuarioRepository.getUsuarioNifByCorreo(correoAntiguo);
            if(nif.equals(nifValido)){
                String correoValido = usuarioRepository.getUsuarioCorreoByNif(nif);
                if(correos.contains(correo)){
                    if(correoValido.equals(correo)){
                        usuarioRepository.updateUsuarioByNif(nombre, apellido1, apellido2, correo, contrasena, cumpleanos, nif);
                        return new LoginServiceResult(true);
                    }else {
                        return new LoginServiceResult(false, "Correo ya registrado");
                    }
                }else {
                    usuarioRepository.updateUsuarioByNif(nombre, apellido1, apellido2, correo, contrasena, cumpleanos, nif);
                    return new LoginServiceResult(true);
                }
            }else{
                return new LoginServiceResult(false, "Este no es el NIF adjunto");
            }
        }else{
            return new LoginServiceResult(false, "El NIF no se puede cambiar");
        }
    }

    @Override
    @Transactional
    public void insertUsuario(String nif, String nombre, String apellido1, String apellido2, String correo, String contrasena, LocalDate cumpleanos, String rol){
        Usuario usuario = new Usuario();
        usuario.setNif(nif);
        usuario.setNombre(nombre);
        usuario.setApellido1(apellido1);
        usuario.setApellido2(apellido2);
        usuario.setCorreo(correo);
        usuario.setContrasena(contrasena);
        usuario.setCumpleanos(cumpleanos);
        usuario.setRol(rol);
        usuarioRepository.insertarUsuario(usuario.getNif(), usuario.getNombre(), usuario.getApellido1(), usuario.getApellido2(),usuario.getCorreo(),usuario.getContrasena(),usuario.getCumpleanos(),usuario.getRol());
    }

    @Override
    @Transactional
    public String deleteUsuariobyNif(String nif) {
        Optional<Usuario> ousuario = usuarioRepository.findById(nif);
        if(ousuario.isPresent()) {
            usuarioRepository.deleteById(nif);
            return "El usuario con NIF " + nif + " se ha eliminado correctamente";
        }else{
            return "No hay ningún usuario con NIF " + nif;
        }
    }

    // INNER-JOIN
    @Override
    public List<UsuarioReservaDTO> getUsuariosConReservas(){

        String query = """ 
        SELECT USUARIO.NIF, USUARIO.NOMBRE, USUARIO.APELLIDO1, USUARIO.APELLIDO2, USUARIO.CORREO, RESERVA.ID, RESERVA.HOTEL, RESERVA.DESTINO, RESERVA.HUESPEDES, RESERVA.HABITACIONES, RESERVA.FECHAENTRADA, RESERVA.FECHASALIDA
        FROM RESERVA
        INNER JOIN USUARIO ON USUARIO.NIF=RESERVA.NIF;
        """;

        List<UsuarioReservaDTO> usuariosLista = jdbcTemplate.query(
                query,
                (rs, rowNum) ->
                        new UsuarioReservaDTO(
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
        return usuariosLista;
    }
}