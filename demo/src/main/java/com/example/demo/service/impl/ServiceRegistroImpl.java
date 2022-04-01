package edu.icai.gittpat.service.impl;

import edu.icai.gittpat.model.MyTable;
import edu.icai.gittpat.repository.MyTableRepository;
import edu.icai.gittpat.service.MyService;
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
