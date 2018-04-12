package com.joaojbarros.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.joaojbarros.model.Toggle;

public interface ToggleRepository extends MongoRepository<Toggle, Long> {

    @Query("{'toggleName':'?0'}")
    Toggle findCustomByToggleName(String toggleName);

    @Query("{'toggleValue':'?0'}")
    List<Toggle> findCustomByToggleValue(Boolean toggleValue);
    
}