package com.example.demo.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelHabitacionDTO {
	private String nombre;
	private String destino;
	private Long capacidadHotel;
	private Long ocupacion;
	private Boolean estadoHotel;
	private String tipo;
	private Long numero;
	private Long planta;
	private Long capacidadHabitacion;
	private Boolean estadoHabitacion;
}
