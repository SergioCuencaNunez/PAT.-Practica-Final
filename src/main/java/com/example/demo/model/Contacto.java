package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table("CONTACTO")
public class Contacto {
    private @Column("NUMERO") @Id Long numero;
    private @Column("CORREO") String correo;
    private @Column("NOMBRE") String nombre;
    private @Column("MENSAJE") String mensaje;
}