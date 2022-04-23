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
@Table("CLIENTE")
public class Cliente {
    private @Column("NIF") @Id String nif;
    private @Column("NOMBRE") String nombre;
    private @Column("APELLIDO1") String apellido1;
    private @Column("APELLIDO2") String apellido2;
    private @Column("CORREO") String correo;
    private @Column("CONTRASENA") String contrasena;
    private @Column("CUMPLEANOS") LocalDate cumpleanos;
}