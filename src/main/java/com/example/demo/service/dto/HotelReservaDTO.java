package com.example.demo.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelReservaDTO {
	private String nombre;
	private String destino;
	private Long capacidad;
	private Long ocupacion;
	private Boolean estado;
	private Long id;
	private String nif;
	private Long huespedes;
	private Long habitaciones;
	private LocalDateTime fechaEntrada;
	private LocalDateTime fechaSalida;
}
