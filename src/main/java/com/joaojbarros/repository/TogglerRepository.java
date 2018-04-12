package com.joaojbarros.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.joaojbarros.model.ServiceToggle;

public interface TogglerRepository extends MongoRepository<ServiceToggle, Long> {

    @Query("{'serviceId':'?0'}")
    ServiceToggle findCustomByServiceId(String serviceId);


    @Query("{'serviceId':'?0','version':'?1'}")
    ServiceToggle findCustomByServiceIdVersion(String serviceId, String version);
    
    @Query("{'serviceId':'?0','version':'?1'}, {?2}")
    ServiceToggle findCustomByServiceIdVersionFields(String serviceId, String version, String fields);

}