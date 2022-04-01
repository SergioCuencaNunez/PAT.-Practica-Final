package com.example.demo.service.impl;

import com.example.demo.model.TablaRegistro;
import com.example.demo.repository.TablaRegistroRepository;
import com.example.demo.service.ServiceRegistro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class ServiceRegistroImpl implements ServiceRegistro {

    @Autowired
    private TablaRegistroRepository tableRegistroRepository;

    @Override
    public List<TablaRegistro> getElements() {
        return StreamSupport.stream(TablaRegistroRepository.findAll().spliterator(), false).toList();
    }
}
