package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("TABLA_REGISTRO")
public class TablaRegistro {
    private @Column("NIF") @Id String nif;
    private @Column("NOMBRE") String nombre;
    private @Column("APELLIDO1") String apellido1;
    private @Column("APELLIDO2") String apellido2;
    private @Column("CORREO") String correo;
    private @Column("CUMPLEANOS") LocalDate cumpleanos;

}