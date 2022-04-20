package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table("HABITACION")
public class Habitacion {
    private @Column("TIPO") @Id String tipo;
    private @Column("NUMERO") Long numero;
    private @Column("PLANTA") Long planta;
    private @Column("HOTEL") String hotel;
    private @Column("CAPACIDAD") Long capacidad;
    private @Column("ESTADO") Boolean estado;
}
