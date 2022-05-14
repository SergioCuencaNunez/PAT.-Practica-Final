package com.example.demo.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginCredentialTest {

    @Test
    public void LoginCredential_con_formato_adecuado(){

        LoginCredential LoginCredential = new LoginCredential("Javier","Barneda","Castillejo","45132222N","08-02-2000","javier_barneda@gmail.com","JavierBarneda654","JavierBarneda654");

        boolean result = LoginCredential.validar();
        assertEquals(true,result);

    }

    @Test
    public void LoginCredential_con_formato_inadecuado(){

        //Formatos Inválidos
        LoginCredential LoginCredential_invalido1 = new LoginCredential("Javier","Barneda","Castillejo","00000000T","08-02-2000","javier_barneda@gmail.com","JavierBarneda654","JavierBarneda654");
        LoginCredential LoginCredential_invalido2 = new LoginCredential("Javier","Barneda","Castillejo","00000001R","08-02-2000","javier_barneda@gmail.com","JavierBarneda654","JavierBarneda654");
        LoginCredential LoginCredential_invalido3 = new LoginCredential("Javier","Barneda","Castillejo","99999999R","08-02-2000","javier_barneda@gmail.com","JavierBarneda654","JavierBarneda654");
        assertEquals(false, LoginCredential_invalido1.validar());
        assertEquals(false, LoginCredential_invalido2.validar());
        assertEquals(false, LoginCredential_invalido3.validar());

    }
    
}
