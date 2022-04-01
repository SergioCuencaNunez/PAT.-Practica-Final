package com.example.demo.repository;

import com.example.demo.model.TablaHoteles;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface MyTableRepository extends CrudRepository<TablaHoteles, String> { }
