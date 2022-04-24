package com.example.demo.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioReservaDTO {
	private String nif;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String correo;
	private Long id;
	private String hotel;
	private String destino;
	private Long huespedes;
	private Long habitaciones;
	private LocalDateTime fechaEntrada;
	private LocalDateTime fechaSalida;
}
