package com.example.demo.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {
	private String nif;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String correo;
	private String contrasena;
	private LocalDate cumpleanos;
	private String rol;
}
