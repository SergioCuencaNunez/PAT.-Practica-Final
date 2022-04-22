package com.example.demo.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelDTO {
	private String nombre;
	private String destino;
	private Long capacidad;
	private Long ocupacion;
	private Boolean estado;
}
