package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table("HOTEL")
public class Hotel {
    private @Column("NOMBRE") @Id String nombre;
    private @Column("DESTINO") String destino;
    private @Column("CAPACIDAD") Long capacidad;
    private @Column("OCUPACION") Long ocupacion;
    private @Column("ESTADO") Boolean estado;
}