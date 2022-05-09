package com.example.demo.controller;

import javax.validation.constraints.Pattern;

record BookingCredential(

    @Pattern(message="máximo 9 caracteres" , regexp="^[a-zA-Z_]*$")
    String destino,

    @Pattern(message="máximo 10 caracteres" , regexp="^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$")
    String checkIn,

    @Pattern(message="máximo 10 caracteres" , regexp="^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$")
    String checkOut,

    @Pattern(message="máximo 1 cáracter" , regexp="^[0-9]{1}$")
    String huespedes,

    @Pattern(message="máximo 1 cáracter" , regexp="^[0-9]{1}$")
    String habitaciones) {}

