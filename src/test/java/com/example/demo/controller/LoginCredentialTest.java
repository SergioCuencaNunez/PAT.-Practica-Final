package com.example.demo.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginCredentialTest {

    @Test
    public void LoginCredential_con_formato_adecuado(){

        LoginCredential LoginCredential = new LoginCredential("Elena","Conderana","Medem","45132222N","26-07-2003","ecm@gmail.com","UniversidadPontificia","UniversidadPontificia");

        boolean result = LoginCredential.validar();
        assertEquals(true,result);

    }

    @Test
    public void LoginCredential_con_formato_inadecuado(){

        //Formatos Inválidos
        LoginCredential LoginCredential_invalido1 = new LoginCredential("Elena","Conderana","Medem","00000000T","26-07-2003","ecm@gmail.com","UniversidadPontificia","UniversidadPontificia");
        LoginCredential LoginCredential_invalido2 = new LoginCredential("Elena","Conderana","Medem","00000001R","26-07-2003","ecm@gmail.com","UniversidadPontificia","UniversidadPontificia");
        LoginCredential LoginCredential_invalido3 = new LoginCredential("Elena","Conderana","Medem","99999999R","26-07-2003","ecm@gmail.com","UniversidadPontificia","UniversidadPontificia");
        assertEquals(false, LoginCredential_invalido1.validar());
        assertEquals(false, LoginCredential_invalido2.validar());
        assertEquals(false, LoginCredential_invalido3.validar());

    }
    
}
