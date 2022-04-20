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
@Table("RESERVA")
public class Reserva {
    private @Column("ID") @Id Long id;
    private @Column("NIF") String nif;
    private @Column("HOTEL") String hotel;
    private @Column("DESTINO") String destino;
    private @Column("HUESPEDES") Long huespedes;
    private @Column("HABITACIONES") Long habitaciones;
    private @Column("FECHAENTRADA") LocalDate fechaEntrada;
    private @Column("FECHASALIDA") LocalDate fechaSalida;
}
