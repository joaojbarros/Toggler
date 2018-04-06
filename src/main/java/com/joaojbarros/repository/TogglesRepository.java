package com.joaojbarros.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.joaojbarros.model.Toggles;

public interface TogglesRepository extends MongoRepository<Toggles, Long> {

    @Query("{'serviceId':'?0'}")
    Toggles findCustomByServiceId(String serviceId);


    @Query("{'serviceId':'?0','version':'?1'}")
    Toggles findCustomByServiceIdVersion(String serviceId, String version);

}