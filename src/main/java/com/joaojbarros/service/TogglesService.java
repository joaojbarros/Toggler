package com.joaojbarros.service;

import com.joaojbarros.model.ServiceToggles;

public interface TogglesService {
	
	public void saveToggle(ServiceToggles toggles);
	public ServiceToggles findByServiceIdVersion(String serviceId, String version);
}
