package com.example.demo.service.impl;

import com.example.demo.model.TablaRegistro;
import com.example.demo.repository.TablaRegistroRepository;
import com.example.demo.service.ServiceRegistro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.stream.StreamSupport;

@Service
public class ServiceRegistroImpl implements ServiceRegistro {

    @Autowired
    private TablaRegistroRepository tableRegistroRepository;

    @Override
    public Iterable<TablaRegistro> getElements() {
        return tableRegistroRepository.findAll();
        //return StreamSupport.stream(tableRegistroRepository.findAll().spliterator(), false).toList();
    }
}
