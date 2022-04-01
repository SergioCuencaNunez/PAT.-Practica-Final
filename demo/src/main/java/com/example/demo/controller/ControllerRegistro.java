package com.example.demo.controller;

import com.example.demo.model.TablaRegistro;
import com.example.demo.service.ServiceRegistro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/registro")
public class ControllerRegistro {

    @Autowired
    private ServiceRegistro servicioRegistro;

    @GetMapping("/elements")
    public ResponseEntity<List<TablaRegistro>> getElements() {

        var elements = servicioRegistro.getElements();

        return ResponseEntity.ok().body(elements);
    }

}