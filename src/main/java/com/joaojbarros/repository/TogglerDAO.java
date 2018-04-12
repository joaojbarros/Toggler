package com.joaojbarros.repository;

import java.util.List;

import com.joaojbarros.model.ServiceToggle;

public interface TogglerDAO{

	public List<ServiceToggle> findCustomByServiceIdVersionFieldsExcludeFields(String serviceId, String version, String feature,
			String fields, String excludeFields);

}