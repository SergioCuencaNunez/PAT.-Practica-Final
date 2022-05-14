package com.example.demo.controller;

import javax.validation.constraints.Pattern;
import java.util.Arrays;

public record LoginCredential(

    @Pattern(message="máximo 15 caracteres" , regexp="^[a-zA-Z]{0,15}$")
    String nombre,

    @Pattern(message="máximo 20 caracteres" , regexp="^[a-zA-Z]{0,20}$")
    String apellido1,

    @Pattern(message="máximo 20 caracteres" , regexp="^[a-zA-Z]{0,20}$")
    String apellido2,

    @Pattern(message="máximo 9 caracteres" , regexp="^[0-9]{8}+[TRWAGMYFPDXBNJZSQVHLCKE]$")
    String nif,

    @Pattern(message="máximo 10 caracteres" , regexp="^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$")
    String cumpleanos,

    @Pattern(message="máximo 50 caracteres" , regexp="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")
    String correo,

    @Pattern(message="mínimo 15 caracteres y máximo 50 caracteres" , regexp="^[a-zA-Z-.0-9]{15,50}$")
    String contrasena,

    @Pattern(message="mínimo 15 caracteres y máximo 50 caracteres" , regexp="^[a-zA-Z-.0-9]{15,50}$")
    String contrasena2){

    private static final String DIGITO_CONTROL = "TRWAGMYFPDXBNJZSQVHLCKE";
    private static final String[] INVALIDOS = new String[]{"00000000T", "00000001R", "99999999R"};

    public boolean validar(){

        return Arrays.binarySearch(INVALIDOS, nif) < 0
                && nif.charAt(8) == DIGITO_CONTROL.charAt(Integer.parseInt(nif.substring(0, 8)) % 23);

    }
}