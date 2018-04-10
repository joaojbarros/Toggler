package com.joaojbarros.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.joaojbarros.model.ServiceToggles;

public interface TogglesRepository extends MongoRepository<ServiceToggles, Long> {

    @Query("{'serviceId':'?0'}")
    ServiceToggles findCustomByServiceId(String serviceId);


    @Query("{'serviceId':'?0','version':'?1'}")
    ServiceToggles findCustomByServiceIdVersion(String serviceId, String version);

}