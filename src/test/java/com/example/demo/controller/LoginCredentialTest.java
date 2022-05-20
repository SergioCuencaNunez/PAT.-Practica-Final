package com.example.demo.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginCredentialTest {

    @Test
    public void LoginCredential_con_formato_adecuado(){

        LoginCredential LoginCredential = new LoginCredential("Javier","Barneda","Castillejo","45132222N","08-02-2000","javier_barneda@gmail.com","JavierBarneda654","JavierBarneda654");

        boolean result = LoginCredential.validar_DNI();
        assertEquals(true,result);

    }

    @Test
    public void LoginCredential_con_formato_inadecuado(){

        //Formatos Inválidos
        LoginCredential LoginCredential_invalido1 = new LoginCredential("Javier","Barneda","Castillejo","00000000T","08-02-2000","javier_barneda@gmail.com","JavierBarneda654","JavierBarneda654");
        LoginCredential LoginCredential_invalido2 = new LoginCredential("Javier","Barneda","Castillejo","00000001R","08-02-2000","javier_barneda@gmail.com","JavierBarneda654","JavierBarneda654");
        LoginCredential LoginCredential_invalido3 = new LoginCredential("Javier","Barneda","Castillejo","99999999R","08-02-2000","javier_barneda@gmail.com","JavierBarneda654","JavierBarneda654");
        assertEquals(false, LoginCredential_invalido1.validar_DNI());
        assertEquals(false, LoginCredential_invalido2.validar_DNI());
        assertEquals(false, LoginCredential_invalido3.validar_DNI());

    }

    @Test
    public void Contrasena_con_formato_adecuado(){

        LoginCredential LoginCredential = new LoginCredential("Javier","Barneda","Castillejo","45132222N","08-02-2000","javier_barneda@gmail.com","JavierBarneda654","JavierBarneda654");

        boolean result = LoginCredential.validar_Contrasena();
        assertEquals(true,result);

    }

    @Test
    public void Contrasena_con_formato_inadecuado(){

        //Formatos Inválidos
        LoginCredential LoginCredential_invalido1 = new LoginCredential("Javier","Barneda","Castillejo","80762074N","08-02-2000","javier_barneda@gmail.com","aaaaaaaaaaaaaaaaa","aaaaaaaaaaaaaaaaa");
        LoginCredential LoginCredential_invalido2 = new LoginCredential("Javier","Barneda","Castillejo","67756676X","08-02-2000","javier_barneda@gmail.com","AAAAAAAAAAkkllkll","AAAAAAAAAAkkllkll");
        LoginCredential LoginCredential_invalido3 = new LoginCredential("Javier","Barneda","Castillejo","41234189L","08-02-2000","javier_barneda@gmail.com","aaaaaaeerrtt6789867","aaaaaaeerrtt6789867");
        assertEquals(false, LoginCredential_invalido1.validar_Contrasena());
        assertEquals(false, LoginCredential_invalido2.validar_Contrasena());
        assertEquals(false, LoginCredential_invalido3.validar_Contrasena());

    }
    
}
