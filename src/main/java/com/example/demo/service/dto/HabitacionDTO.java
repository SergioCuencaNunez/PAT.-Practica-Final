package com.example.demo.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HabitacionDTO {
	private String tipo;
	private Long numero;
	private Long planta;
	private String hotel;
	private Long capacidad;
	private Boolean estado;
}
