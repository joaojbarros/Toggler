package com.joaojbarros.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.joaojbarros.model.ServiceToggle;

@Repository
@Qualifier("togglesDAO")
public class TogglerDAOImp implements TogglerDAO {
	
	@Autowired
	private MongoTemplate mongoTemplate;

    public ServiceToggle findCustomByServiceId(String serviceId) {
    	return null;
    }

    public ServiceToggle findCustomByServiceIdVersion(String serviceId, String version) {
    	return null;
    }
    
    @SuppressWarnings("static-access")
	public List<ServiceToggle> findCustomByServiceIdVersionFieldsExcludeFields(String serviceId, String version, String feature,
			String fields, String excludeFields) {
    	Criteria criteria = new Criteria();
    	if(serviceId!=null && !"".equals(serviceId)) {
    		criteria.and("serviceId").is(serviceId);
    	}
    	if(version != null && !"".equals(version)) {
    		criteria.and("version").is(version);
    	}
    	if(feature != null && !"".equals(feature)) {
    		criteria.and("serviceFeatures.featureId").is(feature);
    	}
        Query query = new Query(criteria);
        if(fields != null) {
			String[] fieldsString = fields.split(",");
			for (int i = 0; i < fieldsString.length; i++) {
				query.fields().include(fieldsString[i]);
			}
        }
        if(excludeFields != null) {
			String[] excludeFieldString = excludeFields.split(",");
			for (int i = 0; i < excludeFieldString.length; i++) {
				query.fields().exclude(excludeFieldString[i]);
			}
        }
        List<ServiceToggle> result = (List<ServiceToggle>) mongoTemplate.find(query, ServiceToggle.class);
		return result;
    }

}