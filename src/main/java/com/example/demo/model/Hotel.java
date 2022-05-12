package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table("HOTEL")
public class Hotel {
    private @Column("NOMBRE") @Id String nombre;
    private @Column("DESTINO") String destino;
    private @Column("HABITACIONES_TOTALES") Long habitacionesTotales;
    private @Column("HABITACIONES_OCUPADAS") Long habitacionesOcupadas;
    private @Column("CAPACIDAD") Long capacidad;
    private @Column("OCUPACION") Long ocupacion;
    private @Column("ESTADO") Boolean estado;
}