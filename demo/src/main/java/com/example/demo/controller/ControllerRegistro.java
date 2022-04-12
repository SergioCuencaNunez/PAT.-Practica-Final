package com.example.demo.controller;

import com.example.demo.model.TablaRegistro;
import com.example.demo.service.ServiceRegistro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;

@RestController
@RequestMapping("/api/v1")
public class ControllerRegistro {

    @Autowired
    private ServiceRegistro servicioRegistro;

    @GetMapping("/registro")
    public ResponseEntity<Iterable<TablaRegistro>> getElements() {

        var elements = servicioRegistro.getElements();

        return ResponseEntity.ok().body(elements);
    }

}