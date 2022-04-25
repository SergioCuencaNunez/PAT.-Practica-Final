package com.example.demo.controller;

import javax.validation.constraints.Pattern;
import java.time.LocalDate;

record LoginCredential(

    @Pattern(message="máximo 15 caracteres" , regexp="^[a-zA-z]{0,15}$")//poner máximo
    String nombre,

    @Pattern(message="máximo 20 caracteres" , regexp="^[a-zA-z]{0,20}$")
    String apellido1,

    @Pattern(message="máximo 20 caracteres" , regexp="^[a-zA-z]{0,20}$")
    String apellido2,

    @Pattern(message="máximo 8 caracteres" , regexp="^[0-9]{8}+[TRWAGMYFPDXBNJZSQVHLCKE]$")
    String nif,

    @Pattern(message="máximo 10 caracteres" , regexp="^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$")
    String cumpleanos,

    @Pattern(message="máximo 50 caracteres" , regexp="[a-z0-9]+@[a-z]+.[a-z]{2,3}")
    String correo,

    @Pattern(message="mínimo 15 caracteres y máximo 50 caracteres" , regexp="^[a-zA-Z-.0-9]{15,50}$")
    String contrasena,

    @Pattern(message="mínimo 15 caracteres y máximo 50 caracteres" , regexp="^[a-zA-Z-.0-9]{15,50}$")
    String contrasena2) {}

